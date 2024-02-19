package community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.CategoryVO;
import community.model.vo.Comment;

public interface CommentDAO {
	
	boolean insertComment(@Param("com") Comment com);

	boolean deleteComment(@Param("com") Comment com);

	boolean updateComment(@Param("com") Comment com);

	List<Comment> selectCommentList(@Param("com") Comment com);

	List<CategoryVO> selectCategoryList();
}