package com.lgy.project_server.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.lgy.project_server.dto.BoardDto;

public interface BoardService {
	
	public ArrayList<BoardDto> getBoardListPaging(int page, int pageSize);
	
	public ArrayList<BoardDto> getBoardWriter(int page, int pageSize,String searchVal);
	
	public ArrayList<BoardDto> getBoardTitle(int page, int pageSize,String searchVal); 
	
	public ArrayList<BoardDto> getCateMenu1(int page, int pageSize,String catemenu); 
	
    public ArrayList<BoardDto> getCateMenu2(int page, int pageSize,String catemenu);
    
    public ArrayList<BoardDto> getCateMenu3(int page, int pageSize,String catemenu); 
    
    public ArrayList<BoardDto> getCSearch(int page, int pageSize,String catemenu2,String cateSelec,String cateVal); 
    
    public ArrayList<BoardDto> getHit();
    
    public ArrayList<BoardDto> contentView(String id);
    
    public int uploadBoard(String mem_id, String mem_Nname, String title, String desc, String category, String file1, String file2, String fileExt, long fileSize);
    
    public int uploadBoardNoFile(String mem_id, String mem_Nname, String title, String desc, String category);
    
    public int modifyBoard(HashMap<String, String> param);

    public int deleteBoard(HashMap<String, String> param);
	
}

//System.out.println("multipartFiles= " + multipartFiles);
//System.out.println("mem_id= " + mem_id);
//System.out.println("title= " + title);
//System.out.println("desc= " + desc);
//System.out.println("category= " + category);
//System.out.println("fileId= " + fileId);
//System.out.println("originName= " + originName);
//System.out.println("fileExtension= " + fileExtension);
//System.out.println("fileSize= " + fileSize);