package com.project.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.login.entity.MemberDTO;
import com.project.login.entity.MemberRoleSetDTO;
import com.project.login.repository.MemberRepository;

@Service
public class RegisterServiceImpl {
	@Autowired
	private MemberRepository member;
	
	public void registerService(MemberDTO memberdto) {
		member.register(memberdto);
	}
	
	public void registerRole(MemberRoleSetDTO member_role) {
		member.registerRole(member_role);
	}
}
