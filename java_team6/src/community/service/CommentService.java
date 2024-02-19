package community.service;

import java.util.List;

import community.model.vo.CategoryVO;
import community.model.vo.Comment;

public interface CommentService {

	List<CategoryVO> getCategoryList();

	List<Comment> getCommentList(Comment comment);

	boolean insertComment(Comment comment);

	boolean updateComment(Comment comment);

	boolean deleteComment(Comment comment);
}