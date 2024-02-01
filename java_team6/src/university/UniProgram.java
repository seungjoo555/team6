package university;

import java.util.InputMismatchException;

import java.util.Scanner;

import lombok.Data;
import program.UniversityProgram;
import university.service.FileService;
import university.service.FileServiceImp;
import university.service.PrintService;
import university.service.PrintServiceImp;
import university.service.UniService;
import university.service.UniServiceImp;

@Data
// 실행 메서드
public class UniProgram implements UniversityProgram {
	
	private PrintService ps = new PrintServiceImp();
	private UniService us = new UniServiceImp();
	private School school = new School();
	private FileService fileService = new FileServiceImp();
	
	
	public static Scanner scan = new Scanner(System.in);
	private final int EXIT = 7;
	private final int PFMEXIT = 4;
	private final int STDMEXIT = 4;
	private final int DPMEXIT = 4;
	private final int SUBDPMEXIT = 3;
	private final int SJMEXIT = 4;
	private final int SEARCHEXIT = 6;
	private final int LETEXIT = 2;
	
	@Override
	public void run() {
		int menu = 0;
		String fileName = "src/university/university.txt";
		//불러오기
		school = fileService.load(fileName);
		do {
			//메뉴 출력
			ps.printMainMenu();
			try {
				//메뉴 선택
				menu = scan.nextInt();
				//메뉴 실행
				runMenu(menu);
			} catch (InputMismatchException e) {
				System.out.println("메뉴를 잘못 선택했습니다.");
				scan.nextLine();
			}
		}while(menu != EXIT);
		//저장
		if(fileService.save(fileName, school)){
			System.out.println("저장이 완료됐습니다.");
		}else {
			System.out.println("저장에 실패했습니다.");
		}
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
			letManager();
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

	private void letManager() {
		int menu;
		do {
			//메뉴 출력
			ps.printLetMenu();
			//메뉴 선택
			menu = scan.nextInt();
			//메뉴 실행
			runLetMenu(menu);
		}while(menu != LETEXIT);
	}


	private void runLetMenu(int menu) {
		switch(menu) {
		case 1:
			// 수강 신청
			us.addlecture(school.getStd(), school.getSub());
			break;
		case 2:
			// 이전으로
			System.out.println("이전 메뉴로 돌아갑니다.");
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
			menu = scan.nextInt();
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
			for(Department dep: school.getDep()) {
				System.out.println(dep);
			}
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
			menu = scan.nextInt();
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
			menu = scan.nextInt();
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
			menu = scan.nextInt();
			//메뉴 실행
			runDPMMenu(menu);
		}while(menu != DPMEXIT);
	}

	private void runDPMMenu(int menu) {
		switch(menu) {
		case 1:
			//학과 등록
			us.addDepartment(school);
			break;
		case 2:
			//학과 수정(교수, 학생 업데이트 포함)
			updateDepartment();
			break;
		case 3:
			//학과 삭제
			us.deleteDepartment(school);
			break;
		case 4:
			//이전으로
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}
	}


	private void updateDepartment() {
		int menu;
		do {
			//메뉴 출력
			ps.printUpdateDPMMenu();
			//메뉴 선택
			menu = scan.nextInt();
			//메뉴 실행
			updateDPMMenu(menu);
		}while(menu != SUBDPMEXIT);
	}


	private void updateDPMMenu(int menu) {
		switch(menu) {
		case 1:
			//학과 이름 변경
			us.updateDPM_Name(school);
			break;
		case 2:
			//교수,학생 업데이트
			us.updateDPM_PfStd(school);
			break;
		case 3:
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
			menu = scan.nextInt();
			//메뉴 실행
			
			runSJMMenu(menu);
			
		}while(menu != SJMEXIT);
	}


	private void runSJMMenu(int menu) {
		switch(menu) {
		case 1:
			//강의 등록
			us.addSubject(school.getSub(),school.getPrf()); //정경호
			break;
		case 2:
			//강의 수정
			us.updateSubject(school.getSub(),school.getPrf());//정경호
			break;
		case 3:
			us.removeSubject(school.getSub(),school.getPrf());//정경호
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