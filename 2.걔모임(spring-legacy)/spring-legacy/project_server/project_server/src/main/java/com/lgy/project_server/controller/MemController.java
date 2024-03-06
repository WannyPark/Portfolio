package com.lgy.project_server.controller;

import java.util.HashMap;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgy.project_server.dto.MemDto;
import com.lgy.project_server.service.MemService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemController {
	
//	메일 전송 주입
	@Autowired
    private JavaMailSender mailSender;

	@Autowired
	private MemService service;
	
	// 회원 정보창 데이터 요청 API
	@ResponseBody
	@RequestMapping(value="/api/view_mem")
	public  ResponseEntity<MemDto> getMem(HttpServletRequest req){
		log.info("@# getMem start");
		HttpSession session = req.getSession();
		MemDto mem = null;
		String id = null;
		try {
			id = session.getAttribute("id").toString();
		} catch (Exception e) {
			System.out.println("회원정보 불러오기 실패 / getMem");
		} finally {
			mem = service.getMem(id);
			
			System.out.println(mem);	
		}
		
		log.info("@# getMem end");
		return new ResponseEntity<MemDto>(mem,HttpStatus.OK);	
	}
	
	// 회원가입 요청 API
	@ResponseBody
	@RequestMapping(value="/api/signUp")
	public int addMem(@RequestBody MemDto mem) {
		int res = 0;
		res = service.addMem(mem);
		
		return res;
	}
	
	// 비밀번호 변경 요청 API
	@ResponseBody
	@RequestMapping(value="/api/modiPw")
	public int modiPw(@RequestBody HashMap<String, String> map, HttpServletRequest req) {
		int res = 0;
		String id = null;
		HttpSession session = req.getSession();
		String oldPw = map.get("oldPw");
		String newPw1 = map.get("newPw1");
		String newPw2 = map.get("newPw2");
		
		try {
			id = session.getAttribute("id").toString();
		} catch (Exception e) {
			System.out.println("회원정보 불러오기 실패 / modiPw");
		} finally {
			res = service.modiPw(id, oldPw, newPw1, newPw2);
		}
		
		return res;
	}
	
	// 닉네임 변경 요청 API
	@ResponseBody
	@RequestMapping(value="/api/modiNn")
	public int modiNname(@RequestBody HashMap<String, String> map, HttpServletRequest req) {
		int res = 0;
		String id = null;
		HttpSession session = req.getSession();
		String newNname = map.get("newNname");
		
		try {
			id = session.getAttribute("id").toString();
		} catch (Exception e) {
			System.out.println("회원정보 불러오기 실패 / modiNname");
		} finally {
			res = service.modiNname(id, newNname);
		}
		
		return res;
	}
	
	// 전화번호 변경 요청 API
	@ResponseBody
	@RequestMapping(value="/api/modiNum")
	public int modiNum(@RequestBody HashMap<String, String> map, HttpServletRequest req) {
		int res = 0;
		String id = null;
		HttpSession session = req.getSession();
		String newNum = map.get("newNum");
		
		try {
			id = session.getAttribute("id").toString();
		} catch (Exception e) {
			System.out.println("회원정보 불러오기 실패 / modiNname");
		} finally {
			res = service.modiNum(id, newNum);
		}
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/api/modiAddr")
	public int modiAddr(@RequestBody HashMap<String, String> map, HttpServletRequest req) {
		int res = 0;
		String id = null;
		HttpSession session = req.getSession();
		String newAddr1 = map.get("newAddr1");
		String newAddr2 = map.get("newAddr2");
		
		try {
			id = session.getAttribute("id").toString();
		} catch (Exception e) {
			System.out.println("회원정보 불러오기 실패 / modiNname");
		} finally {
			res = service.modiAddr(id, newAddr1, newAddr2);
		}
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/api/modiEamil")
	public int modiEamil(@RequestBody HashMap<String, String> map, HttpServletRequest req) {
		int res = 0;
		String id = null;
		HttpSession session = req.getSession();
		String newEmail = map.get("newEmail");
		
		try {
			id = session.getAttribute("id").toString();
		} catch (Exception e) {
			System.out.println("회원정보 불러오기 실패 / modiNname");
		} finally {
			res = service.newEmail(id, newEmail);
		}
		
		return res;
	}
	
	// 비밀번호 변경 인증 메일 전송 API
	@ResponseBody
    @RequestMapping(value = "/api/email")
	public ResponseEntity<HashMap<String, String>> sendEmail(HttpServletRequest request) {
    	int res = 0;
    	HashMap<String, String> map = new HashMap<String, String>();
    	String id = request.getParameter("id");
    	String name = request.getParameter("name");
    	String email = request.getParameter("email");
    	res = service.getmail(id, name, email);
    	System.out.println("@#@#@!"+res);
    	
    	if (res == 0) {
    		map.put("res", res+"");
        	return new ResponseEntity<HashMap<String,String>>(map,HttpStatus.OK);
    	}
    	
        // 랜덤한 숫자문자를 합친 문자열을 전달받은 map의 email로 보낸다.
        Random rand = new Random();
        String strRand = "";
        for (int i = 0; i < 4; i++) {
            strRand += rand.nextInt(10);
        }

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            messageHelper.setSubject("비밀번호 변경 이메일 인증");
            String message = new StringBuffer()
                    .append("<h1>[이메일 인증]발신전용이므로 회신 불가</h1>")
                    .append("<p>인증번호: ")
                    .append(strRand)
                    .append("</p>")
                    .toString();
            messageHelper.setText(message, true);
            messageHelper.setFrom("vgb2145345@gmail.com", "관리자");
            messageHelper.setTo(email);

            mailSender.send(mimeMessage);
            map.put("code", strRand);
            map.put("res", res+"");            
            return new ResponseEntity<HashMap<String,String>>(map,HttpStatus.OK);
        } catch (Exception e) {
        	map.put("res", "-1");
        	return new ResponseEntity<HashMap<String,String>>(map,HttpStatus.OK);
        }
    }
	
	// 이메일 인증 후 비밀번호 변경
	@ResponseBody
	@RequestMapping(value="/api/login/changePw")
	public int findPwAndUpdate(@RequestBody HashMap<String, String> map) {
		int res = 0;
		
		String id = map.get("id");
		String newPw = map.get("newPw");
		
		res = service.modiNewPw(id, newPw);
		
		return res;
	}
	
}