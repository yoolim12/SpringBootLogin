package com.project.login.security.dto;
 
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.List;
import java.util.Map;
 
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User implements OAuth2User{
 
    private static final long serialVersionUID = 1L;
    private String memail;
    private String mname;
    private int menabled;
    private String mpassword;
    private Map<String, Object> OA2_attr;
 
   
  //ClubOAuth2UserDetailsService 용 구성자
    public AuthMemberDTO(
            String memail,
            String mpassword,
            int menabled,
            List<GrantedAuthority> authorities,
            Map<String, Object> OA2_attr) {
        this(memail,mpassword,menabled,authorities);
        
        this.OA2_attr = OA2_attr;
    }//end ClubAuthMemberDTO
 
 
    // 구성자 설정
    public AuthMemberDTO(String memail,
            String mpassword, int menabled
            , List<GrantedAuthority> authorities) {
        // password는 부모클래스 사용
        super(memail, mpassword, authorities);
        System.out.println("오ㅗㅗㅗㅗㅗㅗㅗㅗ써리티ㅣㅣㅣㅣㅣㅣㅣ"+authorities);
        this.memail = memail;
        this.menabled = menabled;
        this.mpassword = mpassword;
    }// end ClubAuthMemberDTO
   
  //OAuth2User 정보 저장
    @Override
    public Map<String, Object> getAttributes() {
        return OA2_attr;
    }


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	} 
}// end class
