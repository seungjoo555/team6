package university.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import university.School;
import university.Student;

// 서비스 구현클래스
public class UniServiceImp implements UniService {
	private Scanner scan = new Scanner(System.in);
	
	//학생 추가 메서드 : 이철범
	@Override
	public boolean addStudent(List<Student> list) {
		System.out.println("이름 : ");
		String sName = scan.next();
		System.out.println("학년 : ");
		int sGrade = scan.nextInt();
		System.out.println("학과 : ");
		String sDep = scan.next();
		System.out.println("학번 : ");
		String sNum = scan.next();
		
		Student std = new Student(sName, sGrade, sDep, sNum);
		
		if(list.contains(std) == true) {
			list.add(std);
			System.out.println("학생을 추가 했습니다.");
			return true;
		}else {
			System.out.println("등록된 학생입니다.");
			return false;
		}
		
	}
	//학생 수정 메서드 : 이철범
	@Override
	public boolean updateStudent(List<Student> list) {
		if(list == null) {
			System.out.println("등록된 학생이 없습니다.");
			return false;
		}
		
		System.out.println("이름 : ");
		String sName = scan.next();
		System.out.println("학년 : ");
		int sGrade = scan.nextInt();
		System.out.println("학과 : ");
		String sDep = scan.next();
		System.out.println("학번 : ");
		String sNum = scan.next();
		
		Student std = new Student(sName, sGrade, sDep, sNum);
		int index = list.indexOf(std);
		
		if(index != -1) {
			System.out.println("수정할 이름 : ");
			sName = scan.next();
			System.out.println("수정할 학년 : ");
			sGrade = scan.nextInt();
			System.out.println("수정할 학과 : ");
			sDep = scan.next();
			Student newStd = new Student(sName, sGrade, sDep, sNum);
			list.remove(index);
			list.add(newStd);
			System.out.println("학생을 수정했습니다.");
			return true;
		}else {
			System.out.println("수정할 학생이 없습니다.");
			return false;
		}
		
	}
	//학생 삭제 메서드 : 이철범
	@Override
	public boolean deleteStudent(List<Student> list) {
		if(list == null) {
			System.out.println("등록된 학생이 없습니다.");
			return false;
		}
		System.out.println("삭제할 이름 : ");
		String sName = scan.next();
		System.out.println("삭제할 학년 : ");
		int sGrade = scan.nextInt();
		System.out.println("삭제할 학과 : ");
		String sDep = scan.next();
		System.out.println("삭제할 학번 : ");
		String sNum = scan.next();
		
		Student std = new Student(sName, sGrade, sDep, sNum);
		
		if(list.contains(std)) {
			list.remove(std);
			System.out.println("학생을 삭제 되었습니다.");
			return true;
		} else {
			System.out.println("삭제할 학생이 없습니다.");
			return false;
		}
	}
	
}
