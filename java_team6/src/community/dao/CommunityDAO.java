package community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.BoardVO;
import community.model.vo.Post;
import community.pagination.Criteria;


public interface CommunityDAO {

	List<BoardVO> selectBoardList();

	boolean insertPost(@Param("post")Post post);

	List<Post> selectPostList();

	boolean updatePost(@Param("post")Post post);

	boolean deletePost(int postNum);

	boolean upView(int postNum);

	List<Post> selectPostListSearch(@Param("cri")Criteria cri);

	Post selectPostContent(int postNum);

	boolean updateView(int postNum);
}
