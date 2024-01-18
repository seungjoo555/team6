package university;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import lombok.Data;

// 교수 리스트
@Data
public class Professor {
	//이름, 전공, 학력(학위), 학과, 교수 고유번호
	//학과는 비워두었다가 생성된 학과 교수리스트에 들어가면 학과의 이름을 넣는 방식
	private String name, major, education, department; //이름, 전공, 학력, 학과
	private int num;		//고유 번호
	private Date regDate;	//임용날짜

	//삭제시 사용할 해시코드
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		return Objects.equals(major, other.major) && Objects.equals(name, other.name) && num == other.num;
	}

	@Override
	public int hashCode() {
		return Objects.hash(major, name, num);
	}
	
	
	//추가용 생성자
	//교수 이름, 전공, 학력, 임용날짜 (학과는 과 생성후 변경)
	public Professor (String name, String major, String education, String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.name = name;
			this.major = major;
			this.education = education;
			this.regDate = format.parse(date);
		} catch (ParseException e) {
			System.out.println("날짜입력 형식이 잘못되었습니다.");
		}
	}
	
	//수정, 삭제용 생성자
	public Professor (String name, String major, int num) {
		this.name = name;
		this.major = major;
		this.num = num;
	}
	
}
