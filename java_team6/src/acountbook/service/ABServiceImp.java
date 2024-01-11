package acountbook.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import acountbook.Item;

public class ABServiceImp implements ABService{

	private Scanner scan = new Scanner(System.in);
	private List<Item> list;
	@Override
	public boolean addIncome() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//수입 품목을 추가하는 메서드 : 이철범
	@Override
	public boolean insertIncome(List<Item> list) {
		System.out.print("년(yyyy) : ");
		int year = scan.nextInt();
		System.out.print("월(mm) : ");
		int month = scan.nextInt();
		System.out.print("일(dd) : ");
		int day = scan.nextInt();
		if(check(year, month, day)) {
			return false;
		}
		System.out.print("금액(원) : ");
		int money = scan.nextInt();
		System.out.print("품목 : ");
		scan.nextLine();
		String title = scan.nextLine();
		
		list.add(new Item(year, month, day, money, title));
		
		System.out.println("날짜 : " + year + "-"  + month + "-" + day + " 수입 : " + money + " 품목 : " +  title);
		System.out.println("수입 품목 등록이 완료되었습니다.");
		
		return true;
	}

	private boolean check(int year, int month, int day) {
		if(month>12||month<=0) {
			System.out.println("잘못된 월 입력입니다.");
			return true;
		}
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
			if(day<=0||day>31) {
				System.out.println("잘못된 일 입력입니다.");
				return true;
			}
		}
		if(month==4||month==6||month==9||month==11) {
			if(day<=0||day>30) {
				System.out.println("잘못된 일 입력입니다.");
				return true;
			}
		}
		if(month == 2) {
			if(day<=0||day>28) {
				System.out.println("잘못된 일 입력입니다.");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateSpend(int index, int year, int month, int day, int money, String title) {
		list.set(index, new Item(year, month, day, money, title));

		return false;
	}
	

}
