package com.lgy.project_server.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.lgy.project_server.dto.GatChatDto;
import com.lgy.project_server.dto.GatDto;
import com.lgy.project_server.dto.GatMemDto;
import com.lgy.project_server.dto.GatPlanDto;

public interface GatService {

	public ArrayList<GatDto> getGat(String search);
	
	public ArrayList<GatDto> searchAll();
	
	public int getTotalPage();
	
	public ArrayList<GatDto> getMemGat(String id);
	
	public ArrayList<GatDto> locationGats(String location1, String location2);
	
	public int makeGat(String mem_id, String mem_nName, String title, String desc, String location1, String location2
			, String file1, String file2, String fileExt, long fileSize);
	
	public int makeGatNoFile(String mem_id, String mem_nName, String title, String desc, String location1, String location2);
	
	public GatDto gatInfo(String id);
	
	public ArrayList<GatMemDto> gatMems(String id);
	
	public ArrayList<GatPlanDto> gatPlans(String id);
	
	public ArrayList<GatChatDto> gatChat(String id);
	
	public int joinGat(String gat_id, String gat_title, String mem_id, String mem_nName);
	
	public int addChat(String gat_id, String mem_id, String mem_nName, String comments);
	
	public int modifyGat(HashMap<String, String> param);
	
	public int deleteGat(HashMap<String, String> param);
	
	public int addPlan(HashMap<String, String> param);
	
	public GatPlanDto getPlan(HashMap<String, String> param);
	
	public int delPlan(HashMap<String, String> param);
	
}
