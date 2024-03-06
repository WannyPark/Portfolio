package com.lgy.project_server.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.lgy.project_server.dto.GatChatDto;
import com.lgy.project_server.dto.GatDto;
import com.lgy.project_server.dto.GatMemDto;
import com.lgy.project_server.dto.GatPlanDto;

public interface GatDao {

	public ArrayList<GatDto> getGat(@Param("search") String search);
	
	public ArrayList<GatDto> searchAll();
	
	public int getTotalPage();
	
	public ArrayList<GatDto> getMemGat(@Param("id") String id);
	
	public ArrayList<GatDto> locationGats(@Param("location1") String location1, @Param("location2") String location2);
	
	public int makeGat(@Param("mem_id") String mem_id, @Param("mem_nName") String mem_nName, @Param("title") String title, @Param("desc") String desc
			, @Param("location1") String location1, @Param("location2") String location2, @Param("file1") String file1
			, @Param("file2") String file2, @Param("fileExt") String fileExt, @Param("fileSize") long fileSize);
	
	public int makeGatNoFile(@Param("mem_id") String mem_id, @Param("mem_nName") String mem_nName
			, @Param("title") String title, @Param("desc") String desc
			, @Param("location1") String location1, @Param("location2") String location2);
	
	public int addGatLeader(@Param("title") String title, @Param("mem_id") String mem_id, @Param("mem_nName") String mem_nName);
	
	public GatDto gatInfo(@Param("id") String id);
	
	public int increGatView(@Param("id") String id);
	
	public int increGatMemsNum(@Param("id") String id);
	
	public ArrayList<GatMemDto> gatMems(@Param("id") String id);
	
	public ArrayList<GatPlanDto> gatPlans(@Param("id") String id);
	
	public ArrayList<GatChatDto> gatChat(@Param("id") String id);
	
	public int joinGat(@Param("gat_id") String gat_id, @Param("gat_title") String gat_title, @Param("mem_id") String mem_id, @Param("mem_nName") String mem_nName);
	
	public int addChat(@Param("gat_id") String gat_id, @Param("mem_id") String mem_id, @Param("mem_nName") String mem_nName, @Param("comments") String comments);
	
	public int modifyGat(HashMap<String, String> param);
	
	public int deleteGat(HashMap<String, String> param);
	
	public int deleteGatP(HashMap<String, String> param);
	
	public int deleteGatC(HashMap<String, String> param);
	
	public int deleteGatM(HashMap<String, String> param);
	
	public int addPlan(HashMap<String, String> param);
	
	public GatPlanDto getPlan(HashMap<String, String> param);
	
	public int delPlan(HashMap<String, String> param);
	
}
