package acountbook.service;

import java.util.Scanner;

import acountbook.Item;

public class ABServiceImp implements ABService{

	private Scanner sc = new Scanner(System.in);
	
	@Override
	public boolean addIncome() {
		System.out.print("날짜 (ex.2023-12-23) : ");
		String date = sc.next();
		System.out.print("품목 : ");
		String title = sc.nextLine();
		System.out.print("수입 : ");
		int money = sc.nextInt();
		Item tmp = new Item(date, title);
		tmp.incomeMoney(money);
		
		
		
		
		return true;
	}

}
