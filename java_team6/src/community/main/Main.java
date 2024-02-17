package community.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import community.controller.CategoryController;

public class Main {

	private static CategoryController caCon;

	public static void main(String[] args) {
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				caCon = new CategoryController(sc);
				caCon.run();
			} catch (InputMismatchException e) {
				System.out.println("잘못 입력 하셨습니다.\n메뉴 번호를 입력하세요.");

			}

		}
	}

}
