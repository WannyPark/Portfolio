package com.lgy.project_server.dao;

import org.apache.ibatis.annotations.Param;

import com.lgy.project_server.dto.MemDto;

public interface MemDao {

	public MemDto getMem(@Param("id") String id);
	
	public int addMem(MemDto mem);
	
	public int modiPw(@Param("id") String id,@Param("newPw") String newPw);
	
	public int modiNname(@Param("id") String id, @Param("newNname") String newNname);
	
	public int modiNum(@Param("id") String id, @Param("newNum") String newNum);
	
	public int modiAddr(@Param("id") String id, @Param("newAddr1") String newAddr1, @Param("newAddr2") String newAddr2);
	
	public int newEmail(@Param("id") String id, @Param("newEmail") String newEmail);
	
	public MemDto getmail(@Param("id") String id);
	
}
