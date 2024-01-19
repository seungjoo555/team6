package university.service;

import java.util.List;
import java.util.Scanner;
import university.Professor;

// 서비스 구현클래스
public class UniServiceImp implements UniService {

	private Scanner scan = new Scanner(System.in);
	
	@Override
	public List<Professor> addProfessor(List<Professor> list) {
		
		System.out.print("교수 번호 : ");
		String pNum = scan.next();
		System.out.print("교수 학과 : ");
		String pDep = scan.next();
		System.out.print("강의 이름 : ");
		String pSub = scan.next();
		System.out.print("교수 이름 : ");
		String pName = scan.next();
		
		Professor pf = new Professor(pName, pSub, pDep, pNum);
		if(list.contains(pf) == true) {
			System.out.println("오류");
			return list;
		}
		list.add(pf);
		
		return list;
	}

	@Override
	public List<Professor> updateProfessor(List<Professor> list) {
		// 리스트가 없을 때
		if(list.size() == 0) {
			return list;
		}
		
		System.out.println(list);
		System.out.print("수정할 교수 번호 : ");
		String pNum = scan.next();
		
		// 입력받은 교수의 index찾기
		int index = -1;
		list.indexOf(pNum);
		
		// 찾은 index가 -1이면 리턴
		if(index == -1) {
			System.out.println("해당 교수가 없습니다.");
			return list;
		}
		
		System.out.print("교수 학과 : ");
		String pDep = scan.next();
		System.out.print("강의 이름 : ");
		String pSub = scan.next();
		System.out.print("교수 이름 : ");
		String pName = scan.next();
		
		Professor pf = new Professor(pName, pSub, pDep, pNum);
		
		// index 자리에 pf로 변경
		list.set(index, pf);
		return list;
	}

	@Override
	public List<Professor> deleteProfessor(List<Professor> list) {
		
		return list;
	}
	
	// 원하는 리스트에서 원하는 값이 존재하는지, 존재하면 몇번째 index에 존재하는지
	public int location(List<Professor> list,String num) {
		int index = -1;

		// 리스트가 없을 때 
		if(list.size() == 0) {
			return index;
		}
		
		// 리스트에 원하는 값이 존재하지 않을 때
		if(!list.contains(num)) {
			return index;
		}
		
		
		
		return index;
	}
}