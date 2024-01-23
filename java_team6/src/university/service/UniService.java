package university.service;

import java.util.List;

import acountbook.Item;
import university.School;
import university.Student;
import university.Professor;
import university.Subject;


// 서비스 인터페이스
public interface UniService {

	//학생 등록
	List<Student> addStudent(List<Student> list);
	//학생 수정
	List<Student> updateStudent(List<Student> list);
	//학생 삭제
	List<Student> deleteStudent(List<Student> list);
	
	// 교수 등록 : 임병훈
	List<Professor> addProfessor(List<Professor> list);
	
	// 교수 수정 : 임병훈
	List<Professor> updateProfessor(List<Professor> list);
	
	// 교수 삭제 : 임병훈
	List<Professor> deleteProfessor(List<Professor> list);

	// 교수 위치 찾기 : 임병훈
	int location(List<Professor> list, String num);
	
	// 강의 추가 : 정경호
	boolean addSubject(List<Subject> list);
	// 강의 삭제 : 정경호
	boolean removeSubject(List<Subject> list);
	// 강의 수정 : 정경호
	boolean updateSubject(List<Subject> list);//=
	// 강의 조회 : 정경호
	boolean checkSub(List<Subject> sb);
}
}

