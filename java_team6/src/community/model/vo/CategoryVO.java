package community.model.vo;

import java.util.Objects;

// 카테고리
public class CategoryVO {
	
	private int ca_num;
	private String ca_title;
	
	public CategoryVO(int ca_num, String ca_title) {
		this.ca_num = ca_num;
		this.ca_title = ca_title;
	}

	public CategoryVO(int bo_ca_num) {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(ca_num, ca_title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryVO other = (CategoryVO) obj;
		return ca_num == other.ca_num && Objects.equals(ca_title, other.ca_title);
	}

	@Override
	public String toString() {
		return "카테고리" + "\n카테고리 번호 : "+ca_num + "\n카테고리 명 : " + ca_title;
	}
	
	

}
