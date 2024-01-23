package acountbook.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import acountbook.Item;

public class ABServiceImp implements ABService{
	
	private Scanner sc = new Scanner(System.in);
	private Scanner scan = new Scanner(System.in);
	
	@Override
	public void printAll(List<Item> list) {
		System.out.println("날짜\t\t품목\t수입/지출");
		printItem(list, (t)->true);
	}

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
		
		if(list.size() > 1) {
			int index = list.size();
			list.get(index).setNum(list.get(index - 1).getNum() + 1);
		}else {
			list.get(0).setNum(1);
		}
		
		return list;
	}

	@Override
	public List<Item> remove(List<Item> list) {
		if(list == null) {
			list = new ArrayList<Item>();
		}
		
		System.out.print("날짜(ex.2024-01-01) : ");
		String str = scan.next();
		System.out.print("품목 : ");
		scan.nextLine();
		String title = scan.nextLine();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date regDate = format.parse(str);
			equals(list, it->it.getRegDate().equals(regDate) && it.getTitle().equals(title));
		} catch (ParseException e) {
			System.out.println("날짜를 다시 입력해주세요.");
		}
		
		
		System.out.print("삭제할 고유번호 입력: ");
		int num = scan.nextInt();
		
		list.remove(new Item(str, title, num));
		
		
		return list;
	}
	
	public void equals(List<Item> list, Predicate<Item> p) {
		for(Item tem : list) {
			if(p.test(tem)) {
				System.out.println("고유번호" + tem.getNum());
				System.out.println(tem);
			}
		}
	}
	
}

