package com.lgy.project_server.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lgy.project_server.dto.DogDto;
import com.lgy.project_server.dto.MemDto;
import com.lgy.project_server.service.DogService;
import com.lgy.project_server.service.MemService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DogController {
	
	@Autowired
	private MemService memService;
	
	@Autowired
	private DogService dogService;

	@ResponseBody
	@RequestMapping(value="/api/dog/register")
	public int register(@RequestBody HashMap<String, String> map, HttpServletRequest req) {
		int res = 0;
		MemDto mem = null;
		String id = null;
		HttpSession session = req.getSession();
//		String name = map.get("name");
//		String age = map.get("age");
//		String kind = map.get("kind");
//		String birthday = map.get("birthday");
//		String gender = map.get("gender");
		
		try {
			id = session.getAttribute("id").toString();
			mem = memService.getMem(id);
			if (mem != null) {
				map.put("mem_id", mem.getMem_id());
				map.put("mem_nname", mem.getMem_nname());
				res = dogService.register(map);
			}
		} catch (Exception e) {
			System.out.println("에러발생 / dog/register");
		}
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping("/api/dog/upload")
	public int upload(@RequestParam("uploadImg") MultipartFile[] multipartFiles, @RequestParam("dog_name") String dogName, HttpServletRequest req) throws IllegalStateException, IOException {
		int res = 0;
		String UPLOAD_PATH = "D:\\jiwhy\\dev\\project-react\\project\\public\\images\\dogs";
		String id = null;
		HttpSession session = req.getSession();
		
		System.out.println(multipartFiles);
		try {
			for(int i=0; i<multipartFiles.length; i++) {
				MultipartFile file = multipartFiles[i];
	            
				String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
				String originName = file.getOriginalFilename(); // ex) 파일.jpg
				String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
				originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
				long fileSize = file.getSize(); // 파일 사이즈
				
				File fileSave = new File(UPLOAD_PATH, fileId + "." + fileExtension); // ex) fileId.jpg
				if(!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
					fileSave.mkdirs();
				}
	            
				file.transferTo(fileSave); // fileSave의 형태로 파일 저장
				
				System.out.println("fileId= " + fileId);
				System.out.println("originName= " + originName);
				System.out.println("fileExtension= " + fileExtension);
				System.out.println("fileSize= " + fileSize);
				
				// DB 에 이미지파일 이름, 용량 저장
				id = session.getAttribute("id").toString();
				System.out.println("id= " + id);
				System.out.println("dogName= " + dogName);
				res = dogService.register_file(id, dogName, fileId, originName, fileExtension, fileSize);
			}
		} catch(IOException e) {
			return 0;
		}
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/dog/getDogs")
	public ResponseEntity<ArrayList<DogDto>> getDogs(HttpServletRequest req) {
		String id = null;
		HttpSession session = req.getSession();
		ArrayList<DogDto> list = null;
		
		try {
			id = session.getAttribute("id").toString();
			list = dogService.getDogs(id);
		} catch (Exception e) {
			System.out.println("에러발생 / dog/register");
		}
		
		return new ResponseEntity<ArrayList<DogDto>>(list, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/dog/remove")
	public int removeDog(@RequestBody HashMap<String, String> map, HttpServletRequest req) {
		int res = 0;
		String dogName = map.get("name");
		String id = null;
		HttpSession session = req.getSession();
		
		try {
			id = session.getAttribute("id").toString();
			res = dogService.removeDog(id, dogName);
		} catch (Exception e) {
			System.out.println("에러발생 / dog/register");
		}
		
		return res;
	}
	
}