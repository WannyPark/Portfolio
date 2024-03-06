package com.lgy.project_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatChatDto {

	private String GATHERING_GAT_ID;
	private String MEMBER_MEM_ID;
	private String MEMBER_MEM_NNAME;
	private String GAT_CHAT_DESC;
	private String GAT_CHAT_DATE;
	
}