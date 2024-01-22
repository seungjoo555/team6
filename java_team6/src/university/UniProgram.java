package university;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import program.Program;
import university.service.PrintServiceImp;
import university.service.UniServiceimp;

// 실행 메서드
public class UniProgram implements Program {
	private final int EXIT = 7;
	private final int SUBJECT_EXIT = 4;
	
	
	private Scanner sc = new Scanner(System.in);
	final PrintServiceImp psi = new PrintServiceImp();
	final UniServiceimp usi = new UniServiceimp();
	final List<Subject>sb = new ArrayList<Subject>();
	final List<School> sh = new ArrayList<School>();
	@Override
	public void run() {
		int menu = 0;
		String fileName = "src/university/UniversityList.txt";
		//불러오기
		load(fileName);
		do {
			//메뉴 출력
			printMenu();
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
		save(fileName);
	}
		

	@Override
	public void printMenu() {
		psi.printMainMenu();
	}

	@Override
	public void runMenu(int menu) {
		
		switch (menu) {
		
		case 1: 
			//강의정보 - 추가 수정 삭제 메서드 구현 정경호
			subjectManager(menu);
			
			break;
			
		case 2: 
			//학과정보
			break;
			
		case 3: 
			//교수정보
			break;
			
		case 4: 
			//학생정보
			break;
			
		case 5: 
			//수강관리
			break;
		case 6: 
			//조회하기
			checkManager(menu);
			break;
			
		case 7: 
			System.out.println("프로그램을 종료합니다.");
			break;
			
		default:
			System.out.println("잘못 입력 하셨습니다.");
		}
		
	}
	private void checkManager(int menu) {
		
		do {
			
			psi.printManager();
			menu = sc.nextInt();
			checkSubject();
			
		} while (menu !=6);
		
		

		
	}


	private boolean checkSubject() {
		if(sb == null || sb.isEmpty()) {
			System.out.println("잘못 입력하셨습니다.");
		return false;
		}
		for(int i=0;i<sb.size();i++) {
			sb.get(i);
			System.out.println(sb.get(i).toString());
			
		}
		return true;
	}
	//강의 정보 메서드 //정경호
	private void subjectManager(int menu) {
			do {
				try {
				psi.subjectPrintMenu();
				menu=sc.nextInt();
				
				runSubjectManager(menu);	
				}catch (Exception e) {
					e.printStackTrace();
				}
			}while(menu != SUBJECT_EXIT);
	}
	//강의 정보 매니저 //정경호
	private void runSubjectManager(int menu) {
		switch(menu){
		case 1:
			addSubject();
			break;
		case 2:
			updateSubject();
			break;
		case 3:
			removeSubject();
			break;
		case 4:
			System.out.println("이전으로");
			break;
		default:
			System.out.println("잘못된 입력");
		 
		}
		
		
	}

	//강의 삭제 : 정경호
	private void removeSubject() {
		usi.removeSubject(sb);	
	}
	//강의 수정 : 정경호
	private void updateSubject() {
		usi.updateSubject(sb);
	}
	//강의 추가 : 정경호
	private void addSubject() {
		usi.addSubject(sb);
	}
	@Override
	public void printExit() {	
	}
	@Override
	public void save(String fileName) {		
		}
	@Override
	public void load(String fileName) {

		}

	
	
}
