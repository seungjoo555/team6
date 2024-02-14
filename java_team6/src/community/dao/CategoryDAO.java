package community.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;

public interface CategoryDAO {
	
	
	boolean insertCategory(@Param("category")BoardVO category);
	boolean updateCategory(@Param("category")BoardVO category);
	boolean deleteCategory(@Param("category")BoardVO category);
	ArrayList<CategoryVO> selectCategoryList();
	

	

}
