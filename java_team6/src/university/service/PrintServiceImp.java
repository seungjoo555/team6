package university.service;

// 프린트 구현클래스
public class PrintServiceImp implements PrintService {

	@Override
	public void printMainMenu() {
		System.out.println("--------메뉴--------");
		System.out.println("1. 교수 관리");
		System.out.println("2. 학생 관리");
		System.out.println("3. 과 관리");
		System.out.println("4. 강의 관리");
		System.out.println("5. 수강 관리");
		System.out.println("6. 조회");
		System.out.println("7. 프로그램 종료");
		System.out.println("-------------------");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void printProMenu() {
		System.out.println("------교수 관리------");
		System.out.println("1. 교수 추가");
		System.out.println("2. 교수 수정");
		System.out.println("3. 교수 삭제");
		System.out.println("4. 이전으로");
		System.out.println("-------------------");
		System.out.print("메뉴 선택 : ");
	}

}
