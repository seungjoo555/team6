package community.controller;

import java.util.Scanner;

import community.service.CommunityService;
import community.service.CommunityServiceImp;


public class CommunityController {
	
	private Scanner scan;
	private CommunityService communityService;
	
	public CommunityController(Scanner scan) {
		if(scan == null) {
			scan = new Scanner(System.in);
		}
		this.scan = scan;
		communityService = new CommunityServiceImp();
	}

	public void run() {
		int menu;
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(menu);
		}while(menu != 5);
	}

	private void runMenu(int menu) {
		switch(menu) {
		case 1:
			MemberMenu();
			break;
		case 2:
			BoardMenu();
			break;
		case 3:
			PostMenu();
			break;
		case 4:
			CommentMenu();
			break;
		case 5:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
		
	}

	private void CommentMenu() {
		
	}

	private void PostMenu() {
		
	}

	private void BoardMenu() {
		
	}

	private void MemberMenu() {
		
	}

	private void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 회원 관리");
		System.out.println("2. 게시판 관리");
		System.out.println("3. 게시글 관리");
		System.out.println("4. 댓글 관리");
		System.out.println("5. 이전으로");
		System.out.print("메뉴 선택 : ");
		
	}

}
