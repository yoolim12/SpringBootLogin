package com.project.login.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
public class MemberDTO implements Serializable {
	private String mid;
	private String mname;
	private String mpassword;
	private int menabled;
	private String mrole;
	private String memail;
	private String telnum;
	private Date birth;
	
	public MemberDTO() {}
}