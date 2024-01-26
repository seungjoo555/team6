package university.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import university.Department;
import university.Professor;
import university.School;
import university.Student;

// 서비스 구현클래스
public class UniServiceImp implements UniService {
	private Scanner scan = new Scanner(System.in);
	
	//학생 추가 메서드 : 이철범
	@Override
	public List<Student> addStudent(List<Student> list) {
		System.out.print("이름 : ");
		String sName = scan.next();
		System.out.print("학년 : ");
		int sGrade = scan.nextInt();
		System.out.print("학과 : ");
		String sDep = scan.next();
		System.out.print("학번 : ");
		String sNum = scan.next();
		
		Student std = new Student(sName, sGrade, sDep, sNum);
		
		int index = list.indexOf(std);
		
		if(index != -1) {
			System.out.println("등록된 학생입니다.");
			return list;
		}else {
			list.add(std);
			System.out.println("학생을 등록했습니다.");
		}
		return list;
	}
	//학생 수정 메서드 : 이철범
	@Override
	public List<Student> updateStudent(List<Student> list) {
		if(list.size() == 0) {
			System.out.println("등록된 학생이 없습니다.");
			return list;
		}
		System.out.println(list);
		System.out.print("학번 : ");
		String sNum = scan.next();
		
		Student std = new Student(sNum);
		int index = list.indexOf(std);
		
		if(index != -1) {
			System.out.print("수정할 이름 : ");
			String sName = scan.next();
			System.out.print("수정할 학년 : ");
			int sGrade = scan.nextInt();
			System.out.print("수정할 학과 : ");
			String sDep = scan.next();
			
			Student newStd = new Student(sName, sGrade, sDep, sNum);
			
			list.remove(index);
			
			list.add(newStd);
			System.out.println("학생을 수정했습니다.");
		}else {
			System.out.println("수정할 학생이 없습니다.");
		}
		return list;
	}
	//학생 삭제 메서드 : 이철범
	@Override
	public List<Student> deleteStudent(List<Student> list) {
		if(list.size() == 0) {
			System.out.println("등록된 학생이 없습니다.");
			return list;
		}
		System.out.println(list);
		System.out.print("삭제할 학번 : ");
		String sNum = scan.next();
		
		Student std = new Student(sNum);
		int index = list.indexOf(std);
		
		if(index != -1) {
			list.remove(index);
			System.out.println("학생을 삭제했습니다.");
			return list;
		} else {
			System.out.println("삭제할 학생이 없습니다.");
			return list;
		}
	}
	
	@Override
	// 교수 정보 추가하는 메서드 : 임병훈
	public List<Professor> addProfessor(List<Professor> list) {
		
		System.out.print("교수 번호 : ");
		String pNum = scan.next();
		System.out.print("교수 학과 : ");
		String pDep = scan.next();
		System.out.print("강의 이름 : ");
		String pSub = scan.next();
		System.out.print("교수 이름 : ");
		String pName = scan.next();
		
		// 입력된 정보로 객체 생성
		Professor pf = new Professor(pName, pSub, pDep, pNum);
		
		// 리스트에 해당 객체와 동일한 객체가 있으면 오류 출력 및 메서드 종료
		int index = list.indexOf(pf);
		
		if(index != -1) {
			System.out.println("이미 등록된 교수 번호입니다.");
			return list;
		}
		
		// 리스트에 객체 추가
		list.add(pf);
		
		return list;
	}

	@Override
	// 리스트에 등록된 교수 정보 수정하는 메서드 : 임병훈
	public List<Professor> updateProfessor(List<Professor> list) {
		
		System.out.println(list);
		System.out.print("수정할 교수 번호 : ");
		String pNum = scan.next();

		// location함수로 원하는 교수의 index값 찾기
		int index = location(list, pNum);
		
		// 해당 index가 -1이면 종료
		if(index == -1) {
			return list;
		}
		
		// index가 -1이 아니면 수정할 정보도 입력
		System.out.print("교수 학과 : ");
		String pDep = scan.next();
		System.out.print("강의 이름 : ");
		String pSub = scan.next();
		System.out.print("교수 이름 : ");
		String pName = scan.next();
		
		Professor pf = new Professor(pName, pSub, pDep, pNum);
		
		// index 자리에 pf로 변경
		list.set(index, pf);
		System.out.println("수정 완료");
		
		return list;
	}

	@Override
	// 리스트에 등록된 교수 정보 삭제하는 메서드 : 임병훈
	public List<Professor> deleteProfessor(List<Professor> list) {
		
		System.out.println("삭제 전" + list);
		System.out.print("수정할 교수 번호 : ");
		String pNum = scan.next();

		// location함수로 원하는 교수의 index값 찾기
		int index = location(list, pNum);
		
		// 해당 index가 -1이면 종료
		if(index == -1) {
			return list;
		}
		
		list.remove(index);
		System.out.println("삭제 완료");
		
		return list;
	}
	
	@Override
	// 원하는 리스트에서 원하는 값이 존재하는지, 존재하면 몇번째 index에 존재하는지 찾는 메서드 : 임병훈
	public int location(List<Professor> list,String num) {
		int index = -1;

		// 리스트가 없을 때 
		if(list.size() == 0) {
			System.out.println("등록된 정보가 없습니다.");
			return index;
		}
		
		Professor pf = new Professor(num);
		index = list.indexOf(pf);
		System.out.println(index);
		
		// 찾는 번호가 없는 경우
		if(index == -1) {
			System.out.println("해당 교수번호가 없습니다.");
			return index;
		}
		
		return index;
	}
	
	//학과 등록
	@Override
	public School addDepartment(School school) {
		List<Department> list = school.getDep();
		List<Professor> pf = school.getPrf();
		List<Student> std = school.getStd();
		if(list == null) {
			list = new ArrayList<Department>();
		}
		System.out.print("등록할 학과명 : ");
		String name = scan.next();
		Department tmp = new Department(name);
		if(!list.contains(tmp)) {
			list.add(tmp);
			System.out.println("학과를 등록했습니다.");
			if(!tmp.updatePf(pf)) {
				System.out.println("학과에 등록할 교수가 없습니다.");
			}
			if(!tmp.updateStd(std)) {
				System.out.println("학과에 등록할 학생이 없습니다.");
			}
		}else {
			System.out.println("이미 등록된 학과입니다.");
		}
		school.setDep(list);
		return school;
	}
	@Override
	public School updateDPM_Name(School school) {
		List<Department> list = school.getDep();
		if(list == null) {
			System.out.println("학과를 먼저 등록해 주세요.");
			return school;
		}
		System.out.print("학과명 : ");
		String name = scan.next();
		int index = dpmLocation(list, name);
		
		if(index == -1) {
			return school;
		}
		
		System.out.print("수정할 학과명 : ");
		String updateName = scan.next();
		
		list.get(index).setName(updateName);
		school.updatePfAll(name, updateName);
		school.updateStdAll(name, updateName);
		
		List<Professor> pf = school.getPrf();
		List<Student> std = school.getStd();
		if(!list.get(index).updatePf(pf)) {
			System.out.println("학과에 등록할 교수가 없습니다.");
		}
		if(!list.get(index).updateStd(std)) {
			System.out.println("학과에 등록할 학생이 없습니다.");
		}
		school.setDep(list);
		System.out.println(name + "를 " + updateName + "로 수정 완료");
		return school;
	}
	
	@Override
	public School updateDPM_PfStd(School school) {
		List<Department> list = school.getDep();
		List<Professor> pf = school.getPrf();
		List<Student> std = school.getStd();
		if(list == null) {
			System.out.println("학과를 먼저 등록해 주세요.");
			return school;
		}
		System.out.print("학과명 : ");
		String name = scan.next();
		int index = dpmLocation(list, name);
		
		if(index == -1) {
			return school;
		}
		
		if(!list.get(index).updatePf(pf)) {
			System.out.println("학과에 등록할 교수가 없습니다.");
		}
		if(!list.get(index).updateStd(std)) {
			System.out.println("학과에 등록할 학생이 없습니다.");
		}
		school.setDep(list);
		System.out.println(name + "의 " + "교수, 학생 정보 업데이트 완료");
		return school;
	}
	
	@Override
	public School deleteDepartment(School school) {
		List<Department> list = school.getDep();
		if(list == null) {
			System.out.println("학과를 먼저 등록해 주세요.");
			return school;
		}
		System.out.print("학과명 : ");
		String name = scan.next();
		int index = dpmLocation(list, name);
		
		if(index == -1) {
			return school;
		}
		list.remove(index);
		school.updatePfAll(name, "무소속");
		school.updateStdAll(name, "무소속");
		school.setDep(list);
		System.out.println(name + "를 삭제했습니다. 남겨진 교수와 학생의 소속을 변경해주세요.");
		return school;
	}
	
	//병훈님 코드 강탈
	public int dpmLocation(List<Department> list, String name) {
		int index = -1;
		
		Department dpm = new Department(name);
		index = list.indexOf(dpm);
		
		// 찾는 번호가 없는 경우
		if(index == -1) {
			System.out.println("일치하는 학과가 없습니다.");
			return index;
		}
		
		return index;
	}
}