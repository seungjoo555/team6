package homework;

import java.util.ArrayList;

import lombok.Data;

@Data
public class WordManager {

	private ArrayList<Word> list = new ArrayList<Word>();
	
	public boolean insertWord(Word word) {
		if(list.contains(word)) {
			return false;
		}
		list.add(word);
		return true;
	}
	
	
	//학생 정보 출력
	public void printAll() {
		list.stream().forEach(s->System.out.println(s));
	}
	
	public void setList(ArrayList<Word> list) {
		if(list == null) {
			return;
		}
		this.list = list;
	}
}
