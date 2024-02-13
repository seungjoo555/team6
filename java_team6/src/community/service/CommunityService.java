package community.service;

import java.util.ArrayList;

import community.model.vo.CommunityVO;

public interface CommunityService {

	ArrayList<CommunityVO> getMemberList();

	boolean insertCommunity(CommunityVO community);

}