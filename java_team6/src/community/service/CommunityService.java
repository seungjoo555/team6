package community.service;

import java.util.List;

import community.model.vo.Board;
import community.model.vo.Category;
import community.model.vo.Comment;
import community.model.vo.Member;
import community.model.vo.Post;

public interface CommunityService {

	List<Board> getBoardList();

	boolean insertPost(Post post);

	List<Post> getPostList();

	boolean updatePost(Post post);

	boolean deleteItem(int postNum);

	List<Category> getCategoryList();

	List<Comment> getCommentList();

	boolean insertComment(Comment comment);

	List<Comment> getMineCommentList(Comment comment);

	boolean updateComment(Comment comment);

	boolean deleteComment(int co_num);
}