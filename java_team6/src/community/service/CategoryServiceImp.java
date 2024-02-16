package community.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import community.dao.CategoryDAO;
import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;

// 카테고리 관리 클래스
public class CategoryServiceImp implements CategoryService {

	private CategoryDAO caDao;
	private InputStream inputStream;
	private SqlSession session;

	public CategoryServiceImp() {
		String resource = "community/config/mybatis-config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			caDao = session.getMapper(CategoryDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override // 카테고리 추가
	public boolean insertCategory(String ca_title) {
		if (ca_title == null ) {
			return false;
		}
	
		return caDao.insertCategory(ca_title);
	}

	private boolean checkCategoryNum(String ca_title) {
		List<CategoryVO> cvList = caDao.selectCategoryList(ca_title);
		return cvList.contains(new CategoryVO(ca_title));
	}

	@Override // 카테고리 수정
	public boolean updateCategory(String ca_title,String new_ca_title ) {
		if (ca_title == null ) {
			return false;
		}
		if (checkCategoryNum(ca_title)) {
			return false;
		}
		return caDao.updateCategory(ca_title,new_ca_title);
	}

	@Override // 카테고리 삭제
	public boolean deleteCategory(String ca_title) {
		return caDao.deleteCategory(ca_title);
	}

	@Override // 카테고리 조회
	public List<CategoryVO> selectCategory(String ca_title) {
		return caDao.selectCategory(ca_title);

	}

	
	
	
	@Override // 게시판 추가
	public boolean insertBoard(BoardVO board) {
		
		
		return false;
	
	}
	@Override // 게시판 추가
	public boolean insertBoard(int bo_ca_num, String bo_name) {
		if (bo_ca_num == 0) {
			return false;
		}
		return caDao.insertBoard(bo_ca_num,bo_name);
	}

	@Override // 게시판 수정
	public boolean updateBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override // 게시판 삭제
	public boolean deleteBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override // 게시판 조회
	public ArrayList<BoardVO> selectBoardList(int bo_ca_num) {
		
		return caDao.selectBoardList(bo_ca_num);
	}

	@Override//카테고리 조회
	public List<CategoryVO> selectCategoryList() {
		
		return caDao.selectCategoryList();
	}

	@Override
	public List<BoardVO> selectBoardList(BoardVO boardVO) {
		
		return caDao.selectBoardList(boardVO);
	}


}
