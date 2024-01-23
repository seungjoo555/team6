package acountbook.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import acountbook.AcountBook;
import acountbook.Item;

public class ABServiceImp implements ABService{
	
	private Scanner sc = new Scanner(System.in);
	/* 강사 피드백 
	 * - 필요 없는 멤버*/
	private AcountBook ab= new AcountBook();
	
	@Override
	public void printAll(List<Item> list) {
		System.out.println("날짜\t\t품목\t수입/지출");
		printItem(list, (t)->true);
	}
	/* 강사 피드백 
	 * - 필요 없는 멤버*/
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

	/* 강사 피드백
	 * - 추가에 실패하는 경우는 없는가? 
	 * - 아이템을 만들고 리스트에 추가 안함.
	 * - 이러면 아이템 리스트에는 새 항목이 추가가 안됨*/
	public boolean addIncome() {
		System.out.print("날짜 (ex.2023-12-23) : ");
		String date = sc.next();
		System.out.print("품목 : ");
		String title = sc.nextLine();
		System.out.print("수입 : ");
		int money = sc.nextInt();
		/* 강사 피드백
		 * - 날짜, 품목, 금액을 추가하는 생성자를 만들어서, 금액이 +이면 수입, -이면 지출로 처리*/
		Item tmp = new Item(date, title);
		tmp.incomeMoney(money);
		
		return true;
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
		String date = sc.next();
		System.out.print("품목 : ");
		sc.nextLine();
		String title = sc.nextLine();
		System.out.print("지출금액 : ");
		int money = sc.nextInt();
		
		Item item = new Item(date, title);
		item.spendingMoney(money);
		list.add(item);
		System.out.println("추가했습니다.");
		return true;
	}



	@Override // 지출삭제 : 정경호
	public boolean removeSpending(List<Item> list) {
		 if (list == null || list.isEmpty()) {
	            System.out.println("내역 없음.");
	            return false;
	        }
		System.out.print("삭제할 날짜입력(yyyy-mm-dd) :");
		String date = sc.next();
		System.out.print("품목 : ");
		sc.nextLine();
		String title = sc.nextLine();
		System.out.print("금액 : ");
		int money = sc.nextInt();
		/* 강사 피드백
		 * - 날짜, 품목, 금액을 추가하는 생성자를 만들어서, 금액이 +이면 수입, -이면 지출로 처리*/
		Item item = new Item(date, title);
		item.spendingMoney(money);
	
		if (list.contains(item)) {
			list.remove(item);
            System.out.println("지출 내역이 성공적으로 삭제되었습니다.");
            return true;
        } else {
            System.out.println("삭제할 지출 내역이 없습니다.");
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
		String str = sc.next();
		System.out.print("수정 후 품목 : ");
		String title = sc.next();
		System.out.print("수정 후 가격 : ");
		int money = sc.nextInt();

		Item item = new Item(str, title);

		/* 강사 피드백
		 * - 아이템 클래스에 업데이트 메서드를 만들었다면 쉽게 수정할 수 있음
		 * - 위에 있는 객체를 만들 필요가 없음*/
		list.get(index).setRegDate(item.getRegDate());
		list.get(index).setTitle(item.getTitle());
		list.get(index).setMoney(money);
		System.out.println("수정했습니다.");
		return list;
	}

	// 수입 삭제 : 임병훈
	/* 강사 피드백
	 * - 수입을 삭제하는 지 확인하는 작업이 없음
	 * - 수입/지출 삭제 기능처럼 보임. 주석으로는 수입 삭제라고 되어 있음.*/
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
	/* 강사 피드백
	 * - 이 메서드는 입력한 index가 유효한지 확인하는 메서드인데
	 *   입력은 밖에서 받고, list와 index를 매개변수로 넘겨서
	 *   유효하면 index, 아니면 -1를 리턴하는 것이 좋아 보임.
	 * - 해당 메서드를 다른 곳에서 활용할 수 있음.*/
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
			index = sc.nextInt() - 1;
		}catch (InputMismatchException e){
			System.out.println("잘못된 메뉴입니다.");
			sc.nextLine();
		}

		if(index >= list.size()) {
			index = -1;
		}

		return index;
	}

	//수입 품목을 추가하는 메서드 : 이철범
	/* 강사 피드백
	 * - 수입 추가와 지출 추가가 차이가 없음.*/
	@Override
	public List<Item> add(List<Item> list) {
		if(list == null) {
			list = new ArrayList<Item>();
		}

		System.out.print("날짜(ex.2024-01-01) : ");
		String str = sc.next();
		System.out.print("품목 : ");
		sc.nextLine();
		String title = sc.nextLine();
		System.out.print("수입금액 : ");
		int don = sc.nextInt();

		Item tem = new Item(str, title);
		tem.incomeMoney(don);

		list.add(tem);
		
		System.out.println("추가했습니다.");
		return list;
	}
	
	//지출 수정하는 메서드 : 이철범
	/* 강사 피드백
	 * - 수입 수정과 지출 수정과 차이가 없음.*/
	@Override
	public List<Item> updateSpending(List<Item> list, int index) {
		if(index == -1) {
			System.out.println("해당 내역이 없습니다.");
			return list;
		}

		System.out.print("수정 후 일자 : ");
		String str = sc.next();
		System.out.print("수정 후 품목 : ");
		String title = sc.next();
		System.out.print("수정 후 가격 : ");
		int money = sc.nextInt();

		Item item = new Item(str, title);

		list.get(index).setRegDate(item.getRegDate());
		list.get(index).setTitle(item.getTitle());
		list.get(index).setMoney(money);
		System.out.println("수정했습니다.");
		return list;
	}
}