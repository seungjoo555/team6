package community.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import community.dao.CommentDAO;
import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;
import community.model.vo.Comment;

public class CommentServiceImp implements CommentService {

	private CommentDAO commentDao;
	private InputStream inputStream;
	private SqlSession session;
	
	public CommentServiceImp() {
		String resource = "community/config/mybatis-config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			commentDao = session.getMapper(CommentDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<CategoryVO> getCategoryList() {
		return commentDao.selectCategoryList();
	}

	@Override
	public List<Comment> getCommentList(int co_po_num) {
		return commentDao.selectCommentList(co_po_num);
	}

	@Override
	public boolean insertComment(Comment com) {
		if(com == null 
				   || com.getCo_content() == null
				   || com.getCo_me_id() == null) {
					return false;
		}
		boolean res = commentDao.insertComment(com);
		if(res) {
			session.commit();
		}
		return res;
	}

	@Override
	public boolean updateComment(Comment com) {
		return commentDao.updateComment(com);
	}

	@Override
	public boolean deleteComment(Comment com) {
		return commentDao.deleteComment(com);
	}
	
	@Override
	public List<BoardVO> getBoardList() {
		return commentDao.selectBoardList();
	}

	@Override
	public boolean deleteAdminComment(int co_num) {
		return commentDao.deleteAdminComment(co_num);
	}

	@Override
	public boolean resetNum() {
		return commentDao.resetComment();
	}
}