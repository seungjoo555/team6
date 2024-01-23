package university.service;

import java.util.List;

import university.Professor;
import university.Subject;

// 서비스 인터페이스
public interface UniService {

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
}