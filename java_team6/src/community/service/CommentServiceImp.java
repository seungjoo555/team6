package community.service;

import java.util.List;

import community.dao.CommentDAO;
import community.model.vo.CategoryVO;
import community.model.vo.Comment;

public class CommentServiceImp implements CommentService {

	private CommentDAO commentDao;
	
	@Override
	public List<CategoryVO> getCategoryList() {
		return commentDao.selectCategoryList();
	}

	@Override
	public List<Comment> getCommentList(Comment com) {
		return commentDao.selectCommentList(com);
	}

	@Override
	public boolean insertComment(Comment com) {
		return false;
	}

	@Override
	public boolean updateComment(Comment com) {
		return false;
	}

	@Override
	public boolean deleteComment(Comment com) {
		return commentDao.deleteComment(com);
	}
}