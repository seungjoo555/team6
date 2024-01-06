package homework;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Word implements Serializable {
	
	private static final long serialVersionUID = 832656411971539925L;
	//단어 - word, 뜻 - mean;
	@NonNull
	private String word;
	private ArrayList<String> mean = new ArrayList<String>();
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		return Objects.equals(word, other.word);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(word);
	}

	@Override
	public String toString() {
		Iterator<String> it = mean.iterator();
		String outmean = "";
		while(it.hasNext()) {
			String tmp = it.next();
			outmean += tmp + " ";
		}
		return "단어 : " + word + "	뜻 : " + outmean;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public void setMean(String mean2) {
		this.mean.add(mean2);
	}
	
	/* 피드백
	 * - 해당 단어가 없을 때 삭제가 안되기 때문에 return을 boolean으로 해서 실패 여부를 알려주는게 좋음.
	 * */
	public void deleteMean(String mean2) {
		this.mean.remove(mean2);
	}

	/* 피드백
	 * - Word 클래스에 메서드를 추가
	 *   - 뜻을 주어지면 뜻 리스트에 해당 뜻이 몇번지에 있는지 알려주는 메서드
	 *   - 번지와 뜻이 주어지면 해당 번지에 뜻을 수정하는 메서드  
	 * - 위 메서드를 이용하면 뜻 수정 및 삭제할 때 활용할 수 있음
	 * 
	 * */
	
	
}
