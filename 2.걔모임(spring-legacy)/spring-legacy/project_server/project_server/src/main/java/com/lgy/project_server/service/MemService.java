package com.lgy.project_server.service;

import com.lgy.project_server.dto.MemDto;

public interface MemService {

	// DB 에서 회원 정보 가져오는 메소드
	public MemDto getMem(String id);
	
	// DB 에 새로운 회원을 추가하는 메소드
	public int addMem(MemDto mem);
	
	// DB 에서 기존 비밀번호를 확인하고 일치시 비밀번호 변경하는 메소드
	public int modiPw(String id, String oldPw, String newPw1, String newPw2);
	
	// DB 에서 닉네임 변경하는 메소드
	public int modiNname(String id, String newNname);
	
	// DB 에서 닉네임 변경하는 메소드
	public int modiNum(String id, String newNum);
	
	// DB 에서 주소 변경하는 메소드
	public int modiAddr(String id, String newAddr1, String newAddr2);
	
	// DB 에서 이메일 변경하는 메소드
	public int newEmail(String id, String newEmail);
	
	// DB 에서 아이디, 이름, 이메일 인증 (비밀번호 찾기)
	public int getmail(String id, String name, String email);
	
	// 이메일 인증 후 새로운 비밀번호로 교체
	public int modiNewPw(String id, String newPw);
	
}