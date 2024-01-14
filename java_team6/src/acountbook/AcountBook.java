package acountbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//가계부 목록
@Getter
@ToString
@NoArgsConstructor
/* 강사 피드백
 * - 수입, 지출 추가를 서비스에게 시키기 때문에 메서드 구현이 필요 없어짐.*/
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

	/* 강사 피드백
	 * - 지출 추가는 구현 안되어 있음.*/
	public void addSpending(int year, int month, int day, int money, String title) {
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

	public void addSpending(int date, int money, String title) {
		
	}
	
}