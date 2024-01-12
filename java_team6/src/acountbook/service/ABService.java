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

	

	List<Item> add(List<Item> list);

	List<Item> remove(List<Item> list);

	List<Item> update(List<Item> list);
}