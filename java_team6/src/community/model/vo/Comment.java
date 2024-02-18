package community.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comment {
	// 댓글
	private Post postNum;
	private int co_ca_num;
	private int co_bo_num;
	private int co_po_num;
	private int co_num;
	private String co_me_id;
	private String co_content;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return co_num == other.co_num;
	}
	@Override
	public int hashCode() {
		return Objects.hash(co_num);
	}
	
	// 댓글 생성할 때 사용
	public Comment(int co_ca_num, int co_bo_num, int co_po_num, String co_me_id, String co_content) {
		this.co_ca_num = co_ca_num;
		this.co_bo_num = co_bo_num;
		this.co_po_num = co_po_num;
		this.co_me_id = co_me_id;
		this.co_content = co_content;
	}
	
	// 댓글 번호가 존재하는지 확인할 때 사용
	public Comment(int co_num) {
		this.co_num = co_num;
	}
	
	// 본인의 댓글인지 확인할 때 사용
	public Comment(int co_num, String co_me_id) {
		this.co_num = co_num;
		this.co_me_id = co_me_id;
	}
	
	// 댓글 수정할때 사용
	public Comment(int co_num, String co_me_id, String co_content) {
		this.co_num = co_num;
		this.co_me_id = co_me_id;
		this.co_content = co_content;
	}
	
	// 댓글 출력할 때 사용
	public Comment(int co_ca_num, int co_po_num, int co_bo_num) {
		this.co_ca_num = co_ca_num;
		this.co_po_num = co_po_num;
		this.co_bo_num = co_bo_num;
	}
	
	// 삭제할 때 사용
	public Comment(int co_ca_num, int co_bo_num, int co_po_num, int co_num) {
		this.co_ca_num = co_ca_num;
		this.co_bo_num = co_bo_num;
		this.co_po_num = co_po_num;
		this.co_num = co_num;
	}
	
}