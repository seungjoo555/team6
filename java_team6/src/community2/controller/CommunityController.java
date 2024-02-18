package community2.controller;

import java.util.List;
import java.util.Scanner;

import community2.model.vo.Post;
import community2.pagination.Criteria;
import community2.service.CommunityService;
import community2.service.CommunityServiceImp;
import community2.model.vo.Board;


public class CommunityController {
	
	private Scanner scan;
	private CommunityService communityService;
	
	public CommunityController(Scanner scan) {
		if(scan == null) {
			scan = new Scanner(System.in);
		}
		this.scan = scan;
		communityService = new CommunityServiceImp();
	}

	public void run() {
		int menu;
		do {
			printMenu();
			menu = scan.nextInt();
			runMenu(menu);
		}while(menu != 5);
	}
	
	private void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 회원 관리");
		System.out.println("2. 게시판 관리");
		System.out.println("3. 게시글 관리");
		System.out.println("4. 댓글 관리");
		System.out.println("5. 이전으로");
		System.out.print("메뉴 선택 : ");
	}
	
	private void runMenu(int menu) {
		switch(menu) {
		case 1:
			//memberMenu();
			break;
		case 2:
			//boardMenu();
			break;
		case 3:
			postMenu();
			break;
		case 4:
			//commentMenu();
			break;
		case 5:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
		
	}
	
	//게시글
	private void postMenu() {
		int menu;
		do {
			printPostMenu();
			menu = scan.nextInt();
			runPostMenu(menu);
		}while(menu != 5);
	}

	private void printPostMenu() {
		System.out.println("메뉴");
		System.out.println("1. 게시글 등록");
		System.out.println("2. 게시글 수정");
		System.out.println("3. 게시글 삭제");
		System.out.println("4. 게시글 조회");
		System.out.println("5. 이전으로");
		System.out.print("메뉴 선택 : ");	
	}

	private void runPostMenu(int menu) {
		switch(menu) {
		case 1:
			insertPost();
			break;
		case 2:
			updatePost();
			break;
		case 3:
			deletePost();
			break;
		case 4:
			printPost();
			break;
		case 5:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}
	//게시글 조회
	private void printPost() {
		System.out.println("검색(제목/내용) : ");
		String text = scan.next();
		int page = 1;
		int menu;
		do {
			Criteria cri = new Criteria(page, 10);
			cri.setSearch(text);
			List<Post> postList = communityService.getPostList(cri);
			if(postList == null || postList.size() == 0) {
				System.out.println("조회할 게시글이 없습니다.");
			}else {
				//조회할 게시글이 있으면 조회 가능한 게시글을 출력
				for(Post post : postList) {
					System.out.println(post);
				}
			}
			System.out.println("1. 이전 페이지");
			System.out.println("2. 다음 페이지");
			System.out.println("3. 게시글 조회");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			switch(menu) {
			case 1: page = page == 1 ? 1 : page - 1;	
				break;
			case 2: ++page; 
				break;
			case 3:	
				System.out.print("게시글 번호를 선택하세요 : ");
				int postNum = scan.nextInt();
				//입력한 게시글 번호가 잘못된 값인지 확인
				if(!postList.contains(new Post(postNum))) {
					System.out.println("잘못된 게시글 번호입니다.");
				}else {
					Post postContent = communityService.getPostContent(postNum);
					System.out.println(postContent.toString1());
					communityService.upView(postNum); //조회수 증가
					System.out.println("게시글을 조회했습니다.");
				} 
				break;
			default : System.out.println("잘못 선택");
			}
		}while(menu != 3);
	}
	//게시글 등록
	private void insertPost() {
		Post post = inputPost();
		if(communityService.insertPost(post)) {
			System.out.println("게시글을 등록했습니다.");
		}else {
			System.out.println("게시글 등록에 실패했습니다.");
		}
	}
	//게시글 수정
	private void updatePost() {
		//게시글 선택
		List<Post> postList = communityService.getPostList();
		if(postList == null || postList.size() == 0) {
			System.out.println("수정할 게시글이 없습니다.");
			return;
		}
		//수정할 게시글이 있으면 수정 가능한 게시글을 출력
		for(Post post : postList) {
			System.out.println(post);
		}
		System.out.print("게시글 번호를 선택하세요 : ");
		int postNum = scan.nextInt();
		//입력한 게시글 번호가 잘못된 값인지 확인
		if(!postList.contains(new Post(postNum))) {
			System.out.println("잘못된 게시글 번호입니다.");
			return;
		}
		
		Post post = inputPost();
		post.setPo_num(postNum);
		if(communityService.updatePost(post)){
			communityService.updateView(postNum); //수정시 조회수 0으로 변경
			System.out.println("게시글 수정이 완료되었습니다.");
		}else {
			System.out.println("게시글을 수정하지 못했습니다.");
		}
	}
	//게시글 삭제
	private void deletePost() {
		List<Post> postList = communityService.getPostList();
		if(postList == null || postList.size() == 0) {
			System.out.println("삭제할 게시글이 없습니다.");
			return;
		}
		//삭제할 게시글이 있으면 삭제 가능한 게시글을 출력
		for(Post post : postList) {
			System.out.println(post);
		}
		System.out.print("게시글 번호를 선택하세요 : ");
		int postNum = scan.nextInt();
		//입력한 게시글 번호가 잘못된 값인지 확인
		if(!postList.contains(new Post(postNum))) {
			System.out.println("잘못된 게시글 번호입니다.");
			return;
		}
		
		if(communityService.deletePost(postNum)) {
			System.out.println("게시글을 삭제했습니다.");
		}else {
			System.out.println("게시글을 삭제하지 못했습니다.");
		}
	}
	
	private Post inputPost() {	
		// 게시판 선택
		List<Board> boardList = communityService.getBoardList();
		for(Board board : boardList) {
			System.out.println(board);
		}
		if(boardList == null || boardList.size() == 0) {
			System.out.println("게시글을 등록할 게시판이 없습니다.");
			return null;
		}
		System.out.print("게시판 번호를 선택하세요 : ");
		int boardNum = scan.nextInt();
		//입력한 게시판 번호가 잘못된 값인지 확인
		if(!boardList.contains(new Board(boardNum))) {
			System.out.println("잘못된 게시판 번호입니다.");
			return null;
		}
		
		System.out.print("제목 : ");
		String title = scan.next();
		System.out.print("내용 : ");
		String content = scan.next();
		System.out.print("작성자 : ");
		String id = scan.next();
		
		return new Post(boardNum, title, content, id);
	}
	
}
