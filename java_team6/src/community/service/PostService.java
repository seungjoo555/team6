package community.service;

import java.util.List;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;
import community.model.vo.Comment;
import community.model.vo.Member;
import community.model.vo.Post;
import community.pagination.Criteria;

public interface PostService {
	//게시글
	List<BoardVO> getBoardList();

	boolean insertPost(Post post);

	List<Post> getPostList();

	boolean updatePost(Post post);

	boolean deletePost(int postNum);

	boolean upView(int postNum);

	List<Post> getPostList(Criteria cri);

	Post getPostContent(int postNum);

	boolean updateView(int postNum);

	List<Post> getPost(Member id);

	List<Post> getPostList(int boardNum);

	List<Post> selectEachPostList(int po_bo_num);

}
