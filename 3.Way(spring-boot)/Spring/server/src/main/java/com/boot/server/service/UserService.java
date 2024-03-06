package com.boot.server.service;

import com.boot.server.dto.Users;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;

public interface UserService {

    // 회원 등록
    public int insert(Users user) throws Exception;

    // 회원 조회 (userNo)
    public Users select(int userNo) throws Exception;

    // 회원 조회 (userName)
    public Users selectByName(String name);

    // 로그인
    public void login(Users user, HttpServletRequest request) throws Exception;

    // 회원 수정
    public int update(HashMap<String, String> data) throws Exception;

    // 회원 삭제
    public int delete(String userId) throws Exception;

}
