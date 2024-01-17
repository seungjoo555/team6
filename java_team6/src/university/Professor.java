package university;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 교수 리스트
@Data
@Getter
@Setter
@AllArgsConstructor
public class Professor implements Serializable{
	
	private static final long serialVersionUID = 3660695893715988058L;
	String name;
	int proNum;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		return Objects.equals(name, other.name) && proNum == other.proNum;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, proNum);
	}
	@Override
	public String toString() {
		return "이름 : " + name;
	}
	
	
}
