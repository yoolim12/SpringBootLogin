package com.project.login.security;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.project.login.repository.MemberRepository;
import com.project.login.entity.MemberAndMemberRoleJoin;

@SpringBootTest
public class PasswordTests {
   @Autowired
   private PasswordEncoder passwordEncoder;
   
   @Autowired
   private MemberRepository memberRepository;
   
   //@Autowired
   private MemberAndMemberRoleJoin tablejoin;
   
   //@Test
   public void testEncide(){
       String password ="1111";
       String enPw = passwordEncoder.encode(password);
       System.out.println("enpw" + enPw);
       boolean matchResult = passwordEncoder.matches(password,enPw);
       System.out.println("matchResult: " + matchResult);

   }//end test..
   
   @Test
   public void encodeTest() throws SQLException {
	   tablejoin = memberRepository.findByMid("test2", 0);
	   System.out.println(tablejoin.getMpassword());
	   String enPw = passwordEncoder.encode(tablejoin.getMpassword());
	   boolean matchResult = passwordEncoder.matches(tablejoin.getMpassword(),enPw);
       System.out.println("matchResult: " + matchResult);
   }
}//end class
