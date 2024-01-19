package university.service;

import java.util.List;

import acountbook.Item;
import university.School;

// 서비스 인터페이스
public interface UniService {

	//교수 등록
	School addProfessor(School school);
	//학생 등록
	boolean addStudent(School school);
	//학생 수정
	boolean updateStudent(School school);
	//학생 삭제
}
