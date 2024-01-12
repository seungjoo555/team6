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

	public boolean addSpending() {
		

		return true;
	}

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
		ab.getList().get(index).setTitle(title);	 // 품목 변경
		return true;
	}



	
	@Override //정경호 지출추가
	public boolean addSpending(List<Item> list) {
		System.out.print("연도 입력 :");
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
		ab.addSpending(year, month, day, money, title);
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
	

	//지출 삭제 :정경호
	@Override
	public boolean removeSpending() {
		System.out.print("삭제할 연도 입력 :");
		int year = scan.nextInt();
		System.out.print("삭제할 월 입력 :");
		int month = scan.nextInt();
		System.out.print("삭제할 일 입력 :");
		int day = scan.nextInt();
		System.out.print("삭제할 금액 입력 :");
		int money = scan.nextInt();
		System.out.print("삭제할 품목 입력 :");
		scan.nextLine();
		String title = scan.nextLine();
		
		Item item = new Item(year, month, day, money, title);
		
		if(ab.getList() != null && ab.getList().contains(item)){
			ab.getList().remove(item);
			System.out.println("지출 내역이 삭제 되었습니다.");
			return true;
		}else {
			System.out.println("일치하는 내역이 없습니다.");
			return false;
		}
		
	}

}