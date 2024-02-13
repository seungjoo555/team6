package community.service;

import java.util.List;

import community.model.vo.BoardVO;
import community.model.vo.CommentVO;
import community.model.vo.CommunityVO;
import community.model.vo.MemberVO;
import community.model.vo.PostVO;

public interface CommunityService {
	

	List<CommunityVO> addCommunity(List<CommunityVO> comuList);
	List<CommunityVO> setCommunity(List<CommunityVO> comuList);
	List<CommunityVO> delCommunity(List<CommunityVO> comuList);
	List<CommunityVO> printCommunity(List<CommunityVO> comuList);
	
	List<BoardVO> addBoard(List<BoardVO> boList,List<CommunityVO> comuList);
	List<BoardVO> setBoard(List<BoardVO> boList,List<CommunityVO> comuList);
	List<BoardVO> delBoard(List<BoardVO> boList,List<CommunityVO> comuList);
	List<BoardVO> printBoard(List<BoardVO> boList,List<CommunityVO> comuList);
	
	List<CommentVO> addComment(List<CommentVO> coList,List<PostVO> poList);
	List<CommentVO> setComment(List<CommentVO> coList,List<PostVO> poList);
	List<CommentVO> delComment(List<CommentVO> coList,List<PostVO> poList);
	List<CommentVO> printComment(List<CommentVO> coList,List<PostVO> poList);
	
	List<PostVO> addPost(List<PostVO> poList,List<BoardVO> boList);
	List<PostVO> setPost(List<PostVO> poList,List<BoardVO> boList);
	List<PostVO> delPost(List<PostVO> poList,List<BoardVO> boList);
	List<PostVO> printPost(List<PostVO> poList,List<BoardVO> boList);
	
	List<MemberVO> addMember(List<MemberVO> meList);
	List<MemberVO> setMember(List<MemberVO> meList);
	List<MemberVO> delMember(List<MemberVO> meList);
	List<MemberVO> printMember(List<MemberVO> meList);
	

}
