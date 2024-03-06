package com.lgy.project_server.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgy.project_server.dao.MemDao;
import com.lgy.project_server.dto.MemDto;

import lombok.extern.slf4j.Slf4j;

@Service("MemService")
@Slf4j
public class MemServiceImpl implements MemService {

	@Autowired
	private SqlSession sqlSession;
	
	public MemDto getMem(String id) {
		log.info("@# getMem Service start");
		MemDao dao = sqlSession.getMapper(MemDao.class);
		MemDto mem = dao.getMem(id);
		
		log.info("@# getMem Service end");
		return mem;
	}
	
	public int addMem(MemDto mem) {
		MemDao dao = sqlSession.getMapper(MemDao.class);
		int res = 0;
		
		res = dao.addMem(mem);
		
		return res;
	}
	
	public int modiPw(String id, String oldPw, String newPw1, String newPw2) {
		int res = 1;
		MemDao dao = sqlSession.getMapper(MemDao.class);
		
		MemDto dto = dao.getMem(id);
		if (!dto.getMem_pw().equals(oldPw)) {
			res = -1;
			return res;
		} else if (!newPw1.equals(newPw2)) {
			res = 0;
			return res;
		}
		res = dao.modiPw(id, newPw1);
		
		return res;
	}
	
	public int modiNname(String id, String newNname) {
		int res = 0;
		MemDao dao = sqlSession.getMapper(MemDao.class);
		
		res = dao.modiNname(id, newNname);
		
		return res;
	}
	
	public int modiNum(String id, String newNum) {
		int res = 0;
		MemDao dao = sqlSession.getMapper(MemDao.class);
		
		res = dao.modiNum(id, newNum);
		
		return res;
	}
	
	public int modiAddr(String id, String newAddr1, String newAddr2) {
		int res = 0;
		MemDao dao = sqlSession.getMapper(MemDao.class);
		
		res = dao.modiAddr(id, newAddr1, newAddr2);
		
		return res;
	}
	
	public int newEmail(String id, String newEmail) {
		int res = 0;
		MemDao dao = sqlSession.getMapper(MemDao.class);
		
		res = dao.newEmail(id, newEmail);
		
		return res;
	}
	
	public int getmail(String id, String name, String email) {
		int res = 0;
		
		MemDao dao = sqlSession.getMapper(MemDao.class);
		MemDto dto = dao.getmail(id);
		
		if (dto.getMem_id().isEmpty()) {
			res= 0;
		}else if (!dto.getMem_id().equals(id) || !dto.getMem_name().equals(name) || !dto.getMem_email().equals(email)) {
			res= 0;
		}else {
			res=1;
		}
		
		return res;
	}
	
	public int modiNewPw(String id, String newPw) {
		int res = 0;
		MemDao dao = sqlSession.getMapper(MemDao.class);		
		
		res = dao.modiPw(id, newPw);
		
		return res;
	}
	
}
