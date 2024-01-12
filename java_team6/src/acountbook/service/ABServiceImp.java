package acountbook.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import acountbook.Item;

public class ABServiceImp implements ABService{
	
	private Scanner sc = new Scanner(System.in);
	
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
	public List<Item> insertIncome(List<Item> list) {
		System.out.print("년(yyyy) : ");
		int year = scan.nextInt();
		System.out.print("월(mm) : ");
		int month = scan.nextInt();
		System.out.print("일(dd) : ");
		int day = scan.nextInt();
		if(check(year, month, day)) {
			return list;
		}
		System.out.print("금액(원) : ");
		int money = scan.nextInt();
		System.out.print("품목 : ");
		scan.nextLine();
		String title = scan.nextLine();
		
		list.add(new Item(year, month, day, money, title));
		
		System.out.println("날짜 : " + year + "-"  + month + "-" + day + " 수입 : " + money + " 품목 : " +  title);
		System.out.println("수입 품목 등록이 완료되었습니다.");
		
		return list;
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

	// 수입 수정 : 임병훈
	@Override
	public List<Item> updateIncome(List<Item> list, int index) {
		
		if(index == -1) {
			System.out.println("해당 내역이 없습니다.");
			return list;
		}
		
		System.out.print("수정 후 일자 : ");
		String str = scan.next();
		System.out.print("수정 후 품목 : ");
		String title = scan.next();
		System.out.print("수정 후 가격 : ");
		int money = scan.nextInt();
		
		Item item = new Item(str, title);
		
		list.get(index).setRegDate(item.getRegDate());
		list.get(index).setTitle(item.getTitle());
		list.get(index).setMoney(money);
		return list;
	}
	
	// 수입 삭제 : 임병훈
	@Override
	public List<Item> deleteIncome(List<Item> list, int index) {
		if(index == -1) {
			System.out.println("해당 내역이 없습니다.");
			return list;
		}
		list.remove(index);
		return list;
	}
	
	// 원하는 내역 index값 찾기 : 임병훈
	@Override
	public int incomeLocation(List<Item> list) {
		int index = 0;
		
		if(list.size() == 0) {
			index = -1;
			return index;
		}
		
		// 수입 내역 출력
		printAll(list);
		
		//수정 전 항목 받아오기
		try {
			System.out.print("작업할 항목을 선택하세요 : "); 
			index = scan.nextInt() - 1;
		}catch (InputMismatchException e){
			System.out.println("잘못된 메뉴입니다.");
			scan.nextLine();
		}

		if(index >= list.size()) {
			index = -1;
		}
		
		return index;
	}
	
	@Override
	public List<Item> add(List<Item> list) {
		if(list == null) {
			list = new ArrayList<Item>();
		}
		
		System.out.println("날짜(ex.2024-01-01) : ");
		String str = scan.next();
		System.out.println("품목 : ");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.println("수입금액 : ");
		int don = scan.nextInt();
		
		Item tem = new Item(str, title);
		tem.incomeMoney(don);
		
		list.add(tem);
		
		return list;
	}
	
	
	@Override
	public List<Item> addSpend(List<Item> list) {
		if(list == null) {
			list = new ArrayList<Item>();
		}
		
		System.out.println("날짜(ex.2024-01-01) : ");
		String str = scan.next();
		System.out.println("품목 : ");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.println("수입금액 : ");
		int don = scan.nextInt();
		
		Item tem = new Item(str, title);
		tem.incomeMoney(-don);
		
		list.add(tem);
		
		return list;
	}
	
}