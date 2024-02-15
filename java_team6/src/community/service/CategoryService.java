package community.service;

import java.util.List;

import community.model.vo.CategoryVO;

public interface CategoryService {


	boolean insertCategory(CategoryVO category);
	boolean updateCategory(CategoryVO category);
	boolean deleteCategory(int ca_num);
	List<CategoryVO> selectCategory(int ca_num);

}
