package com.lgy.project_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemDto {

	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private String mem_nname;
	private String mem_email;
	private String mem_addr1;
	private String mem_addr2;
	private String mem_num;
	private String mem_bir;
	private String mem_gat1_id;
	private String mem_gat2_id;
	private String mem_gat3_id;
	
}