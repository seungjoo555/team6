package university;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// 리스트 총괄 관리
public class School implements Serializable {
	private static final long serialVersionUID = -3631361192718600576L;

	private List<Professor> prf;// 교수 리스트
	private List<Student> std; // 학생 리스트
	private List<Subject> sub; // 강의 리스트
	private List<Department>dep; // 학과 리스트
	
	public School() {
		this.prf = new ArrayList<Professor>();
		this.std = new ArrayList<Student>();
		this.sub = new ArrayList<Subject>();
		this.dep = new ArrayList<Department>();
	}
	
	//교수추가 메서드
	public void addProfessor(Professor prf) {
		this.prf.add(prf);
	}
	
	//교수수정 메서드
	public void updateProfessor() {
		
	}
	//교수삭제 메서드
	public void removeProfessor() {
		
	}
	//학생추가 메서드
	public void addStudent(Student std) {
		this.std.add(std);
	}
	//학생수정 메서드
	public void updateStudent() {
		
	}
	//학생삭제 메서드
	public void removeStudent() {
		
	}
	//강의추가 메서드
	public void addSubject(Subject sub) {
		this.sub.add(sub);
	}
	//강의수정 메서드
	public void updateSubject() {
		
	}
	//강의삭제 메서드
	public void removeSubject() {
		
	}
	//학과추가 메서드
	public void addDepartment(Department dep) {
		this.dep.add(dep);
	}
	//학과수정 메서드
	public void updateDepartment() {
		
	}
	//학과삭제 메서드
	public void removeDepartment() {
		
	}
	
	
	
}
