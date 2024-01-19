package university.service;

import java.util.List;

import university.Professor;
import university.School;

// 서비스 인터페이스
public interface UniService {

	//교수 등록
	List<Professor> addProfessor(List<Professor> list);

	List<Professor> updateProfessor(List<Professor> list);

	List<Professor> deleteProfessor(List<Professor> list);

}
