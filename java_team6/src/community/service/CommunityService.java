package community.service;

import java.util.List;

import community.model.vo.Board;
import community.model.vo.Post;
import community.pagination.Criteria;

public interface CommunityService {
	


	List<Board> getBoardList();

	boolean insertPost(Post post);

	List<Post> getPostList();

	boolean updatePost(Post post);

	boolean deletePost(int postNum);

	boolean upView(int postNum);

	List<Post> getPostList(Criteria cri);

	Post getPostContent(int postNum);

	boolean updateView(int postNum);
}
