package community.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import community.model.vo.CommentVO;
import community.model.vo.CommunityVO;
import community.model.vo.MemberVO;
import community.model.vo.Post;


public interface CommunityDAO {

	boolean insertCommunity(@Param("community")CommunityVO commuity);
	ArrayList<CommunityVO> selectCommunityList();
	
	boolean insertMember(@Param("member")MemberVO member);
	ArrayList<CommunityVO> selectMemberList();


	boolean insertPost(@Param("post")Post post);
	ArrayList<Post> selectPostList();
	
	boolean insertComment(@Param("comment")CommentVO comment);
	ArrayList<CommentVO> selectCommentList();

}
