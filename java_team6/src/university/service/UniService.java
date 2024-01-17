package university.service;

import java.util.List;

import university.Professor;

// 서비스 인터페이스
public interface UniService {
	//교수 추가 메서드
	void addPro(List<Professor> proList);
	//교수 수정 메서드
	void updatePro(List<Professor> proList);
		
	
}
