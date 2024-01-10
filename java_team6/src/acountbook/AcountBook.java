package acountbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//가계부 목록
@Data
@ToString
@NoArgsConstructor
public class AcountBook {

	private List<Item> list = new ArrayList<Item>();
	
	
	
	public AcountBook(List<Item> list) {
		if(list == null) {
			list = new ArrayList<Item>();
		}
		this.list = list;
	}
	

	//수입 추가
	public void addIncome() {
		list.add(new Item("2024-01-01", "아침"));
		list.add(new Item("2024-01-01", "점심"));
		list.add(new Item("2024-01-01", "저녁"));
		list.add(new Item("2024-02-01", "아침"));
		list.add(new Item("2024-02-01", "점심"));
		list.add(new Item("2024-02-01", "저녁"));
		for(int i = 0; i < list.size(); i++) {
			list.get(i).incomeMoney(1000 * i);
		}
	}
	

	//전체 조회 : 이승주
	public void printAll() {
		System.out.println("날짜\t\t품목\t수입/지출");
		list.stream().forEach(s->System.out.println(s));
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