package acountbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//가계부 목록
@Getter
@ToString
@NoArgsConstructor
public class AcountBook {

	private List<Item> list;
	
	
	public AcountBook(List<Item> list) {
		if(list == null) {
			list = new ArrayList<Item>();
		}
		this.list = list;
	}
	
	//수입 추가 : 이철범
	public void insertIncome(Item income) {
		list.add(income);
	}

	// 수입 수정 : 임병훈
	public boolean updateIncome(String regDate, String title, int index, int money) {
		
		Item item = new Item(regDate, title);
		
		Date date = item.getRegDate();
		list.get(index).setRegDate(date);	  // 일자 변경
		list.get(index).setTitle(title);	  // 품목 변경
		list.get(index).setMoney(money); 	  // 가격 변경
		return true;
	}

	// 수입 삭제 : 임병훈
	public boolean deleteIncome(int index) {

		if(list.remove(index) == null) {
			return false;
		}
		return true;
	}
}
