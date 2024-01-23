package university;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

// 교수 리스트
@Data
@AllArgsConstructor
public class Professor implements Serializable{
	private static final long serialVersionUID = 8377957422955045309L;
	private String pName; 		// 교수 이름
	private String pSubject; 	// 교수 강의 과목
	private String pDep; 		//교수 학과
	private String pNum; 		//교수 번호 - 중복 X
	
	@Override
	public int hashCode() {
		return Objects.hash(pNum);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		return Objects.equals(pNum, other.pNum);
	}
	
	@Override
	public String toString() {
		return"[" + "교수이름=" + pName + ", 학과=" +pDep + "\n번호=" + pNum + " ,강의명=" + pSubject + "]";
	}

	public Professor(String pNum) {
		this.pNum = pNum;
	}

}
