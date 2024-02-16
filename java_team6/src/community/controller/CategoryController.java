package community.controller;

import java.util.List;
import java.util.Scanner;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;
import community.service.CategoryService;
import community.service.CategoryServiceImp;

public class CategoryController {

	private CategoryService caService;
	private Scanner sc;

	public CategoryController(Scanner sc) {
		if (sc == null) {
			sc = new Scanner(System.in);
		}
		this.sc = sc;
		caService = new CategoryServiceImp();
	}

	public void run() {
		int menu;
		do {
			printMainMenu();
			menu = sc.nextInt();
			runMenu(menu);
		} while (menu != 0);

	}

	public void runMenu(int menu) {
		switch (menu) {
		case 0:
			System.out.println("프로그램 종료");
			break;
		case 1:
			categoryManager(menu);
			break;
		case 2:
			boardManager(menu);
			break;
		default:
			System.out.println("잘못 입력했습니다.");
		}

	}

	private void boardManager(int menu) {

		do {
			printBoard();
			menu = sc.nextInt();
			runBoard(menu);

		} while (menu != 0);

	}

	private void runBoard(int menu) {
		switch (menu) {
		case 0:
			System.out.println("뒤로가기");
			break;
		case 1:
			addBoard(); // 추가
			break;
		case 2:
			updateBoard();// 수정
			break;
		case 3:
			deleteBoard();// 삭제
			break;
		case 4:
			selectBoard();// 조회
			break;
		default:
			System.out.println("잘못 입력 하셨습니다. 다시 입력하세요");

		}

	}

	// 게시판 추가
	private void addBoard() {
		try {

			// 카테고리를 선택
			System.out.print("카테고리 번호를 선택하세요");
			int bo_ca_num = sc.nextInt();
			// 카테고리가 없으면 리턴
			List<BoardVO> bvList = caService.selectBoardList(bo_ca_num);
			if (bvList.isEmpty()) {
				System.out.println("카테고리 안에 존재하는 게시판이 없습니다.");
				return;
			} else {
				System.out.println("선택한 카테고리의 게시판 목록 : ");
				for (BoardVO bv : bvList) {
					System.out.println(bv);
				}

			}
			// 카테고리가 있으면 게시판 추가 시작
			// 게시판 이름 받기
			System.out.println("추가할 게시판 : ");
			String bo_name = sc.next();
			List<BoardVO> bv = caService.selectBoardList(new BoardVO(bo_name));
			if(bv.equals(bo_name)) {
				System.out.println("존재하는 게시판 입니다.");
				return;
			}else {
			// 카테고리에 게시판 저장
			caService.insertBoard(bo_ca_num, bo_name);
			}
			System.out.println("게시판 추가 완료");

		} catch (Exception e) {
			System.out.println("존재하는 카테고리가 없습니다." + e.getMessage());
		}
	}

	// 게시판 수정
	private void updateBoard() {

	}

	// 게시판 삭제
	private void deleteBoard() {

	}

	// 게시판 조회
	private void selectBoard() {

	}

	// 게시판 메뉴 출력
	private void printBoard() {
		System.out.println("1.게시판 추가");
		System.out.println("2.게시판 수정");
		System.out.println("3.게시판 삭제");
		System.out.println("4.게시판 조회");
		System.out.println("0.뒤로 가기");
		System.out.println("-------------");
		System.out.println("메뉴 입력 : ");

	}

	private void categoryManager(int menu) {

		do {
			printCategory();
			menu = sc.nextInt();
			runCategory(menu);

		} while (menu != 0);

	}

	private void runCategory(int menu) {
		switch (menu) {
		case 0:
			System.out.println("뒤로가기");
			break;
		case 1:
			addCategory(); // 추가
			break;
		case 2:
			updateCategory();// 수정
			break;
		case 3:
			deleteCategory();// 삭제
			break;
		case 4:
			selectCategory();// 조회
			break;
		default:
			System.out.println("잘못 입력 하셨습니다. 다시 입력하세요");

		}

	}

	// 조회
	private void selectCategory() {
		System.out.println("1.카테고리 전체 조회");
		System.out.println("2.카테고리 개별 조회");
		System.out.println("0.뒤로 가기");
		System.out.println("---------------");
		System.out.println("메뉴 입력 : ");
		int menu = sc.nextInt();
		switch (menu) {
		case 0:
			System.out.println("뒤로가기");
			break;
		case 1:
			// 전체 조회
			allCategory();
			break;
		case 2:
			// 개별 조회 카테고리 조회시 안에 있는 게시판까지 보여주기
			eachCategory();
			break;
		default:
			System.out.println("잘못 입력 하셨습니다. 다시 입력하세요");
			// 뒤로가기

		}
	}

	// 카테고리 개별 조회 메서드
	private void eachCategory() {
		System.out.println("개별 조회 구현중");
	}

	// 카테고리 전체 조회 메서드
	private void allCategory() {
		List<CategoryVO> caList = caService.selectCategoryList();
		for (CategoryVO tmp : caList) {
			System.out.println(tmp);
		}
	}

	// 삭제
	private void deleteCategory() {

		System.out.print("삭제 할 카테고리 : ");
		String ca_title = sc.next();
		List<CategoryVO> cvList = caService.selectCategory(ca_title);
		if (cvList == null || cvList.size() == 0) {
			System.out.println("카테고리가 존재하지 않습니다.");
			return;
		}
		if (caService.deleteCategory(ca_title)) {
			System.out.println("내역을 삭제 했습니다.");
		} else {
			System.out.println("내역 삭제에 실패 했습니다.");
			return;
		}

	}

	// 수정
	private void updateCategory() {
		try {
			System.out.print("수정 할 카테고리 : ");
			String ca_title = sc.next();
			System.out.print("새로운 카테고리 : ");
			String new_ca_title = sc.next();
			if (caService.updateCategory(ca_title, new_ca_title)) {
				System.out.println("카테고리 수정이 완료 되었습니다.");
				return;
			}
			System.out.println("카테고리 수정에 실패하였습니다.");
			return;
		} catch (Exception e) {
			System.out.println("중복된 카테고리 입니다.");
		}
		return;
	}

//	private CategoryVO inputCategoty() {
//
//		// 카테고리 선택
//		List<CategoryVO> cvList = caService.selectCategory(null);
//		for (CategoryVO cv : cvList) {
//			System.out.println(cv);
//		}
//		if (cvList == null || cvList.size() == 0) {
//			System.out.println("카테고리가 없습니다.");
//			return null;
//		}
//		System.out.print("카테고리명을 입력 하세요. : ");
//		String ca_title = sc.next();
//		// 입력한 게시판 번호가 잘못된 값인지 확인
//		if (!cvList.contains(new CategoryVO(ca_title))) {
//			System.out.println("잘못된 게시판 번호입니다.");
//			return null;
//		}
//
//		System.out.print("새 카테고리명 : ");
//		ca_title = sc.next();
//
//		return new CategoryVO(ca_title);
//	}

	// 추가
	private CategoryVO addCategory() {
		try {
			System.out.print("추가 할 카테고리명을 입력하세요 : ");
			String ca_title = sc.next();
			List<CategoryVO> cvList = caService.selectCategory(ca_title);
			if (cvList.equals(ca_title) && cvList != null) {
				return (CategoryVO) cvList;
			}
			if (caService.insertCategory(ca_title)) {
				System.out.println("카테고리 추가 성공!");
			} else {
				System.out.println("카테고리 추가 실패!");
			}
		} catch (Exception e) {
			System.out.println("중복된 카테고리 입니다.");
		}
		return null;

	}

	private void printCategory() {
		System.out.println("----카테고리----");
		System.out.println("1.카테고리 추가");
		System.out.println("2.카테고리 수정");
		System.out.println("3.카테고리 삭제");
		System.out.println("4.카테고리 조회");
		System.out.println("0.뒤로가기");
		System.out.println("-------------");
		System.out.println("메뉴 입력 : ");

	}

	public void printMainMenu() {
		System.out.println("---관리 프로그램---");
		System.out.println("1.카테고리 관리");
		System.out.println("2.게시판 관리");
		System.out.println("0.프로그램 종료");
		System.out.println("---------------");
		System.out.println("메뉴 입력 : ");

	}

}
