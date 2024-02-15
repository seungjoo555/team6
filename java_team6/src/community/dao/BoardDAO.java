package community.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import community.model.vo.BoardVO;

public interface BoardDAO {
	
	boolean insertBoard(@Param("board")BoardVO board);
	boolean updateBoard(@Param("board")BoardVO board);
	boolean deleteBoard(@Param("board")BoardVO board);
	ArrayList<BoardVO> selectBoardList();

}
