package community.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Category {
	private int ca_num;
	private String ca_name;
	
	public Category(int categoryNum) {
		this.ca_num = categoryNum;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return ca_num == other.ca_num;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ca_num);
	}

	@Override
	public String toString() {
		return "Category [categoryNum=" + ca_num + ", categoryName=" + ca_name + "]";
	}
	
	
}
