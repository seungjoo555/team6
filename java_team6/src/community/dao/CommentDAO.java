package community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;
import community.model.vo.Comment;

public interface CommentDAO {
	
	boolean insertComment(@Param("com") Comment com);

	boolean deleteComment(@Param("com") Comment com);

	boolean updateComment(@Param("com") Comment com);

	List<Comment> selectCommentList(@Param("co_po_num") int co_po_num);

	List<CategoryVO> selectCategoryList();
	
	List<BoardVO> selectBoardList();

	boolean deleteAdminComment(@Param("co_num") int co_num);

	boolean resetComment();
}