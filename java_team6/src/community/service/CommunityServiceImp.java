package community.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import community.dao.CommunityDAO;
import community.model.vo.Board;
import community.model.vo.Category;
import community.model.vo.Comment;
import community.model.vo.Member;
import community.model.vo.Post;

public class CommunityServiceImp implements CommunityService {
	
	private CommunityDAO communityDao;
	private InputStream inputStream;
	private SqlSession session;
	
	public CommunityServiceImp() {
		String resource = "community/config/mybatis-config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			communityDao = session.getMapper(CommunityDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Board> getBoardList() {
		return communityDao.selectBoardList();
	}

	@Override
	public boolean insertPost(Post post) {
		if(post == null 
				|| post.getPo_title() == null 
				|| post.getPo_content() == null
				|| post.getPo_me_id() == null) {
			return false;
		}
		boolean res = communityDao.insertPost(post);
		if(res) {
			session.commit();
		}
		return res;
	}

	@Override
	public List<Post> getPostList() {
		return communityDao.selectPostList();
	}

	@Override
	public boolean updatePost(Post post) {
		if(post == null 
				|| post.getPo_title() == null 
				|| post.getPo_content() == null
				|| post.getPo_me_id() == null) {
			return false;
		}
		return communityDao.updatePost(post);
	}

	@Override
	public boolean deleteItem(int postNum) {
		return communityDao.deletePost(postNum);
	}

	@Override
	public boolean insertComment(Comment com) {
		if(com == null 
		   || com.getCo_content() == null
		   || com.getCo_me_id() == null) {
			return false;
		}
		boolean res = communityDao.insertComment(com);
		if(res) {
			session.commit();
		}
		return res;
	}

	@Override
	public List<Comment> getCommentList() {
		return communityDao.selectCommentList();
	}

	@Override
	public List<Comment> getMineCommentList(Comment com) {
		return communityDao.selectMineCommentList(com);
	}

	@Override
	public boolean deleteComment(int co_num) {
		return communityDao.deleteComment(co_num);
	}

	@Override
	public boolean updateComment(Comment comment) {
		return communityDao.updateComment(comment);
	}
	
	@Override
	public List<Category> getCategoryList() {
		return communityDao.selectCategoryList();
	}

}