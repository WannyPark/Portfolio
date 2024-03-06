package com.lgy.project_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogDto {

	private String dog_id;
	private String member_mem_id;
	private String member_mem_nname;
	private String dog_name;
	private String dog_age;
	private String dog_kind;
	private String dog_bir;
	private String dog_gender;
	private String dog_file_name1;
	private String dog_file_name2;
	private String dog_file_ext;
	private long dog_file_byte;
	
}