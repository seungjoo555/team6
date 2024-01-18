package university.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import university.Professor;
import university.School;

// 서비스 구현클래스
public class UniServiceImp implements UniService {

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
		
		
		if(list.size() > 1) {
			int index = list.size();
			list.get(index).setNum(list.get(index - 1).getNum() + 1);
		}else {
			list.get(0).setNum(1);
		}
		
		school.setProfessorList(list);
		return school;
	}

}
