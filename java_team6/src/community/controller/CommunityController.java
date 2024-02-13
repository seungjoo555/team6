package community.controller;

import java.util.Scanner;

import community.model.vo.CommunityVO;
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
			break;
		default:
			System.out.println("이전으로");
			break;
		}
	}

	private void insertMember() {
		System.out.print("아이디 : ");
		String id = scan.next();
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("비번 : ");
		String pw = scan.next();
		System.out.print("이메일 : ");
		String email = scan.next();
		System.out.print("주소 : ");
		String addr = scan.next();
		System.out.print("핸드폰 : ");
		String phone = scan.next();
		
		CommunityVO community = new CommunityVO(id,pw,name,email,addr,phone);
		if(communityService.insertCommunity(community)) {
			System.out.println("회원을 추가했습니다.");
		}else {
			System.out.println("회원을 추가에 실패했습니다.");
		}
	}
	
	private void printCommunityMenu() {
		System.out.println("메뉴");
		System.out.println("1. 게시판 관리");
		System.out.println("2. 게시글 관리");
		System.out.println("3. 댓글 관리");
		System.out.println("4. 이전");
		System.out.print("메뉴 선택 : ");
	}

	public void memberRun() {
		
	}
}