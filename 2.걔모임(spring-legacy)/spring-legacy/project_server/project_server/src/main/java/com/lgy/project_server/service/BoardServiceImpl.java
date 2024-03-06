package com.lgy.project_server.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgy.project_server.dao.BoardDao;
import com.lgy.project_server.dto.BoardDto;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<BoardDto> getBoardListPaging(int page, int pageSize) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
        int start = (page - 1) * pageSize + 1;
        int end = start + pageSize - 1;
        return dao.getBoardListPaging(start, end);
	}

	@Override
	public ArrayList<BoardDto> getBoardWriter(int page, int pageSize, String searchVal) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		int start = (page - 1) * pageSize + 1;
		
		int end = start + pageSize - 1;
		ArrayList<BoardDto> search = dao.getBoardWriter(start,end,searchVal);
		System.out.println(search);
		
		return search;
	}

	@Override
	public ArrayList<BoardDto> getBoardTitle(int page, int pageSize, String searchVal) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		int start = (page - 1) * pageSize + 1;
		int end = start + pageSize - 1;
		ArrayList<BoardDto> search = dao.getBoardTitle(start,end,searchVal);
		
		return search;
	}

	@Override
	public ArrayList<BoardDto> getCateMenu1(int page, int pageSize, String catemenu) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		int start = (page - 1) * pageSize + 1;
		int end = start + pageSize - 1;
		ArrayList<BoardDto> cate = dao.getCateMenu1(start,end, catemenu);
		
		return cate;
	}

	@Override
	public ArrayList<BoardDto> getCateMenu2(int page, int pageSize, String catemenu) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		int start = (page - 1) * pageSize + 1;
		int end = start + pageSize - 1;
		ArrayList<BoardDto> cate = dao.getCateMenu2(start,end, catemenu);
		
		return cate;
	}

	@Override
	public ArrayList<BoardDto> getCateMenu3(int page, int pageSize, String catemenu) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		int start = (page - 1) * pageSize + 1;
		int end = start + pageSize - 1;
		ArrayList<BoardDto> cate = dao.getCateMenu3(start,end, catemenu);
		
		return cate;
	}

	@Override
	public ArrayList<BoardDto> getCSearch(int page, int pageSize, String catemenu2, String cateSelec, String cateVal) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);

		int start = (page - 1) * pageSize + 1;
		int end = start + pageSize - 1;
		ArrayList<BoardDto> Search = dao.getCSearch(start,end, catemenu2,cateSelec,cateVal);

		return Search;
	}
	
	@Override
	public ArrayList<BoardDto> getHit() {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		ArrayList<BoardDto> getHit = dao.getHit();
		return getHit;
	}

	@Override
	public ArrayList<BoardDto> contentView(String id) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.increView(id);
		ArrayList<BoardDto> dtos = dao.contentView(id);
		
		return dtos;
	}

	@Override
	public int uploadBoard(String mem_id, String mem_Nname, String title, String desc, String category, String file1,
			String file2, String fileExt, long fileSize) {
		int res = 0;
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		res = dao.uploadBoard(mem_id, mem_Nname, title, desc, category, file1, file2, fileExt, fileSize);
		
		return res;
	}

	@Override
	public int uploadBoardNoFile(String mem_id, String mem_Nname, String title, String desc, String category) {
		int res = 0;
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		res = dao.uploadBoardNoFile(mem_id, mem_Nname, title, desc, category);
		
		return res;
	}

	@Override
	public int modifyBoard(HashMap<String, String> param) {
		int res = 0;
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		res = dao.modifyBoard(param);
		
		return res;
	}

	@Override
	public int deleteBoard(HashMap<String, String> param) {
		int res = 0;
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		res = dao.deleteBoard(param);
		
		return res;
	}

}
