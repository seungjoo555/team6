package community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.Board;
import community.model.vo.Comment;
import community.model.vo.Member;
import community.model.vo.Post;

public interface CommunityDAO {

	List<Board> selectBoardList();

	boolean insertPost(@Param("post")Post post);

	List<Post> selectPostList();

	boolean updatePost(@Param("post")Post post);

	boolean deletePost(int postNum);

	List<Comment> selectCommentList();

	List<Comment> selectMineCommentList(@Param("id") String id);

	boolean insertComment(@Param("com") Comment com);

	boolean deleteComment(@Param("co_num") int co_num);

	boolean updateComment(@Param("comment") Comment comment);

	List<Member> selectMemberList();

}