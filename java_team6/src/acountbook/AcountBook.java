package acountbook;

import java.util.ArrayList;
import java.util.List;
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
	
	
}
