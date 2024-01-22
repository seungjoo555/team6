package university;

import java.util.List;
import java.util.Objects;

import lombok.Data;

// 학과 리스트
@Data
public class Department {
/*과 클래스 // Department
- 과 이름 (중복 x)
- 정원 제한 (선택사항)
- 학과 교수 (리스트)
- 학생 (리스트)
과를 만든후에 교수, 학생 추가
교수 정원제한 학생 정원 제한을 입력받고 추가할때 사용
 */
	private String name; //학과명 -중복 X
	private List<Professor> professorList; // 학과교수 리스트
	private List<Student> studentList; // 학생 리스트
	private int maxProfessor;	//교수 정원 제한(5 고정)
	private int maxStudent;		//학생 정원 제한(10 고정)
	
	//수정, 삭제시 사용
	@Override
	public int hashCode() {
		return Objects.hash(name);
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
		return Objects.equals(name, other.name);
	}
	
	
	//학과 등록 생성자 : 학과 이름		*교수 정원(5 고정), 학생 정원(10 고정)
	public Department(String name) {
		this.name = name;
		this.maxStudent = 10;
		this.maxProfessor = 5;
	}
	
}
