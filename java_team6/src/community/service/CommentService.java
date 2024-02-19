package community.service;

import java.util.List;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;
import community.model.vo.Comment;

public interface CommentService {

	List<CategoryVO> getCategoryList();
	
	List<BoardVO> getBoardList();

	List<Comment> getCommentList(int po_num);

	boolean insertComment(Comment comment);

	boolean updateComment(Comment comment);

	boolean deleteComment(Comment comment);

	boolean deleteAdminComment(int co_num);

	boolean resetNum();
}