package community.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;

public interface CategoryDAO {
	
	// 카테고리
	boolean insertCategory(@Param("ca_title")String ca_title);
	boolean updateCategory(@Param("ca_title") String ca_title, @Param("new_ca_title") String new_ca_title );
	boolean deleteCategory(@Param("ca_title")String ca_title);
	List<CategoryVO> selectCategory(@Param("ca_title")String ca_title);

	ArrayList<CategoryVO> selectCategoryList();
	
	//----------------------------------------------------------
	List<CategoryVO> selectCategoryList(String ca_title);
	// 게시판
	boolean insertBoard(@Param("bo_ca_num")int bo_ca_num,@Param("bo_name")String bo_name);
	boolean updateBoard(@Param("bo_name")String bo_name,@Param("new_bo_name")String new_bo_name);
	boolean deleteBoard(@Param("bo_name")String bo_name);

	ArrayList<BoardVO> selectBoardList(int bo_ca_num,String bo_name);
	List<BoardVO> selectEachBoardList(int bo_ca_num);
	

}
