package program;

public interface Program {

	void run();
	void printMenu();
	void runMenu(int menu);
	void printExit();
	void save(String fileName);
	void load(String fileName);
}
