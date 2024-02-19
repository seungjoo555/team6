package community.model.vo;

import lombok.Data;

@Data
public class PostVO {
	
	private int po_num, po_bo_num;
	private String po_title, po_content, po_me_id;
	
	public PostVO(int po_num, int po_bo_num, String po_title, String po_content, String po_me_id) {
		this.po_num = po_num;
		this.po_bo_num = po_bo_num;
		this.po_title = po_title;
		this.po_content = po_content;
		this.po_me_id = po_me_id;
	}
}