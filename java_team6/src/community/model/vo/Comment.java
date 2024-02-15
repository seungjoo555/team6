package community.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comment {
	// 댓글
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
	
	public Comment(int co_po_num, int co_num, String co_me_id, String co_content) {
		this.co_po_num = co_po_num;
		this.co_num = co_num;
		this.co_me_id = co_me_id;
		this.co_content = co_content;
	}
	public Comment(int co_num) {
		this.co_num = co_num;
	}
	
	public Comment(int co_num, String co_content) {
		this.co_num = co_num;
		this.co_content = co_content;
	}
	public Comment(String co_me_id) {
		this.co_me_id = co_me_id;
	}
	
}