package com.project.login.security.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.login.entity.MemberAndMemberRoleJoin;
import com.project.login.repository.MemberRepository;
import com.project.login.security.dto.AuthMemberDTO;

import lombok.extern.log4j.Log4j2;

/* UserDetailsService
개발자가 원하는 방식으로 로그인을 처리하기 위해서 구현하는 인터페이스 
사용자가 원하는 방식으로 인가/인증 처리를 하기 위해서는 직접 loadUserByUsername( )이라는 
메서드의 반환 타입인 UserDetails 역시 인터페이스로, 사용자의 정보와 권한 정보 등을 담는 타입  */

@Service
@Log4j2
public class MemberDetailsService implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String mid) 
			throws UsernameNotFoundException {
		// 입력한 mid로 member 찾음
		MemberAndMemberRoleJoin tablejoin;
		
		try {
			log.info(mid);
			tablejoin = memberRepository.findByMid(mid, 0);
			System.out.println("try 성고오오오오오오오오오오오ㅗ오오옹");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^6"+ tablejoin);
		} catch(SQLException e) {
			System.out.println("ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ");
			throw new UsernameNotFoundException("Check Email or Social!!");
		}
		
		if(tablejoin == null) {
            try {
            	tablejoin = memberRepository.findByGmail(mid+"@gmail.com", 1);
            	System.out.println("이제 null아님!!! 성고오오오오오오오오오오오ㅗ오오옹");
            	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^6"+ tablejoin);
            } catch (SQLException e1) {
                throw new UsernameNotFoundException("Check Email or Social!!");
            }//end try  
           
        }//end if
		
		log.info("------------------");
		log.info(tablejoin);
		log.info(tablejoin.getRole_set().toString());
		
		List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + tablejoin.getRole_set()));
        System.out.println("--------------------------오써리티: "+authorities);
        // tablejoin --> AuthMemberDTO 변환
        AuthMemberDTO authMemberDTO = new
                AuthMemberDTO(tablejoin.getMid(), tablejoin.getMpassword(),
                tablejoin.getMenabled(), authorities);
        
        // AuthMemberDTO 값 세팅
        authMemberDTO.setMname(tablejoin.getMname());
        authMemberDTO.setMenabled(tablejoin.getMenabled());
        
        log.info(authMemberDTO);
        log.info(authMemberDTO.getAuthorities().toString());
        System.out.println("AUTHMEMBERDTO IS "+ authMemberDTO);
 
        // ClubAuthMemberDTO는 UserDetails 타입으로 처리됨
        return authMemberDTO;
	}
}
