package community.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import community.model.vo.BoardVO;
import community.model.vo.CategoryVO;

public class BoardController {
	private Scanner sc = new Scanner(System.in);

	public void run() {
		int menu;
		do {
			printBoardMenu();
			menu = sc.nextInt();
			runMenu(menu);
		} while (menu != 0);

	}

	private void runMenu(int menu) {
		switch (menu) {
		case 0:
			System.out.println("프로그램 종료");
			break;
		case 1:
			boardManager();
			break;

		default:
			System.out.println("다시 입력하세요.");

		}

	}

	private void boardManager() {
		int menu;
		do {
			boadrPrint();
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
			insertBoard(); // 추가
			break;
		case 2:
			updateBoard(); // 수정
			break;
		case 3:
			deleteBoard(); // 삭제
			break;
		case 4:
			selectBoard(); // 조회
			break;

		}

	}

	private void selectBoard() {
		List<CategoryVO> cvList = new ArrayList<CategoryVO>();
		// 카테고리를 불러오고
		cvList.toString();
		System.out.println("카테고리 번호 : ");
		int bo_ca_num = sc.nextInt();
		int cvIndex = cvList.indexOf(new CategoryVO(bo_ca_num));
		// 카테고리가 있는지 확인
		while (cvIndex == -1) {
			System.out.println("잘못 입력했거나 없는 번호입니다.");
			// 없으면 다시 입력
			System.out.println("카테고리 번호 : ");
			bo_ca_num = sc.nextInt();
			cvIndex = cvList.indexOf(new CategoryVO(bo_ca_num));
			break;
		}
		
		List<BoardVO>bvList = new ArrayList<BoardVO>();
		// 있으면 게시판 번호 입력
		System.out.println("게시판 번호 : ");
		int bo_num = sc.nextInt();
		int bvIndex = bvList.indexOf(new BoardVO(bo_num));
		while (bvIndex == -1) {
			System.out.println("잘못 입력했거나 없는 번호입니다.");
			// 없으면 다시 입력
			System.out.println("게시판 번호 : ");
			bo_ca_num = sc.nextInt();
			bvIndex = cvList.indexOf(new BoardVO(bo_num));
			break;
		}
		// 카테고리 안에 있는 게시판을 조회

	}
	

	private void deleteBoard() {

	}

	private void updateBoard() {

	}

	private void insertBoard() {

	}

	private void boadrPrint() {
		System.out.println("----게시판----");
		System.out.println("1.게시판 추가");
		System.out.println("2.게시판 수정");
		System.out.println("3.게시판 삭제");
		System.out.println("4.게시판 조회");
		System.out.println("0.뒤로가기");
		System.out.println("-------------");
		System.out.println("메뉴 입력 : ");

	}

	public void printBoardMenu() {
		System.out.println("---게시판 관리---");
		System.out.println("1.게시판 관리");
		System.out.println("0.프로그램 종료");
		System.out.println("-------------");
		System.out.println("메뉴 입력 : ");

	}

}
