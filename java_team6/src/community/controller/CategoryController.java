package community.controller;

import java.util.List;
import java.util.Scanner;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;
import community.service.CategoryService;
import community.service.CategoryServiceImp;

// 카테고리 관리 겸 게시판 관리 클래스
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
			System.out.println(">>>>>>\n프로그램 종료");
			break;
		case 1:
			categoryManager(menu);
			break;
		case 2:
			boardManager(menu);
			break;
		default:
			System.out.println(">>>>>>\n잘못 입력했습니다.");
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
			
			List<BoardVO> bvList = caService.selectBoardList(bo_ca_num, null);
			
			for (BoardVO bv : bvList) {
				System.out.println(bv);
				System.out.print("수정 할 게시판 명 : ");
				String bo_name = sc.next();
				if (!bv.getBo_name().equals(bo_name)) {
					System.out.println(">>>>>>\n" + bo_name + " 는(은) 존재하지 않는 게시판 입니다.");
					return;
				}
				
				System.out.print("새로운 게시판 : ");
				String new_bo_name = sc.next();
				if (bo_name.equals(new_bo_name)) {
					System.out.println(">>>>>>\n현재 게시판과 새로운 게시판 이름이 동일합니다.");
					return;
				}
				
				for (BoardVO board : bvList) {
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
			System.out.println(">>>>>>\n존재하는 카테고리가 없습니다." + e.getMessage());
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
			
			List<BoardVO> bvList = caService.selectBoardList(bo_ca_num, null);
			for (BoardVO bv : bvList) {
				System.out.println(bv);
			}
			
			// 게시판 삭제 코딩하기
			System.out.print("삭제 할 게시판 명 : ");
			String bo_name = sc.next();
			bvList = caService.selectBoardList(bo_ca_num, bo_name);
			if (bvList == null || bvList.size() == 0) {
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
			System.out.println(">>>>>>\n존재하는 카테고리가 없습니다.");
		}
	}

	// 게시판 조회
	private void selectBoard() {
		List<BoardVO> bvList = caService.selectBoardList(0, null);
		if (!bvList.isEmpty()) {
			for (BoardVO tmp : bvList) {
				System.out.println(tmp);
			}
		} else {
			System.out.println(">>>>>>\n조회 가능한 게시판이 없습니다.");
		}
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

	// 조회
	private void selectCategory() {

		List<CategoryVO> caList = caService.selectCategoryList();
		if (!caList.isEmpty()) {
			for (CategoryVO tmp : caList) {
				System.out.println(tmp);
			}
		} else {
			System.out.println(">>>>>>\n조회 가능한 카테고리가 없습니다.");
		}
	}

	// 삭제
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

	// 수정
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
			if (ca_title.equals(new_ca_title)) {
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
		System.out.println("0.프로그램 종료");
		System.out.println("---------------");
		System.out.println("메뉴 입력 : ");
	}
}