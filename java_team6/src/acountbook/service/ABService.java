package acountbook.service;

import java.util.List;

import acountbook.Item;

public interface ABService {

	//가계부 수입 추가 : 이승주
	boolean addIncome();
	
	//수입 품목을 추가하는 메서드 : 이철범
	boolean insertIncome(List<Item> list);
	
	//지출 품목을 수정하는 메서드 : 이철범
	boolean updateSpend(int index, int year, int month, int day, int money, String title);
	
}
