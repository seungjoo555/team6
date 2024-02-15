package community.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;

public interface CategoryDAO {
	
	
	boolean insertCategory(@Param("category")CategoryVO category);
	boolean updateCategory(@Param("category")CategoryVO category);
	boolean deleteCategory(@Param("category")CategoryVO category);
	
	
	ArrayList<CategoryVO> selectCategoryList();
	List<CategoryVO> selectCategoryList(String ca_title);
	boolean deleteCategory(int ca_num);
	List<CategoryVO> selectCategory(@Param("ca_num")int ca_num);
	

	

}
