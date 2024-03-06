package com.lgy.project_server.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.lgy.project_server.dto.DogDto;

public interface DogDao {

	public int register(HashMap<String, String> map);
	
	public int register_file(@Param("id") String id, @Param("dogName") String dogName, @Param("file_name1") String file_name1, @Param("file_name2") String file_name2, @Param("file_ext") String file_ext, @Param("file_size") String file_size);
	
	public ArrayList<DogDto> getDogs(@Param("id") String id);
	
	public int removeDog(@Param("id") String id, @Param("dogName") String dogName);
	
}
