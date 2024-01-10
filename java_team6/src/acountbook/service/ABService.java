package acountbook.service;

import java.util.List;

import acountbook.Item;

public interface ABService {

	//가계부 수입 추가 : 이승주
	boolean addIncome();
	
	//수입 내역을 추가하는 메소드 : 이철범
	boolean insertIncome(List<Item> list);

}
