package university.service;

// 프린트 구현클래스
public class PrintServiceImp implements PrintService {

	@Override
	public void printMainMenu() {
		System.out.println("------메인 메뉴------");
		System.out.println("1. 교수 관리");
		System.out.println("2. 학생 관리");
		System.out.println("3. 과 관리");
		System.out.println("4. 강의 관리");
		System.out.println("5. 수강 관리");
		System.out.println("6. 조회");
		System.out.println("7. 프로그램 종료");
		System.out.println("-------------------");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void printPFMMenu() {
		System.out.println("-----교수 관리 메뉴-----");
		System.out.println("1. 교수 등록");
		System.out.println("2. 교수 수정");
		System.out.println("3. 교수 삭제");
		System.out.println("4. 이전으로");
		System.out.println("--------------------");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void printSTDMMenu() {
		System.out.println("-----학생 관리 메뉴-----");
		System.out.println("1. 학생 등록");
		System.out.println("2. 학생 수정");
		System.out.println("3. 학생 삭제");
		System.out.println("4. 이전으로");
		System.out.println("--------------------");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void printDPMMenu() {
		System.out.println("-----학과 관리 메뉴-----");
		System.out.println("1. 학과 등록");
		System.out.println("2. 학과 수정");
		System.out.println("3. 학과 삭제");
		System.out.println("4. 이전으로");
		System.out.println("--------------------");
		System.out.print("메뉴 선택 : ");
	}

	@Override
	public void printSJMMenu() {
		System.out.println("-----교수 관리 메뉴-----");
		System.out.println("1. 강의 등록");
		System.out.println("2. 강의 수정");
		System.out.println("3. 강의 삭제");
		System.out.println("4. 이전으로");
		System.out.println("--------------------");
		System.out.print("메뉴 선택 : ");
	}

}
