package university;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

// 강의 리스트
public class Subject implements Serializable {
	private static final long serialVersionUID = -8958668600735079489L;
/*강의 클래스 // Subject
- 강의 이름
- 강의교수 (중복x)
- 학생(리스트) - 학번
- 정원 제한 (선택)
---------------
수강관리
- 강의 이름, 교수 이름으로 선택
- 학생정보로 등록
- 정원이 가득차면 신청 불가 (선택)
*/
 
 private String subName; //강의 이름 - 중복 X
 private String pName; //강의 교수명 - 중복 X
 
@Override
public int hashCode() {
	return Objects.hash(subName);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Subject other = (Subject) obj;
	return Objects.equals(subName, other.subName);
}
public Subject(String subNam, String pName) {
	this.subName = subNam;
	this.pName = pName;
}
@Override
public String toString() {
	return "-------"+"\n강의명 :" + subName + "\n교수명 :" + pName ;
}
public void removeSubject() {
	
	
}
public void addSubject(String sub, String pName) {
	
}

}
