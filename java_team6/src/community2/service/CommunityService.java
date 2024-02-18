package community2.service;

import java.util.List;

import community2.model.vo.Board;
import community2.model.vo.Post;
import community2.pagination.Criteria;

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
