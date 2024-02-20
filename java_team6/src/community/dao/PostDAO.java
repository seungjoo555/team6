package community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.BoardVO;
import community.model.vo.Member;
import community.model.vo.Post;
import community.pagination.Criteria;


public interface PostDAO {

	List<BoardVO> selectBoardList();

	boolean insertPost(@Param("post")Post post);

	List<Post> selectPostList();

	boolean updatePost(@Param("post")Post post);

	boolean deletePost(int postNum);

	boolean upView(int postNum);

	List<Post> selectPostListSearch(@Param("cri")Criteria cri);

	Post selectPostContent(int postNum);

	boolean updateView(int postNum);

	List<Post> selectPost(Member id);

	List<Post> selectPostBolist(int boardNum);

	List<Post> selectEachPostList(int po_bo_num);

}