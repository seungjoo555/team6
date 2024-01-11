package acountbook.service;

import java.util.List;

import acountbook.Item;

public interface ABService {

	//가계부 수입 추가 : 이승주
	boolean addIncome();
	boolean updateSpending();
	//지출 추가 : 정경호
	boolean addSpending(List<Item> list);
	//지출 삭제 : 정경호
	boolean removeSpending();
	//수입 품목을 추가하는 메서드 : 이철범
	boolean insertIncome(List<Item> list);
	
	//지출 품목을 수정하는 메서드 : 이철범
	boolean updateSpend(int index, int year, int month, int day, int money, String title);
	
	//전체 조회 : 이승주
	void printAll(List<Item> list);

	//월별 조회 : 이승주
	void printMonth(List<Item> list);

	//날짜별 조회 : 이승주
	void printDay(List<Item> list);

	boolean addIncome();
	boolean updateSpending();
	boolean addSpending(List<Item> list);
	boolean removeSpending();
	boolean addSpending();
	//수입 품목을 추가하는 메서드 : 이철범
	boolean insertIncome(List<Item> list);
	
	//지출 품목을 수정하는 메서드 : 이철범
	boolean updateSpend(int index, int year, int month, int day, int money, String title);

}
