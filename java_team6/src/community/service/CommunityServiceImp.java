package community.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import community.dao.CommentDAO;
import community.dao.CommunityDAO;
import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;
import community.model.vo.Comment;
import community.model.vo.Post;
import community.pagination.Criteria;

public class CommunityServiceImp implements CommunityService {
	
	private CommunityDAO communityDao;
	private CommentDAO commentDao;
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
	public List<BoardVO> getBoardList() {
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
	public boolean deletePost(int postNum) {
		return communityDao.deletePost(postNum);
	}

	@Override
	public boolean upView(int postNum) {
		return communityDao.upView(postNum);
		
	}

	@Override
	public List<Post> getPostList(Criteria cri) {
		if(cri == null) {
			cri = new Criteria();
		}
		return communityDao.selectPostListSearch(cri);
	}

	@Override
	public Post getPostContent(int postNum) {
		return communityDao.selectPostContent(postNum);
	}

	@Override
	public boolean updateView(int postNum) {
		return communityDao.updateView(postNum);
	}
}