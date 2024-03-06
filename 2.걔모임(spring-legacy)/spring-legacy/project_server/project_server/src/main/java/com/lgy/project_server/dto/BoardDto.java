package com.lgy.project_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

	private String BOARD_ID;
	private String MEMBER_MEM_ID;
	private String MEMBER_MEM_NNAME;
	private String BOARD_TITLE;
	private String BOARD_DESC;
	private String BOARD_CATE;
	private String BOARD_IMG_NAME1;
	private String BOARD_IMG_NAME2;
	private String BOARD_IMG_EXT;
	private long BOARD_IMG_BYTE;
	private String BOARD_DATE;
	private int BOARD_VIEW;
	
}