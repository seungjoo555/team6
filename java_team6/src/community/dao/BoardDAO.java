package community.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import community.model.vo.BoardVO;

public interface BoardDAO {
	
	boolean insertBoadrd(@Param("board")BoardVO board);
	boolean updateBoadrd(@Param("board")BoardVO board);
	boolean deleteBoadrd(@Param("board")BoardVO board);
	ArrayList<BoardVO> selectBoardList();

}
