package university.service;

import lombok.Data;

@Data
// 프린트 구현클래스
public class PrintServiceImp implements PrintService {

	@Override
	public void printMainMenu() {
		System.out.println("============");
		System.out.println("1.강의정보");
		System.out.println("2.학과정보");
		System.out.println("3.교수정보");
		System.out.println("4.학생정보");
		System.out.println("5.수강관리");
		System.out.println("6.조회하기");
		System.out.println("7.프로그램종료");
		System.out.println("============");
		System.out.println("메뉴입력 : ");
	}

	@Override
	public void printPrintMenu() {
		
	}

	@Override
	public void subjectPrintMenu() {
		System.out.println("============");
		System.out.println("1.강의 추가");
		System.out.println("2.강의 수정");
		System.out.println("3.강의 삭제");
		System.out.println("4.이전으로");
		System.out.println("============");
		System.out.println("메뉴입력 : ");
		
	}

	@Override
	public void printManager() {
		System.out.println("============");
		System.out.println("1.강의조회");
		System.out.println("2.교수조회");
		System.out.println("3.학생조회");
		System.out.println("4.수강조회");
		System.out.println("5.전체조회");
		System.out.println("============");
		System.out.print("메뉴입력 : ");

	}

	

}
