package acountbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.Data;

// 날짜, 품목, 수입, 지출, 계정항목(보류)
@Data
public class Item {
	private int year, month, day;	//작성일
	private String item;			//품목
	private int money;				//수입,지출
	private Date regDate;
	
	
	public Item(String str, String item) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.regDate = format.parse(str);
			this.item = item;
			Calendar cal = Calendar.getInstance();
			cal.setTime(regDate);
			this.year = cal.get(Calendar.YEAR);
			this.month = cal.get(Calendar.MONTH) + 1;
			this.day = cal.get(Calendar.DAY_OF_MONTH);
		} catch (ParseException e) {
			System.out.println("에러");
		}
	}
	
	
	
	
	
	
	
	
}
