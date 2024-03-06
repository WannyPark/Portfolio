package com.lgy.project_server.dao;

import java.util.HashMap;

public interface LoginDao {

	public HashMap<String, String> login_check(String id);
	
}
