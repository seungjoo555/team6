package acountbook;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
	@NonNull
	private Date regDate;
	
	
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
	/* 강사 피드백
	 * - money가 음수일 때 처리 안함 아래도 마찬가지*/
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

	public Item(int year, int month, int day, int money, String title) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.money = money;
		this.title = title;
	}
}