package com.boot.server.security.jwt.filter;

import com.boot.server.config.SecurityConfig;
import com.boot.server.dto.CustomUser;
import com.boot.server.dto.Users;
import com.boot.server.mapper.UserMapper;
import com.boot.server.security.jwt.constants.JwtConstants;
import com.boot.server.security.jwt.provider.JwtTokenProvider;
import io.jsonwebtoken.security.Password;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

//           (/login)
// client -> filter -> server
// username, password 인증 시도 (attemptAuthentication)
// 인증 실패 (attemptAuthentication / response => status : 401 (UNAUTHORIZED))
// 인증 성공 (successfulAuthentication)
// -> JWT 생성
// -> response => headers => authorization : (JWT)
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    // 생성자
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        // 필터 URL 경로 설정 : /login
        setFilterProcessesUrl(JwtConstants.AUTH_LOGIN_URL); // "/login"
    }

    // 인증 시도 메소드
    // : /login 경로로 요청하면, 필터로 걸러서 인증을 시도
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        log.info("username: " + username);
        log.info("password: " + password);

        // 사용자 인증 정보 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        log.info(authentication.toString());
        try {
            // 사용자 인증 (로그인)
            authentication = authenticationManager.authenticate(authentication);
        } catch (Exception e) {
            log.info("사용자 인증 error 발생 ===> ");
            e.printStackTrace();
        }

        log.info("인증 여부: " + authentication.isAuthenticated());

        // 인증 실패 (username, password 불일치)
        if (!authentication.isAuthenticated()) {
            log.info("인증 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
            response.setStatus(401); // 401 UNAUTHORIZED (인증 실패)
            return null;
        }

        return authentication;
    }

    // 인증 성공 메소드
    // JWT 을 생성
    // JWT 를 응답 헤더에 설정
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        log.info("인증 성공 ...");

//        Users user = userMapper.login((String)authentication.getPrincipal());
        CustomUser user = (CustomUser)authentication.getPrincipal();
        int userNo = user.getUser().getNo();
        String userId = user.getUser().getUserId();

        List<String> roles = user.getUser().getAuthList().stream().map((auth) -> auth.getAuth()).collect(Collectors.toList());

        // JWT 토큰 생성 요청
        String jwt = jwtTokenProvider.createToken(userNo, userId, roles);

        // { Authorization : Bearer + {jwt} }
        response.addHeader(JwtConstants.TOKEN_HEADER, JwtConstants.TOKEN_PREFIX + jwt);
        response.setStatus(200);
    }

}
