package university.service;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import university.Student;
import university.Professor;
import university.Subject;

// 서비스 구현클래스
public class UniServiceImp implements UniService {
	private Scanner sc = new Scanner(System.in);



	
	@Override //강의 추가 메서드 : 정경호
	public boolean addSubject(List<Subject> addList) {
		System.out.println("=====강의 추가=====");
		System.out.print("추가할 교수번호 : ");
		String pNum = sc.nextLine();
		System.out.print("추가할 강의명 :" );
		String sub = sc.nextLine();
		System.out.print("강의할 교수명 :");
		String pName = sc.nextLine();
		Subject sj = new Subject(sub, pName, pNum);
		int index = addList.indexOf(sj);
		if(index == -1) {
			addList.add(sj);
			System.out.println("강의가 추가 되었습니다.");
			return true;
		}else {
			System.out.println("중복된 강의 입니다.");
		return false;
		}
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
		if(list.size() == 0) {
			System.out.println("등록된 학생이 없습니다.");
			return list;
		}
		System.out.println(list);
		System.out.print("학번 : ");
		String sNum = scan.next();
		
		Student std = new Student(sNum);
		int index = list.indexOf(std);
		
		if(index != -1) {
			System.out.print("수정할 이름 : ");
			String sName = scan.next();
			System.out.print("수정할 학년 : ");
			int sGrade = scan.nextInt();
			System.out.print("수정할 학과 : ");
			String sDep = scan.next();
			
			Student newStd = new Student(sName, sGrade, sDep, sNum);
			
			list.remove(index);
			
			list.add(newStd);
			System.out.println("학생을 수정했습니다.");
		}else {
			System.out.println("수정할 학생이 없습니다.");
		}
		return list;
	}
	//학생 삭제 메서드 : 이철범
	@Override
	public List<Student> deleteStudent(List<Student> list) {
		if(list.size() == 0) {
			System.out.println("등록된 학생이 없습니다.");
			return list;
		}
		System.out.println(list);
		System.out.print("삭제할 학번 : ");
		String sNum = scan.next();
		
		Student std = new Student(sNum);
		int index = list.indexOf(std);
		
		if(index != -1) {
			list.remove(index);
			System.out.println("학생을 삭제했습니다.");
			return list;
		} else {
			System.out.println("삭제할 학생이 없습니다.");
			return list;
		}
	}
	
	@Override
	// 교수 정보 추가하는 메서드 : 임병훈
	public List<Professor> addProfessor(List<Professor> list) {
		
		 
		
	}
	@Override //강의 삭제 메서드 : 정경호
	public boolean removeSubject(List<Subject> removelist) {
		System.out.println("=====강의 삭제=====");
		try {	
		if(removelist == null || removelist.isEmpty()) {
			System.out.println("삭제할 강의가 없습니다.");
			return false;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print("삭제할 교수번호");
		String pNum = sc.nextLine();
		System.out.print("삭제할 강의명: ");
		String sub =sc.nextLine();
		System.out.print("삭제할 교수명 :");
		String pName = sc.next();
		Subject sj = new Subject(sub, pName, pNum);
		sj.removeSubject();
		
		if(removelist.contains(sj)) {
			removelist.remove(sj);
			System.out.println("강의가 삭제 되었습니다.");
			return true;
		} else {
			System.out.println("삭제할 강의가 없습니다.");
			return false;
		}		
	}
	@Override //강의 수정 메서드 : 정경호
	public boolean updateSubject(List<Subject> uplist) {
		int index=0;
		System.out.println("=====강의 수정=====");
		if(uplist == null || uplist.isEmpty()) {
			System.out.println("수정할 강의가 없습니다.");
			return false;
		}
		
		if(index !=-1) {
		System.out.print("수정할 교수번호 : ");
		String oldPnum = sc.nextLine();
		System.out.print("수정할 강의명 :" );
		String oldSub = sc.nextLine();
		System.out.print("수정할 교수명 :");
		String oldPName = sc.nextLine();
		Subject oldSj = new Subject(oldSub, oldPName, oldPnum);
		 index = uplist.indexOf(oldSj);
		
		}
		
		if(index != -1) {
			System.out.println("---------------");
			System.out.print("새로운 교수번호 :");
			String newPnum = sc.nextLine();
			System.out.print("새로운 강의명 :");
			String newSub = sc.nextLine();
			System.out.print("새로운 교수명 :");
			String newPName = sc.nextLine();
			
			Subject newSj = new Subject(newSub, newPName, newPnum);
			uplist.remove(index);
			uplist.add(newSj);
			newSj.toString();
			System.out.println("수정이 완료 되었습니다.");
			return true;
		}else {
			System.out.println("수정을 실패했습니다.");
			return false;
		}	
	}
	@Override
	public List<Professor> addProfessor(List<Professor> list) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Professor> updateProfessor(List<Professor> list) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Professor> deleteProfessor(List<Professor> list) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int location(List<Professor> list, String num) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean checkSub(List<Subject> sb) {
		if(sb == null || sb.isEmpty()) {
			System.out.println("강의가 없습니다.");
		return false;
		}
		for(int i=0;i<sb.size();i++) {
			sb.get(i);
			System.out.println(sb.get(i).toString());
		}
		return true;		
	}
}
