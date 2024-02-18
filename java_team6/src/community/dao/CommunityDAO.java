package community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.Board;
import community.model.vo.Category;
import community.model.vo.Comment;
import community.model.vo.Post;

public interface CommunityDAO {

	List<Board> selectBoardList();

	boolean insertPost(@Param("post")Post post);

	List<Post> selectPostList();

	boolean updatePost(@Param("post")Post post);

	boolean deletePost(int postNum);

	List<Comment> selectCommentList(@Param("com") Comment com);

	boolean insertComment(@Param("com") Comment com);

	boolean deleteComment(@Param("com") Comment com);

	boolean updateComment(@Param("com") Comment com);

	List<Category> selectCategoryList();

}