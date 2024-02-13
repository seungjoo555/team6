package community.model.vo;

import java.util.Objects;

public class BoardVO {
	private String BoardNum;
	private String BoardName;
	
	
	public BoardVO(String boardNum, String boardName) {
		super();
		BoardNum = boardNum;
		BoardName = boardName;
	}


	@Override
	public int hashCode() {
		return Objects.hash(BoardName, BoardNum);
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
		return Objects.equals(BoardName, other.BoardName) && Objects.equals(BoardNum, other.BoardNum);
	}


	@Override
	public String toString() {
		return "----게시판----"+ "\n게시판 번호 :" + BoardNum + "\n게시판 이름 :" + BoardName;
	}
	
	
}
	
	
