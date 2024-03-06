package com.lgy.project_server.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgy.project_server.dao.GatDao;
import com.lgy.project_server.dto.GatChatDto;
import com.lgy.project_server.dto.GatDto;
import com.lgy.project_server.dto.GatMemDto;
import com.lgy.project_server.dto.GatPlanDto;

@Service("GatService")
public class GatServiceImpl implements GatService {

	@Autowired
	private SqlSession sqlSession;
	
	public ArrayList<GatDto> getGat(String search) {
		GatDao dao = sqlSession.getMapper(GatDao.class);
		ArrayList<GatDto> list = dao.getGat(search);
		System.out.println(list);
		
		return list;
	}

	@Override
	public ArrayList<GatDto> searchAll() {
		GatDao dao = sqlSession.getMapper(GatDao.class);
		ArrayList<GatDto> list = null;
		
		try {
			list = dao.searchAll();
		} catch (Exception e) {
			System.out.println("GatService / searchAll");
		}
		
		return list;
	}

	@Override
	public int getTotalPage() {
		int res = 0;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		try {
			res = dao.getTotalPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public ArrayList<GatDto> getMemGat(String id) {
		ArrayList<GatDto> list = null;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		try {
			list = dao.getMemGat(id);
		} catch (Exception e) {
			System.out.println("Error / GatServiceImpl/getMemGat");
		}
		
		return list;
	}

	@Override
	public ArrayList<GatDto> locationGats(String location1, String location2) {
		ArrayList<GatDto> list = null;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		try {
			list = dao.locationGats(location1, location2);
		} catch (Exception e) {
			System.out.println("Error / GatServiceImpl/locationGats");
		}
		
		return list;
	}

	@Override
	public int makeGat(String mem_id, String mem_nName, String title, String desc, String location1, String location2,
			String file1, String file2, String fileExt, long fileSize) {
		int res = 0;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		res = dao.makeGat(mem_id, mem_nName, title, desc, location1, location2, file1, file2, fileExt, fileSize);
		System.out.println("add gat success");
		dao.addGatLeader(title, mem_id, mem_nName);
		
		return res;
	}

	@Override
	public int makeGatNoFile(String mem_id, String mem_nName, String title, String desc, String location1, String location2) {
		int res = 0;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		res = dao.makeGatNoFile(mem_id, mem_nName, title, desc, location1, location2);
		System.out.println("add gat success");
		dao.addGatLeader(title, mem_id, mem_nName);
		
		return res;
	}

	@Override
	public GatDto gatInfo(String id) {
		GatDto dto = null;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		dto = dao.gatInfo(id);
		
		return dto;
	}

	@Override
	public ArrayList<GatMemDto> gatMems(String id) {
		ArrayList<GatMemDto> list = null;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		list = dao.gatMems(id);
		
		return list;
	}
	
	@Override
	public ArrayList<GatPlanDto> gatPlans(String id) {
		ArrayList<GatPlanDto> list = null;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		dao.increGatView(id);
		list = dao.gatPlans(id);
		
		return list;
	}
	
	@Override
	public ArrayList<GatChatDto> gatChat(String id) {
		ArrayList<GatChatDto> list = null;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		list = dao.gatChat(id);
		
		return list;
	}

	@Override
	public int joinGat(String gat_id, String gat_title, String mem_id, String mem_nName) {
		int res = 0;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		res = dao.joinGat(gat_id, gat_title, mem_id, mem_nName);
		dao.increGatMemsNum(gat_id);
		
		return res;
	}

	@Override
	public int addChat(String gat_id, String mem_id, String mem_nName, String comments) {
		int res = 0;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		res = dao.addChat(gat_id, mem_id, mem_nName, comments);
		
		return res;
	}

	@Override
	public int modifyGat(HashMap<String, String> param) {
		int res = 0;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		res = dao.modifyGat(param);
		
		return res;
	}

	@Override
	public int deleteGat(HashMap<String, String> param) {
		int res = 0;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		try {
			dao.deleteGatC(param);
			dao.deleteGatM(param);
			dao.deleteGatP(param);
			res = dao.deleteGat(param);
		} catch (Exception e) {
			return 0;
		}
		
		return res;
	}

	@Override
	public int addPlan(HashMap<String, String> param) {
		int res = 0;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		res = dao.addPlan(param);
		
		return res;
	}

	@Override
	public GatPlanDto getPlan(HashMap<String, String> param) {
		GatPlanDto plan = null;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		plan = dao.getPlan(param);
		
		return plan;
	}

	@Override
	public int delPlan(HashMap<String, String> param) {
		int res = 0;
		GatDao dao = sqlSession.getMapper(GatDao.class);
		
		res = dao.delPlan(param);
		
		return res;
	}

}
