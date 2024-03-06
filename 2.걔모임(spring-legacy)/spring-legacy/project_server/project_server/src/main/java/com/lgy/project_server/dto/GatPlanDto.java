package com.lgy.project_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatPlanDto {

	private String GAT_PLAN_ID;
	private String GAT_PLAN_LEADER;
	private String GATHERING_GAT_ID;
	private String GAT_PLAN_DATE;
	private String GAT_PLAN_ADDR;
	private String GAT_PLAN_TITLE;
	private String GAT_PLAN_DESC;
	
}
