package university.service;

import university.School;

// 서비스 인터페이스
public interface UniService {

	//교수 등록
	School addProfessor(School school);
	//학과 등록
	School addDepartment(School school);
	//학과 수정
	School updateDepartment(School school);
	//학과 삭제
	School deleteDepartment(School school);
	//학과에 교수/학생 관리
	School managerDepartment(School school);

}
