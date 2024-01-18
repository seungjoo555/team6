package university.service;

import java.util.List;

import acountbook.Item;
import university.School;
import university.Subject;

// 서비스 인터페이스
public interface UniService {
	
		//전체 조회 : 이승주
		void printAll(List<Item> list);
		//월별 조회 : 이승주
		void printMonth(List<Item> list);
		//날짜별 조회 : 이승주
		void printDay(List<Item> list);
		//수입 품목을 추가하는 메서드 : 이철범
		List<Item> add(List<Item> list);
		//지출 품목을 수정하는 메서드 : 이철범
		List<Item> updateSpending(List<Item> list, int index);
		// 수입 삭제 : 임병훈
		List<Item> delete(List<Item> list, int index);
		// 수입 수정 : 임병훈
		List<Item> update(List<Item> list, int index);
		// 원하는 내역 index값 찾기 : 임병훈
		int location(List<Item> list);

		// 교수 추가 : 정경호
		boolean Professor(List<Item> list);
		// 교수 삭제 : 정경호
		boolean removeProfessor(List<Item> list);
		// 교수 수정 : 정경호
		boolean updateProfessor(List<Item> list);//=
		
		// 학생 추가 : 정경호
		boolean Student(List<Item> list);
		// 학생 삭제 : 정경호
		boolean removeStudent(List<Item> list);
		// 학생 수정 : 정경호
		boolean updateStudent(List<Item> list);//=
		
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
