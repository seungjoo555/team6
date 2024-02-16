package community.model.vo;

import java.util.Objects;

import lombok.Data;
@Data
public class Category {
	
	private int ca_num;
	private String ca_name;
	@Override
	public int hashCode() {
		return Objects.hash(ca_num);
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
	public String toString() {
		return "[" + ca_num + "] ca_name=" + ca_name;
	}
	
	
	
}
