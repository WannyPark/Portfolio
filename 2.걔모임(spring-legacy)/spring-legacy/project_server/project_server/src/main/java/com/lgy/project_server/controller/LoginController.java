package com.lgy.project_server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgy.project_server.dto.LoginDto;
import com.lgy.project_server.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@ResponseBody
	@RequestMapping(value="/api/login")
	public int login_check(@RequestBody LoginDto dto, HttpServletRequest req) {
		log.info("@# Con.login_check() start");
		HttpSession session = null;
		int res = 0;
		
		res = loginService.login_check(dto.getId(), dto.getPw());
		if (res == 1) {
			session = req.getSession();
			session.setAttribute("id", dto.getId());
		}
		
		log.info("@# Con.login_check() end");
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/api/getSession")
	public String getSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String id = null;
		try {
			id = session.getAttribute("id").toString();			
		} catch (Exception e) {
			System.out.println("세션 없음");
		} finally {
			return id;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/api/logout")
	public int logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String id = null;
		session.removeAttribute("id");
		
		try {
			id = session.getAttribute("id").toString();			
		} catch (Exception e) {
			System.out.println("로그아웃 성공");
		} finally {
			if (id == null) {
				return 1;
			} else {
				return 0;
			}			
		}
	}
	
}
