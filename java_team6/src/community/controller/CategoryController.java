package community.controller;

import java.util.List;
import java.util.Scanner;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;
import community.model.vo.Post;
import community.service.CategoryService;
import community.service.CategoryServiceImp;
import community.service.PostService;
import community.service.PostServiceImp;

// 카테고리 관리 겸 게시판 관리 클래스
public class CategoryController {

	private PostService poService = new PostServiceImp();
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
			System.out.println(">>>>>>\n뒤로가기");
			break;
		case 1:
			categoryManager();
			break;
		case 2:
			boardManager();
			break;
		default:
			System.out.println(">>>>>>\n잘못 입력했습니다.");
		}
	}

	public void boardManager() {
		int menu;
		do {
			printBoard();
			menu = sc.nextInt();
			runBoard(menu);

		} while (menu != 0);
	}

	private void runBoard(int menu) {
		switch (menu) {
		case 0:
			System.out.println(">>>>>>\n뒤로가기");
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
			System.out.println(">>>>>>\n잘못 입력 하셨습니다. 다시 입력하세요");
		}
	}

	// 게시판 추가
	private void addBoard() {
		try {
			List<CategoryVO> cvList = caService.selectCategoryList();

			// 카테고리가 비어있는지 확인 비어있으면 리턴
			if (cvList.isEmpty()) {
				System.out.println(">>>>>>\n존재하는 카테고리가 없습니다.\n카테고리를 추가후 다시 실행하세요.");
				return;
			}

			// 카테고리 리스트 출력
			for (CategoryVO cv : cvList) {
				System.out.println(cv);
			}
			System.out.print("카테고리 번호를 선택하세요 : ");
			int bo_ca_num = sc.nextInt();

			boolean ok = false;

			// 입력 받은 카테고리가 있으면 게시판 추가로 감
			for (CategoryVO cv : cvList) {
				if (cv.getCa_num() == bo_ca_num) {
					ok = true;
					break;
				}
			}

			if (!ok) {
				System.out.println(">>>>>>\n존재하는 카테고리가 없습니다.");
				return;
			}

			System.out.print("추가할 게시판 입력 : ");
			String bo_name = sc.next();
			List<BoardVO> boList = caService.selectBoardList(bo_ca_num, bo_name);

			for (BoardVO bv : boList) {
				if (bv.getBo_name().equals(bo_name)) {
					ok = false;
					break;
				}
			}

			if (!ok) {
				System.out.println(">>>>>>\n중복된 게시판이 있습니다.");
				return;
			}

			if (caService.insertBoard(bo_ca_num, bo_name)) {
				System.out.println(">>>>>>\n게시판 추가가 완료 되었습니다.");
			} else {
				System.out.println(">>>>>>\n게시판 추가에 실패 했습니다.");
			}
		} catch (Exception e) {
			System.out.println(">>>>>>\n존재하는 카테고리가 없습니다.");
		}
	}

	// 게시판 수정
	private void updateBoard() {
		try {
			List<CategoryVO> cvList = caService.selectCategoryList();

			// 카테고리가 비어있는지 확인 비어있으면 리턴
			if (cvList.isEmpty()) {
				System.out.println(">>>>>>\n존재하는 카테고리가 없습니다.\n카테고리를 추가후 다시 실행하세요.");
				return;
			}

			// 카테고리 리스트 출력
			for (CategoryVO cv : cvList) {
				System.out.println(cv);
			}
			System.out.print("카테고리 번호를 선택하세요 : ");
			int bo_ca_num = sc.nextInt();
			List<BoardVO> boList = caService.selectEachBoardList(bo_ca_num);
			// 없으면 리턴
			if (boList.isEmpty()) {
				System.out.println(">>>>>>\n조회 가능한 게시판이 없습니다.");
				return;
			} else {
				System.out.println(">>>>>>\n선택한 카테고리의 게시판 목록");
				for (BoardVO tmp : boList) {
					System.out.println(tmp);

				}

			}

			for (BoardVO bv : boList) {
				System.out.println(bv);
				System.out.print("수정 할 게시판 명 : ");
				String bo_name = sc.next();
				if (bv != null || bv.equals(boList)) {

				} else {
					System.out.println(">>>>>>\n카테고리가 존재하지 않습니다.");
					return;
				}

				System.out.print("새로운 게시판 : ");
				String new_bo_name = sc.next();
				if (bo_name.equals(new_bo_name)) {
					System.out.println(">>>>>>\n현재 게시판과 새로운 게시판 이름이 동일합니다.");
					return;
				}

				for (BoardVO board : boList) {
					if (board.getBo_name().equals(new_bo_name)) {
						System.out.println(">>>>>>\n이미 존재하는 게시판 이름입니다. 다른 이름을 입력하세요.");
						return;
					}
				}
				if (caService.updateBoard(bo_name, new_bo_name)) {
					System.out.println(">>>>>>\n게시판 수정이 완료 되었습니다.");
					break;
				}

				System.out.println(">>>>>>\n게시판 수정에 실패하였습니다.");
				return;
			}
		} catch (Exception e) {
			System.out.println(">>>>>>\n존재하는 카테고리가 없습니다.");
			return;
		}
	}

	// 게시판 삭제
	private void deleteBoard() {
		try {
			List<CategoryVO> cvList = caService.selectCategoryList();

			// 카테고리가 비어있는지 확인 비어있으면 리턴
			if (cvList.isEmpty()) {
				System.out.println(">>>>>>\n존재하는 카테고리가 없습니다.\n카테고리를 추가후 다시 실행하세요.");
				return;
			}

			// 카테고리 리스트 출력
			for (CategoryVO cv : cvList) {
				System.out.println(cv);
			}

			System.out.print("카테고리 번호를 선택하세요 : ");
			int bo_ca_num = sc.nextInt();
			List<BoardVO> boList = caService.selectEachBoardList(bo_ca_num);
			// 없으면 리턴
			if (boList.isEmpty()) {
				System.out.println(">>>>>>\n조회 가능한 게시판이 없습니다.");
				return;
			} else {
				System.out.println(">>>>>>\n선택한 카테고리의 게시판 목록");
				for (BoardVO tmp : boList) {
					System.out.println(tmp);

				}

			}

			// 게시판 삭제 코딩하기
			System.out.print("삭제 할 게시판 명 : ");
			String bo_name = sc.next();
			boList = caService.selectBoardList(bo_ca_num, bo_name);
			if (boList == null || boList.size() == 0) {
				System.out.println(">>>>>>\n게시판이 존재하지 않습니다.");
				return;
			}
			if (caService.deleteBoard(bo_name)) {
				System.out.println(">>>>>>\n게시판을 삭제 했습니다.");
				return;
			} else {
				System.out.println(">>>>>>\n게시판 삭제에 실패 했습니다.");
				return;
			}

		} catch (Exception e) {
			System.out.println(">>>>>>\n존재하는 카테고리가 없습니다.1");
		}
	}

	// 게시판 전체 조회
	private void allBoard() {
		List<BoardVO> bvList = caService.selectBoardList(0, null);
		if (!bvList.isEmpty()) {
			for (BoardVO tmp : bvList) {
				System.out.println(tmp);
			}
		} else {
			System.out.println(">>>>>>\n조회 가능한 게시판이 없습니다.");
		}
	}

	private void selectBoard() {
		int menu;
		System.out.println("게시판 조회");
		System.out.println("1.게시판 전체 조회");
		System.out.println("2.게시판 상세 조회");
		System.out.println("---------------");
		System.out.print("메뉴 입력 : ");
		menu = sc.nextInt();
		switch (menu) {
		case 1:
			allBoard();
			break;
		case 2:
			eachBoard();
			break;
		default:
			System.out.println(">>>>>>\n잘못 입력 하셨습니다. 다시 입력하세요");

		}
	}

	// 게시판 상세 조회
	private void eachBoard() {
		try {
			List<BoardVO> boList = caService.selectBoardList(0, null);
			if (!boList.isEmpty()) {
				// 카테고리 리스트 출력
				for (BoardVO tmp : boList) {
					System.out.println(tmp);
				}
			} else {
				System.out.println(">>>>>>\n게시판안이 비어있습니다.");
				return;
			}
			// 카테고리 번호 입력

			System.out.print("게시판 번호를 입력하세요 : ");
			int po_bo_num = sc.nextInt();

			List<Post> poList = poService.selectEachPostList(po_bo_num);
			// 없으면 리턴
			if (poList.isEmpty()) {
				System.out.println(">>>>>>\n조회 가능한 게시판이 없습니다.");
				return;
			} else {
				System.out.println(">>>>>>\n선택한 게시판의 게시글 목록");
				for (Post tmp : poList) {
					System.out.println(tmp);

				}

			}
		} catch (Exception e) {
			System.out.println(">>>>>>\n게시판 안이 비어 있습니다.\n게시글을 추가하세요." + e.getMessage());
		}
		return;

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

	public void categoryManager() {
		int menu;
		do {
			printCategory();
			menu = sc.nextInt();
			runCategory(menu);
		} while (menu != 0);

	}

	private void runCategory(int menu) {
		switch (menu) {
		case 0:
			System.out.println(">>>>>>\n뒤로가기");
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
			System.out.println(">>>>>>\n잘못 입력 하셨습니다. 다시 입력하세요");
		}
	}

	private void allCategory() {
		List<CategoryVO> caList = caService.selectCategoryList();
		if (!caList.isEmpty()) {
			for (CategoryVO tmp : caList) {
				System.out.println(tmp);
			}
		} else {
			System.out.println(">>>>>>\n조회 가능한 카테고리가 없습니다.");
			return;
		}
	}

	// 카테고리만 조회
	private void selectCategory() {
		int menu;
		System.out.println("카테고리 조회");
		System.out.println("1.카테고리 전체 조회");
		System.out.println("2.카테고리 상세 조회");
		System.out.println("---------------");
		System.out.print("메뉴 입력 : ");
		menu = sc.nextInt();
		switch (menu) {
		case 1:
			allCategory();
			break;
		case 2:
			eachCategory();
			break;
		default:
			System.out.println(">>>>>>\n잘못 입력 하셨습니다. 다시 입력하세요");

		}

	}

	private void eachCategory() {
		try {

			List<CategoryVO> caList = caService.selectCategoryList();
			if (!caList.isEmpty()) {
				// 카테고리 리스트 출력
				for (CategoryVO tmp : caList) {
					System.out.println(tmp);
				}
			} else {
				System.out.println(">>>>>>\n조회 가능한 카테고리가 없습니다.");
				return;
			}
			// 카테고리 번호 입력

			System.out.print("카테고리 번호를 입력하세요 : ");
			int bo_ca_num = sc.nextInt();

			List<BoardVO> boList = caService.selectEachBoardList(bo_ca_num);
			// 없으면 리턴
			if (boList.isEmpty()) {
				System.out.println(">>>>>>\n조회 가능한 게시판이 없습니다.");
				return;
			} else {
				System.out.println(">>>>>>\n선택한 카테고리의 게시판 목록");
				for (BoardVO tmp : boList) {
					System.out.println(tmp);

				}

			}
		} catch (Exception e) {
			System.out.println(">>>>>>\n카테고리 안이 비어 있습니다.\n게시판을 추가하세요.");
		}
		return;

	}

	// 카테고리 삭제
	private void deleteCategory() {
		try {
			List<CategoryVO> cvList = caService.selectCategoryList();
			for (CategoryVO cv : cvList) {
				System.out.println(cv);
			}
			System.out.print("삭제 할 카테고리 명 : ");
			String ca_title = sc.next();
			cvList = caService.selectCategory(ca_title);
			if (cvList == null || cvList.size() == 0) {
				return;
			}
			if (caService.deleteCategory(ca_title)) {
				System.out.println(">>>>>>\n내역을 삭제 했습니다.");
			} else {
				System.out.println(">>>>>>\n내역 삭제에 실패 했습니다.");
				return;
			}
		} catch (Exception e) {
			System.out.println(">>>>>>\n카테고리가 존재하지 않습니다.");
		}
		return;
	}

	// 카테고리 수정
	private void updateCategory() {
		try {
			List<CategoryVO> cvList = caService.selectCategoryList();
			for (CategoryVO cv : cvList) {
				System.out.println(cv);
			}

			System.out.print("수정 할 카테고리 명 : ");
			String ca_title = sc.next();
			cvList = caService.selectCategory(ca_title);
			if (cvList == null || cvList.size() == 0) {
				System.out.println(">>>>>>\n카테고리가 존재하지 않습니다.");
				return;
			}

			System.out.print("새로운 카테고리 명 : ");
			String new_ca_title = sc.next();
			if (new_ca_title.equals(ca_title)) {
				System.out.println(">>>>>>\n!!! 현재 카테고리와 새로운 카테고리 이름이 동일합니다.");
				return;
			}
			if (!caService.updateCategory(ca_title, new_ca_title)) {
				System.out.println(">>>>>>\n카테고리 수정에 실패하였습니다.");
				return;
			}
			System.out.println(">>>>>>\n카테고리 수정이 완료 되었습니다.");
		} catch (Exception e) {
			System.out.println(">>>>>>\n중복된 카테고리 입니다.");
		}
		return;
	}

	// 카테고리 추가
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
				return (CategoryVO) cvList;
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
		System.out.println("0.뒤로 가기");
		System.out.println("---------------");
		System.out.println("메뉴 입력 : ");
	}
}