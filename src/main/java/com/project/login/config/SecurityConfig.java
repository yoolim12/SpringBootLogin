package com.project.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.login.security.handler.ClubLoginSuccessHandler;
import com.project.login.security.service.OAuth2UserDetailsService;
import lombok.extern.log4j.Log4j2;

/* USER 권한 = ROLE_USER 라는 상수 의미
스프링 시큐리티의 내부에서 USER 라는 단어를 상수처럼 인증된 사용자를 의미하는 용도로 사용
 */

/* 스프링 시큐리티를 적용하면 기본적으로 CSRF 방지를 위한 토큰(CSRF토큰)이 사용됨 
세션마다 다른 CSRF토큰 값이 생성
GET방식을 제외한 모든 요청에 대해서 CSRF토큰이 필수적으로 필요 
*/

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private OAuth2UserDetailsService oAuth2UserDetailsService;
	
	//ClubLoginSuccessHandler 등록
    @Bean
    public ClubLoginSuccessHandler successHandler(){
        return new ClubLoginSuccessHandler(passwordEncoder());
    }//end CLu..   

   @Bean
   PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }//end pass..
   
   @Bean
   public RoleHierarchyImpl roleHierarchyImpl() {
       log.info("실행");
       RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
       roleHierarchyImpl.setHierarchy("ADMIN > MANAGER > USER");
       return roleHierarchyImpl;
   }


   /* ClubUserDetailsService가 빈으로 등록되므로 별도의 설정없이 
    * 기존의 AuthenticationManagerBuilder를 이용하는 메서드는 필요없음 
    * configure(AuthenticationManagerBuilder auth) 메소드 주석 처리 */
//   @Override
//   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       //사용자 계정 세팅 user1//패스워드 1111
//       auth.inMemoryAuthentication()
//               .withUser("user1")
//               .password("$2a$10$qbTVRGiC8RePIsMz4z/QP.LjBmLOMGXBCkmW2comzfNaoeidd5/aa")
//               .roles("USER");
//   }//configure AM

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       
       
      // http.authorizeRequests()
        //       .antMatchers("/loginaccess/all").permitAll()//  /loginaccess/all 모든 사용자 가능(바로 all.html로 이동)
          //     .antMatchers("/loginaccess/member").hasRole("USER")//  /loginaccess/member USER 롤 사용자만(로그인 창으로 이동)
            //   .antMatchers("/loginaccess/admin").hasRole("ADMIN");//  /loginaccess/admin ADMIN 롤 사용자만(로그인 창으로 이동)

       //인가 인증 문제시 로그인 화면
       http.formLogin()
       //.loginPage("/loginaccess/loginpage")
       //.usernameParameter("mid")
       //.passwordParameter("mpassword")
       .defaultSuccessUrl("/loginaccess/modify");
       
       //crsf 비활성화
       http.csrf().disable();
       
       //로그 아웃 세팅
       http.logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/loginaccess/all");
       
       // 구글 oauth 인증
       http.oauth2Login().defaultSuccessUrl("/loginaccess/member");
    // 구글 oauth 인증 추가
       //ClubLoginSuccessHandler 등록
       //http.oauth2Login().successHandler(successHandler());
       
     //일반 from 로그인 rememberMe 설정
       http.rememberMe()  //1day
               .tokenValiditySeconds(60 * 60 * 24)
               .userDetailsService(userDetailsService());     


   }//end configure http
}//end class