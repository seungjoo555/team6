package community2.model.vo;

import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {
	// 게시글
	private int po_num;
	private String po_title;
	private String po_content;
	private int po_view;
	private String po_me_id;
	private int po_bo_num;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return po_num == other.po_num;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(po_num);
	}
	
	@Override
	public String toString() {
		return "[" + po_num + "] " + "제목 : " + po_title + ", " + "내용 : " + po_content + ", "
				+ "작성자 : " + po_me_id + ", " + "조회수 : " + po_view;
	}
	
	public String toString1() {
		return "내용 : " + po_content;
	}
	
	public Post(int boardNum, String title, String content, String id) {
		po_bo_num = boardNum;
		po_title = title;
		po_content = content;
		po_me_id = id;
	}
	
	public Post(int postNum) {
		po_num = postNum;
	}
}
