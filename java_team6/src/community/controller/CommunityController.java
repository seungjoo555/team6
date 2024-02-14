package community.controller;

import java.util.Scanner;

import community.service.CommunityService;
import community.service.CommunityServiceImp;


public class CommunityController {

	private CommunityService communityService = new CommunityServiceImp();
	Scanner scan = new Scanner(System.in);
	
	public void boardRun() {
		int menu;
		do {
			printCommunityMenu();
			menu = scan.nextInt();
			runCommunitymenu(menu);
		}while(menu != 4);
	}

	private void runCommunitymenu(int menu) {
		switch(menu) {
		case 1:
			// 게시판 관리
			break;
		case 2:
			// 게시글 관리
			break;
		case 3: 
			// 댓글 관리
			commentRun();
			break;
		case 4:
			System.out.println("이전으로");
			break;
		default:
			System.out.println("잘못된 입력");
			break;
		}
	}

	private void commentRun() {
		int menu;
		do {
			printCommentMenu();
			menu = scan.nextInt();
			runCommentmenu(menu);
		}while(menu != 4);
	}

	private void runCommentmenu(int menu) {
		switch(menu) {
		case 1:
			// 댓글 추가
			insertComment();
			break;
		case 2:
			// 댓글 수정
			updateComment();
			break;
		case 3: 
			// 댓글 삭제
			deleteComment();
			break;
		case 4:
			System.out.println("이전으로");
			break;
		default:
			System.out.println("잘못된 입력");
			break;
		}
	}
	
	private void insertComment() {
		
		// 로그인 유무 체크
		if(isLogin() == false) {
			System.out.println("로그인을 하지 않았습니다.");
			return;
		}
		
		// 카테고리 번호, 게시판 번호, 게시글 번호, 내용을 입력
		
		// 입력받은 정보로 객체를 생성
		
		// 생성한 객체를 boolean형 성공 유무 확인하는 메서드로 서비스에 넘김
	}
	
	private void updateComment() {
		// 로그인 유무 체크
		if(isLogin() == false) {
			System.out.println("로그인을 하지 않았습니다.");
			return;
		}
		
		// 카테고리 번호, 게시판 번호, 게시글 번호, 내용을 입력
		
		// 수정할 정보를 입력받음
		
		// 입력받은 정보로 객체를 생성
		
		// 
	}
	
	private void deleteComment() {
		// 로그인 유무 체크
		if(isLogin() == false) {
			System.out.println("로그인을 하지 않았습니다.");
			return;
		}
	}
	
	// 로그인 확인하는 메서드
	private boolean isLogin() {
		
	}
	
	private void printCommentMenu() {
		System.out.println("메뉴");
		System.out.print("1. 댓글 추가");
		System.out.print("2. 댓글 수정");
		System.out.print("3. 댓글 삭제");
		System.out.print("4. 이전");
	}
	
	private void printCommunityMenu() {
		System.out.println("메뉴");
		System.out.print("1. 게시판 관리");
		System.out.print("2. 게시글 관리");
		System.out.print("3. 댓글 관리");
		System.out.print("4. 이전");
	}
}