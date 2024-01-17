package university.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import university.Professor;
import university.School;

// 서비스 구현클래스
public class UniServiceimp implements UniService {
	private Scanner scan = new Scanner(System.in);
	private School sc = new School();
	@Override
	//교수 추가 메서드
	public void addPro(List<Professor> proList) {
		
		System.out.println("이름 : ");
		String name = scan.next();
		System.out.println("교수번호 : ");
		int proNum =  scan.nextInt();
		
		Professor pro = new Professor(name, proNum);
		
		int index = proList.indexOf(pro);
		
		if(index != -1) {
			proList.add(pro);
			System.out.println("교수를 추가 했습니다.");
			return;
		}
		System.out.println("이미 등록된 교수입니다.");
	}
	@Override
	public void updatePro(List<Professor> proList) {
		
	}
}
