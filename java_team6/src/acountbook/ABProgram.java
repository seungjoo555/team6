package acountbook;

import java.util.InputMismatchException;
import java.util.Scanner;

import acountbook.service.ABService;
import acountbook.service.ABServiceImp;
import acountbook.service.PrintService;
import acountbook.service.PrintServiceImp;
import program.AB_Program;


public class ABProgram implements AB_Program{

	private final int EXIT = 4;
	private final int INCOME_EXIT = 3;
	private final int SPENDING_EXIT = 3;
	private final int PRINT_EXIT = 3;
	
	private Scanner scan = new Scanner(System.in);
	private AcountBook ab = new AcountBook(null);
	
	private PrintService printService= new PrintServiceImp();
	//private ABService acountBookService = new ABServiceImp();
	//private FileService fileService = new FileServiceImp();
	
	
	@Override
	public void run() {
		int menu = 0;
		String fileName = "src/seungjoo/ABList.txt";
		//불러오기
		do {
			try {
				printMenu();
				//메뉴 선택
				menu = scan.nextInt();
				runMenu(menu);
			}catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				//메뉴를 잘못 입력하면 입력 버퍼에 잘못 입력된 메뉴가 남아있어서 비워줘야 함.
				//비워주지 않으면 무한 루프 발생
				scan.nextLine();
			}
		}while(menu != EXIT);
		//저장하기
	}

	@Override
	public void printMenu() {
		printService.printMainMenu();
	}

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1:
			//수입 관리
			incomeManager();
			break;
		case 2:
			//지출 관리
			spendingManager();
			break;
		case 3:
			// 수입/지출 조회
			printManager();
			break;
		case 4:
			System.out.println("프로그램 종료");
			break;
		default:
			throw new InputMismatchException();
		}
	}

	private void printManager() {
		int menu = 0;
		do {
			//메뉴
			printService.printPrintMenu();
			//메뉴선택
			menu = scan.nextInt();
			//실행
			runPrintMenu(menu);
		}while(menu != PRINT_EXIT);
	}
	
	private void runPrintMenu(int menu) {
		switch(menu) {
		case 1:
			//전체 조회
			ab.printAll();
			break;
		case 2:
			//연도별 조회
			break;
		case 3:
			//월별 조회
			break;
		default:
			throw new InputMismatchException();
		}
	}

	private void spendingManager() {
		int menu = 0;
		do {
			//메뉴
			printService.printSpendingMenu();
			//메뉴선택
			menu = scan.nextInt();
			//실행
			runSpendingMenu(menu);
		}while(menu != SPENDING_EXIT);
	}
	
	private void runSpendingMenu(int menu) {
		switch(menu) {
		case 1:
			//지출 추가
			break;
		case 2:
			//지출 수정
			break;
		case 3:
			//지출 삭제
			break;
		default:
			throw new InputMismatchException();
		}
	}

	private void incomeManager() {
		int menu = 0;
		do {
			//메뉴
			printService.printIncomeMenu();
			//메뉴선택
			menu = scan.nextInt();
			//실행
			runIncomeMenu(menu);
		}while(menu != INCOME_EXIT);
	}

	private void runIncomeMenu(int menu) {
		switch(menu) {
		case 1:
			//수입 추가
			ab.addIncome();
			break;
		case 2:
			//수입 수정
			break;
		case 3:
			//수입 삭제
			break;
		default:
			throw new InputMismatchException();
		}
	}

}
