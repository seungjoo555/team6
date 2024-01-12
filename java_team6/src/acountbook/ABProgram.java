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
	private final int UPDATE_EXIT = 6;

	private Scanner scan = new Scanner(System.in);
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
			addSpending();
		//	ab.addIncome();
			break;
		case 2:
			//지출 수정
			updateSpending1();
			break;
		case 3:
			//지출 삭제
			break;
		case 4:
			//이전으로
			break;
		default:
			throw new InputMismatchException();
		}
	}
	//지출수정 메서드 : 이철범
	private void updateSpending1() {
		
		
		for (int i = 0; i < ab.getList().size(); i++) {
	        Item item = ab.getList().get(i);
	        System.out.println(item.toString(i));
	    }
		
		int index=-1;
		//수정할 항목 받아오기
		try {
			System.out.print("어떤 항목을 수정하시겠습니까? : "); 
			index = scan.nextInt()-1;
		}catch (InputMismatchException e){
			System.out.println("잘못된 메뉴입니다.");
			scan.nextLine();
		}
		int menu = 0;
		do {
			printUpdateMenu();
			try {
				menu = scan.nextInt();
				runUpdateMenu(menu, index);
			} catch (InputMismatchException e){
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		} while (menu != UPDATE_EXIT);
	}


	private void runUpdateMenu(int menu, int index) {
		switch(menu) {
		case 1 :	//년
			runUpateInYear(index);
			break;
		case 2 :	//월수정
			runUpateInMonth(index);
			break;
		case 3 :	//일수정
			runUpateInDay(index);
			break;
		case 4 :	//금액 수정
			runUpateInMoney(index);
			break;
		case 5 :	//품목수정				
			runUpateInTitle(index);
			break;
		case 6 : //뒤로가기
			System.out.println("뒤로가기");
			break;
		default : 
			throw new InputMismatchException();
	}
	System.out.println("수정을 완료했습니다.");
		
	}

	
	//년 수정
	private void runUpateInYear(int index) {
		int year =0;
		try {		
			System.out.print("년(yyyy) : ");
			year = scan.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}		
		ab.getList().get(index).setYear(year);
		
	}
	
	//월 수정
	private void runUpateInMonth(int index) {
		int month =0;
		try {		
			System.out.print("월(mm) : ");
			month = scan.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}		
		ab.getList().get(index).setMonth(month);
		
	}
	
	//일 수정
	private void runUpateInDay(int index) {
		int day =0;
		try {		
			System.out.print("일(dd) : ");
			day = scan.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}		
		ab.getList().get(index).setDay(day);
		
	}
	
	//금액 수정
	private void runUpateInMoney(int index) {
		int money =0;
		try {		
			System.out.print("금액(원) : ");
			money = scan.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}		
		ab.getList().get(index).setMoney(money);
	}
	
	//품목 수정
	private void runUpateInTitle(int index) {
		String title = null;
		try {		
			System.out.print("품목 : ");
			scan.nextLine();
			title = scan.nextLine();
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다.");
			scan.nextLine();
		}		
		ab.getList().get(index).setTitle(title);
		
	}


	private void printUpdateMenu() {
		printService.printUpdateMenu();
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
		int index = acountBookService.incomeLocation(ab.getList());
		acountBookService.deleteIncome(ab.getList(), index);
	}

	private void updateIncome() {
		int index = acountBookService.incomeLocation(ab.getList());
		acountBookService.updateIncome(ab.getList(), index);
	}

	//수입추가 메서드 : 이철범
	private void addIncome() {
		acountBookService.add(ab.getList());
	}
	
	private void addSpending() {
		acountBookService.addSpending(ab.getList());
			System.out.println("지출등록");
	}
}