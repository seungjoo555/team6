package university;

import java.io.Serializable;
import java.util.Objects;

// 교수 리스트
public class Professor implements Serializable{
	private static final long serialVersionUID = 8377957422955045309L;
	private String pName; // 교수 이름
	private String pSubject; // 교수 강의 과목
	private String pDep; //교수 학과
	private String pNum; //교수 번호 - 중복 X
	
	@Override
	public int hashCode() {
		return Objects.hash(pDep, pNum, pSubject);
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
		return Objects.equals(pDep, other.pDep) && pNum == other.pNum && Objects.equals(pSubject, other.pSubject);
	}
	@Override
	public String toString() {
		return "교수이름=" + pName + "\n학과=" +pDep  + "\n강의명=" + pSubject + "/n번호=" + pNum;
	}
	
	public Professor(String pName, String pSubject, String pDep, String pNum) {
		super();
		this.pName = pName;
		this.pSubject = pSubject;
		this.pDep = pDep;
		this.pNum = pNum;
	}
}

