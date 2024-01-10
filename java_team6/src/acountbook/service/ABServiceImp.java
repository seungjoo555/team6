package acountbook.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import acountbook.AcountBook;
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

	@Override
	public boolean updateSpending() {
		
		System.out.print("수정할 일자 : ");
		String regDate = sc.next();
		System.out.print("수정할 품목 : ");
		String title = sc.next();
		
		Item item = new Item(regDate, title);
		List<Item> list = new ArrayList<Item>();
		list.add(item);
		AcountBook ab = new AcountBook();
		
		if(!ab.getList().contains(item)) {
			System.out.println("동일한 내역이 없습니다.");
			return false;
		}
		
		System.out.print("수정할 가격 : ");
		int money = sc.nextInt();
		// 내역 위치 찾기
		int index = ab.getList().indexOf(item);
		System.out.println(index);
		Date date = ab.getList().get(index).getRegDate();
		ab.getList().get(index).setRegDate(date); 	  // 일자 변경
		ab.getList().get(index).setTitle(title);	  // 품목 변경
		ab.getList().get(index).spendingMoney(money); // 지출 값 변경
		return true;
	}
	

}
