package university.service;

import java.util.List;

import acountbook.Item;
import university.School;
import university.Student;

// 서비스 인터페이스
public interface UniService {

	//학생 등록
	boolean addStudent(List<Student> list);
	//학생 수정
	boolean updateStudent(List<Student> list);
	//학생 삭제
	boolean deleteStudent(List<Student> list);
}
