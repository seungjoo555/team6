package community.main;

import java.util.Scanner;

import community.controller.CommunityController;


public class Main {

	private static CommunityController CommunityController = new CommunityController();
	public static void main(String[] args) {
		
		int menu;
		Scanner scan = new Scanner(System.in);
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(menu);
		}while(menu != 3);
	}

	private static void runMenu(int menu) {
		
		switch(menu) {
		case 1:
			CommunityController.boardRun();
			break;
		case 2:
			
			break;
		case 3:
			System.out.println("프로그램 종료");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	private static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 게시판 관리");
		System.out.println("3. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
	}
}
