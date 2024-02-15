package community.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import community.dao.CategoryDAO;
import community.model.vo.CategoryVO;

// 카테고리 관리 클래스
public class CategoryServiceImp implements CategoryService{
	
	private CategoryDAO caDao;
	private InputStream inputStream;
	private SqlSession session;
	public CategoryServiceImp() {
		String resource =  "community/config/mybatis-config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			caDao = session.getMapper(CategoryDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean insertCategory(CategoryVO category) {
		if(category == null || category.getCa_title() == null) {
			return false;
		}
		if(!checkCategoryNum(category.getCa_title(),category.getCa_num())) {
			return false;
		}
		return caDao.insertCategory(category);
	}
	
	
	private boolean checkCategoryNum(String ca_title, int ca_num) {
		List<CategoryVO> cvList = caDao.selectCategoryList(ca_title);
		return cvList.contains(new CategoryVO(ca_num));
	}
	@Override
	public boolean updateCategory(CategoryVO category) {
		if(category == null || category.getCa_title() == null) {
			return false;
		}
		if(!checkCategoryNum(category.getCa_title(),category.getCa_num())){
			return false;
		}
		return caDao.updateCategory(category);
	}
	@Override
	public boolean deleteCategory(int ca_num) {
		return caDao.deleteCategory(ca_num);
	}
	
	@Override
	public List<CategoryVO> selectCategory(int ca_num) {
		return caDao.selectCategory(ca_num);
		
	}
	
	
}
