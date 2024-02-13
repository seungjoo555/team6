package community.model.vo;

import java.util.Date;

public class CommunityVO {
	private String id;
	private String pw;
	private String email;
	private Date reg_Date;
	
	public CommunityVO(String id, String pw, String email) {
		this.id = id;
		this.pw = pw;
		this.email = email;
	}
}