package com.project.login.security;

import java.sql.Date;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.login.entity.MemberDTO;
import com.project.login.repository.MemberRepository;

@SpringBootTest
public class MemberTest {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void insertMemberTest() throws SQLException {
		for (int i = 1; i <= 101; i++ ) {
			MemberDTO member = new MemberDTO();
			member.setMid("user" + i);
			member.setMname("user" + i);
			member.setMpassword(passwordEncoder.encode("1111"));
			member.setMenabled(1);
			if (i < 33) {
				member.setMrole("user");
			}
			else if (i < 66) {
				member.setMrole("manager");
			}
			else {
				member.setMrole("admin");
			}
			
			member.setMemail("user" + i + "@gmail.com");
			member.setTelnum("01011112222");
			member.setBirth(Date.valueOf("1998-02-12"));
			memberRepository.insertMember(member);
		}
		
		System.out.println("insert success");
	}
}
