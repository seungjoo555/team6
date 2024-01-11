package acountbook.service;

import java.util.List;

import acountbook.Item;

public interface ABService {

	//가계부 수입 추가 : 이승주
	boolean addIncome();
	boolean updateSpending();
	boolean addSpending(List<Item> list);
	boolean removeSpending();
	boolean addSpending();
}
