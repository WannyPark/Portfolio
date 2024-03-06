package com.lgy.project_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatDto {

	private String gat_id;
	private String member_mem_id;
	private String member_mem_nname;
	private String gat_title;
	private String gat_desc;
	private String gat_date;
	private int gat_view;
	private int gat_memnum;
	private String gat_img_name1;
	private String gat_img_name2;
	private String gat_img_ext;
	private int gat_img_byte;
	
}