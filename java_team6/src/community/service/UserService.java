package community.service;

import java.util.List;

import community.model.vo.Member;

public interface UserService {

	boolean insertMember(Member member);

	Member checkId(String id);

	List<Member> getRequestMember(String string);

	void okeydokeyAllRequest();

	boolean okeydokeyRequest(String me_id);

}