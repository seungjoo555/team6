package acountbook.service;

public class PrintServiceImp implements PrintService {

	@Override
	public void printMainMenu() {
		System.out.println("--------메뉴--------");
		System.out.println("1. 수입 관리");
		System.out.println("2. 지출 관리");
		System.out.println("3. 가계부 조회");
		System.out.println("4. 프로그램 종료");
		System.out.println("-------------------");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void printPrintMenu() {
		System.out.println("------가계부 조회------");
		System.out.println("1. 전체 조회");
		System.out.println("2. 월별 조회");
		System.out.println("3. 날짜별 조회");
		System.out.println("4. 이전으로");
		System.out.println("-------------------");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void printSpendingMenu() {
		System.out.println("------지출 관리------");
		System.out.println("1. 지출 추가");
		System.out.println("2. 지출 수정");
		System.out.println("3. 지출 삭제");
		System.out.println("4. 이전으로");
		System.out.println("-------------------");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void printIncomeMenu() {
		System.out.println("------수입 관리------");
		System.out.println("1. 수입 추가");
		System.out.println("2. 수입 수정");
		System.out.println("3. 수입 삭제");
		System.out.println("4. 이전으로");
		System.out.println("-------------------");
		System.out.print("메뉴 선택 : ");
	}

}
