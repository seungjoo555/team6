package university;

import java.io.Serializable;
import java.util.Objects;

// 학생 리스트
public class Student implements Serializable {
	private static final long serialVersionUID = -7837347182174632918L;
	//학생 이름
	/*학생 클래스 // Student
- 이름, 학년, 과, 등등..	(정보)
- 학번 (중복x)
*/
	private String sName; //학생 이름 
	private int sGrade; //학생 학년
	private String sDep; //학생 학과
	private String sNum; //학생 번호 - 중복 X
	
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
		return "학생명 :" + sName + "\n학년 :" + sGrade + "\n학과 :" + sDep + "\n번호" + sNum;
	}
	public Student(String sName, int sGrade, String sDep, String sNum) {
		super();
		this.sName = sName;
		this.sGrade = sGrade;
		this.sDep = sDep;
		this.sNum = sNum;
	}

}
