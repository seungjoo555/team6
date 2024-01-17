package university;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


// 리스트 총괄 관리
@Data
@ToString
@NoArgsConstructor
public class School {
	private List<Professor> proList;
	
	public School(List<Professor> proList) {
		if(proList == null) {
			proList = new ArrayList<Professor>();
		}
		this.proList = proList;
	}

}
