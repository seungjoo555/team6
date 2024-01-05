package homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import program.Program;

public class HomworkProgram implements Program {

	
	private ArrayList<Word> list = new ArrayList<Word>();
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
			System.out.println(list);
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
			printMeanMenu();
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

	private void printMeanMenu() {
		System.out.println("-------- 메뉴 --------");
		System.out.println("1. 뜻 추가");
		System.out.println("2. 뜻 수정");
		System.out.println("3. 뜻 삭제");
		System.out.println("4. 뒤로가기");
		System.out.println("---------------------");
		System.out.print("메뉴 선택 : ");
	}

	private void meanMenu(int menu) {
		switch(menu) {
		case 1:
			//뜻추가
			insertMean();
			break;
		case 2:
			//뜻수정
			changeMean();
			break;
		case 3:
			//뜻삭제
			deleteMean();
			break;
		case 4:
			System.out.println("뒤로가기");
			break;
		default:
		}
	}

	private void changeMean() {
		System.out.print("단어 입력 : ");
		String word = scan.next();
		
		Word wd = new Word(word, new ArrayList<String>());
		
		int index = list.indexOf(wd);
		if(index == -1) {
			System.out.println("없는 단어 입니다.");
			return;
		}
		
		System.out.print("수정할 뜻 입력 : ");
		String mean = scan.next();
		list.get(index).deleteMean(mean);
		
		System.out.print(mean + "  수정할 뜻 : ");
		String newMean = scan.next();
		list.get(index).setMean(newMean);
		
		System.out.println("단어를 수정했습니다.");
		
	}
	
	
	private void deleteMean() {
		System.out.println("삭제할 단어 입력 : ");
		String word = scan.next();
		
		Word wd = new Word(word, new ArrayList<String>());
		
		int index = list.indexOf(wd);
		if(index == -1) {
			System.out.println("없는 단어 입니다.");
			return;
		}
		
		System.out.println("삭제할 뜻 입력 : ");
		String mean = scan.next();
		
		list.get(index).deleteMean(mean);
		System.out.println("뜻 삭제 완료");
	}

	private void insertMean() {
		System.out.print("뜻을 추가할 단어 : ");
		String word = scan.next();
		
		Word wd = new Word(word, new ArrayList<String>());
		//있는지 확인
		int index = list.indexOf(wd);
		if(index == -1) {
			System.out.println("없는 단어 입니다.");
			return;
		}
		System.out.print("추가할 뜻 : ");
		String mean = scan.next();
		//있으면 뜻추가
		list.get(index).setMean(mean);
		System.out.println("뜻 추가 완료");
	}

	private void wordM() {
		int menu = 0;
		//반복
		do {
			//메뉴 출력
			printWordMenu();
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

	private void printWordMenu() {
		System.out.println("-------- 메뉴 --------");
		System.out.println("1. 단어 추가");
		System.out.println("2. 단어 수정");
		System.out.println("3. 단어 삭제");
		System.out.println("4. 뒤로가기");
		System.out.println("---------------------");
		System.out.print("메뉴 선택 : ");
	}

	private void wordMenu(int menu) {
		switch(menu) {
		
		case 1:
			//단어추가
			insertWord();
			break;
		case 2:
			//단어 수정
			changeWord();
			break;
		case 3:
			//단어 삭제
			deleteWord();
			break;
		case 4:
			System.out.println("뒤로가기");
			break;
		default:
		}
	
	}

	private void deleteWord() {
		System.out.print("삭제할 단어 : ");
		String word = scan.next();
		
		Word wd = new Word(word);
		int index = list.indexOf(wd);
		
		if(index == -1) {
			System.out.println("없는단어");
			return;
		}

		list.remove(index);
	}

	private void changeWord() {
<<<<<<< Updated upstream
		 System.out.print("수정할 단어 : ");
	        scan.nextLine();
	        String wordToFind = scan.next();
	        Word wordToModify = new Word(wordToFind, new ArrayList<String>());
	        int index = list.indexOf(wordToModify);
	        if (index == -1) {
	            System.out.println("없는 단어 입니다.");
	            return;
	        }
	        System.out.print("단어 수정 : ");
	        String newWord = scan.next();
	        Word newWord1 = new Word(newWord, new ArrayList<String>());
	        if (list.contains(newWord1)) {
	            System.out.println("이미 존재하는 단어입니다.");
	            return;
	        }
	        list.set(index, newWord1);
	        System.out.println("단어를 수정 했습니다.");
	    }
=======
		System.out.print("수정할 단어 : ");
		scan.nextLine();
		String word = scan.next();
		Word wd = new Word(word,new ArrayList<String>());
		int index = list.indexOf(wd);
		if(index == -1) {
			System.out.println("없는 단어 입니다.");
			return;
		}
	
	}
	
>>>>>>> Stashed changes

	private void insertWord() {
		System.out.print("단어 추가 : ");
		String word = scan.next();
		Word wd = new Word(word, new ArrayList<String>());
		if(!list.contains(wd)) {
			list.add(wd);
			System.out.println("단어를 추가 했습니다.");
			return;
		}
		System.out.println("이미 등록된 단어입니다.");
	}
	@Override
	public void save(String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
				oos.writeObject(list);
				System.out.println("단어장 저장 완료");
			} catch (IOException e) {
				System.out.println("저장에 실패했습니다.");
			}
	}

	@Override
	public void load(String fileName) {
		try(FileInputStream fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			list = ((ArrayList<Word>)ois.readObject());
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