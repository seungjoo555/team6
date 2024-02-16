package community.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;

public interface CategoryDAO {
	
	// 카테고리
	boolean insertCategory(@Param("ca_title")String ca_title);
	boolean updateCategory(@Param("category")CategoryVO category);
	boolean deleteCategory(@Param("ca_title")String ca_title);
	List<CategoryVO> selectCategory(@Param("ca_title")String ca_title);
	
	ArrayList<CategoryVO> selectCategoryList();
	List<CategoryVO> selectCategoryList(String ca_title);
	boolean deleteCategory(int ca_num);
	// 게시판
	boolean insertBoard(@Param("board")BoardVO board);
	boolean updateBoard(@Param("board")BoardVO board);
	boolean deleteBoard(@Param("board")BoardVO board);
	ArrayList<BoardVO> selectBoardList();

	

}
