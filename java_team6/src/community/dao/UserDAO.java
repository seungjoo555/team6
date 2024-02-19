package community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.Member;

public interface UserDAO {

	boolean insertMember(@Param("member")Member member);

	Member searchId(@Param("me_id")String id);

	List<Member> selectMemberList(@Param("me_ms_state")String string);

	List<Member> selectStopMemberList(@Param("me_ms_state1")String string1, @Param("me_ms_state2")String string2);
	
	void updateAllRequestMember();

	boolean updateRequestMember(@Param("me_id")String me_id);

	boolean updateStopMember(@Param("me_id")String me_id);

	boolean deleteMember(@Param("me_id")String me_id);

	boolean updateMy(@Param("user")Member user);


}