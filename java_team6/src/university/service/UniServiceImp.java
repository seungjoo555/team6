package university.service;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import university.Professor;
import university.School;
=======
import java.util.List;
import java.util.Scanner;
import university.Professor;
>>>>>>> main

// 서비스 구현클래스
public class UniServiceImp implements UniService {

<<<<<<< HEAD
	private Scanner sc = new Scanner(System.in);
	
	/**
	 * @param school 리스트를 관리하는 학교 클래스
	 * @return school 교수 등록후 변경된 학교 클래스
	 */
	@Override
	public School addProfessor(School school) {
		List<Professor> list = school.getProfessorList();
		if(list == null) {
			list = new ArrayList<Professor>();
		}
		
		System.out.print("교수 이름 : ");
		String name = sc.next();
		System.out.print("교수 전공 : ");
		String major = sc.next();
		System.out.print("교수 학력 : ");
		String education = sc.next();
		System.out.print("임용 날짜(형식:yyyy-MM-dd) : ");
		String date = sc.next();
		
		Professor pf = new Professor(name, major, education, date);
=======
	private Scanner scan = new Scanner(System.in);
	
	@Override
	// 교수 정보 추가하는 메서드 : 임병훈
	public List<Professor> addProfessor(List<Professor> list) {
>>>>>>> main
		
		System.out.print("교수 번호 : ");
		String pNum = scan.next();
		System.out.print("교수 학과 : ");
		String pDep = scan.next();
		System.out.print("강의 이름 : ");
		String pSub = scan.next();
		System.out.print("교수 이름 : ");
		String pName = scan.next();
		
<<<<<<< HEAD
		if(list.size() > 1) {
			int index = list.size();
			list.get(index).setNum(list.get(index - 1).getNum() + 1);
		}else {
			list.get(0).setNum(1);
		}
		
		school.setProfessorList(list);
		return school;
=======
		// 입력된 정보로 객체 생성
		Professor pf = new Professor(pName, pSub, pDep, pNum);
		
		// 리스트에 해당 객체와 동일한 객체가 있으면 오류 출력 및 메서드 종료
		int index = list.indexOf(pf);
		
		if(index != -1) {
			System.out.println("이미 등록된 교수 번호입니다.");
			return list;
		}
		
		// 리스트에 객체 추가
		list.add(pf);
		
		return list;
>>>>>>> main
	}

	@Override
	// 리스트에 등록된 교수 정보 수정하는 메서드 : 임병훈
	public List<Professor> updateProfessor(List<Professor> list) {
		
		System.out.println(list);
		System.out.print("수정할 교수 번호 : ");
		String pNum = scan.next();

		// location함수로 원하는 교수의 index값 찾기
		int index = location(list, pNum);
		
		// 해당 index가 -1이면 종료
		if(index == -1) {
			return list;
		}
		
		// index가 -1이 아니면 수정할 정보도 입력
		System.out.print("교수 학과 : ");
		String pDep = scan.next();
		System.out.print("강의 이름 : ");
		String pSub = scan.next();
		System.out.print("교수 이름 : ");
		String pName = scan.next();
		
		Professor pf = new Professor(pName, pSub, pDep, pNum);
		
		// index 자리에 pf로 변경
		list.set(index, pf);
		System.out.println("수정 완료");
		
		return list;
	}

	@Override
	// 리스트에 등록된 교수 정보 삭제하는 메서드 : 임병훈
	public List<Professor> deleteProfessor(List<Professor> list) {
		
		System.out.println("삭제 전" + list);
		System.out.print("수정할 교수 번호 : ");
		String pNum = scan.next();

		// location함수로 원하는 교수의 index값 찾기
		int index = location(list, pNum);
		
		// 해당 index가 -1이면 종료
		if(index == -1) {
			return list;
		}
		
		list.remove(index);
		System.out.println("삭제 완료");
		
		return list;
	}
	
	@Override
	// 원하는 리스트에서 원하는 값이 존재하는지, 존재하면 몇번째 index에 존재하는지 찾는 메서드 : 임병훈
	public int location(List<Professor> list,String num) {
		int index = -1;

		// 리스트가 없을 때 
		if(list.size() == 0) {
			System.out.println("등록된 정보가 없습니다.");
			return index;
		}
		
		Professor pf = new Professor(num);
		index = list.indexOf(pf);
		System.out.println(index);
		
		// 찾는 번호가 없는 경우
		if(index == -1) {
			System.out.println("해당 교수번호가 없습니다.");
			return index;
		}
		
		return index;
	}
}