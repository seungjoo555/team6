package community.service;

import java.util.List;

import community.model.vo.Board;
import community.model.vo.Comment;
import community.model.vo.Member;
import community.model.vo.Post;

public interface CommunityService {

	List<Board> getBoardList();

	boolean insertPost(Post post);

	List<Post> getPostList();

	boolean updatePost(Post post);

	boolean deleteItem(int postNum);

	boolean insertComment(Comment com);

	List<Comment> getCommentList();

	List<Comment> getMineCommentList(String id);

	boolean deleteComment(int co_num);

	boolean updateComment(Comment comment);

	List<Member> getMemberList();

}
