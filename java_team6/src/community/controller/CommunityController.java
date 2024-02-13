package community.controller;

import java.util.Scanner;

import community.service.CommunityService;
import community.service.CommunityServiceImp;
import community.service.ControllerSevice;
import program.Program;

public class CommunityController implements ControllerSevice {
	
	private CommunityService cs = new CommunityServiceImp();
	private Scanner sc = new Scanner(System.in);
	@Override
	public void run() {
		int menu;
		do {
		printMainMenu();
		menu = sc.nextInt();
		runMenu(menu);
		}while(menu != 0);
		
	}
	@Override
	public void runMenu(int menu) {
		
		
	}
	@Override
	public void printMainMenu() {
		
		
	}
	

	

}
