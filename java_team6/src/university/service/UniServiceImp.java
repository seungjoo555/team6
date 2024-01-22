package university.service;

import java.util.List;
import java.util.Scanner;

import university.Student;

// 서비스 구현클래스
public class UniServiceImp implements UniService {
	private Scanner scan = new Scanner(System.in);
	
	//학생 추가 메서드 : 이철범
	@Override
	public List<Student> addStudent(List<Student> list) {
		System.out.print("이름 : ");
		String sName = scan.next();
		System.out.print("학년 : ");
		int sGrade = scan.nextInt();
		System.out.print("학과 : ");
		String sDep = scan.next();
		System.out.print("학번 : ");
		String sNum = scan.next();
		
		Student std = new Student(sName, sGrade, sDep, sNum);
		
		int index = list.indexOf(std);
		
		if(index != -1) {
			System.out.println("등록된 학생입니다.");
			return list;
		}else {
			list.add(std);
			System.out.println("학생을 등록했습니다.");
		}
		return list;
		
	}
	//학생 수정 메서드 : 이철범
	@Override
	public List<Student> updateStudent(List<Student> list) {
		if(list.size() <= 0) {
			System.out.println("등록된 학생이 없습니다.");
			return list;
		}
		System.out.println(list);
		System.out.print("학번 : ");
		String sNum = scan.next();
		
		int index = location(list, sNum);
		
		if(index != -1) {
			System.out.print("수정할 이름 : ");
			String sName = scan.next();
			System.out.print("수정할 학년 : ");
			int sGrade = scan.nextInt();
			System.out.print("수정할 학과 : ");
			String sDep = scan.next();
			
			Student std = new Student(sName, sGrade, sDep, sNum);
			
			list.remove(index);
			
			list.add(std);
			System.out.println("학생을 수정했습니다.");
		}else {
			System.out.println("수정할 학생이 없습니다.");
		}
		return list;
	}
	private int location(List<Student> list, String sNum) {
		int index = -1;
		
		Student std = new Student(sNum);
		index = list.indexOf(std);
		
		return index;
	}
	//학생 삭제 메서드 : 이철범
	@Override
	public List<Student> deleteStudent(List<Student> list) {
		if(list.size() <= 0) {
			System.out.println("등록된 학생이 없습니다.");
			return list;
		}
		System.out.println(list);
		System.out.print("삭제할 학번 : ");
		String sNum = scan.next();
		
		int index = location(list, sNum);
		
		if(index != -1) {
			list.remove(index);
			System.out.println("학생을 삭제했습니다.");
			return list;
		} else {
			System.out.println("삭제할 학생이 없습니다.");
			return list;
		}
	}
	
}
