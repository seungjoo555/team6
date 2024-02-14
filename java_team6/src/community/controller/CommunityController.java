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
			memberMenu();
			break;
		case 2:
			boardMenu();
			break;
		case 3:
			postMenu();
			break;
		case 4:
			commentMenu();
			break;
		case 5:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
		
	}

	private void commentMenu() {
		int menu;
		do {
			CommentMenu();
			menu = scan.nextInt();
			runCommentMenu(menu);
		}while(menu != 4);
	}

	private void CommentMenu() {
		System.out.println("메뉴");
		System.out.println("1. 댓글 등록");
		System.out.println("2. 게시글 수정");
		System.out.println("3. 댓글 삭제");
		System.out.println("4. 이전으로");
		System.out.print("메뉴 선택 : ");	
	}

	private void runCommentMenu(int menu) {
		switch(menu) {
		case 1:
			insertComment();
			break;
		case 2:
			updateComment();
			break;
		case 3:
			deleteComment();
			break;
		case 4:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	private void insertComment() {
		// TODO Auto-generated method stub
		
	}

	private void updateComment() {
		// TODO Auto-generated method stub
		
	}

	private void deleteComment() {
		// TODO Auto-generated method stub
		
	}

	private void postMenu() {
		int menu;
		do {
			printPostMenu();
			menu = scan.nextInt();
			runPostMenu(menu);
		}while(menu != 4);
	}

	private void printPostMenu() {
		System.out.println("메뉴");
		System.out.println("1. 게시글 등록");
		System.out.println("2. 게시글 수정");
		System.out.println("3. 게시글 삭제");
		System.out.println("4. 이전으로");
		System.out.print("메뉴 선택 : ");	
	}

	private void runPostMenu(int menu) {
		switch(menu) {
		case 1:
			insertPost();
			break;
		case 2:
			updatePost();
			break;
		case 3:
			deletePost();
			break;
		case 4:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	private void insertPost() {
		// TODO Auto-generated method stub
		
	}

	private void updatePost() {
		// TODO Auto-generated method stub
		
	}

	private void deletePost() {
		// TODO Auto-generated method stub
		
	}

	private void boardMenu() {
		int menu;
		do {
			printBoardMenu();
			menu = scan.nextInt();
			runBoardMenu(menu);
		}while(menu != 4);
	}

	private void runBoardMenu(int menu) {
		switch(menu) {
		case 1:
			insertBoard();
			break;
		case 2:
			updateBoard();
			break;
		case 3:
			deleteBoard();
			break;
		case 4:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	private void insertBoard() {
		// TODO Auto-generated method stub
		
	}

	private void updateBoard() {
		// TODO Auto-generated method stub
		
	}

	private void deleteBoard() {
		// TODO Auto-generated method stub
		
	}

	private void printBoardMenu() {
		System.out.println("메뉴");
		System.out.println("1. 게시판 등록");
		System.out.println("2. 게시판 수정");
		System.out.println("3. 게시판 삭제");
		System.out.println("4. 이전으로");
		System.out.print("메뉴 선택 : ");	
	}

	private void memberMenu() {
		int menu;
		do {
			printMemberMenu();
			menu = scan.nextInt();
			runMemeberMenu(menu);
		}while(menu != 4);
	}

	private void runMemeberMenu(int menu) {
		switch(menu) {
		case 1:
			insertMember();
			break;
		case 2:
			login();
			break;
		case 3:
			deleteMember();
			break;
		case 4:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	private void insertMember() {
		// TODO Auto-generated method stub
		
	}

	private void login() {
		// TODO Auto-generated method stub
		
	}

	private void deleteMember() {
		// TODO Auto-generated method stub
		
	}

	private void printMemberMenu() {
		System.out.println("메뉴");
		System.out.println("1. 회원 가입");
		System.out.println("2. 로그인");
		System.out.println("3. 회원 탈퇴");
		System.out.println("4. 이전으로");
		System.out.print("메뉴 선택 : ");
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
