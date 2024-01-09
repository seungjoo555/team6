package acountbook;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

//가계부 목록
@Getter
public class AcountBook {

	List<Item> list = new ArrayList<Item>();
	
	public AcountBook(List<Item> list) {
		if(list == null) {
			list = new ArrayList<Item>();
		}
		this.list = list;
	}
	
	//수입 추가
	public boolean addIncome(String date, String title) {
		if(list == null) {
			return false;
		}
		
		
		
		return true;
	}
	
}
