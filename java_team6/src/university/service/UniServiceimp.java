package university.service;

import java.util.List;
import java.util.Scanner;

import acountbook.Item;
import university.School;
import university.Subject;

// 서비스 구현클래스
public class UniServiceimp implements UniService {
	private Scanner sc = new Scanner(System.in);

	@Override
	public void printAll(List<Item> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printMonth(List<Item> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printDay(List<Item> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> add(List<Item> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> updateSpending(List<Item> list, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> delete(List<Item> list, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> update(List<Item> list, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int location(List<Item> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean Professor(List<Item> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeProfessor(List<Item> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProfessor(List<Item> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Student(List<Item> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeStudent(List<Item> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStudent(List<Item> list) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean Department(List<Item> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeDepartment(List<Item> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDepartment(List<Item> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override //강의 추가 메서드 : 정경호
	public boolean addSubject(List<Subject> list) {
		System.out.println("=====강의 추가=====");
		System.out.print("추가할 강의명 :" );
		sc.nextLine();
		String sub = sc.nextLine();
		System.out.println("강의할 교수명 :");
		String pName = sc.nextLine();
		Subject sj = new Subject(sub, pName, null);
		sj.addSubject(sub,pName);
		if(list.contains(sj)) {
			list.add(sj);
			System.out.println("강의가 추가 되었습니다.");
			return true;
		}else {
			System.out.println("이미 등록된 강의 입니다.");
			return false;
		}
	}
	@Override //강의 삭제 메서드 : 정경호
	public boolean removeSubject(List<Subject> list) {
		System.out.println("=====강의 삭제=====");
		if(list == null || list.isEmpty()) {
			System.out.println("삭제할 강의가 없습니다.");
			return false;
		}
		System.out.print("삭제할 강의 입력 : ");
		sc.nextLine();
		String sub =sc.nextLine();
		Subject sj = new Subject(sub, null, null);
		sj.removeSubject(sub);
		
		if(list.contains(sj)) {
			list.remove(sj);
			System.out.println("강의가 삭제 되었습니다.");
			return true;
		} else {
			System.out.println("삭제할 강의가 없습니다.");
			return false;
		}
			
	}
		
	
	
	@Override //강의 수정 메서드 : 정경호
	public boolean updateSubject(List<Subject> list) {
		System.out.println("=====강의 수정=====");
		if(list == null || list.isEmpty()) {
			System.out.println("수정할 강의가 없습니다.");
			return false;
		}
		System.out.print("수정할 강의명 :" );
		sc.nextLine();
		String oldSub = sc.nextLine();
		System.out.println("수정할 교수명 :");
		String oldPName = sc.nextLine();
		Subject oldSj = new Subject(oldSub, oldPName, null);
		int index = list.indexOf(oldSj);
		
		if(index != -1) {
			System.out.println("새로운 강의명 :");
			sc.nextLine();
			String newSub = sc.nextLine();
			System.out.println("새로운 교수명 :");
			String newPName = sc.nextLine();
			Subject newSj = new Subject(newSub, newPName,null);
			list.remove(index);
			list.add(newSj);
			System.out.println("수정이 완료 되었습니다.");
			return true;
		}else {
			System.out.println("수정을 실패했습니다.");
			return false;
		}	
	}
}
