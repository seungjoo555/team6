package community.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import community.model.vo.BoardVO;
import community.model.vo.Comment;
import community.model.vo.Member;
import community.model.vo.Post;
import community.pagination.Criteria;
import community.service.CommentService;
import community.service.CommentServiceImp;
import community.service.CommunityPrintService;
import community.service.CommunityPrintServiceImp;
import community.service.PostService;
import community.service.PostServiceImp;
import community.service.UserService;
import community.service.UserServiceImp;

public class CommunityController {

	private static Member user;
	private UserService userService;
	private CommentService commentService;
	private PostService postService;
	private Scanner scan;
	private CommunityPrintService communityPrint;

	public CommunityController(Scanner scan) {
		if (scan == null) {
			scan = new Scanner(System.in);
		}
		this.scan = scan;
		userService = new UserServiceImp();
		postService = new PostServiceImp();
		commentService = new CommentServiceImp();
		communityPrint = new CommunityPrintServiceImp();
		commentService = new CommentServiceImp();
	}
	
	public void rogIn() {
		// 로그인체크
		System.out.print("아이디 : ");
		String id = scan.next();
		// 아이디로 데이터베이스에서 가져오기
		user = userService.checkId(id);
		// 없다면 종료
		if (user == null) {
			System.out.println("없는아이디입니다. 회원가입을 해주세요");
			return;
		}
		System.out.print("비밀번호 : ");
		String pwd = scan.next();
		// 비밀번호 체크 다르면 초기화
		if (!user.getMe_pw().equals(pwd)) {
			System.out.println("비밀번호가 틀렸습니다. 다시 로그인 해주세요.");
			user = new Member();
			return;
		}
		if (user.getMe_authority().equals("ADMIN")) {
			// 관리자일때 실행할 메뉴메서드
			adminMenu();
		} else if (user.getMe_authority().equals("USER") && user.getMe_ms_state().equals("회원")) {
			// 유저이고 회원상태일때 실행할 메뉴메서드
			userMenu();
		} else {
			System.out.println("가입요청중 혹은 이용정지된 회원입니다.");
		}

	}

	public void signUp() {
		System.out.println("회원가입 절차 시작");
		String idRegex = "^[a-zA-Z0-9_]{6,12}$";
		String pwdRegex = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
		String emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		String id, pwd, pwd2, email, name, phone, addr;
		do {
			System.out.print("아이디(영문,숫자 6~12자리) : ");
			id = scan.next();
			if (Pattern.matches(idRegex, id)) {
				System.out.println("아이디로 사용 가능합니다.");
				break;
			} else {
				System.out.println("아이디 형식에 맞지 않습니다.");
				continue;
			}
		} while (true);
		do {
			System.out.print("비밀번호(영문,숫자,특수문자포함 8~15자리) : ");
			pwd = scan.next();
			System.out.print("비밀번호 확인 : ");
			pwd2 = scan.next();
			if (!pwd.equals(pwd2)) {
				System.out.println("비밀번호와 확인부분이 다릅니다. 다시 입력 해주세요.");
				continue;
			}
			if (Pattern.matches(pwdRegex, pwd)) {
				System.out.println("비밀번호로 사용 가능합니다.");
				break;
			} else {
				System.out.println("비밀번호 형식에 맞지 않습니다.");
				continue;
			}
		} while (true);
		do {
			System.out.print("이메일 : ");
			email = scan.next();
			if (Pattern.matches(emailRegex, email)) {
				System.out.println("이메일로 사용 가능합니다.");
				break;
			} else {
				System.out.println("이메일 형식에 맞지 않습니다.");
				continue;
			}
		} while (true);
		do {
			System.out.print("이름 : ");
			name = scan.next();
			if (name.length() < 2 || name.length() > 30) {
				System.out.println("이름은 2자이상 30자이하 입니다.");
				continue;
			} else {
				break;
			}
		} while (true);
		System.out.print("전화번호 : ");
		phone = scan.next();

		System.out.print("주소(oo시 oo구 oo동) : ");
		scan.nextLine();
		addr = scan.nextLine();

		Member member = new Member(id, pwd, email, name, phone, addr);
		if (userService.insertMember(member)) {
			System.out.println("회원가입 요청 완료.");
		} else {
			System.out.println("회원가입 실패. 잠시후 다시 시도해주세요.");
		}
	}

	public void adminMenu() {
		// 관리자메뉴 (사용자관리 - 정보수정x, 이용정지, 탈퇴기능, 가입승인) - 데이터베이스에 사용자상태 추가
		// 1.사용자관리, 2.게시판관리(카테고리,게시판), 3.게시글관리, 4.댓글관리, 5.로그아웃
		int menu;
		do {
			System.out.println("관리자메뉴");
			System.out.println("1.사용자관리");
			System.out.println("2.카페이용(추가,수정,삭제 가능) - 대기중");
			System.out.println("3.로그아웃");
			System.out.print("메뉴선택 : ");
			menu = scan.nextInt();
			runAdmin(menu);
		} while (menu != 3 && user.getMe_id() != null);
	}

	private void runAdmin(int menu) {
		switch (menu) {
		case 1:// 사용자 관리
			adminUserManagerMenu();
			break;
		case 2:
			adminCommunityManageMenu();
			break;
		case 3:// 로그아웃
			System.out.println(user.getMe_id() + "님 로그아웃 완료");
			user = new Member();
			break;
		default:
			throw new InputMismatchException();
		}
	}

	private void adminUserManagerMenu() {
		int menu;
		do {
			System.out.println("사용자 관리");
			System.out.println("1.가입요청 승인");
			System.out.println("2.회원 이용정지(복구)");
			System.out.println("3.회원 탈퇴기능");
			System.out.println("4.뒤로가기");
			System.out.print("메뉴선택 : ");
			menu = scan.nextInt();
			runAdminUserManager(menu);
		}while(menu != 4 && user.getMe_id() != null);
	}

	private void runAdminUserManager(int menu) {
		switch (menu) {
		case 1:// 가입요청 승인
			memberRequest();
			break;
		case 2:// 회원 이용정지
			stopMember();
			break;
		case 3:// 회원 탈퇴기능
			deleteMember();
			break;
		case 4:// 뒤로가기
			System.out.println("이전으로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}
	}

	private void memberRequest() {
		//가입요청중인 아이디 목록
		System.out.println("----가입요청 목록----");
		List<Member> userList = userService.getMemberList("가입요청");
		if(userList == null || userList.size() == 0) {
			System.out.println("가입요청중인 회원이 없습니다.");
			return;
		}
		//가입요청중인 아이디가 있으면 목록으로 출력
		for(Member member : userList) {
			System.out.println("아이디: " + member.getMe_id() + " 상태: " + member.getMe_ms_state());
		}
		// 승인할 아이디 입력 all일경우 모두 승인
		System.out.print("승인할 아이디 입력(모두 승인하려면 all) : ");
		String me_id = scan.next();
		if (me_id.equals("all")) {
			userService.okeydokeyAllRequest();
			System.out.println("가입요청 모두승인");
			return;
		}
		// 입력한 아이디가 있는지 확인
		else if (!userList.contains(new Member(me_id))) {
			System.out.println("아이디를 잘못 입력했습니다.");
			return;
		}

		if (userService.okeydokeyRequest(me_id)) {
			System.out.println(me_id + "의 가입요청을 승인했습니다.");
		} else {
			System.out.println("요청을 승인하지 못했습니다.");
		}

	}

	private void stopMember() {
		//회원인 아이디 목록
		System.out.println("----회원 목록----");
		List<Member> userList = userService.getStopMemberList("회원", "이용정지");
		if(userList == null || userList.size() == 0) {
			System.out.println("가입 또는 활동중인 회원이 없습니다.");
			return;
		}
		//회원 목록 출력
		for(Member member : userList) {
			System.out.println("아이디: " + member.getMe_id() + " 상태: " + member.getMe_ms_state());
		}
		//정지시킬 아이디 입력
		System.out.print("정지(복구)할 아이디 입력 : ");
		String me_id = scan.next();
		//목록에 아이디가 있는지 확인
		if(!userList.contains(new Member(me_id))) {
			System.out.println("아이디를 잘못 입력했습니다.");
			return;
		}
		//회원이 이용정지면 회원으로 되돌리기		
		//회원이 회원상태면 이용정지
		if(userService.stopStateMember(me_id)) {
			System.out.println(me_id + " 의 이용정지(복구) 완료");
		}else {
			System.out.println("요청을 승인하지 못했습니다.");
		}
		
	}

	private void deleteMember() {
		//가입요청중이 아닌 전체 유저 목록
		System.out.println("----회원 목록----");
		List<Member> userList = userService.getStopMemberList("회원", "이용정지");
		if(userList == null || userList.size() == 0) {
			System.out.println("가입 또는 활동중인 회원이 없습니다.");
			return;
		}
		for(Member member : userList) {
			System.out.println("아이디: " + member.getMe_id() + " 상태: " + member.getMe_ms_state());
		}
		//탈퇴(삭제)시킬 아이디 입력
		System.out.print("강제탈퇴(삭제) 아이디 : ");
		String me_id = scan.next();
		//목록에 아이디가 있는지 확인
		if(!userList.contains(new Member(me_id))) {
			System.out.println("아이디를 잘못 입력했습니다.");
			return;
		}
		//있다면 회원의 정보를 모두 삭제
		if(userService.deleteMember(me_id)) {
			System.out.println(me_id + " 의 정보를 삭제했습니다.");
		}else {
			System.out.println("요청을 승인하지 못했습니다.");

	private void adminCommunityManageMenu() {
		int menu;
		do {
			System.out.println("사용자 관리");
			System.out.println("1.커뮤니티 관리");
			System.out.println("2.게시판 관리");
			System.out.println("3.게시글 관리");
			System.out.println("4.댓글 관리");
			System.out.println("5.뒤로가기");
			System.out.print("메뉴선택 : ");
			menu = scan.nextInt();
			runAdminCommunityManager(menu);
		} while (menu != 5);
	}

	private void runAdminCommunityManager(int menu) {
		switch (menu) {
		case 1: // 커뮤니티 관리
			System.out.println("미구현");
			break;
		case 2: // 게시판 관리
			System.out.println("미구현");
			break;
		case 3: // 게시글 관리
			AdminPostManage();
			break;
		case 4: // 댓글 관리
			AdmincommentManage();
			break;
		case 5: // 뒤로 가기
			System.out.println("이전으로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}
	}

	private void AdminPostManage() {
		int menu;
		do {
			System.out.println("메뉴");
			System.out.println("1. 게시글 삭제");
			System.out.println("2. 이전으로");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			runAdminPostManage(menu);
		} while (menu != 2);
	}

	private void runAdminPostManage(int menu) {
		switch (menu) {
		case 1:
			deletePost();
			break;
		case 2:
			System.out.println("이전으로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}

	}

	private void AdmincommentManage() {
		int menu;
		do {
			System.out.println("메뉴");
			System.out.println("1. 댓글 삭제");
			System.out.println("2. 이전으로");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			runAdminCommentManage(menu);
		} while (menu != 2);
	}

	private void runAdminCommentManage(int menu) {
		switch (menu) {
		case 1:
			deleteAdminComment();
			break;
		case 2:
			System.out.println("이전으로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}
	}

	// 댓글 삭제
	private void deleteAdminComment() {
		int co_po_num = menuSelectAll();
		
		List<Comment> CommentList = commentService.getCommentList(co_po_num);

		// 본인의 아이디와 동일한 댓글이 존재하는지 확인 존재하면 출력
		if (CommentList == null || CommentList.size() == 0) {
			System.out.println("해당 게시글에 댓글이 없어 삭제할 수 없습니다.");
			return;
		}

		// 존재하면 출력
		for (Comment comment : CommentList) {
			System.out.println(comment);
		}

		// 삭제할 댓글의 번호를 선택
		System.out.print("댓글 번호 : ");
		int co_num = scan.nextInt();

		// 입력한 댓글 번호가 잘못된 값인지 확인
		if (!CommentList.contains(new Comment(co_num))) {
			System.out.println("잘못된 내역 번호입니다.");
			return;
		}

		// 본인이 작성한 댓글인지 확인
		if (!CommentList.contains(new Comment(co_num, user.getMe_id()))) {
			System.out.println("본인의 댓글이 아닙니다.");
		}

		// 생성한 객체를 boolean형 성공 유무 확인하는 메서드로 서비스에 넘김
		if(commentService.deleteAdminComment(co_num)) {
			if(CommentList.size() == 0) {
				commentService.resetNum();
			}
			System.out.println("댓글 삭제했습니다.");
		} else {
			System.out.println("댓글 삭제에 실패했습니다.");
		}
	}

	public void userMenu() {
		// 사용자메뉴 (내정보관리 - 수정, 탈퇴)
		// 1.내정보관리, 2.카페이용(게시글, 댓글), 3.로그아웃
		int menu;
		do {
			System.out.println("메뉴");
			System.out.println("1.내정보관리");
			System.out.println("2.카페이용(게시글, 댓글)");
			System.out.println("3.로그아웃");
			System.out.print("메뉴선택 : ");
			menu = scan.nextInt();
			runUser(menu);
		}while(menu != 3 && user.getMe_id() != null);
	}

	private void runUser(int menu) {
		switch (menu) {
		case 1:
			//내정보관리 - 아이디에 맞는 데이터를 불러오고 수정할지 탈퇴(삭제)할지 선택
			myManager();
			break;
		case 2:
			cafeManage(); // 카페이용
			break;
		case 3:
			System.out.println(user.getMe_id() + "님 로그아웃 완료");
			break;
		default:
			throw new InputMismatchException();
		}
	}

	private void myManager() {
		if(user.getMe_id() == null) {
			return;
		}
		int menu;
		do {
			System.out.println("----------내정보----------");
			System.out.println(user);
			System.out.println("------------------------");
			System.out.println("1.내정보 수정");
			System.out.println("2.회원 탈퇴");
			System.out.println("3.뒤로 가기");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			runUserManager(menu);
		}while(menu != 3 && user.getMe_id() != null);
	}

	private void runUserManager(int menu) {
		switch(menu) {
		case 1://내정보 수정
			updateMy();
			break;
		case 2://회원탈퇴
			deleteMy();
			break;
		case 3:
			System.out.println("이전으로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}
	}

	private void updateMy() {
		System.out.println("회원정보를 수정하려면 비밀번호를 입력하세요.");
		System.out.print("비밀번호 : ");
		String pwd = scan.next();
		if(!user.getMe_pw().equals(pwd)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		int menu;
		do {
			System.out.println("수정할 내용 선택");
			System.out.println("1. 이름");
			System.out.println("2. 이메일");
			System.out.println("3. 전화번호");
			System.out.println("4. 주소");
			System.out.println("5. 수정 끝내기");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			runUpdateMy(menu);
		}while(menu != 5 && user.getMe_id() != null);
	}
	
	private void runUpdateMy(int menu) {
		String emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		switch(menu) {
		case 1://이름 수정
			do {
				System.out.print("수정할 이름 : ");
				String name = scan.next();
				if(name.length() < 2 || name.length() > 30) {
					System.out.println("이름은 2자이상 30자이하 입니다.");
					continue;
				}else {
					user.setMe_name(name);
					break;
				}
			}while(true);
			break;
		case 2://이메일 수정
			do {
				System.out.print("이메일 : ");
				String email = scan.next();
				if(Pattern.matches(emailRegex, email)) {
					System.out.println("이메일로 사용 가능합니다.");
					user.setMe_email(email);
					break;
				}else {
					System.out.println("이메일 형식에 맞지 않습니다.");
					continue;
				}
			}while(true);
			break;
		case 3://전화번호 수정
			System.out.print("전화번호 : ");
			String phone = scan.next();
			user.setMe_phoneNum(phone);
			break;
		case 4://주소 수정
			System.out.print("주소(oo시 oo구 oo동) : ");
			scan.nextLine();
			String addr = scan.nextLine();
			user.setMe_address(addr);
			break;
		case 5://수정 끝내기
			if(userService.updateMember(user)) {
				System.out.println("내정보 수정 완료");
			}else {
				System.out.println("수정에 실패했습니다.");
			}
			break;
		default:
			throw new InputMismatchException();
		}
	}

	private void deleteMy() {
		System.out.println("회원탈퇴를 하려면 비밀번호를 입력하세요.");
		System.out.print("비밀번호 : ");
		String pwd = scan.next();
		if(!user.getMe_pw().equals(pwd)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}
		//삭제
		if(userService.deleteMember(user.getMe_id())) {
			System.out.println("회원탈퇴가 완료되었습니다.");
			user = new Member();
		}else {
			System.out.println("탈퇴 실패. 잠시후 다시 시도해주세요.");
		}
		
	}

	private void cafeManage() {
		int menu;
		do {
			System.out.println("메뉴");
			System.out.println("1. 게시글 관리");
			System.out.println("2. 댓글 관리");
			System.out.println("3. 이전으로");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			runCafeManage(menu);
		} while (menu != 3);
	}

	private void runCafeManage(int menu) {
		switch (menu) {
		case 1:
			postManage();
			break;
		case 2:
			commentManage();
			break;
		case 3:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	private void postManage() {
		int menu;
		do {
			communityPrint.printPost();
			menu = scan.nextInt();
			runPostManage(menu);
		} while (menu != 0);

	}

	private void runPostManage(int menu) {
		switch (menu) {
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
		case 0:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	// 게시글 조회
	private void printPost() {
		System.out.println("검색(제목/내용) : ");
		String text = scan.next();
		int page = 1;
		int menu;
		do {
			Criteria cri = new Criteria(page, 10);
			cri.setSearch(text);
			List<Post> postList = postService.getPostList(cri);
			if (postList == null || postList.size() == 0) {
				System.out.println("조회할 게시글이 없습니다.");
			} else {
				// 조회할 게시글이 있으면 조회 가능한 게시글을 출력
				for (Post post : postList) {
					System.out.println(post);
				}
			}
			System.out.println("1. 이전 페이지");
			System.out.println("2. 다음 페이지");
			System.out.println("3. 게시글 조회");
			System.out.print("메뉴 선택 : ");
			menu = scan.nextInt();
			switch (menu) {
			case 1:
				page = page == 1 ? 1 : page - 1;
				break;
			case 2:
				++page;
				break;
			case 3:
				System.out.print("게시글 번호를 선택하세요 : ");
				int postNum = scan.nextInt();
				// 입력한 게시글 번호가 잘못된 값인지 확인
				if (!postList.contains(new Post(postNum))) {
					System.out.println("잘못된 게시글 번호입니다.");
				} else {
					Post postContent = postService.getPostContent(postNum);
					System.out.println(postContent.toString1());
					postService.upView(postNum); // 조회수 증가
					System.out.println("게시글을 조회했습니다.");
				}
				break;
			default:
				System.out.println("잘못 선택");
			}
		} while (menu != 3);
	}

	// 게시글 등록
	private void insertPost() {
		Post post = inputPost();
		if (postService.insertPost(post)) {
			System.out.println("게시글을 등록했습니다.");
		} else {
			System.out.println("게시글 등록에 실패했습니다.");
		}
	}

	// 게시글 수정
	private void updatePost() {
		// 게시글 선택
		List<Post> postList = postService.getPostList();
		if (postList == null || postList.size() == 0) {
			System.out.println("수정할 게시글이 없습니다.");
			return;
		}
		// 수정할 게시글이 있으면 수정 가능한 게시글을 출력
		for (Post post : postList) {
			System.out.println(post);
		}
		System.out.print("게시글 번호를 선택하세요 : ");
		int postNum = scan.nextInt();

		// 입력한 게시글 번호가 잘못된 값인지 확인
		if (!postList.contains(new Post(postNum))) {
			System.out.println("잘못된 게시글 번호입니다.");
			return;
		}

		Post post = inputPost();
		post.setPo_num(postNum);
		if (postService.updatePost(post)) {
			postService.updateView(postNum); // 수정시 조회수 0으로 변경
			System.out.println("게시글 수정이 완료되었습니다.");
		} else {
			System.out.println("게시글을 수정하지 못했습니다.");
		}
	}

	// 게시글 삭제
	private void deletePost() {
		List<Post> postList = postService.getPostList();
		if (postList == null || postList.size() == 0) {
			System.out.println("삭제할 게시글이 없습니다.");
			return;
		}
		// 삭제할 게시글이 있으면 삭제 가능한 게시글을 출력
		for (Post post : postList) {
			System.out.println(post);
		}
		System.out.print("게시글 번호를 선택하세요 : ");
		int postNum = scan.nextInt();
		// 입력한 게시글 번호가 잘못된 값인지 확인
		if (!postList.contains(new Post(postNum))) {
			System.out.println("잘못된 게시글 번호입니다.");
			return;
		}

		if (postService.deletePost(postNum)) {
			System.out.println("게시글을 삭제했습니다.");
		} else {
			System.out.println("게시글을 삭제하지 못했습니다.");
		}
	}

	private Post inputPost() {
		// 게시판 선택
		List<BoardVO> boardList = postService.getBoardList();
		for (BoardVO board : boardList) {
			System.out.println(board);
		}
		if (boardList == null || boardList.size() == 0) {
			System.out.println("게시글을 등록할 게시판이 없습니다.");
			return null;
		}
		System.out.print("게시판 번호를 선택하세요 : ");
		int boardNum = scan.nextInt();

		boolean ok = false;
		// 입력 받은 게시판이 있으면 게시판 추가로 감
		for (BoardVO board : boardList) {
			if (board.getBo_num() == boardNum) {
				ok = true;
				break;
			}
		}
		if (!ok) {
			System.out.println("게시글을 등록할 게시판이 없습니다.");
			return null;
		}

		System.out.print("제목 : ");
		String title = scan.next();
		System.out.print("내용 : ");
		String content = scan.next();

		return new Post(boardNum, title, content, user.getMe_id());
	}

	private void commentManage() {
		int menu;
		do {
			communityPrint.printComment();
			menu = scan.nextInt();
			runCommentManage(menu);
		} while (menu != 0);
	}

	private void runCommentManage(int menu) {
		switch (menu) {
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
		case 0:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
		}
	}

	private void insertComment() {
		int co_po_num = menuSelectAll();
		System.out.print("내용 : ");
		scan.nextLine();
		String co_content = scan.nextLine();

		// 입력받은 정보로 객체를 생성
		Comment com = new Comment(user.getMe_id(), co_content, co_po_num);

		// 생성한 객체를 boolean형 성공 유무 확인하는 메서드로 서비스에 넘김
		if (commentService.insertComment(com)) {
			System.out.println("댓글 추가했습니다.");
		} else {
			System.out.println("댓글 추가에 실패했습니다.");
		}
	}

	// 유저가 댓글 수정(내용만 가능)
	private void updateComment() {	
		int co_po_num = menuSelectAll();
		
		List<Comment> CommentList = commentService.getCommentList(co_po_num);

		// 본인의 아이디와 동일한 댓글이 존재하는지 확인 존재하면 출력
		if (CommentList == null || CommentList.size() == 0) {
			System.out.println("댓글이 없어 수정할 수 없습니다.");
			return;
		}

		// 출력
		for (Comment comment : CommentList) {
			System.out.println(comment);
		}

		// 수정할 댓글 번호를 선택
		System.out.print("댓글 번호 : ");
		int co_num = scan.nextInt();

		// 입력한 댓글 번호가 잘못된 값인지 확인
		if (!CommentList.contains(new Comment(co_num))) {
			System.out.println("잘못된 내역 번호입니다.");
			return;
		}

		// 본인이 작성한 댓글인지 확인
		if (!CommentList.contains(new Comment(co_num, user.getMe_id()))) {
			System.out.println("본인의 댓글이 아닙니다.");
		}

		// 수정할 정보를 입력받음
		System.out.print("수정할 댓글 내용 : ");
		scan.nextLine();
		String co_content = scan.nextLine();

		// 생성한 객체를 boolean형 성공 유무 확인하는 메서드로 서비스에 넘김
		if (commentService.updateComment(new Comment(co_num, user.getMe_id(), co_content))) {
			System.out.println("댓글 수정했습니다.");
		} else {
			System.out.println("댓글 수정에 실패했습니다.");
		}
	}
	
	// 댓글 삭제
	private void deleteComment() {
		int co_po_num = menuSelectAll();
		
		List<Comment> CommentList = commentService.getCommentList(co_po_num);
		
		// 본인의 아이디와 동일한 댓글이 존재하는지 확인 존재하면 출력
		if(CommentList == null || CommentList.size() == 0) {
			System.out.println("해당 게시글에 댓글이 없어 삭제할 수 없습니다.");
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
		
		// 본인이 작성한 댓글인지 확인 후
		// 생성한 객체를 boolean형 성공 유무 확인하는 메서드로 서비스에 넘김
		if(commentService.deleteComment(new Comment(co_num, user.getMe_id()))) {
			CommentList = commentService.getCommentList(co_po_num);
			if(CommentList.size() == 0) {
				if(commentService.resetNum()) {
				}
			}
			System.out.println("댓글 삭제했습니다.");
		}else {
			System.out.println("댓글 삭제에 실패했습니다.");
		}
	}

	// 댓글 조회
	private void printComment() {
		int co_po_num = menuSelectAll();
		
		List<Comment> CommentList = commentService.getCommentList(co_po_num);

		// 본인의 아이디와 동일한 댓글이 존재하는지 확인 존재하면 출력
		if (CommentList == null || CommentList.size() == 0) {
			System.out.println("댓글이 없어 조회할 수 없습니다.");
			return;
		}

		// 출력
		for (Comment comment : CommentList) {
			System.out.println(comment);
		}
	}

	// 게시글 번호 입력받기
	private int menuSelectAll() {
		List<Post> postList = postService.getPostList();

		// Post 내역 출력
		for (Post post : postList) {
			System.out.println(post);
		}

		System.out.print("게시글 번호 : ");
		int po_num = scan.nextInt();
		
		//입력한 게시글 번호가 잘못된 값인지 확인
		if(po_num > postList.size()) {
			System.out.println("잘못된 게시글 번호입니다.");
			return 0;
		}
		return po_num;
	}
}