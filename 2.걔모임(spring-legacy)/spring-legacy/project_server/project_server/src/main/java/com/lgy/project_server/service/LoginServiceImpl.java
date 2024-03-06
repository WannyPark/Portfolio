package com.lgy.project_server.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgy.project_server.dao.LoginDao;

import lombok.extern.slf4j.Slf4j;

@Service("LoginService")
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SqlSession sqlSession;
	
	public int login_check(String id, String pw) {
		log.info("@# ser.login_check() start");
		int res = 0;
		LoginDao dao = sqlSession.getMapper(LoginDao.class);
		HashMap<String, String> map = dao.login_check(id);
		
		if (map != null) {
			if (map.containsKey("MEM_ID") && !map.get("MEM_ID").isEmpty()) {
				res = (map.get("MEM_PW") != null && map.get("MEM_PW").equals(pw)) ? 1 : -1;
			}
		}
//		if (map.get("MEM_ID").isEmpty()) {
//			res = 0;
//		} else if (map.get("MEM_PW").equals(pw)) {
//			res = 1;
//		} else {
//			res = -1;
//		}
		
		log.info("@# ser.login_check() end");
		return res;
	}
	
}
