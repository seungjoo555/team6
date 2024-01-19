package university.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import university.School;
import university.Student;

// 서비스 구현클래스
public class UniServiceImp implements UniService {
	private Scanner scan = new Scanner(System.in);
	@Override
	public School addProfessor(School school) {
		// TODO Auto-generated method stub
		
		
		
		return school;
	}
	//학생 추가 메서드
	@Override
	public boolean addStudent(School school) {
		System.out.println("이름 : ");
		String sName = scan.next();
		System.out.println("학년 : ");
		int sGrade = scan.nextInt();
		System.out.println("학과 : ");
		String sDep = scan.next();
		System.out.println("학번 : ");
		String sNum = scan.next();
		
		Student std = new Student(sName, sGrade, sDep, sNum);
		if(school.addStudnet(std)) {
			System.out.println("학생을 추가했습니다.");
		}else {
			System.out.println("중복된 학번입니다.");
		}
		return true;
		
	}
	//학생 수정 메서드
	@Override
	public boolean updateStudent(School school) {
		System.out.println("학번 : ");
		String sNum = scan.next();
		
		System.out.println("수정할 이름 : ");
		String sName = scan.next();
		System.out.println("수정할 학년 : ");
		int sGrade = scan.nextInt();
		System.out.println("수정할 학과 : ");
		String sDep = scan.next();
		
		Student std = new Student(sName, sGrade, sDep, sNum);
		
		return false;
			
	}
	
}
