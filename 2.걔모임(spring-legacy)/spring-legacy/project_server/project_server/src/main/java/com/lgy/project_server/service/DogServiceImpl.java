package com.lgy.project_server.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgy.project_server.dao.DogDao;
import com.lgy.project_server.dto.DogDto;

@Service("DogService")
public class DogServiceImpl implements DogService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int register(HashMap<String, String> map) {
		int res = 0;
		DogDao dao = sqlSession.getMapper(DogDao.class);

		res = dao.register(map);
		
		return res;
	}

	@Override
	public int register_file(String id, String dogName, String file_name1, String file_name2, String file_ext, long file_size) {
		int res = 0;
		DogDao dao = sqlSession.getMapper(DogDao.class);
		System.out.println(id);
		System.out.println(dogName);
		System.out.println(file_name1);
		System.out.println(file_name2);
		System.out.println(file_size+"");
		res = dao.register_file(id, dogName, file_name1, file_name2, file_ext, file_size+"");
		
		return res;
	}
	
	@Override
	public ArrayList<DogDto> getDogs(String id) {
		ArrayList<DogDto> list = null;
		DogDao dao = sqlSession.getMapper(DogDao.class);
		
		try {
			list = dao.getDogs(id);
		} catch (Exception e) {
			System.out.println("Error / dogservice/getdogs");
		}
		
		return list;
	}

	@Override
	public int removeDog(String id, String dogName) {
		int res = 0;
		DogDao dao = sqlSession.getMapper(DogDao.class);
		
		try {
			res = dao.removeDog(id, dogName);
		} catch (Exception e) {
			System.out.println("Error / dogservice/removeDog");
		}
		
		return res;
	}

}
