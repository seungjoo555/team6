package acountbook.service;

import java.util.List;

import acountbook.Item;
import university.School;

public interface FileService {

	List<Item> load(String fileName);

	boolean save(String fileName, List<Item> list);

}
