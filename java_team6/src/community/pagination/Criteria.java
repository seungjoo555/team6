package community.pagination;

import lombok.Data;

@Data
public class Criteria {
	
	private int page;
	private int perPageNum;
	private String search;
	
	public Criteria() {
		page = 1;
		perPageNum = 10;
	}
	public Criteria(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}
	public int getPageStart() {
		return (page - 1) * perPageNum;
	}
}
