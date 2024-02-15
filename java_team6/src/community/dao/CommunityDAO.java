package community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.Board;
import community.model.vo.Post;

public interface CommunityDAO {

	List<Board> selectBoardList();

	boolean insertPost(@Param("post")Post post);

	List<Post> selectPostList();

	boolean updatePost(@Param("post")Post post);

	boolean deletePost(int postNum);

	boolean updateView(int postNum);

}
