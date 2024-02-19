package community.controller;

import java.util.Scanner;

import community.model.vo.MemberVO;
import community.service.CommunityService;
import community.service.CommunityServiceImp;

public class MemberController {

	private CommunityService communityService = new CommunityServiceImp();
	Scanner scan;
	
	public MemberController(Scanner scan) {
		if(scan == null) {
			scan = new Scanner(System.in);
		}
		this.scan = scan;
		communityService = new CommunityServiceImp();
	}
	
	public void memberRun() {
		int menu;
		do {
			printMemberMenu();
			menu = scan.nextInt();
			runMembermenu(menu);
		}while(menu != 7);
	}

	private void runMembermenu(int menu) {
		switch(menu) {
		case 1:
			insertMember();
			break;
		case 2:
			updateMember();
			break;
		case 3:
			deleteMember();
			break;
		case 4:
			login();
			break;
		case 5:
			logout();
			break;
		case 6:
			printMember();
			break;
		case 7:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}


	private void printMember() {
		// TODO Auto-generated method stub
		
	}

	private void logout() {
		// TODO Auto-generated method stub
		
	}
	
	private void login() {
		System.out.print("아이디 : ");
		String id = scan.next();
		System.out.print("비번 : ");
		String pw = scan.next();
		
		MemberVO member = new MemberVO(id, pw);
		
	}

	private void deleteMember() {
		// TODO Auto-generated method stub
		
	}

	private void updateMember() {

		
	}

	private void insertMember() {
		
		System.out.print("아이디 : ");
		String id = scan.next();
		System.out.print("비번 : ");
		String pw = scan.next();
		System.out.print("비번 확인 : ");
		String check_pw = scan.next();
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("이메일 : ");
		String email = scan.next();
		System.out.print("주소 : ");
		String addr = scan.next();
		System.out.print("핸드폰 : ");
		String phone = scan.next();
		
		MemberVO mem = new MemberVO();
		
		if(pw.equals(check_pw)) {
			System.out.println("비번이 다릅니다.");
			return;
		}
		
		MemberVO member = new MemberVO(id, pw, name, email, addr, phone);
		if(communityService.insertMember(member)) {
			System.out.println("회원을 추가했습니다.");
		}else {
			System.out.println("회원을 추가에 실패했습니다.");
		}
	}
	
	private void printMemberMenu() {
		System.out.println("메뉴");
		System.out.println("1. 회원 가입");
		System.out.println("2. 회원 수정");
		System.out.println("3. 회원 탈퇴");
		System.out.println("4. 로그인");
		System.out.println("5. 로그아웃");
		System.out.println("6. 회원 조회");
		System.out.println("7. 이전으로");
		System.out.print("메뉴 선택 : ");
	}
}