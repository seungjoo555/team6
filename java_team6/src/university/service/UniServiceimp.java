package university.service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import acountbook.Item;
import university.Subject;

// 서비스 구현클래스
public class UniServiceimp implements UniService {
	private Scanner sc = new Scanner(System.in);




	@Override
	public boolean Department(List<Item> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeDepartment(List<Item> list) {
		return false;
	}

	@Override
	public boolean updateDepartment(List<Item> list) {
		return false;
	}

	@Override //강의 추가 메서드 : 정경호
	public boolean addSubject(List<Subject> addlist) {
		System.out.println("=====강의 추가=====");
		System.out.print("추가할 강의명 :" );
		String sub = sc.nextLine();
		System.out.print("강의할 교수명 :");
		String pName = sc.nextLine();
		Subject sj = new Subject(sub, pName, null);
		sj.addSubject(sub,pName);
	
		if(sj!=null) {
			addlist.add(sj);
			System.out.println("강의가 추가 되었습니다.");
			return true;
		}else {
			System.out.println("이미 등록된 강의 입니다.");
			return false;
		}
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
		System.out.print("삭제할 강의명: ");
		String sub =sc.nextLine();
		System.out.print("삭제할 교수명 :");
		String pName = sc.next();
		Subject sj = new Subject(sub, pName,null);
		sj.removeSubject();
		
		if(removelist.contains(sj)) {
			removelist.remove(sj);
			sj.toString();
			System.out.println("강의가 삭제 되었습니다.");
			return true;
		} else {
			System.out.println("삭제할 강의가 없습니다.");
			return false;
		}
			
	}
		
	
	
	@Override //강의 수정 메서드 : 정경호
	public boolean updateSubject(List<Subject> uplist) {
		System.out.println("=====강의 수정=====");
		if(uplist == null || uplist.isEmpty()) {
			System.out.println("수정할 강의가 없습니다.");
			return false;
		}
		System.out.print("수정할 강의명 :" );
		String oldSub = sc.nextLine();
		System.out.print("수정할 교수명 :");
		String oldPName = sc.nextLine();
		Subject oldSj = new Subject(oldSub, oldPName, null);
		int index = uplist.indexOf(oldSj);
		
		if(index != -1) {
			System.out.print("새로운 강의명 :");
			String newSub = sc.nextLine();
			System.out.print("새로운 교수명 :");
			String newPName = sc.nextLine();
			Subject newSj = new Subject(newSub, newPName,null);
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
}
