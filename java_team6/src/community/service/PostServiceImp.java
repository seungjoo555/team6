package community.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import community.dao.PostDAO;
import community.model.vo.BoardVO;
import community.model.vo.Post;
import community.pagination.Criteria;

public class PostServiceImp implements PostService {
	
	private PostDAO postDao;
	private InputStream inputStream;
	private SqlSession session;
	
	public PostServiceImp() {
		String resource = "community/config/mybatis-config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			postDao = session.getMapper(PostDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BoardVO> getBoardList() {
		return postDao.selectBoardList();
	}

	@Override
	public boolean insertPost(Post post) {
		if(post == null 
				|| post.getPo_title() == null 
				|| post.getPo_content() == null
				|| post.getPo_me_id() == null) {
			return false;
		}
		boolean res = postDao.insertPost(post);
		if(res) {
			session.commit();
		}
		return res;
	}

	@Override
	public List<Post> getPostList() {
		return postDao.selectPostList();
	}

	@Override
	public boolean updatePost(Post post) {
		if(post == null 
				|| post.getPo_title() == null 
				|| post.getPo_content() == null
				|| post.getPo_me_id() == null) {
			return false;
		}
		return postDao.updatePost(post);
	}

	@Override
	public boolean deletePost(int postNum) {
		return postDao.deletePost(postNum);
	}

	@Override
	public boolean upView(int postNum) {
		return postDao.upView(postNum);
		
	}

	@Override
	public List<Post> getPostList(Criteria cri) {
		if(cri == null) {
			cri = new Criteria();
		}
		return postDao.selectPostListSearch(cri);
	}

	@Override
	public Post getPostContent(int postNum) {
		return postDao.selectPostContent(postNum);
	}

	@Override
	public boolean updateView(int postNum) {
		return postDao.updateView(postNum);
	}
}
