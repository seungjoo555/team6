package community.model.vo;

import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Board {
	// 게시판
	private int bo_num;
	private String bo_name;
	
	public Board(int boardNum) {
		bo_num = boardNum;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		return bo_num == other.bo_num;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bo_num);
	}
	
}
