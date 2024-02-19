package community2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import community2.model.vo.Board;
import community2.model.vo.Post;
import community2.pagination.Criteria;

public interface CommunityDAO {

	List<Board> selectBoardList();

	boolean insertPost(@Param("post")Post post);

	List<Post> selectPostList();

	boolean updatePost(@Param("post")Post post);

	boolean deletePost(int postNum);

	boolean upView(int postNum);

	List<Post> selectPostListSearch(@Param("cri")Criteria cri);

	Post selectPostContent(int postNum);

	boolean updateView(int postNum);
}
