package seungjoo.service;

public class PrintServiceImp implements PrintService {

	@Override
	public void printMainMenu() {
		System.out.println("--------메뉴--------");
		System.out.println("1. 단어 관리");
		System.out.println("2. 뜻 관리");
		System.out.println("3. 조회");
		System.out.println("4. 게임");
		System.out.println("5. 종료");
		System.out.println("-------------------");
		System.out.print("메뉴 선택 : ");
	}

}
