package community.main;


import java.util.InputMismatchException;
import java.util.Scanner;

import community.controller.CategoryController;

public class Main {
	
	private static CategoryController caCon;

	public static void main(String[] args) {
	try {
		Scanner sc = new Scanner(System.in);
		caCon = new CategoryController(sc);
		caCon.run();
		
		
	} catch (InputMismatchException e) {
		System.out.println("잘못 입력했습니다.\n프로그램을 종료합니다.");
		return;
	}
	
		
	}

}
