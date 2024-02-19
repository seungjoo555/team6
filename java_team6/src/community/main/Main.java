package community.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import community.controller.CommunityController;
import community.controller.MemberController;

public class Main {

	private static MemberController memberController;
	private static CommunityController communityController;
	
	public static void main(String[] args) {
		int menu = 0;
		Scanner scan = new Scanner(System.in);
		memberController = new MemberController(scan);
		communityController = new CommunityController(scan);
		
		do {
			try {
				printMenu();
				menu = scan.nextInt();
				runMenu(menu);
			}catch(InputMismatchException e) {
				System.out.println("없는 메뉴입니다.");
				scan.nextLine();	//입력 버퍼 비우기
			}
		}while(menu != 3);

	}
	
	private static void printMenu() {
		System.out.println("킹스맨 카페");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 종료");
		System.out.print("메뉴 선택 : ");
	}
	
	private static void runMenu(int menu) {
		switch(menu) {
		case 1:
			communityController.rogIn();
			break;
		case 2:
			communityController.signUp();
			break;
		case 3:
			System.out.println("프로그램 종료");
			break;
		default:
			throw new InputMismatchException();
		}
	}

}