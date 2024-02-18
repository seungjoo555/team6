package community.controller;

import java.util.List;
import java.util.Scanner;

import community.model.vo.Board;
import community.model.vo.Category;
import community.model.vo.Comment;
import community.model.vo.Post;
import community.service.CommunityService;
import community.service.CommunityServiceImp;


public class CommunityController {
	
	private Scanner scan;
	private CommunityService communityService;
	public String id = "qwe123";
	
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
			memberMenu();
			break;
		case 2:
			boardMenu();
			break;
		case 3:
			postMenu();
			break;
		case 4:
			commentMenu();
			break;
		case 5:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
		
	}
	
	//댓글
	private void commentMenu() {
		int menu;
		do {
			printCommentMenu();
			menu = scan.nextInt();
			runCommentMenu(menu);
		}while(menu != 5);
	}

	private void printCommentMenu() {
		System.out.println("메뉴");
		System.out.println("1. 댓글 등록");
		System.out.println("2. 댓글 수정");
		System.out.println("3. 댓글 삭제");
		System.out.println("4. 댓글 조회");
		System.out.println("5. 이전으로");
		System.out.print("메뉴 선택 : ");	
	}

	private void runCommentMenu(int menu) {
		switch(menu) {
		case 1:
			insertComment();
			break;
		case 2:
			updateComment();
			break;
		case 3:
			deleteComment();
			break;
		case 4:
			printComment();
			break;
		case 5:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	private void printComment() {
		// 조회
		
	}

	private int[] menuSelectAll() {
		int a[] = new int[3];
		
		List<Category> categoryList = communityService.getCategoryList();
		// categoryList 내역 출력
		for(Category category : categoryList) {
			System.out.println(category);
		}
		
		System.out.print("카테고리 번호 : ");
		a[0] = scan.nextInt();
		
		//입력한 게시판 번호가 잘못된 값인지 확인
		if(a[0] > categoryList.size()) {
			System.out.println("잘못된 카테고리 번호입니다.");
			return null;
		}
		
		List<Board> boardList = communityService.getBoardList();
		// boardList 출력
		for(Board board : boardList) {
			System.out.println(board);
		}
		
		System.out.print("게시판 번호 : ");
		a[1] = scan.nextInt();
		
		//입력한 게시판 번호가 잘못된 값인지 확인
		if(a[1] > categoryList.size()) {
			System.out.println("잘못된 게시판 번호입니다.");
			return null;
		}
		
		List<Post> postList = communityService.getPostList();
		// Post 내역 출력
		for(Post post : postList) {
			System.out.println(post);
		}
		
		System.out.print("게시글 번호 : ");
		a[2] = scan.nextInt();
		
		//입력한 게시글 번호가 잘못된 값인지 확인
		if(a[2] > categoryList.size()) {
			System.out.println("잘못된 게시글 번호입니다.");
			return null;
		}
		return a;
	}
	
	
	private void insertComment() {
		
		int[] a = menuSelectAll();
		if(a == null || a.length == 0) {
			return;
		}
		
		System.out.print("내용 : ");
		scan.nextLine();
		String co_content = scan.nextLine();
		
		// 입력받은 정보로 객체를 생성
		Comment com = new Comment(a[0], a[1], a[2], id, co_content);
		
		// 생성한 객체를 boolean형 성공 유무 확인하는 메서드로 서비스에 넘김
		if(communityService.insertComment(com)) {
			System.out.println("댓글 추가했습니다.");
		}else {
			System.out.println("댓글 추가에 실패했습니다.");
		}
	}

	private void updateComment() {	// 내용만 변경 가능
		int[] a = menuSelectAll();
		if(a == null) {
			return;
		}
		
		Comment com = new Comment(a[0], a[1], a[2]);
		List<Comment> CommentList = communityService.getCommentList(com);

		// 본인의 아이디와 동일한 댓글이 존재하는지 확인 존재하면 출력
		if(CommentList == null || CommentList.size() == 0) {
			System.out.println("댓글이 없어 수정할 수 없습니다.");
			return;
		}
		
		// 출력
		for(Comment comment : CommentList) {
			System.out.println(comment);
		}
		
		// 수정할 댓글 번호를 선택
		System.out.print("댓글 번호 : ");
		int co_num = scan.nextInt();
		
		//입력한 댓글 번호가 잘못된 값인지 확인
		if(!CommentList.contains(new Comment(co_num))) {
			System.out.println("잘못된 내역 번호입니다.");
			return;
		}
		
		// 본인이 작성한 댓글인지 확인
		if(!CommentList.contains(new Comment(co_num, id))) {
			System.out.println("본인의 댓글이 아닙니다.");
		}
		
		// 수정할 정보를 입력받음
		System.out.print("수정할 댓글 내용 : ");
		scan.nextLine();
		String co_content = scan.nextLine();
		
		// 생성한 객체를 boolean형 성공 유무 확인하는 메서드로 서비스에 넘김
		if(communityService.updateComment(new Comment(co_num, id, co_content))) {
			System.out.println("댓글 수정했습니다.");
		}else {
			System.out.println("댓글 수정에 실패했습니다.");
		}
	}

	private void deleteComment() {
		int[] a = menuSelectAll();
		if(a == null) {
			return;
		}
		
		Comment com = new Comment(a[0], a[1], a[2]);
		List<Comment> CommentList = communityService.getCommentList(com);
		
		// 본인의 아이디와 동일한 댓글이 존재하는지 확인 존재하면 출력
		if(CommentList == null || CommentList.size() == 0) {
			System.out.println("본인 댓글이 없어 수정할 수 없습니다.");
			return;
		}
		
		// 존재하면 출력
		for(Comment comment : CommentList) {
			System.out.println(comment);
		}
		
		// 삭제할 댓글의 번호를 선택
		System.out.print("댓글 번호 : ");
		int co_num = scan.nextInt();
		
		//입력한 댓글 번호가 잘못된 값인지 확인
		if(!CommentList.contains(new Comment(co_num))) {
			System.out.println("잘못된 내역 번호입니다.");
			return;
		}
		
		// 본인이 작성한 댓글인지 확인
		if(!CommentList.contains(new Comment(co_num, id))) {
			System.out.println("본인의 댓글이 아닙니다.");
		}
		
		// 생성한 객체를 boolean형 성공 유무 확인하는 메서드로 서비스에 넘김
		if(communityService.deleteComment(new Comment(a[0], a[1], a[2], co_num))) {
			System.out.println("댓글 삭제했습니다.");
		}else {
			System.out.println("댓글 삭제에 실패했습니다.");
		}
	}
	
	// 카테고리, 게시판, 게시글 비었는지 확인하는 메서드
	private boolean isNull() {
		// 게시판 선택
		List<Category> categoryList = communityService.getCategoryList();
		List<Board> boardList = communityService.getBoardList();
		List<Post> postList = communityService.getPostList();

		// boardList가 비어있는지
		if(boardList == null || categoryList.size() == 0) {
			System.out.println("카테고리가 없습니다.");
			return false;
		}
		
		// boardList가 비어있는지
		if(boardList == null || boardList.size() == 0) {
			System.out.println("게시판이 없습니다.");
			return false;
		}
		
		// PostList가 비어있는지
		if(postList == null || postList.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return false;
		}
		return true;
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
		// TODO Auto-generated method stub
		
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
		//입력한 내역 번호가 잘못된 값인지 확인
		if(!postList.contains(new Post(postNum))) {
			System.out.println("잘못된 게시글 번호입니다.");
			return;
		}
		if(communityService.deleteItem(postNum)) {
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
		System.out.print("조회수 : ");
		int view = scan.nextInt();
		
		return new Post(boardNum, title, content, id, view);
	}
	
	
	//게시판
	private void boardMenu() {
		int menu;
		do {
			printBoardMenu();
			menu = scan.nextInt();
			runBoardMenu(menu);
		}while(menu != 5);
	}
	
	private void printBoardMenu() {
		System.out.println("메뉴");
		System.out.println("1. 게시판 등록");
		System.out.println("2. 게시판 수정");
		System.out.println("3. 게시판 삭제");
		System.out.println("4. 게시판 조회");
		System.out.println("5. 이전으로");
		System.out.print("메뉴 선택 : ");	
	}

	private void runBoardMenu(int menu) {
		switch(menu) {
		case 1:
			insertBoard();
			break;
		case 2:
			updateBoard();
			break;
		case 3:
			deleteBoard();
			break;
		case 4:
			printBoard();
			break;
		case 5:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	private void printBoard() {
		// TODO Auto-generated method stub
		
	}

	private void insertBoard() {
		// TODO Auto-generated method stub
		
	}

	private void updateBoard() {
		// TODO Auto-generated method stub
		
	}

	private void deleteBoard() {
		// TODO Auto-generated method stub
		
	}
	
	//회원
	private void memberMenu() {
		int menu;
		do {
			printMemberMenu();
			menu = scan.nextInt();
			runMemeberMenu(menu);
		}while(menu != 7);
	}
	
	private void printMemberMenu() {
		System.out.println("메뉴");
		System.out.println("1. 회원 가입");
		System.out.println("2. 회원 수정");
		System.out.println("3. 회원 탈퇴");
		System.out.println("4. 로그인");
		System.out.println("5. 로그아웃");
		System.out.println("6. 회원 조회");
		System.out.println("7. 이전으로");
		System.out.print("메뉴 선택 : ");
	}
	
	private void runMemeberMenu(int menu) {
		switch(menu) {
		case 1:
			insertMember();
			break;
		case 2:
			updateMember();
			break;
		case 3:
			deleteMember();
			break;
		case 4:
			//login();
			break;
		case 5:
			logout();
			break;
		case 6:
			printMember();
			break;
		case 7:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	private void logout() {
		// TODO Auto-generated method stub
		
	}

	private void updateMember() {
		// TODO Auto-generated method stub
		
	}

	private void printMember() {
		// TODO Auto-generated method stub
		
	}

	private void insertMember() {
		// TODO Auto-generated method stub
		
	}

	private void deleteMember() {
		// TODO Auto-generated method stub
		
	}
}