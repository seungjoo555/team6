package university.service;

import java.util.List;

import acountbook.Item;
import university.School;
import university.Subject;

// 서비스 인터페이스
public interface UniService {
		// 강의 추가 : 정경호
		boolean addSubject(List<Subject> list);
		// 강의 삭제 : 정경호
		boolean removeSubject(List<Subject> list);
		// 강의 수정 : 정경호
		boolean updateSubject(List<Subject> list);//=
		
		// 학과 추가 : 정경호
		boolean Department(List<Item> list);
		// 학과 삭제 : 정경호
		boolean removeDepartment(List<Item> list);
		// 학과 수정 : 정경호
		boolean updateDepartment(List<Item> list);//=
		

}
