package university;

import java.util.InputMismatchException;
import java.util.Scanner;

import program.AB_Program;
import university.service.PrintService;
import university.service.PrintServiceImp;
import university.service.UniService;
import university.service.UniServiceimp;

// 실행 메서드
public class UniProgram implements AB_Program{
	Scanner scan = new Scanner(System.in);
	private PrintService printService= new PrintServiceImp();
	private UniService uniService = new UniServiceimp();
	private final int EXIT = 7;
	private final int PRO_EXIT = 4;
	public void run() {
		int menu = 0;
		//불러오기
		do {
			try {
				printMenu();
				//메뉴 선택
				menu = scan.nextInt();
				runMenu(menu);
			}catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT);
	}

	private void professorManage() {
		int menu = 0;
		do {
			try {
				printProfessorMenu();
				menu = scan.nextInt();
				runProfessorMenu(menu);
			}catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != PRO_EXIT);
	}

	private void runProfessorMenu(int menu) {
		switch(menu) {
		case 1:
			//교수 추가
			addProfessor();
			break;
		case 2:
			//교수 수정
			break;
		case 3:
			//교수 삭제
			break;
		case 4:
			//이전으로
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}
		
	}

	private void addProfessor() {
		uniService.addPro();
	}

	private void printProfessorMenu() {
		printService.printProMenu();
	}

	@Override
	public void printMenu() {
		printService.printMainMenu();
		
	}

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1:
			//교수 관리
			professorManage();
			break;
		case 2:
			//학생 관리
			break;
		case 3:
			//과 관리 
			break;
		case 4:
			//강의 관리
			break;
		case 5:
			//수강 관리
			break;
		case 6:
			//조회
			break;
		case 7:
			//프로그램 종료
			System.out.println("프로그램을 종료합니다.");
			break;
		default:
			throw new InputMismatchException();
		}
	}

}