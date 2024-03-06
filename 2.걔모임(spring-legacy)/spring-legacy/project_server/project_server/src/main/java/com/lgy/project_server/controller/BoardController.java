package com.lgy.project_server.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lgy.project_server.dto.BoardDto;
import com.lgy.project_server.dto.MemDto;
import com.lgy.project_server.service.BoardService;
import com.lgy.project_server.service.MemService;

@Controller
public class BoardController {

	@Autowired
	private MemService memService;
	
	@Autowired
	private BoardService boardService;
	
	// 전체 게시판 return API
	@ResponseBody
    @RequestMapping(value = "/api/boardCheck")
    public ResponseEntity<ArrayList<BoardDto>> boardCheck(
            @RequestParam(defaultValue = "1") int page, 
            @RequestParam(defaultValue = "10") int pageSize) {
        ArrayList<BoardDto> list = boardService.getBoardListPaging(page, pageSize);
        
        System.out.println("list ===>" + list);
        return new ResponseEntity<ArrayList<BoardDto>>(list, HttpStatus.OK);
    }
	
	// 전체보기 검색 결과 return API
	@ResponseBody
	@RequestMapping(value = "/api/boardSearch")
	public ResponseEntity<ArrayList<BoardDto>> boardSearch(
			@RequestParam(defaultValue = "1") int page, 
			@RequestParam(defaultValue = "30") int pageSize,
			@RequestParam String selec,
			@RequestParam String searchVal) {
		System.out.println("page ===> " + page);
		System.out.println("pageSize ===> " + pageSize);
		System.out.println("selec ===> " + selec);
		System.out.println("searchVal ===> " + searchVal);
		if (selec.equals("boardWriter")) {
			ArrayList<BoardDto> search = boardService.getBoardWriter(page, pageSize, searchVal);
			System.out.println("search ===>" + search);
			return new ResponseEntity<ArrayList<BoardDto>>(search, HttpStatus.OK);
		} else {
			ArrayList<BoardDto> search = boardService.getBoardTitle(page, pageSize, searchVal);
			System.out.println("search ===>" + search);
			return new ResponseEntity<ArrayList<BoardDto>>(search, HttpStatus.OK);	
		}
	}
	
	// 카테고리별 게시물 return API
	@ResponseBody
	@RequestMapping(value = "/api/boardCate")
	public ResponseEntity<ArrayList<BoardDto>> boardCate(	
			@RequestParam(defaultValue = "1") int page, 
			@RequestParam(defaultValue = "30") int pageSize,
			@RequestParam String catemenu) {
		if (catemenu == "반려견자랑") {
			ArrayList<BoardDto> cate = boardService.getCateMenu1(page, pageSize, catemenu);	
			return new ResponseEntity<ArrayList<BoardDto>>(cate, HttpStatus.OK);
		} else if (catemenu == "동물병원추천"){
			ArrayList<BoardDto> cate = boardService.getCateMenu2(page, pageSize, catemenu);
			return new ResponseEntity<ArrayList<BoardDto>>(cate, HttpStatus.OK);
		}else {
			ArrayList<BoardDto> cate = boardService.getCateMenu3(page, pageSize, catemenu);					
			return new ResponseEntity<ArrayList<BoardDto>>(cate, HttpStatus.OK);	
		}
	}
	
	// 카테고리별 검색 게시물 return API
	@ResponseBody
	@RequestMapping(value = "/api/cateSearch")
	public ResponseEntity<ArrayList<BoardDto>> cateSearch(
			@RequestParam(value = "page",defaultValue = "1") int page, 
			@RequestParam(value = "pageSize",defaultValue = "30") int pageSize,
		    @RequestParam(value = "catemenu2") String catemenu2,
		    @RequestParam(value = "cateSelec") String cateSelec,
		    @RequestParam(value = "cateVal") String cateVal) {
		if (cateSelec.equals("boardWriter")) cateSelec = "MEMBER_MEM_NNAME";
		else cateSelec = "BOARD_TITLE";
		ArrayList<BoardDto>search  = boardService.getCSearch(page, pageSize, catemenu2,cateSelec,cateVal);	
		return new ResponseEntity<ArrayList<BoardDto>>(search, HttpStatus.OK);
	}
	
	// view 가 가장 높은 5개 게시물 return API
	@ResponseBody
	@RequestMapping(value="/api/getHit")
	public ResponseEntity<ArrayList<BoardDto>> getHit(Model model){
		ArrayList<BoardDto> getHit = boardService.getHit();
		return new ResponseEntity<ArrayList<BoardDto>>(getHit, HttpStatus.OK);
	}
	
	// 게시물 VIEW return API
	@ResponseBody
	@RequestMapping(value="/api/boardView")
	public ResponseEntity<List<BoardDto>> saveData(@RequestBody HashMap<String, String> map) {
		String id = map.get("id");
		ArrayList<BoardDto> res = boardService.contentView(id);
		
		return new ResponseEntity<List<BoardDto>>(res, HttpStatus.OK);
	}
	
	// 게시물 업로드
	@ResponseBody
	@RequestMapping(value="/api/boardWrite")
	public int uploadBoard(@RequestParam("uploadImg") MultipartFile[] multipartFiles, @RequestParam("mem_id") String mem_id, @RequestParam("title") String title
			, @RequestParam("desc") String desc, @RequestParam("category") String category, HttpServletRequest req) {
		int res = 0;
		MemDto mem = null;
		String UPLOAD_PATH = "D:\\jiwhy\\dev\\project-react\\project\\public\\images\\board";
		
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
				
				res = boardService.uploadBoard(mem_id, mem.getMem_nname(), title, desc, category, fileId, originName, fileExtension, fileSize);
			}
		} catch(IOException e) {
			return 0;
		}
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/api/boardWriteNoFile")
	public int uploadBoardNoFile(@RequestParam("mem_id") String mem_id, @RequestParam("title") String title
			, @RequestParam("desc") String desc, @RequestParam("category") String category, HttpServletRequest req) {
		int res = 0;
		MemDto mem = null;
		
		try {
			mem = memService.getMem(mem_id);
			res = boardService.uploadBoardNoFile(mem_id, mem.getMem_nname(), title, desc, category);
		} catch (Exception e) {
			return 0;
		}
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/api/updateBoard", method = RequestMethod.POST)
	public int modify(@RequestBody HashMap<String, String> param) {
		int res = 0;
		res = boardService.modifyBoard(param);
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/api/deleteBoard", method = RequestMethod.DELETE)
	public int deleteBoard(@RequestBody HashMap<String, String> param) {
		int res = 0;
		
		res = boardService.deleteBoard(param);
		
		return res;
	}
	
}
