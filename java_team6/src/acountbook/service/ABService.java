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


	boolean addSpending(List<Item> list);

	boolean removeSpending(List<Item> list);

	


	//수입 품목을 추가하는 메서드 : 이철범
	List<Item> add(List<Item> list);
	
	//지출 품목을 수정하는 메서드 : 이철범
	List<Item> updateSpending(List<Item> list, int index);


	
	// 수입 삭제 : 임병훈
	List<Item> delete(List<Item> list, int index);

	// 수입 수정 : 임병훈
	List<Item> update(List<Item> list, int index);

	// 원하는 내역 index값 찾기 : 임병훈
	int location(List<Item> list);
	




	List<Item> removeSpending(List<Item> list, int index);

}