package community.service;

import java.util.ArrayList;
import java.util.List;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;

public interface CategoryService {

	//카테고리
	boolean insertCategory(String ca_title);
	boolean updateCategory(String ca_title,String new_ca_title );
	boolean deleteCategory(String ca_title);
	List<CategoryVO> selectCategory(String ca_title);
	List<CategoryVO> selectCategoryList();

	
	//게시판
	boolean insertBoard(BoardVO board);
	boolean updateBoard(BoardVO board);
	boolean deleteBoard(BoardVO board);
	ArrayList<BoardVO> selectBoardList();
}
