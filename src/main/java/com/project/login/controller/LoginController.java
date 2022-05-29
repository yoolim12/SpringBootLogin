// 작성자 : 최유림
package com.project.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.login.entity.MemberDTO;
import com.project.login.entity.MemberRoleSetDTO;
import com.project.login.security.dto.AuthMemberDTO;
import com.project.login.service.RegisterServiceImpl;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/loginaccess")
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class LoginController {

	@Autowired
	private RegisterServiceImpl service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 권한 상관 없이 모두 접근 가능
	@PreAuthorize("permitAll()")
	@RequestMapping("/all")
   public void accessALL(){
       log.info("exAll.....");
   }//end ex..
	
//	@PreAuthorize("permitAll()")
//	@RequestMapping("/loginpage")
//	public void loginpage() {
//		System.out.println("login form return");
//	}
//	
//	@PreAuthorize("permitAll()")
//	@RequestMapping("/loginpage/done")
//	public void loginpageDone() {
//		System.out.println("login form return");
//	}
	
	// 권한 상관 없이 모두 접근 가능
	@PreAuthorize("permitAll()")
	@RequestMapping("/register")
	public void register() {
		System.out.println("register form return");
	}
	
	// 권한 상관 없이 모두 접근 가능
		@PreAuthorize("permitAll()")
	   @RequestMapping("/register/done")
	   public String registerDone(@ModelAttribute MemberDTO memberdto,
			   @ModelAttribute MemberRoleSetDTO memberRoleSetDTO,
			   HttpServletResponse response, HttpServletRequest request) throws Exception {
			memberdto.setMid(request.getParameter("mid"));
			memberdto.setMname(request.getParameter("mname"));
			memberdto.setMpassword(passwordEncoder.encode(request.getParameter("mpassword")));
			memberdto.setMemail(request.getParameter("memail"));
			memberdto.setTelnum(request.getParameter("telnum"));
			
			/*String birth = request.getParameter("birth");
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date birth_date = (Date) transFormat.parse(birth);
			memberdto.setBirth(birth_date);*/
			System.out.println("yooooooooooooooooolim"+memberdto);
	       log.info("exAll.....");
	       //memberdto.setMid(null);
	       
	       service.registerService(memberdto);
	       
	       memberRoleSetDTO.setMid(request.getParameter("mid"));
	       service.registerRole(memberRoleSetDTO);
	       
	       return "loginaccess/all";
	   }//end ex..

	// USER 권한을 가진 사람만 접근 가능
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/member")
   public void accessMember(){
       log.info("exMember.....");
   }//end ex..

	// ADMIN 권한을 가진 사람만 접근 가능
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/admin")
   public void accessAdmin(){
       log.info("exAdmin.....");
   }//end ex..
   
	@RequestMapping("/test")
   public void test(){
       log.info("test.....");
   }//end ex..
   
   @PreAuthorize("hasRole('ROLE_USER')")
   @RequestMapping("/modify")
   public void exmodify(
           @AuthenticationPrincipal AuthMemberDTO clubAuthMemberDTO
   ){
       log.info("exModify.....");
       log.info("--------------");
       log.info(clubAuthMemberDTO);
   }//end ex..
   
	/*
	 * //user95@zerock.org 만 접근 가능
	 * 
	 * @PreAuthorize(" #AuthMemberDTO != null " +
	 * " && #AuthMemberDTO.memail eq \"user95@zerock.org\" ")
	 * 
	 * @GetMapping("/exOnly") public void exMemebrOnly(
	 * 
	 * @AuthenticationPrincipal AuthMemberDTO clubAuthMemberDTO){
	 * log.info("exMemberOnly-------"); log.info(clubAuthMemberDTO); }//end exM..
	 */
}//end class