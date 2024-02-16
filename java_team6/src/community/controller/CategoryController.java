package community.controller;

import java.util.ArrayList;
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
		
		List<CategoryVO> caList = caService.selectCategoryList();
		for(CategoryVO tmp : caList) {
			System.out.println(tmp);
		}
	}

	// 삭제
	private void deleteCategory() {
		System.out.print("삭제 할 카테고리명을 입력하세요 : ");
		String ca_title = sc.next();
		List<CategoryVO> cvList = caService.selectCategory(ca_title);
		if (cvList == null || cvList.size() == 0) {
			System.out.println("카테고리명이 존재하지 않습니다.");
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
		System.out.print("수정 할 카테고리명을 입력하세요 : ");
		String ca_title = sc.next();
		System.out.print("새로운 카테고리명을 입력하세요 : ");
		String new_ca_title = sc.next();
		if(caService.updateCategory(ca_title,new_ca_title)) {
			System.out.println("카테고리 수정이 완료 되었습니다.");
			return;
		}
		System.out.println("카테고리 수정에 실패하였습니다.");
		return;
		} catch (Exception e) {
			System.out.println("중복된 카테고리 입니다.");
		}
		return ;
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
		System.out.println("---카테고리 관리---");
		System.out.println("1.카테고리 관리");
		System.out.println("0.프로그램 종료");
		System.out.println("---------------");
		System.out.println("메뉴 입력 : ");

	}

}
