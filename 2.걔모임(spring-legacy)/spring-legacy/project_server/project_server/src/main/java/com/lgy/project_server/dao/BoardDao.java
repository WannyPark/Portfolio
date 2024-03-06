package com.lgy.project_server.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.lgy.project_server.dto.BoardDto;

public interface BoardDao {

	public ArrayList<BoardDto> getBoardListPaging(@Param("start") int start, @Param("end") int end);
	
	public ArrayList<BoardDto> getBoardWriter(
			@Param("start") int page
			, @Param("end") int pageSize
			,@Param("searchVal") String searchVal );
	
	public ArrayList<BoardDto> getBoardTitle(
			@Param("start") int page
			, @Param("end") int pageSize
			,@Param("searchVal") String searchVal );
	
	public ArrayList<BoardDto> getCateMenu1(
			@Param("start") int page
			, @Param("end") int pageSize
			,@Param("catemenu") String catemenu );
	
	public ArrayList<BoardDto> getCateMenu2(
			@Param("start") int page
			, @Param("end") int pageSize
			,@Param("catemenu") String catemenu );
	
	public ArrayList<BoardDto> getCateMenu3(
			@Param("start") int page
			, @Param("end") int pageSize
			,@Param("catemenu") String catemenu );
	
	public ArrayList<BoardDto> getCSearch(
			@Param("start") int page
			,@Param("end") int pageSize
			,@Param("catemenu2") String catemenu2 
			,@Param("cateSelec") String cateSelec
			,@Param("cateVal") String cateVal);
	
	public ArrayList<BoardDto> getHit();
	
	public ArrayList<BoardDto> contentView(@Param("id") String id);
	
	public int increView(@Param("id") String id);
	
	public int uploadBoard(@Param("mem_id") String mem_id, @Param("mem_Nname") String mem_Nname, @Param("title") String title, @Param("desc") String desc
			, @Param("category") String category, @Param("file1") String file1, @Param("file2") String file2, @Param("fileExt") String fileExt, @Param("fileSize") long fileSize);
	
	public int uploadBoardNoFile(@Param("mem_id") String mem_id, @Param("mem_Nname") String mem_Nname, @Param("title") String title, @Param("desc") String desc
			, @Param("category") String category);
	
	public int modifyBoard(HashMap<String, String> param);
	
	public int deleteBoard(HashMap<String, String> param);
	
}
