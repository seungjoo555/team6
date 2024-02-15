package community.service;

import java.util.ArrayList;

import community.model.vo.BoardVO;

public interface BoardService {
	

	boolean insertBoard(BoardVO board);
	boolean updateBoard(BoardVO board);
	boolean deleteBoard(BoardVO board);
	ArrayList<BoardVO> selectBoardList();
	
}
