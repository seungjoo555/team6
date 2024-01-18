package university;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

// 리스트 총괄 관리
@Data
public class School {

	List<Professor> professorList = new ArrayList<Professor>();
	
}
