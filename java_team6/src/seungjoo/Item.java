package seungjoo;

import lombok.Data;

// 날짜, 품목, 수입, 지출, 계정항목(보류)
@Data
public class Item {
	private int year, month, day;	//작성일
	private String Product;			//품목
	private int money;
	
	
	
	
}
