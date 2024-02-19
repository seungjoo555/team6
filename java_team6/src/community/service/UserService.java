package community.service;

import java.util.List;

import community.model.vo.Member;

public interface UserService {

	boolean insertMember(Member member);

	Member checkId(String id);

	List<Member> getMemberList(String string);
	
	List<Member> getStopMemberList(String string1, String string2);

	void okeydokeyAllRequest();

	boolean okeydokeyRequest(String me_id);

	boolean stopStateMember(String me_id);

	boolean deleteMember(String me_id);

	boolean updateMember(Member user);

}