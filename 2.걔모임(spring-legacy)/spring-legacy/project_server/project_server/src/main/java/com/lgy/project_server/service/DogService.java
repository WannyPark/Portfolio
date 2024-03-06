package com.lgy.project_server.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.lgy.project_server.dto.DogDto;

public interface DogService {

	public int register(HashMap<String, String> map);
	
	public int register_file(String id, String dogName, String file_name1, String file_name2, String file_ext, long file_size);
	
	public ArrayList<DogDto> getDogs(String id);
	
	public int removeDog(String id, String dogName);
	
}
