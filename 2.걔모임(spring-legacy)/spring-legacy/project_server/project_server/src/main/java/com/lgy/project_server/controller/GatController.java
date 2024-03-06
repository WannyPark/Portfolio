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

import com.lgy.project_server.dto.GatChatDto;
import com.lgy.project_server.dto.GatDto;
import com.lgy.project_server.dto.GatMemDto;
import com.lgy.project_server.dto.GatPlanDto;
import com.lgy.project_server.dto.MemDto;
import com.lgy.project_server.service.GatService;
import com.lgy.project_server.service.MemService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GatController {

	@Autowired
	private GatService gatService;
	
	@Autowired
	private MemService memService;
	
	// 홈 화면 검색 모임 반환 API
	@ResponseBody
	@RequestMapping(value="/api/getGat")
	public ResponseEntity<ArrayList<GatDto>> getGat(HttpServletRequest request, @RequestBody HashMap<String, String> param) {
		ArrayList<GatDto> list = null;
		
		try {
			String search = param.get("search");
			list = gatService.getGat(search);			
		} catch (Exception e) {
			return null;
		}
		
		return new ResponseEntity<ArrayList<GatDto>>(list, HttpStatus.OK);
	}
	
	// 모임 홈 화면 첫 로딩시 전체 모임 중 VIEW 순위로 상위 6개 반환 API
	@ResponseBody
	@RequestMapping(value="/api/gat/searchAll")
	public ResponseEntity<ArrayList<GatDto>> searchAll(HttpServletRequest request) {
		ArrayList<GatDto> list = null;
		
		try {
			list = gatService.searchAll();
		} catch (Exception e) {
			System.out.println("Error / Controller / gat/searchAll");
		}
				
		return new ResponseEntity<ArrayList<GatDto>>(list, HttpStatus.OK);
	}
	
	// 모임 홈 화면 전체 모임 수 반환 API
	@ResponseBody
	@RequestMapping(value="/api/gat/getTotalPage")
	public int getTotalPage(HttpServletRequest request) {
		int res = 0;
		
		try {
			res = gatService.getTotalPage();
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	// 해당 ID 회원의 모임 반환 API
	@ResponseBody
	@RequestMapping(value="/api/gat/getMemGat")
	public ResponseEntity<ArrayList<GatDto>> getMemGat(HttpServletRequest request) {
		ArrayList<GatDto> list = null;
		String id = null;
		HttpSession session = request.getSession();
		
		try {
			id = session.getAttribute("id").toString();
			list = gatService.getMemGat(id);
		} catch (Exception e) {
			System.out.println("Error / GatController/getMemGat");
		}
		
		return new ResponseEntity<ArrayList<GatDto>>(list, HttpStatus.OK);
	}
	
	// 지역별 모임 반환 API
	@ResponseBody
	@RequestMapping(value="api/gat/locationGats")
	public ResponseEntity<ArrayList<GatDto>> locationGats(HttpServletRequest request, @RequestBody HashMap<String, String> map) {
		ArrayList<GatDto> list = null;
		String location1 = map.get("location1");
		String location2 = map.get("location2");
		
		try {
			list = gatService.locationGats(location1, location2);
		} catch (Exception e) {
			System.out.println("Error / GatController / locationGats");
		}
		
		return new ResponseEntity<ArrayList<GatDto>>(list, HttpStatus.OK);
	}
	
	// 모임 생성(파일 o) API
	@ResponseBody
	@RequestMapping(value = "/api/makeGat")
	public int makeGat(@RequestParam("uploadImg") MultipartFile[] multipartFiles, @RequestParam("mem_id") String mem_id
			, @RequestParam("title") String title, @RequestParam("desc") String desc, @RequestParam("location1") String location1
			, @RequestParam("location2") String location2, HttpServletRequest request) {
		int res = 0;
		MemDto mem = null;
		String UPLOAD_PATH = "D:\\jiwhy\\dev\\project-react\\project\\public\\images\\gathering";
		
		try {
			mem = memService.getMem(mem_id);
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
				
				res = gatService.makeGat(mem_id, mem.getMem_nname(), title, desc, location1, location2, fileId, originName, fileExtension, fileSize);
			}
		} catch(IOException e) {
			return 0;
		}
		
		return res; 
		
	}
	
	// 모임 생성(파일 x) API
	@ResponseBody
	@RequestMapping(value = "/api/makeGatNoFile")
	public int makeGatNoFile(@RequestParam("mem_id") String mem_id, @RequestParam("title") String title
			, @RequestParam("desc") String desc, @RequestParam("location1") String location1
			, @RequestParam("location2") String location2, HttpServletRequest request) {
		int res = 0;
		MemDto mem = null;
		
		try {
			mem = memService.getMem(mem_id);
			res = gatService.makeGatNoFile(mem_id, mem.getMem_nname(), title, desc, location1, location2);
		} catch (Exception e) {
			return 0;
		}
		
		return res;
	}
	
	// 모임 내용 반환 API
	@ResponseBody
	@RequestMapping(value = "/api/gatInfo")
	public ResponseEntity<GatDto> getInfo(@RequestBody HashMap<String, String> map, HttpServletRequest request) {
		GatDto dto = null;
		
		try {
			dto = gatService.gatInfo(map.get("id").toString());
			System.out.println("gatInfo ===> " + dto);
		} catch (Exception e) {
			return null;
		}
		
		return new ResponseEntity<GatDto>(dto, HttpStatus.OK);
	}
	
	// 모임 회원 반환 API
	@ResponseBody
	@RequestMapping(value = "/api/gatMems")
	public ResponseEntity<ArrayList<GatMemDto>> gatMems(@RequestBody HashMap<String, String> map, HttpServletRequest request) {
		ArrayList<GatMemDto> list = null;
		
		try {
			list = gatService.gatMems(map.get("id").toString());
			System.out.println("gatMems ===> " + list);
		} catch (Exception e) {
			return null;
		}
		
		return new ResponseEntity<ArrayList<GatMemDto>>(list, HttpStatus.OK);
	}
	
	// 모임 계획 반환 API
	@ResponseBody
	@RequestMapping(value = "/api/gatPlans")
	public ResponseEntity<ArrayList<GatPlanDto>> gatPlans(@RequestBody HashMap<String, String> map, HttpServletRequest request) {
		ArrayList<GatPlanDto> list = null;
		
		try {
			list = gatService.gatPlans(map.get("id").toString());
			System.out.println("gatPlans ===> " + list);
		} catch (Exception e) {
			return null;
		}
		
		return new ResponseEntity<ArrayList<GatPlanDto>>(list, HttpStatus.OK);
	}
	
	// 모임 채팅 반환 API
	@ResponseBody
	@RequestMapping(value = "/api/gatChats")
	public ResponseEntity<ArrayList<GatChatDto>> gatChat(@RequestBody HashMap<String, String> map, HttpServletRequest request) {
		ArrayList<GatChatDto> list = null;
		
		try {
			list = gatService.gatChat(map.get("id").toString());
			System.out.println("gatChat ===> " + list);
		} catch (Exception e) {
			return null;
		}
		
		return new ResponseEntity<ArrayList<GatChatDto>>(list, HttpStatus.OK);
	}
	
	// 모임 참여 API
	@ResponseBody
	@RequestMapping(value = "/api/joinGat")
	public int joinGat(@RequestBody HashMap<String, String> map, HttpServletRequest request) {
		int res = 0;
		MemDto mem = null;
		GatDto gat = null;
		String gat_id = map.get("gat_id");
		String mem_id = map.get("mem_id");
		
		try {
			mem = memService.getMem(mem_id);
			gat = gatService.gatInfo(gat_id);
			res = gatService.joinGat(gat_id, gat.getGat_title(), mem_id, mem.getMem_nname());
		} catch (Exception e) {
			return 0;
		}
		
		return res;
	}
	
	// 댓글 추가 API
	@ResponseBody
	@RequestMapping(value = "/api/addChat")
	public int addChat(@RequestBody HashMap<String, String> map, HttpServletRequest request) {
		int res = 0;
		MemDto mem = null;
		String gat_id = map.get("gat_id");
		String mem_id = map.get("mem_id");
		String comments = map.get("comments");
		
		try {
			mem = memService.getMem(mem_id);
			res = gatService.addChat(gat_id, mem_id, mem.getMem_nname(), comments);
		} catch (Exception e) {
			return 0;
		}
		
		return res;
	}
	
	// 모임 수정 API
	@ResponseBody
	@RequestMapping(value = "/api/updateGat")
	public int updateGat(@RequestBody HashMap<String, String> param) {
		int res = 0;
		
		try {
			res = gatService.modifyGat(param);
		} catch (Exception e) {
			return 0;
		}
		
		return res;
	}
	
	// 모임 삭제 API
	@ResponseBody
	@RequestMapping(value = "/api/deleteGat")
	public int deleteGat(@RequestBody HashMap<String, String> param) {
		int res = 0;
		
		try {
			res = gatService.deleteGat(param);
		} catch (Exception e) {
			return 0;
		}
		
		return res;
	}
	
	// 계획 등록 API
	@ResponseBody
	@RequestMapping(value = "/api/addPlan")
	public int addPlan(@RequestBody HashMap<String, String> param, HttpServletRequest request) {
		int res = 0;
		MemDto mem = null;
		String id = null;
		HttpSession session = request.getSession();
		
		try {
			id = session.getAttribute("id").toString();
			mem = memService.getMem(id);
			param.put("mem_nName", mem.getMem_nname());
			System.out.println(param.get("mem_nName"));
			res = gatService.addPlan(param);
		} catch (Exception e) {
			return 0;
		}
		
		return res;
	}
	
	// 계획 뷰 API
	@ResponseBody
	@RequestMapping(value = "/api/getPlan")
	public ResponseEntity<GatPlanDto> getPlan(@RequestBody HashMap<String, String> param, HttpServletRequest request) {
		GatPlanDto plan = null;
		
		try {
			plan = gatService.getPlan(param);
		} catch (Exception e) {
			return null;
		}
		
		return new ResponseEntity<GatPlanDto>(plan, HttpStatus.OK);
	}
	
	// 계획 자세히 보기 주인인지 판단 API
	@ResponseBody
	@RequestMapping(value = "api/plan/isMine")
	public int isMine(HttpServletRequest request, @RequestBody HashMap<String, String> param) {
		int res = 0;
		MemDto mem = null;
		String id = null;
		HttpSession session = request.getSession();
		
		try {
			id = session.getAttribute("id").toString();
			mem = memService.getMem(id);
			if (mem.getMem_nname().equals(param.get("mem_nName"))) res = 1;
		} catch (Exception e) {
			return 0;
		}
		
		return res;
	}
	
	// 계획 삭제 API
	@ResponseBody
	@RequestMapping(value = "/api/delPlan")
	public int delPlan(HttpServletRequest request, @RequestBody HashMap<String, String> param) {
		int res = 0;
		
		try {
			res = gatService.delPlan(param);
		} catch (Exception e) {
			return 0;
		}
		
		return res;
	}
	
}
