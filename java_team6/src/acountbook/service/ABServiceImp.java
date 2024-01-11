package acountbook.service;

import java.util.ArrayList;
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

}
