package university;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.Data;

// 학생 리스트
@Data
public class Student implements Serializable {
	private static final long serialVersionUID = -7837347182174632918L;
	//학생 이름
	/*학생 클래스 // Student
	- 이름, 학년, 과, 등등..	(정보)
	- 학번 (중복x)
	*/
	private String sName;	// 학생 이름 
	private int sGrade; 	// 학생 학년
	private String sDep; 	// 학생 학과
	private String sNum; 	// 학생 번호 - 중복 X
	private String subName; // 강의명
	private Map<String,Integer> map = new HashMap<String,Integer>();
	
	@Override
	public int hashCode() {
		return Objects.hash(sNum);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(sNum, other.sNum);
	}
	@Override
	public String toString() {
		return "\n학생 이름 : " + sName + "\n학년 : " + sGrade + "\n학과 : " + sDep + "\n학번 : " + sNum + "\n성적=" + map + "\n수강중인 강의 : " + subName;
	}

	
	public Student(String sName, int sGrade, String sDep, String sNum) {
		this.sName = sName;
		this.sGrade = sGrade;
		this.sDep = sDep;
		this.sNum = sNum;
	}
	
	//수정,삭제하기 위해 만든 메서드
	public Student(String sNum) {
		this.sNum = sNum;
	}

	public Student(String subName, String sName, int sGrade, String sDep, String sNum) {
		this.subName = subName;
		this.sName = sName;
		this.sGrade = sGrade;
		this.sDep = sDep;
		this.sNum = sNum;
	}

}