package com.project.login.repository;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.login.entity.MemberAndMemberRoleJoin;
import com.project.login.entity.MemberDTO;
import com.project.login.entity.MemberRoleSetDTO;

@Mapper
public interface MemberRepository {
	public void insertMember(MemberDTO member);
	public void insertMemberRoleSet(MemberRoleSetDTO memberRoleSet);
	public MemberAndMemberRoleJoin findByMid(@Param("mid") String mid
			, @Param("menabled") int menabled) throws SQLException;
	public MemberAndMemberRoleJoin findByGmail(@Param("memail") String memail
			, @Param("menabled") int menabled) throws SQLException;
	
	public void register(MemberDTO member);
	
	public void registerRole(MemberRoleSetDTO member_role);
}
