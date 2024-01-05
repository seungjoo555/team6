package homework;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Word implements Serializable {
	
	/**
	 * 
	 */
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
		return "[" + word + " - " + "뜻 = " + mean + "]";
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public void setMean(String mean2) {
		this.mean.add(mean2);
	}
	
	public void deleteMean(String mean2) {
		this.mean.remove(mean2);
	}

	public void deleteWord(String changeWord) {

		
	}
}
