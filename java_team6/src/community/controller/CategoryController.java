package community.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
		default:
			System.out.println("잘못 입력했습니다.");
		}

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
			System.out.println("잘못 입력");

		}

	}

	// 조회
	private void selectCategory() {
		List<CategoryVO> cvList = new ArrayList<CategoryVO>();
		System.out.println(cvList.toString());

	}

	// 삭제
	private void deleteCategory() {
		System.out.print("카테고리 번호 입력 : ");
		int ca_num = sc.nextInt();
		if (caService.selectCategory(ca_num) == null) {
			System.out.println("존재 하지 않습니다.");
			return;
		}
		if (caService.deleteCategory(ca_num)) {
			System.out.println("내역을 삭제 했습니다.");
		} else {
			System.out.println("내역 삭제에 실패 했습니다.");
			return;
		}

	}

	// 수정
	private void updateCategory() {
		System.out.print("카테고리 번호 입력 : ");
		int ca_num = sc.nextInt();
		List<CategoryVO> cvList = caService.selectCategory(ca_num);
		if (cvList == null || cvList.size() == 0) {
			System.out.println("수정할 내역이 없습니다.");
			return;
		}
		for (CategoryVO cv : cvList) {
			System.out.println(cv);
		}
		System.out.print("카테고리 번호 입력 : ");
		ca_num = sc.nextInt();
		if (!cvList.contains(new CategoryVO(ca_num))) {
			System.out.println("잘못된 번호 입니다.");
			return;
		}
		CategoryVO cv = new CategoryVO();
		cv.setCa_num(ca_num);
		if (caService.updateCategory(cv)) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
			return;
		}
	}

	// 추가
	private void addCategory() {
//		System.out.print("추가할 카테고리 번호 : ");
//		int ca_num = sc.nextInt();
//		List<CategoryVO> cvList = caService.selectCategory(ca_num);
//		if(cvList != null) {
//			System.out.println("이미 존재하는 번호 입니다.");
//			return;
//		}
		
		System.out.print("카테고리명 입력 : ");
		String ca_title = sc.next();
		CategoryVO cv = new CategoryVO(0, ca_title);
		if(!caService.insertCategory(cv)) {
			System.out.println("카테고리 추가 실패!");
		}else {
			System.out.println("카테고리 추가 성공!");
		}
		
//
//		try {
//			if (caService.selectCategory(ca_num) != null) {
//				System.out.println("이미 존재하는 번호.");
//				return;
//			}
//			CategoryVO cv = new CategoryVO(ca_num, ca_title);
//			if (caService.insertCategory(cv)) {
//				System.out.println("카테고리 추가 성공");
//			} else {
//				System.out.println("카테고리 추가 실패");
//				return;
//			}
//		} catch (InputMismatchException e) {
//			System.out.println("잘못 입력 하셨습니다.");
//			return;
//		}

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
		System.out.println("---카테고리 관리---");
		System.out.println("1.카테고리 관리");
		System.out.println("0.프로그램 종료");
		System.out.println("---------------");
		System.out.println("메뉴 입력 : ");

	}

}
