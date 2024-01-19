package university.service;

// 프린트 구현클래스
public class PrintServiceImp implements PrintService {

	@Override
	public void printMainMenu() {
		System.out.println("1.강의정보");
		System.out.println("2.학과정보");
		System.out.println("3.교수정보");
		System.out.println("4.학생정보");
		System.out.println("5.수강관리");
		System.out.println("6.프로그램종료");
		System.out.println("============");
		System.out.println("메뉴입력 : ");
	}

	@Override
	public void printPrintMenu() {
		
	}

	

}
