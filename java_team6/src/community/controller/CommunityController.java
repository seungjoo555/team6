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
		// TODO Auto-generated method stub
		
	}

	private void printMenu() {
		// TODO Auto-generated method stub
		
	}

}
