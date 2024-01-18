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
	
}