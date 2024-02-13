package university.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import university.Department;
import university.Professor;
import university.School;
import university.Student;
import university.Subject;
import university.UniProgram;

// 서비스 구현클래스
public class UniServiceImp implements UniService {

	private Scanner scan = new Scanner(System.in);

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

		if (index != -1) {
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
		if (index == -1) {
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
		System.out.print("삭제할 교수 번호 : ");
		String pNum = scan.next();

		// location함수로 원하는 교수의 index값 찾기
		int index = location(list, pNum);

		// 해당 index가 -1이면 종료
		if (index == -1) {
			return list;
		}

		list.remove(index);
		System.out.println("삭제 완료");

		return list;
	}

	@Override
	// 원하는 리스트에서 원하는 값이 존재하는지, 존재하면 몇번째 index에 존재하는지 찾는 메서드 : 임병훈
	public int location(List<Professor> list, String num) {
		int index = -1;

		// 리스트가 없을 때
		if (list.size() == 0) {
			System.out.println("등록된 정보가 없습니다.");
			return index;
		}

		Professor pf = new Professor(num);
		index = list.indexOf(pf);
		System.out.println(index);

		// 찾는 번호가 없는 경우
		if (index == -1) {
			System.out.println("해당 교수번호가 없습니다.");
			return index;
		}

		return index;
	}

	// 학과 등록
	@Override
	public School addDepartment(School school) {
		List<Department> list = school.getDep();
		List<Professor> pf = school.getPrf();
		List<Student> std = school.getStd();
		if (list == null) {
			list = new ArrayList<Department>();
		}
		System.out.print("등록할 학과명 : ");
		String name = scan.next();
		Department tmp = new Department(name);
		if (!list.contains(tmp)) {
			list.add(tmp);
			System.out.println("학과를 등록했습니다.");
			if (!tmp.updatePf(pf)) {
				System.out.println("학과에 등록할 교수가 없습니다.");
			}
			if (!tmp.updateStd(std)) {
				System.out.println("학과에 등록할 학생이 없습니다.");
			}
		} else {
			System.out.println("이미 등록된 학과입니다.");
		}
		school.setDep(list);
		return school;
	}

	// 학과 이름 수정
	@Override
	public School updateDPM_Name(School school) {
		List<Department> list = school.getDep();
		if (list == null) {
			System.out.println("학과를 먼저 등록해 주세요.");
			return school;
		}
		System.out.print("학과명 : ");
		String name = scan.next();
		int index = dpmLocation(list, name);

		if (index == -1) {
			return school;
		}

		System.out.print("수정할 학과명 : ");
		String updateName = scan.next();

		list.get(index).setName(updateName);
		school.updatePfAll(name, updateName);
		school.updateStdAll(name, updateName);

		List<Professor> pf = school.getPrf();
		List<Student> std = school.getStd();
		if (!list.get(index).updatePf(pf)) {
			System.out.println("학과에 등록할 교수가 없습니다.");
		}
		if (!list.get(index).updateStd(std)) {
			System.out.println("학과에 등록할 학생이 없습니다.");
		}
		school.setDep(list);
		System.out.println(name + "를 " + updateName + "로 수정 완료");
		return school;
	}

	// 학과 업데이트
	@Override
	public School updateDPM_PfStd(School school) {
		List<Department> list = school.getDep();
		List<Professor> pf = school.getPrf();
		List<Student> std = school.getStd();
		if (list == null) {
			System.out.println("학과를 먼저 등록해 주세요.");
			return school;
		}
		System.out.print("학과명 : ");
		String name = scan.next();
		int index = dpmLocation(list, name);

		if (index == -1) {
			return school;
		}

		if (!list.get(index).updatePf(pf)) {
			System.out.println("학과에 등록할 교수가 없습니다.");
		}
		if (!list.get(index).updateStd(std)) {
			System.out.println("학과에 등록할 학생이 없습니다.");
		}
		school.setDep(list);
		System.out.println(name + "의 " + "교수, 학생 정보 업데이트 완료");
		return school;
	}

	// 학과 삭제
	@Override
	public School deleteDepartment(School school) {
		List<Department> list = school.getDep();
		if (list == null) {
			System.out.println("학과를 먼저 등록해 주세요.");
			return school;
		}
		System.out.print("학과명 : ");
		String name = scan.next();
		int index = dpmLocation(list, name);

		if (index == -1) {
			return school;
		}
		list.remove(index);
		school.updatePfAll(name, "무소속");
		school.updateStdAll(name, "무소속");
		school.setDep(list);
		System.out.println(name + "를 삭제했습니다. 남겨진 교수와 학생의 소속을 변경해주세요.");
		return school;
	}

	// 병훈님 코드 강탈
	public int dpmLocation(List<Department> list, String name) {
		int index = -1;

		Department dpm = new Department(name);
		index = list.indexOf(dpm);

		// 찾는 번호가 없는 경우
		if (index == -1) {
			System.out.println("일치하는 학과가 없습니다.");
			return index;
		}

		return index;
	}

	// 학생 추가 메서드 : 이철범
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

		if (index != -1) {
			System.out.println("등록된 학생입니다.");
			return list;
		} else {
			list.add(std);
			System.out.println("학생을 등록했습니다.");
		}
		return list;
	}

	// 학생 수정 메서드 : 이철범
	@Override
	public List<Student> updateStudent(List<Student> list) {
		if (list.size() == 0) {
			System.out.println("등록된 학생이 없습니다.");
			return list;
		}
		System.out.println(list);
		System.out.print("학번 : ");
		String sNum = scan.next();

		Student std = new Student(sNum);
		int index = list.indexOf(std);

		if (index != -1) {
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
		} else {
			System.out.println("수정할 학생이 없습니다.");
		}
		return list;
	}

	// 학생 삭제 메서드 : 이철범
	@Override
	public List<Student> deleteStudent(List<Student> list) {
		if (list.size() == 0) {
			System.out.println("등록된 학생이 없습니다.");
			return list;
		}
		System.out.println(list);
		System.out.print("삭제할 학번 : ");
		String sNum = scan.next();

		Student std = new Student(sNum);
		int index = list.indexOf(std);

		if (index != -1) {
			list.remove(index);
			System.out.println("학생을 삭제했습니다.");
			return list;
		} else {
			System.out.println("삭제할 학생이 없습니다.");
			return list;
		}
	}

	// 학생 조회 메서드 : 이철범
	public void printStudent(List<Student> list) {
		List<Student> std = new ArrayList<Student>();
		std.addAll(list);
		sort(std);
		System.out.println(std);
	}

	// 학생 정렬 메서드 : 이철범
	private void sort(List<Student> list) {
		// 학년
		list.sort((s1, s2) -> {
			if (s1.getSGrade() != s2.getSGrade()) {
				return s1.getSGrade() - s2.getSGrade();
			}
			return 0;
		});
		// 학과
		Collections.sort(list, (s1, s2) -> s1.getSDep().charAt(1) - s2.getSDep().charAt(1));
		// 학번
		Collections.sort(list, (s1, s2) -> s1.getSNum().charAt(1) - s2.getSNum().charAt(1));
	}

	@Override // 강의 삭제 메서드 : 정경호
	public List<Subject> removeSubject(List<Subject> removelist, List<Professor> pfList) {
		System.out.println(pfList.toString());
		System.out.print("삭제할 교수번호 :");
		UniProgram.scan.nextLine();
		String pNum = UniProgram.scan.nextLine();
		int pfIndex = -1;
		for (int i = 0; i < pfList.size(); i++) {
			if (pfList.get(i).getPNum().equals(pNum)) {
				pfIndex = i;
				break;
			}
		}
		if (pfIndex == -1) {
			System.out.println("등록되지 않은 교수 번호입니다.");
			return removelist;
		}
		System.out.print("삭제할 강의명 : ");
		String sName = UniProgram.scan.nextLine();
		System.out.println("삭제할 교수명 :" + pfList.get(pfIndex).getPName());
		Subject sj = new Subject(sName, pfList.get(pfIndex).getPName(), pNum, pfList.get(pfIndex).getPDep());

		if (removelist.contains(sj)) {
			removelist.remove(sj);
			System.out.println("강의가 삭제 되었습니다.");
		} else {
			System.out.println("삭제할 강의가 없습니다.");
		}
		return removelist;
	}

	@Override // 강의 추가 메서드 : 정경호
	public List<Subject> addSubject(List<Subject> addList, List<Professor> pfList) {
		System.out.println(pfList.toString());
		System.out.print("추가할 교수번호 :");
		UniProgram.scan.nextLine();
		String pNum = UniProgram.scan.nextLine();
		int pfIndex = -1;
		for (int i = 0; i < pfList.size(); i++) {
			if (pfList.get(i).getPNum().equals(pNum)) {
				pfIndex = i;
				break;
			}
		}
		if (!pfList.contains(new Professor(pNum))) {
			System.out.println("등록되지 않은 교수 번호입니다.");
			return addList;
		}
		System.out.println(" 교수명 :" + pfList.get(pfIndex).getPName());
		System.out.println(" 학과명 : " + pfList.get(pfIndex).getPDep());
		System.out.println(" 강의명 :" + pfList.get(pfIndex).getPSubject());
		Subject sj = new Subject(pfList.get(pfIndex).getPSubject(), pfList.get(pfIndex).getPName(), pNum,
				pfList.get(pfIndex).getPDep());
		int addIndex = addList.indexOf(sj);
		if (addIndex == -1) {
			addList.add(sj);
			System.out.println("강의가 추가 되었습니다.");
		} else {
			System.out.println("중복된 강의 입니다.");
		}
		return addList;
	}

	@Override // 강의 수정 메서드 : 정경호
	public List<Subject> updateSubject(List<Subject> upList, List<Professor> pfList) {
		System.out.println(upList.toString());
		System.out.print("수정할 교수번호 : ");
		UniProgram.scan.nextLine();
		String oldPnum = UniProgram.scan.nextLine();
		int pfIndex = pfList.indexOf(new Professor(oldPnum));
		/*
		for (int i = 0; i < upList.size(); i++) {
			if (pfList.get(i).getPNum().equals(oldPnum)) {
				pfIndex = i;
				break;
			}
		}
		*/
		if (pfIndex == -1) {
			System.out.println("존재하지 않습니다.");
			return upList;
		}
		Professor selectedProfessor = pfList.get(pfIndex);
		System.out.println("수정할 강의명 :" + selectedProfessor.getPSubject());
		System.out.println("수정할 교수명 :" + selectedProfessor.getPName());

//		Subject oldSj = new Subject(selectedProfessor.getPSubject(),
//				selectedProfessor.getPName(), oldPnum,selectedProfessor.getPDep());

		Subject oldSj = new Subject(selectedProfessor);
		pfIndex = upList.indexOf(oldSj);

		System.out.println("---------------");
		System.out.print("새로운 교수번호 :");
		String newPnum = UniProgram.scan.nextLine();
		for (int i = 0; i < upList.size(); i++) {
			if (pfList.get(i).getPNum().equals(newPnum)){
				pfIndex = i;
				return upList;
			}
		}
		if (pfIndex < 0 || pfIndex >= pfList.size()) {
			System.out.println("교수번호가 존재하지 않습니다.");
			return upList;
		}
		System.out.print("새로운 강의명 :" + pfList.get(pfIndex).getPSubject());
		System.out.println("새로운 교수명 :" + pfList.get(pfIndex).getPName());
		System.out.println("새로운 교수 학과 : " + pfList.get(pfIndex).getPDep());

		Subject newSj = new Subject(pfList.get(pfIndex).getPSubject(), 
				pfList.get(pfIndex).getPName(), newPnum, pfList.get(pfIndex).getPDep());
		upList.remove(pfIndex); 
		upList.add(newSj);
		System.out.println(newSj.toString());
		System.out.println("수정이 완료 되었습니다.");
		return upList;
	}

	@Override // 강의 조회 : 정경호
	public boolean checkSub(List<Subject> sb) {
		if (sb == null || sb.isEmpty()) {
			System.out.println("강의가 없습니다.");
			return false;
		}
		for (int i = 0; i < sb.size(); i++) {
			sb.get(i);
			System.out.println(sb.get(i).toString());
		}
		return true;
	}

	// 수강 신청
	@Override
	public List<Student> addlecture(List<Student> list, List<Subject> subList) {
		System.out.println(list);
		System.out.print("신청할 학생 학번 : ");
		String sNum = scan.next();
		int stdIndex = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSNum().equals(sNum)) {
				stdIndex = i;
				break;
			}
		}
		if (stdIndex == -1) {
			System.out.println("등록되지 않은 학생 번호입니다.");
			return list;
		}

		System.out.println(subList);
		System.out.print("신청할 강의명: ");
		String subName = scan.next();
		int subIndex = -1;
		for (int i = 0; i < subList.size(); i++) {
			if (subList.get(i).getSubName().equals(subName)) {
				subIndex = i;
				break;
			}
		}
		if (subIndex == -1) {
			System.out.println("등록되지 않은 강의명입니다.");
			return list;
		}
		String index = list.get(stdIndex).getSubName();
		if (index.contains(subName)) {
			list.get(stdIndex).setSubName(subName);
			System.out.println("강의를 신청했습니다.");
			return list;
		} else {
			System.out.println("이미 신청한 강의입니다.");
		}
		return list;
	}

	public List<Student> addScore(List<Student> list) {

		System.out.print("점수를 추가할 학번 : ");
		String sNum = scan.next();
		System.out.print("점수를 추가할 과목 : ");
		String subNum = scan.next();
		System.out.print("해당 과목 점수 : ");
		int score = scan.nextInt();

		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSNum().equals(sNum)) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			System.out.println("등록된 학생이 아닙니다.");
			return list;
		}

		Map<String, Integer> map = list.get(index).getMap();
		if (map == null) {
			map = new HashMap<>();
		}

		map.put(subNum, score);
		list.get(index).setMap(map);

		System.out.println("학생 점수를 추가했습니다.");
		return list;
	}

	@Override
	public List<Student> updateScore(List<Student> list) {

		System.out.print("점수를 수정할 학번 : ");
		String sNum = scan.next();
		System.out.print("점수를 추가할 과목 : ");
		String subNum = scan.next();

		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSNum().equals(sNum)) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			System.out.println("등록된 학생이 아닙니다.");
			return list;
		}

		Map<String, Integer> map = list.get(index).getMap();
		if (map == null) {
			map = new HashMap<>();
		}

		if (map.containsKey(subNum)) {
			System.out.print("수정할 점수 : ");
			int score = scan.nextInt();

			map.put(subNum, score);
			list.get(index).setMap(map);

			System.out.println("수정 성공!");
			return list;
		}
		System.out.println("수정 실패");
		return list;
	}

	@Override
	public List<Student> removeScore(List<Student> list) {

		System.out.print("점수를 삭제할 학생 : ");
		String sNum = scan.next();
		System.out.print("점수를 삭제할 과목 : ");
		String subNum = scan.next();

		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSNum().equals(sNum)) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			System.out.println("등록된 학생이 아닙니다.");
			return list;
		}

		Map<String, Integer> map = list.get(index).getMap();
		if (map == null) {
			map = new HashMap<>();
		}

		if (map.containsKey(subNum)) {
			map.remove(subNum);
			System.out.println("삭제 성공!");
			return list;
		}
		System.out.println("삭제 실패");
		return list;
	}

}