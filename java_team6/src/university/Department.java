package university;

import java.util.ArrayList;
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
	 */
	private String name; //학과명 -중복 X
	private List<Professor> professorList; // 학과교수 리스트
	private List<Student> studentList; // 학생 리스트
	
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
	
	/**
	 * 교수 목록 업데이트
	 * @param p 학교에 있는 교수 리스트
	 * @return 들어온 리스트에 과이름에 맞는 교수가 있으면 추가하고 성공리턴
	 */
	public boolean updatePf(List<Professor> p) {
		if(p == null) {
			return false;
		}
		int count = (int)p.stream().filter(pf->pf.getPDep().contains(this.name)).count();
		if(count == 0) {
			return false;
		}
		this.professorList = new ArrayList<Professor>();
		p.stream().filter(pf->pf.getPDep().contains(this.name))
		.forEach(pf->this.professorList.add(pf));
		return true;
	}
	
	/**
	 * 학생 목록 업데이트
	 * @param s 학교에 있는 학생 리스트
	 * @return 들어온 리스트에 과이름에 맞는 학생이 있으면 추가하고 성공리턴
	 */
	public boolean updateStd(List<Student> s) {
		if(s == null) {
			return false;
		}
		int count = (int)s.stream().filter(std->std.getSDep().contains(this.name)).count();
		if(count == 0) {
			return false;
		}
		this.studentList = new ArrayList<Student>();
		s.stream().filter(std->std.getSDep().contains(this.name))
		.forEach(std->this.studentList.add(std));
		return true;
	}
	
	
	//학과 등록 생성자 : 학과 이름
	public Department(String name) {
		this.name = name;
	}
}
