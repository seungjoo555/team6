package acountbook.service;

import java.util.List;

import acountbook.Item;

public interface ABService {
  
	//전체 조회 : 이승주
	void printAll(List<Item> list);

	//월별 조회 : 이승주
	void printMonth(List<Item> list);

	//날짜별 조회 : 이승주
	void printDay(List<Item> list);


	boolean addSpending();

	boolean addIncome();
	boolean updateSpending();
	boolean addSpending(List<Item> list);
	boolean removeSpending();

	//수입 품목을 추가하는 메서드 : 이철범
	boolean insertIncome(List<Item> list);
	
	//지출 품목을 수정하는 메서드 : 이철범
	boolean updateSpend(int index, int year, int month, int day, int money, String title);


	boolean addSpending(List<Item> list);

	boolean removeSpending();
	
	// 수입 삭제 : 임병훈
	List<Item> deleteIncome(List<Item> list, int index);

	// 수입 수정 : 임병훈
	List<Item> updateIncome(List<Item> list, int index);

	// 원하는 내역 index값 찾기 : 임병훈
	int incomeLocation(List<Item> list);
	
	List<Item> add(List<Item> list);


}