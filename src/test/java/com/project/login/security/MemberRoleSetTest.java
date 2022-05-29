package com.project.login.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.project.login.entity.MemberDTO;
import com.project.login.entity.MemberRole;
import com.project.login.entity.MemberRoleSetDTO;
import com.project.login.repository.MemberRepository;
import java.sql.SQLException;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRoleSetTest {
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void insertMemberRoleSetTest() throws SQLException {
		for (int i = 1; i <= 101; i++) {
			MemberRoleSetDTO memberRole = new MemberRoleSetDTO();
			memberRole.setMid("user" + i);
			
			if (i < 33) {
				memberRole.setRole_set(MemberRole.USER.toString());
			}
			else if (i < 66) {
				memberRole.setRole_set(MemberRole.MANAGER.toString());
			}
			else {
				memberRole.setRole_set(MemberRole.ADMIN.toString());
			}
			
			memberRepository.insertMemberRoleSet(memberRole);
		}
	}
}
