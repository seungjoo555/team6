package acountbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

	public boolean updateSpending(String regDate, String title, int money) {
			
		Item item = new Item(regDate, title);
		AcountBook ab = new AcountBook();
		
		if(!list.contains(item)) {
			return false;
		}
		
		// 내역 위치 찾기
		int index = ab.getList().indexOf(item);
		System.out.println(index);
		Date date = list.get(index).getRegDate();
		list.get(index).setRegDate(date);
		list.get(index).setTitle(title);	  // 품목 변경
		list.get(index).spendingMoney(money); // 지출 값 변경
		return true;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcountBook other = (AcountBook) obj;
		return Objects.equals(list, other.list);
	}

	@Override
	public int hashCode() {
		return Objects.hash(list);
	}
	

	

	
}
