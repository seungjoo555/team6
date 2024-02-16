package community.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

// 커뮤니티 안에 게시판 넣기
@Data
@NoArgsConstructor
public class BoardVO {
	private int bo_num;
	private String bo_name;
	private int bo_ca_num;
	
	
	
	public BoardVO(int bo_num, String bo_name,int bo_ca_num) {
		this.bo_num = bo_num;
		this.bo_name = bo_name;
		this.bo_ca_num = bo_ca_num;
	}



	public BoardVO(int bo_num) {
		this.bo_num = bo_num;
	}



	@Override
	public int hashCode() {
		return Objects.hash(bo_ca_num, bo_num);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardVO other = (BoardVO) obj;
		return bo_ca_num == other.bo_ca_num && bo_num == other.bo_num;
	}



	@Override
	public String toString() {
		return "----게시판----"+ "\n게시판 번호 :" + bo_num + "\n게시판 이름 :" + bo_name;
	}



	public BoardVO(String bo_name) {
		this.bo_name = bo_name;
	}
	
	
}
	
	
