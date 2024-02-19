package university.service;

import university.School;

public interface FileService {

	School load(String fileName);

	boolean save(String fileName, School school);
}
