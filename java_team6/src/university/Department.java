package university;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

// 학과 리스트
public class Department implements Serializable {
	private static final long serialVersionUID = 3597327171139648919L;
	/*과 클래스 // Department
	- 과 이름 (중복x)
	- 정원 제한 (선택사항)
	- 학과 교수 (리스트)
	- 학생 (리스트)
	 */
	private String dName; //학과명 -중복 X
	private List<Professor> prf; // 학과교수 리스트
	private List<Student> std; // 학생 리스트
	
	@Override
	public int hashCode() {
		return Objects.hash(dName, prf, std);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(dName, other.dName) && Objects.equals(prf, other.prf) && Objects.equals(std, other.std);
	}
	public Department(String dName, List<Professor> prf, List<Student> std) {
		super();
		this.dName = dName;
		this.prf = prf;
		this.std = std;
	}
	@Override
	public String toString() {
		return "학과명 :" + dName + "\n교수명 :" + prf + "\n학생명 :" + std ;
	}
	
}