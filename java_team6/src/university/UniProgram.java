package university;

import java.util.InputMismatchException;
import java.util.Scanner;
import program.UniversityProgram;
import university.service.PrintService;
import university.service.PrintServiceImp;
import university.service.UniService;
import university.service.UniServiceImp;

// 실행 메서드
public class UniProgram implements UniversityProgram {
	
	private PrintService ps = new PrintServiceImp();
	private UniService us = new UniServiceImp();
	private School school = new School();
	
	
	private Scanner sc = new Scanner(System.in);
	private final int EXIT = 7;
	private final int PFMEXIT = 4;
	private final int STDMEXIT = 4;
	private final int DPMEXIT = 4;
	private final int SJMEXIT = 4;
	private final int SEARCHEXIT = 6;
	
	@Override
	public void run() {
		int menu = 0;
		//불러오기
		System.out.println("불러오기 구현 예정");
		do {
			//메뉴 출력
			ps.printMainMenu();
			try {
				//메뉴 선택
				menu = sc.nextInt();
				//메뉴 실행
				runMenu(menu);
			} catch (InputMismatchException e) {
				System.out.println("메뉴를 잘못 선택했습니다.");
				sc.nextLine();
			}
		}while(menu != EXIT);
		//저장
		System.out.println("저장 구현 예정");
	}
		

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1:
			//교수 관리
			pfManager();
			break;
		case 2:
			//학생 관리
			stdManager();
			break;
		case 3:
			//과 관리
			dpmManager();
			break;
		case 4:
			//강의 관리
			sjManager();
			break;
		case 5:
			//수강 관리 (강의관리 후에 추가)
			System.out.println("수강관리 예정");
			break;
		case 6:
			//조회
			searchManager();
			break;
		case 7:
			//프로그램 종료
			System.out.println("프로그램을 종료 합니다.");
			break;
		default:
			throw new InputMismatchException();
		}
	}

	private void searchManager() {
		int menu;
		do {
			//메뉴 출력
			ps.printMenu();
			// 메뉴 선택
			menu = sc.nextInt();
			//메뉴 실행
			runSearch(menu);
		}while(menu != SEARCHEXIT);
	}

	private void runSearch(int menu) {
		switch(menu) {
		case 1:
			// 교수 조회
			System.out.println(school.getPrf());
			break;
		case 2:
			// 학생 조회
			System.out.println(school.getStd());
			break;
		case 3:
			// 과 조회
			break;
		case 4:
			// 강의 조회
			us.checkSub(school.getSub()) ;//정경호 
			break;
		case 5:
			// 수강 조회
			break;
		case 6:
			// 이전으로
			break;
		default:
			throw new InputMismatchException();
		}
	}


	/**
	 * 교수 등록/수정/삭제
	 */
	private void pfManager() {
		int menu;
		do {
			//메뉴 출력
			ps.printPFMMenu();
			//메뉴 선택
			menu = sc.nextInt();
			//메뉴 실행
			runPFMMenu(menu);
		}while(menu != PFMEXIT);
	}

	private void runPFMMenu(int menu) {
		switch(menu) {
		case 1:
			//교수 등록
			us.addProfessor(school.getPrf());
			break;
		case 2:
			//교수 수정
			us.updateProfessor(school.getPrf());
			break;
		case 3:
			//교수 삭제
			us.deleteProfessor(school.getPrf());
			break;
		case 4:
			//이전으로
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}
	}


	/**
	 * 학생 등록/수정/삭제
	 */
	private void stdManager() {
		int menu;
		do {
			//메뉴 출력
			ps.printSTDMMenu();
			//메뉴 선택
			menu = sc.nextInt();
			//메뉴 실행
			runSTDMMenu(menu);
		}while(menu != STDMEXIT);
	}

	private void runSTDMMenu(int menu) {
		switch(menu) {
		case 1:
			//학생 등록
			us.addStudent(school.getStd());
			break;
		case 2:
			//학생 수정
			us.updateStudent(school.getStd());
			break;
		case 3:
			//학생 삭제
			us.deleteStudent(school.getStd());
			break;
		case 4:
			//이전으로
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}
	}


	/**
	 * 과 등록/수정/삭제
	 */
	private void dpmManager() {
		int menu;
		do {
			//메뉴 출력
			ps.printDPMMenu();
			//메뉴 선택
			menu = sc.nextInt();
			//메뉴 실행
			runDPMMenu(menu);
		}while(menu != DPMEXIT);
	}

	private void runDPMMenu(int menu) {
		switch(menu) {
		case 1:
			//학과 등록
			us.addScore(school);
			break;
		case 2:
			//학과 수정
			Scanner scan = new Scanner(System.in);
			System.out.print("점수를 추가할 학생 : ");
			String sNum = scan.next();
			
			Student std = new Student(sNum);
			int index = school.getStd().indexOf(std);
			
			System.out.println(school.getStd().get(index).getMap());
			break;
		case 3:
			//학과 삭제
			break;
		case 4:
			//이전으로
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}
	}


	/**
	 * 강의 등록/수정/삭제
	 */
	private void sjManager() {
		int menu;
		do {
			//메뉴 출력
			ps.printSJMMenu();
			//메뉴 선택
			menu = sc.nextInt();
			//메뉴 실행
			runSJMMenu(menu);
		}while(menu != SJMEXIT);
	}


	private void runSJMMenu(int menu) {
		switch(menu) {
		case 1:
			//강의 등록
			us.addSubject(school.getSub()); //정경호
			break;
		case 2:
			//강의 수정
			us.updateSubject(school.getSub());//정경호
			break;
		case 3:
			us.removeSubject(school.getSub());//정경호
			//강의 삭제
			break;
		case 4:
			//이전으로
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}
	}
}
