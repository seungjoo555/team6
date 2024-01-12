package acountbook.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
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
	@Override //정경호 지출추가
	public boolean addSpending(List<Item> list) {
		System.out.print("날짜입력 입력(yyyy-mm-dd) :");
		String str = scan.next();
		System.out.print("품목 : ");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.print("지출금액 : ");
		int money = scan.nextInt();
		
		Item item = new Item(str, title);
		item.spendingMoney(money);
		list.add(item);
		System.out.println("추가했습니다.");
		return true;
	}
	//지출 삭제 :정경호
	@Override
	public boolean removeSpending() {
		System.out.print("삭제할 날짜입력(yyyy-mm-dd) :");
		String str = scan.next();
		System.out.print("품목 : ");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.print("금액 : ");
		int money = scan.nextInt();
		
		Item item = new Item(str,title);
		if(ab.getList() != null && ab.getList().contains(item)){
			ab.getList().remove(item);
			System.out.println("지출 내역이 삭제 되었습니다.");
			return true;
		}else {
			System.out.println("일치하는 내역이 없습니다.");
			return false;
		}

	}

	// 수입 수정 : 임병훈
		@Override
		public List<Item> update(List<Item> list, int index) {

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
			System.out.println("수정했습니다.");
			return list;
		}

		// 수입 삭제 : 임병훈
		@Override
		public List<Item> delete(List<Item> list, int index) {
			if(index == -1) {
				System.out.println("해당 내역이 없습니다.");
				return list;
			}
			list.remove(index);
			System.out.println("삭제했습니다.");
			return list;
		}

		// 원하는 내역 index값 찾기 : 임병훈
		@Override
		public int location(List<Item> list) {
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

		//수입 품목을 추가하는 메서드 : 이철범
		@Override
		public List<Item> add(List<Item> list) {
			if(list == null) {
				list = new ArrayList<Item>();
			}

			System.out.print("날짜(ex.2024-01-01) : ");
			String str = scan.next();
			System.out.print("품목 : ");
			scan.nextLine();
			String title = scan.nextLine();
			System.out.print("수입금액 : ");
			int don = scan.nextInt();

			Item tem = new Item(str, title);
			tem.incomeMoney(don);

			list.add(tem);
			
			System.out.println("추가했습니다.");
			return list;
		}
		
		//지출 수정하는 메서드 : 이철범
		@Override
		public List<Item> updateSpending(List<Item> list, int index) {
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
				System.out.println("수정했습니다.");
				return list;
			}
	
}