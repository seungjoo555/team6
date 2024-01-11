package acountbook.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import acountbook.AcountBook;
import acountbook.Item;

public class ABServiceImp implements ABService{

	private Scanner sc = new Scanner(System.in);
	private AcountBook ab = new AcountBook(null);

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
		if(ab.getList() == null) {
			System.out.println("내역이 없습니다.");
			return false;
		}
		
		System.out.print("수정할 일자 : ");
		String regDate = sc.next();
		System.out.print("수정할 품목 : ");
		String title = sc.next();
		System.out.print("수정할 가격 : ");
		int money = sc.nextInt();
		
		Item item = new Item(regDate, title);
		List<Item> list = new ArrayList<Item>();
		list.add(item);
		
		if(list.contains(item) == false) {
			System.out.println("동일한 내역이 없습니다.");
			return false;
		}
		// 내역 위치 찾기
		int index = ab.getList().indexOf(item);
		Date date = ab.getList().get(index).getRegDate();
		ab.getList().get(index).setRegDate(date); 	  // 일자 변경
		ab.getList().get(index).setTitle(title);	  // 품목 변경
		return true;
	}

	@Override
	public boolean addSpending() {
		
		
		
		return true;
	}

	@Override
	public boolean removeSpending() {
		return true;
	}

	@Override
	public boolean addSpending(List<Item> list) {
		System.out.println("년 입력 :");
		int year = sc.nextInt();
		System.out.println("월 입력 :");
		int month = sc.nextInt();
		System.out.println("일 입력 :");
		int day = sc.nextInt();
		System.out.println("금액 입력 :");
		int money = sc.nextInt();
		System.out.println("품목 입력 :");
		sc.nextLine();
		String title = sc.nextLine();
		list.add(year,month,day,money,title);
			return true;
	}
	

}
