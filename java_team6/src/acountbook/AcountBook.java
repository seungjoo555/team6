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
