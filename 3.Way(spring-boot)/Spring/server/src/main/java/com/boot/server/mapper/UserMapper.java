package com.boot.server.mapper;

import com.boot.server.dto.UserAuth;
import com.boot.server.dto.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface UserMapper {

    // 회원 등록
    public int insert(Users user) throws Exception;

    // 회원 조회 (userNo)
    public Users select(int userNo) throws Exception;

    // 회원 조회 (userName)
    public Users selectByName(String name);

    // 사용자 인증(로그인) - id
    public Users login(String username);

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

    // 회원 수정
    public int update(HashMap<String, String> user) throws Exception;

    // 회원 삭제
    public int delete(String userId) throws Exception;

    // 회원 권한 삭제
    public int deleteAuth(String userId) throws Exception;

}
