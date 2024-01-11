package acountbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.ToString;

//가계부 목록
@Data
@ToString
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
	
	
	
	//전체 조회 : 이승주
	public void printAll() {
		System.out.println("날짜\t\t품목\t수입/지출");
		list.stream().forEach(s->System.out.println(s));
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
