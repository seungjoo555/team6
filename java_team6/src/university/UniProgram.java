package university;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import program.Program;

// 실행 메서드
public class UniProgram implements Program {
	School sh = new School();
	private Scanner sc = new Scanner(System.in);
	private final int EXIT = 99999;
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
		
	}

	@Override
	public void runMenu(int menu) {
		
	}

	@Override
	public void printExit() {
		
	}

	@Override
	public void save(String fileName) {
//		try(FileOutputStream fos = new FileOutputStream(fileName);
//				ObjectOutputStream oos = new ObjectOutputStream(fos)){
//				oos.writeObject(sh.getList());
//				System.out.println("저장하기를 성공 했습니다.");
//			} catch (FileNotFoundException e) {
//				//폴더 경로가 잘 못된 경우
//				System.out.println("지정된 위치에 파일을 찾을 수 없습니다.");
//			} catch (IOException e) {
//				System.out.println("저장에 실패 했습니다.");
//			}
			
		}
	@Override
	public void load(String fileName) {
//		try(FileInputStream fis = new FileInputStream(fileName);
//				ObjectInputStream ois = new ObjectInputStream(fis)){
//				sh.setList((ArrayList<Student>)ois.readObject());
//				System.out.println("불러오기를 성공 했습니다.");
//			} catch (FileNotFoundException e) {
//				//폴더 경로가 잘 못된 경우
//				System.out.println("지정된 위치에 파일을 찾을 수 없습니다.");
//			} catch (Exception e) {
//				System.out.println("불러오기에 실패 했습니다.");
//			}
		}

	
	
	
}
