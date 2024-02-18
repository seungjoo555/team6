package community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.Member;

public interface UserDAO {

	boolean insertMember(@Param("member")Member member);

	Member searchId(@Param("me_id")String id);

	List<Member> selectRequestMemberList(@Param("me_ms_state")String string);

	void updateAllRequestMember();

	boolean updateRequestMember(@Param("me_id")String me_id);

}