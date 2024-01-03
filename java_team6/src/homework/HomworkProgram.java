package homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import program.Program;

public class HomworkProgram implements Program {

	private WordManager wm = new WordManager();
	private Scanner scan = new Scanner(System.in);
	private final int EXIT = 4;
	
	@Override
	public void run() {
		String fileName = "src/homework/wordInfo.txt";
		int menu = 0;
		load(fileName);
		//반복
		do {
			//메뉴 출력
			printMenu();
			try {
				//메뉴 선택
				menu = scan.nextInt();
				//메뉴 실행
				runMenu(menu);
			}catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT);
		save(fileName);
	}

	@Override
	public void printMenu() {
		System.out.println("-------- 메뉴 --------");
		System.out.println("1. 단어 관리");
		System.out.println("2. 뜻 관리");
		System.out.println("3. 단어 조회");
		System.out.println("4. 종료");
		System.out.println("---------------------");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1:
			//단어 관리
			wordM();
			break;
		case 2:
			//뜻 관리
			meanM();
			break;
		case 3:
			//단어 조회
			System.out.println("구현중");
			break;
		case 4:
			break;
		default:
			throw new InputMismatchException();
		}
	}

	private void meanM() {
		int menu = 0;
		//반복
		do {
			//메뉴 출력
			printMenu();
			try {
				//메뉴 선택
				menu = scan.nextInt();
				//메뉼 실행
				meanMenu(menu);
			}catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT);
	}

	private void meanMenu(int menu) {
		switch(menu) {
		case 1:
			//뜻추가
			break;
		case 2:
			//뜻수정
			
			break;
		case 3:
			//뜻삭제
			break;
		case 4:
			System.out.println("뒤로가기");
			break;
		default:
		}
	}

	private void wordM() {
		int menu = 0;
		//반복
		do {
			//메뉴 출력
			printMenu();
			try {
				//메뉴 선택
				menu = scan.nextInt();
				//메뉼 실행
				wordMenu(menu);
			}catch(InputMismatchException e) {
				System.out.println("잘못된 메뉴입니다.");
				scan.nextLine();
			}
		}while(menu != EXIT);
	}

	private void wordMenu(int menu) {
		switch(menu) {
		case 1:
			//단어추가
			System.out.println("단어 추가 : ");
			String word1 = scan.next();
			Word word = new Word(word1, null);
			if(wm.insertStudent(word)) {
				System.out.println("단어를 추가했습니다.");
			}else {
				System.out.println("이미 있는 단어입니다.");
			}
			break;
		case 2:
			//단어 수정
			break;
		case 3:
			//단어 삭제
			break;
		case 4:
			System.out.println("뒤로가기");
			break;
		default:
		}
	}

	@Override
	public void save(String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
				oos.writeObject(wm.getList());
				System.out.println("단어장 저장 완료");
			} catch (IOException e) {
				System.out.println("저장에 실패했습니다.");
			}
	}

	@Override
	public void load(String fileName) {
		try(FileInputStream fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			wm.setList((ArrayList<Word>)ois.readObject());
			System.out.println("단어장을 불러왔습니다.");
		} catch (Exception e) {
			System.out.println("불러오기에 실패 했습니다.");
		}
	}

	@Override
	public void printExit() {
		System.out.println("-----------------------");
		System.out.println("프로그램을 종료합니다.");
		System.out.println("-----------------------");
	}

}
