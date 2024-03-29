package acountbook;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import acountbook.service.ABService;
import acountbook.service.ABServiceImp;
import acountbook.service.FileService;
import acountbook.service.FileServiceImp;
import acountbook.service.PrintService;
import acountbook.service.PrintServiceImp;
import program.AB_Program;

// 수입 수정,삭제
public class ABProgram implements AB_Program{

	private final int EXIT = 4;
	private final int INCOME_EXIT = 4;
	private final int SPENDING_EXIT = 4;
	private final int PRINT_EXIT = 4;


	private Scanner scan = new Scanner(System.in);
	/* 강사 피드백
	 * - 서비스에게 가계부에 있는 리스트만 계속 넘기기 때문에 가계부를 만들 필요가 없음.
	 * - ab.getList() 대신 ab를 넘기거나 가계부 대신 아이템 리스트를 관리하는게 나아 보임.*/
	private AcountBook ab = new AcountBook();

	private PrintService printService= new PrintServiceImp();
	private ABService acountBookService = new ABServiceImp();
	private FileService fileService = new FileServiceImp();

	
	
	@Override
	public void run() {
		int menu = 0;
		String fileName = "src/acountbook/ABList.txt";
		
		//불러오기
		List<Item> list = fileService.load(fileName);
		ab = new AcountBook(list);
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
		if(fileService.save(fileName, ab.getList())) {
			System.out.println("저장이 완료됐습니다.");
		}else {
			System.out.println("저장에 실패했습니다.");
		}
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
			acountBookService.printAll(ab.getList());
			break;
		case 2:
			//월별 조회
			acountBookService.printMonth(ab.getList());
			break;
		case 3:
			//날짜별 조회
			acountBookService.printDay(ab.getList());
			break;
		case 4:
			//이전으로
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
			addSpending1();
			break;
		case 2:
			//지출 수정
			updateS();
			break;
		case 3:
			//지출 삭제
			removeSpending();
			break;
		case 4:
			//이전으로
			break;
		default:
			throw new InputMismatchException();
		}
	}

	//지출수정 메서드 : 이철범
	private void updateS() {
		int index = acountBookService.location(ab.getList());
		acountBookService.updateSpending(ab.getList(), index);
	}
		
	
	//지출삭제 정경호
	private void removeSpending() {
		acountBookService.removeSpending(ab.getList());
	}
	//지출추가 정경호
	private void addSpending1() {

	    if (acountBookService.addSpending(ab.getList())) {
	        System.out.println("지출 내역이 추가되었습니다.");
	    } else {
	        System.out.println("지출 내역 추가에 실패했습니다.");

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
			addIncome();
			break;
		case 2:
			//수입 수정
			updateIncome();
			break;
		case 3:
			//수입 삭제
			deleteincome();
			break;
		case 4:
			//이전으로
			break;
		default:
			throw new InputMismatchException();
		}
	}



	private void deleteincome() {
		int index = acountBookService.location(ab.getList());
		acountBookService.delete(ab.getList(), index);
	}

	private void updateIncome() {
		int index = acountBookService.location(ab.getList());
		acountBookService.update(ab.getList(), index);
	}

	//수입추가 메서드 : 이철범
	private void addIncome() {
		acountBookService.add(ab.getList());
	}
	
}