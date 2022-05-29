package com.project.login.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberAndMemberRoleJoin implements Serializable {
	private String mid;
	private String mpassword;
	private String mname;
	private int menabled;
	private Date birth;
	private String role_set;
	
	private String memail;
}
