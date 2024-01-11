package acountbook.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import acountbook.AcountBook;
import acountbook.Item;

public class ABServiceImp implements ABService{
	
	private Scanner sc = new Scanner(System.in);
	private AcountBook ab= new AcountBook();
	
	@Override
	public void printAll(List<Item> list) {
		System.out.println("날짜\t\t품목\t수입/지출");
		printItem(list, (t)->true);
	}

	private Scanner scan = new Scanner(System.in);
	private List<Item> list;
	@Override

	public void printMonth(List<Item> list) {
		System.out.print("조회할 월 : ");
		int month = sc.nextInt();
		printItem(list, it->it.getMonth() == month);
	}

	@Override
	public void printDay(List<Item> list) {
		System.out.print("조회할 월 : ");
		int month = sc.nextInt();
		System.out.print("조회할 날짜 : ");
		int day = sc.nextInt();
		printItem(list, it->it.getMonth() == month && it.getDay() == day);
	}

	private void sort(List<Item> list) {
		list.sort((t1, t2)-> {
			if(t1.getMonth() != t2.getMonth()) {
				return t1.getMonth() - t2.getMonth();
			}
			return t1.getDay() - t2.getDay();
		});
	}

	public boolean addIncome() {
		System.out.print("날짜 (ex.2023-12-23) : ");
		String date = scan.next();
		System.out.print("품목 : ");
		String title = scan.nextLine();
		System.out.print("수입 : ");
		int money = scan.nextInt();
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
		String regDate = scan.next();
		System.out.print("수정할 품목 : ");
		String title = scan.next();
		System.out.print("수정할 가격 : ");
		int money = scan.nextInt();
		
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


	@Override //정경호 지출삭제
	public boolean removeSpending() {
		
		return true;
	}

	@Override //정경호 지출삭제
	public boolean addSpending(List<Item> list) {
		System.out.print("년 입력 :");
		int year = scan.nextInt();
		System.out.print("월 입력 :");
		int month = scan.nextInt();
		System.out.print("일 입력 :");
		int day = scan.nextInt();
		System.out.print("금액 입력 :");
		int money = scan.nextInt();
		System.out.print("품목 입력 :");
		scan.nextLine();
		String title = scan.nextLine();
		list.add(new Item(year, month, day, money, title));
		System.out.println("날짜 :" +year+"-" + month + "-" + day +
				"\n금액 : "+money+"원" + "\n품목 : " + title );
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
	
	private void printItem(List<Item> list, Predicate<Item> p) {
		List<Item> tmp = new ArrayList<Item>();
		tmp.addAll(list);
		sort(tmp);
		for(Item item : tmp) {
			if(p.test(item)) {
				System.out.println(item);
			}
		}
	}

	@Override
	public boolean addSpending() {
		// TODO Auto-generated method stub
		return false;
	}

}