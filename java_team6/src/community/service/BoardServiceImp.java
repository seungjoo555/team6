package community.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import community.dao.BoardDAO;
import community.dao.CategoryDAO;
import community.model.vo.BoardVO;

//게시판 관리
public class BoardServiceImp implements BoardService{
	
	private BoardDAO boDao;
	private InputStream inputStream;
	private SqlSession session;
	
	
	public BoardServiceImp() {
		String resource = "community/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			boDao = session.getMapper(BoardDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public boolean insertBoard(BoardVO board) {
		
		return false;
		
	}
	@Override
	public boolean updateBoard(BoardVO board) {

		return false;
	}
	@Override
	public boolean deleteBoard(BoardVO board) {

		return false;
	}
	@Override
	public ArrayList<BoardVO> selectBoardList() {

		return null;
	}

}
