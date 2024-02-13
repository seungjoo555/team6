package community.model.vo;


public class CommunityVO {
	
	private String id, pw, name;
	private String email, addr, phone;
	
	public CommunityVO(String id, String pw, String name, String email, String addr, String phone) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.addr = addr;
		this.phone = phone;
	}
}