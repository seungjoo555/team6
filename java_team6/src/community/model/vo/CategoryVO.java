package community.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

// 카테고리

@Data
@NoArgsConstructor
public class CategoryVO {
	private int ca_num;
	private String ca_title;
	private int bo_ca_num;

	public CategoryVO(int ca_num, String ca_title) {
		this.ca_num = ca_num;
		this.ca_title = ca_title;
	}

	public CategoryVO(String ca_title) {
		this.ca_title = ca_title;
	}
	
	public CategoryVO(int bo_ca_num) {
		this.bo_ca_num = bo_ca_num;
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
		return "[카테고리 번호 : " + ca_num + "]" + "\n[카테고리 명 : " + ca_title + "]\n";
	}

}
