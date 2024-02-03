package university;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

// 리스트 총괄 관리
@Data
public class School implements Serializable {
	private static final long serialVersionUID = -3631361192718600576L;
   
	private List<Professor> prf;// 교수 리스트
	private List<Student> std; // 학생 리스트
	private List<Subject> sub; // 강의 리스트
	private List<Department> dep; // 학과 리스트
	
	public School() {
		this.prf = new ArrayList<Professor>();
		this.std = new ArrayList<Student>();
		this.sub = new ArrayList<Subject>();
		this.dep = new ArrayList<Department>();
	}
	
	//school에 있는 교수 학과 업데이트 : 이승주
	public void updatePfAll(String name, String updateName){
		if(prf == null) {
			return;
		}
		int count = (int)prf.stream().filter(p->p.getPDep().contains(name)).count();
		if(count == 0) {
			return;
		}
		prf.stream().filter(p->p.getPDep().contains(name))
		.forEach(p->p.setPDep(updateName));
		return;
	}
	//school에 있는 학생 학과 업데이트 : 
	public void updateStdAll(String name, String updateName){
		if(std == null) {
			return;
		}
		int count = (int)std.stream().filter(s->s.getSDep().contains(name)).count();
		if(count == 0) {
			return;
		}
		std.stream().filter(s->s.getSDep().contains(name))
		.forEach(s->s.setSDep(updateName));
		return;
	}

}
