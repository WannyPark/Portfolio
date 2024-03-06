package com.boot.server.security.custom;

import com.boot.server.dto.CustomUser;
import com.boot.server.dto.Users;
import com.boot.server.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("login - loadUserByUsername: " + username);

        Users user = userMapper.login(username);
        log.info("@#@# user password ===> " + user.getUserPw());

        if (user == null) {
            log.info("사용자 없음 ... (일치하는 아이디가 없음)");
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + username);
        }

        // UserDetails : spring boot security 에서 유저 정보 저장하는 객체
        log.info("user: ");
        log.info(user.toString());

        // Users -> CustomUser
        CustomUser customUser = new CustomUser(user);

        log.info("customUser: ");
        log.info(customUser.toString());

        return customUser;
    }

}
