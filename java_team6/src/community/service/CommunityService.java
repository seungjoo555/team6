package community.service;

import java.util.List;

import community.model.vo.Board;
import community.model.vo.Post;

public interface CommunityService {

	List<Board> getBoardList();

	boolean insertPost(Post post);

	List<Post> getPostList();

	boolean updatePost(Post post);

	boolean deletePost(int postNum);

	boolean upView(int postNum);
}
