package community.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {
	// 사용자
	private String me_id;
	private String me_name;
	private String me_pw;
	private String me_email;
	private String me_authority;
	private String me_address;
	private String me_phoneNum;
	
	public Member(String me_id, String me_pw) {
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
		Member other = (Member) obj;
		return Objects.equals(me_id, other.me_id) && Objects.equals(me_pw, other.me_pw);
	}

	@Override
	public int hashCode() {
		return Objects.hash(me_id, me_pw);
	}
	
}