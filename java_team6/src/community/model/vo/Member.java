package community.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {
	String me_id;			//아이디
	String me_pw;			//비밀번호
	String me_email;		//이메일
	String me_authority;	//권한
	String me_name;			//이름
	String me_phoneNum;		//전화번호
	String me_address;		//주소
	String me_ms_state;		//유저상태 (가입요청, 회원, 이용정지, 관리자)
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(me_id, other.me_id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(me_id);
	}
	
	public Member(String id, String pwd, String email, String name, String phone, String addr) {
		me_id = id;
		me_pw = pwd;
		me_email = email;
		me_name = name;
		me_phoneNum = phone;
		me_address = addr;
		me_ms_state = "가입요청";
	}

	public Member(String me_id) {
		this.me_id = me_id;
	}

}