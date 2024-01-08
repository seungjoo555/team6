package seungjoo;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import program.AB_Program;
import seungjoo.service.ABService;
import seungjoo.service.ABServiceImp;
import seungjoo.service.FileService;
import seungjoo.service.FileServiceImp;

public class ABProgram implements AB_Program{

	private final int EXIT = 0;
	
	private Scanner scan = new Scanner(System.in);
	
	List<Item> list;
	
	private PrintService printService= new PrintServiceImp();
	private ABService acountBookService = new ABServiceImp();
	private FileService fileService = new FileServiceImp();
	
	
	@Override
	public void run() {
		int menu = 0;
		String fileName = "src/word/wordList.txt";
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
		// TODO Auto-generated method stub
		
	}

}
