package university.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import university.School;

public class FileServiceImp implements FileService{

	@Override
	public School load(String fileName) {
		try(ObjectInputStream ois = 
				new ObjectInputStream(new FileInputStream(fileName))){
				return (School) ois.readObject();
			} catch (Exception e) {
			}
		return new School();
	}

	@Override
	public boolean save(String fileName, School school) {
		try(ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream(fileName))){
				oos.writeObject(school);
				return true;
			}catch(Exception e) {
			}
		return false;
	}
}