package community.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import community.model.vo.Member;
import community.service.UserService;
import community.service.UserServiceImp;

public class CommunityController {
	
	private static Member user;
	private Scanner scan;
	private UserService userService;
	
	public CommunityController(Scanner scan) {
		if(scan == null) {
			scan = new Scanner(System.in);
		}
		this.scan = scan;
		userService = new UserServiceImp();
	}
	
	public void rogIn() {
		//로그인체크
		System.out.print("아이디 : ");
		String id = scan.next();
		//아이디로 데이터베이스에서 가져오기
		user = userService.checkId(id);
		//없다면 종료
		if(user == null) {
			System.out.println("없는아이디입니다. 회원가입을 해주세요");
			return;
		}
		System.out.print("비밀번호 : ");
		String pwd = scan.next();
		//비밀번호 체크 다르면 초기화
		if(!user.getMe_pw().equals(pwd)) {
			System.out.println("비밀번호가 틀렸습니다. 다시 로그인 해주세요.");
			user = new Member();
			return;
		}
		if(user.getMe_authority().equals("ADMIN")) {
			//관리자일때 실행할 메뉴메서드
			adminMenu();
		}else if(user.getMe_authority().equals("USER") && user.getMe_ms_state().equals("회원")){
			//유저이고 회원상태일때 실행할 메뉴메서드
			userMenu();
		}else {
			System.out.println("가입요청중 혹은 이용정지된 회원입니다.");
		}
		
	}
	
	public void signUp() {
		System.out.println("회원가입 절차 시작");
		String idRegex = "^[a-zA-Z0-9_]{6,12}$";
		String pwdRegex = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
		String emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		String id,pwd,pwd2,email,name,phone,addr;
		do {
			System.out.print("아이디(영문,숫자 6~12자리) : ");
			id = scan.next();
			if(Pattern.matches(idRegex, id)) {
				System.out.println("아이디로 사용 가능합니다.");
				break;
			}else {
				System.out.println("아이디 형식에 맞지 않습니다.");
				continue;
			}
		}while(true);
		do {
			System.out.print("비밀번호(영문,숫자,특수문자포함 8~15자리) : ");
			pwd = scan.next();
			System.out.print("비밀번호 확인 : ");
			pwd2 = scan.next();
			if(!pwd.equals(pwd2)) {
				System.out.println("비밀번호와 확인부분이 다릅니다. 다시 입력 해주세요.");
				continue;
			}
			if(Pattern.matches(pwdRegex, pwd)) {
				System.out.println("비밀번호로 사용 가능합니다.");
				break;
			}else {
				System.out.println("비밀번호 형식에 맞지 않습니다.");
				continue;
			}
		}while(true);
		do {
			System.out.print("이메일 : ");
			email = scan.next();
			if(Pattern.matches(emailRegex, email)) {
				System.out.println("이메일로 사용 가능합니다.");
				break;
			}else {
				System.out.println("이메일 형식에 맞지 않습니다.");
				continue;
			}
		}while(true);
		do {
			System.out.print("이름 : ");
			name = scan.next();
			if(name.length() < 2 || name.length() > 30) {
				System.out.println("이름은 2자이상 30자이하 입니다.");
				continue;
			}else {
				break;
			}
		}while(true);
		System.out.print("전화번호 : ");
		phone = scan.next();
		
		System.out.print("주소(oo시 oo구 oo동) : ");
		scan.nextLine();
		addr = scan.nextLine();
		
		Member member = new Member(id, pwd, email, name, phone, addr);
		if(userService.insertMember(member)) {
			System.out.println("회원가입 요청 완료.");
		}else {
			System.out.println("회원가입 실패. 잠시후 다시 시도해주세요.");
		}
	}
	
	public void adminMenu() {
		//관리자메뉴 (사용자관리 - 정보수정x, 이용정지, 탈퇴기능, 가입승인) - 데이터베이스에 사용자상태 추가
		//1.사용자관리, 2.게시판관리(카테고리,게시판), 3.게시글관리, 4.댓글관리, 5.로그아웃
		int menu;
		do {
			System.out.println("관리자메뉴");
			System.out.println("1.사용자관리");
			System.out.println("2.카페이용(추가,수정,삭제 가능) - 대기중");
			System.out.println("3.로그아웃");
			System.out.print("메뉴선택 : ");
			menu = scan.nextInt();
			runAdmin(menu);
		}while(menu != 3);
	}
	
	private void runAdmin(int menu) {
		switch(menu) {
		case 1://사용자 관리
			adminUserManagerMenu();
			break;
		case 2:
			System.out.println("미구현");
			break;
		case 3://로그아웃
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
			System.out.println("2.회원 이용정지");
			System.out.println("3.회원 탈퇴기능");
			System.out.println("4.뒤로가기");
			System.out.print("메뉴선택 : ");
			menu = scan.nextInt();
			runAdminUserManager(menu);
		}while(menu != 4);
	}

	private void runAdminUserManager(int menu) {
		switch(menu) {
		case 1://가입요청 승인
			memberRequest();
			break;
		case 2://회원 이용정지
			stopMember();
			break;
		case 3://회원 탈퇴기능
			deleteMember();
			break;
		case 4://뒤로가기
			System.out.println("이전으로 돌아갑니다.");
			break;
		default:
			throw new InputMismatchException();
		}
		
	}

	private void memberRequest() {
		//가입요청중인 아이디 목록
		List<Member> userList = userService.getRequestMember("가입요청");
		if(userList == null || userList.size() == 0) {
			System.out.println("가입요청중인 회원이 없습니다.");
			return;
		}
		//가입요청중인 아이디가 있으면 목록으로 출력
		for(Member member : userList) {
			System.out.println(member.getMe_id() + " " +member.getMe_ms_state());
		}
		//승인할 아이디 입력 all일경우 모두 승인
		System.out.print("승인할 아이디 입력(모두 승인하려면 all) : ");
		String me_id = scan.next();
		if(me_id.equals("all")) {
			userService.okeydokeyAllRequest();
			System.out.println("가입요청 모두승인");
			return;
		}
		//입력한 아이디가 있는지 확인
		else if(!userList.contains(new Member(me_id))) {
			System.out.println("아이디를 잘못 입력했습니다.");
			return;
		}
		
		if(userService.okeydokeyRequest(me_id)) {
			System.out.println(me_id + "의 가입요청을 승인했습니다.");
		}else {
			System.out.println("요청을 승인하지 못했습니다.");
		}
		
		
	}

	private void stopMember() {
		//회원인 아이디 목록
		
		//정지시킬 아이디 입력
		
	}

	private void deleteMember() {
		//회원인 아이디 목록
		
		//탈퇴(삭제)시킬 아이디 입력
		
	}

	public void userMenu() {
		//사용자메뉴 (내정보관리 - 수정, 탈퇴)
		//1.내정보관리, 2.카페이용(게시글, 댓글), 3.로그아웃
		int menu;
		do {
			System.out.println("메뉴");
			System.out.println("1.내정보관리");
			System.out.println("2.카페이용(게시글, 댓글) - 대기중");
			System.out.println("3.로그아웃");
			System.out.print("메뉴선택 : ");
			menu = scan.nextInt();
			runUser(menu);
		}while(menu != 3);
	}

	private void runUser(int menu) {
		switch(menu) {
		case 1:
			break;
		case 2:
			System.out.println("미구현");
			break;
		case 3:
			System.out.println(user.getMe_id() + "님 로그아웃 완료");
			user = new Member();
			break;
		default:
			throw new InputMismatchException();
		}
	}
	
	
}