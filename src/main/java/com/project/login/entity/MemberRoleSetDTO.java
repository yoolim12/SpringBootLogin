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
public class MemberRoleSetDTO implements Serializable {
	private String mid;
	private String role_set;
}
