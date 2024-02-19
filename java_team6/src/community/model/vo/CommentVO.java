package community.model.vo;

import lombok.Data;

@Data
public class CommentVO {
	
	private int co_num, co_po_num;
	private String co_content, co_me_id;
	
	public CommentVO(int co_num, int co_po_num, String co_content, String co_me_id) {
		this.co_num = co_num;
		this.co_po_num = co_po_num;
		this.co_content = co_content;
		this.co_me_id = co_me_id;
	}
}