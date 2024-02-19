package community.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {
	
	private String me_id, me_pw, me_name;
	private String me_email, me_address, me_phoneNum, me_authority;
	
	public MemberVO(String me_id, String me_pw, String me_name, String me_email, String me_address, String me_phoneNum) {
		this.me_id = me_id;
		this.me_pw = me_pw;
		this.me_name = me_name;
		this.me_email = me_email;
		this.me_address = me_address;
		this.me_phoneNum = me_phoneNum;
	}

	public MemberVO(String me_id, String me_pw) {
		this.me_id = me_id;
		this.me_pw = me_pw;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		return Objects.equals(me_id, other.me_id) && Objects.equals(me_pw, other.me_pw);
	}

	@Override
	public int hashCode() {
		return Objects.hash(me_id, me_pw);
	}

	@Override
	public String toString() {
		return "MemberVO [me_id=" + me_id + ", me_pw=" + me_pw + ", me_name=" + me_name + ", me_email=" + me_email
				+ ", me_address=" + me_address + ", me_phoneNum=" + me_phoneNum + "]";
	}
	
	
}