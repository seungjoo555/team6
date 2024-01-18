package acountbook;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

// 날짜, 품목, 수입, 지출, 계정항목(보류)
@Data
@AllArgsConstructor
public class Item implements Serializable{
	
	private static final long serialVersionUID = -7504275902981826903L;
	private int year, month, day;	//작성일
	private String title;			//품목
	private int money;				//수입,지출
	private int num;				//가계부 고유번호
	@NonNull
	private Date regDate;
	
	//수정 또는 삭제할때 비교
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return num == other.num && Objects.equals(regDate, other.regDate) && Objects.equals(title, other.title);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(num, regDate, title);
	}
	
	//수정 또는 삭제할때 사용할 생성자
	public Item(String str, String title, int num) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.regDate = format.parse(str);
			this.title = title;
			Calendar cal = Calendar.getInstance();
			cal.setTime(regDate);
			this.year = cal.get(Calendar.YEAR);
			this.month = cal.get(Calendar.MONTH) + 1;
			this.day = cal.get(Calendar.DAY_OF_MONTH);
			this.money = 0;
			this.num = num;
		} catch (ParseException e) {
			System.out.println("에러");
		}
	}
	
	//추가할때 사용할 생성자
	public Item(String str, String title) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.regDate = format.parse(str);
			this.title = title;
			Calendar cal = Calendar.getInstance();
			cal.setTime(regDate);
			this.year = cal.get(Calendar.YEAR);
			this.month = cal.get(Calendar.MONTH) + 1;
			this.day = cal.get(Calendar.DAY_OF_MONTH);
			this.money = 0;
		} catch (ParseException e) {
			System.out.println("에러");
		}
	}
	
	public void incomeMoney(int money) {
		this.money += money;
	}
	
	public void spendingMoney(int money) {
		this.money -= money;
	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(regDate) + "\t" + title + "\t" + money;
	}

	
}
