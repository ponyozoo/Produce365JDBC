package space.produce.menu;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import space.produce.debut.Debut;
import space.produce.debut.DebutDao;
import space.produce.debut.JDBCDebutDao;

public class DebutMenu {
	private Scanner scanner = new Scanner(System.in);

	public void readDebut() {
		// JDBCDebutDAO.selectAll 사용해서 받아온 list 한 줄씩 출력
		DebutDao debutDao = new JDBCDebutDao();
		List<Debut> debutList = debutDao.findAll();

		// 변수로 i를 받고 i는 debutList의 크기보다는 작고, for문이 한 번 돌아갈때마다 값이 하나씩 커진다.
		// 1. i+1를 출력.(i는 0부터 시작하므로)
		// 2. i옆에 selectAll 구문을 이용하여 받아온 리스트를 한 줄씩 출력.
		for (int i = 0; i < debutList.size(); i++) {
			System.out.println((i + 1) + ": " + debutList.get(i));
		}
	}

	public void addDebut() {
		// 사용자 입력으로 name, member_count, concept, grade, debut_date 받은 후
		System.out.println("그룹명 입력 : ");
		String name = scanner.nextLine();

		System.out.println("인원 수 입력 : ");
		int memberCount = Integer.parseInt(scanner.nextLine());

		System.out.println("콘셉트 입력 : ");
		String concept = scanner.nextLine();

		System.out.println("종합 평가 점수 입력 : ");
		String grade = scanner.nextLine();

		System.out.println("데뷔 예정일을 YYYY-MM-DD의 형식으로 입력해주세요 : ");
		String inputDate = scanner.nextLine();
		Date debutDate = Date.valueOf(inputDate);

		//입력받은debutDate이 오늘 이후인지 체크.
		

		Debut debut = new Debut();
		debut.setName(name);
		debut.setMemberCount(memberCount);
		debut.setConcept(concept);
		debut.setGrade(grade);
		debut.setDebutDate(debutDate);

		DebutDao debutDao = new JDBCDebutDao();
		debutDao.insert(debut);
		System.out.println("입력이 완료되었습니다!👍");

		// 그 값으로 Debut 객체 생성해서
		// JDBCDebutDAO.insert 호출하기
		// true 반환 받으면 성공 메시지 출력

		// debut_date가 오늘 이후인지 한 번 체크하고 아니라면 입력 다시 받기 기능이 있어야 하는데
		// 요부분 살짝 까다로워서 하실 수 있으면 해주시고 아니면 그냥 값 받은대로 넣어주세요!
	}

	public void deleteDebut() {
		// JDBCDebutDAO.selectAll으로 받아온 list 출력 후 (넘버링해서)
		DebutMenu debutMenu = new DebutMenu();
		DebutDao debutDao = new JDBCDebutDao();
		List<Debut> debutList = debutDao.findAll();
		for (int i = 0; i < debutList.size(); i++) {
			System.out.println((i + 1) + ": " + debutList.get(i));
		}

		// 사용자가 선택한 번호의 데뷔조를 JDBCTraineeDAO.delete에 넘겨 호출
		System.out.println("✔️ 삭제할 데뷔조를 선택해주세요 : ");
		int pickIndex = Integer.parseInt(scanner.nextLine());
		
		int pickId = debutList.get(pickIndex-1).getId();
		
		
		System.out.println("⚠️ 해당 팀을 정말로 삭제하시겠습니까? : 1.예  2.아니오");
		int pickDel = Integer.parseInt(scanner.nextLine());
		if(pickDel == 1) {
			debutDao.deleteById(pickId);
			// true 반환 받으면 성공 메시지 출력
			System.out.println("🪦 삭제가 완료되었습니다.");
		}else if(pickDel ==2) {
			deleteDebut();
		}else {
			System.out.println("🚨 잘못된 입력입니다.");
			deleteDebut();
		}
	}

	

	public void updateDebut() {
		// JDBCDebutDAO.selectAll으로 받아온 list 출력 후 (넘버링해서)
		DebutMenu debutMenu = new DebutMenu();
		DebutDao debutDao = new JDBCDebutDao();
		List<Debut> debutList = debutDao.findAll();
		for (int i = 0; i < debutList.size(); i++) {
			System.out.println((i + 1) + ": " + debutList.get(i));
		}
		
		// 사용자가 선택한 번호의 데뷔조를 수정할 것임
		System.out.println("✔️ 수정할 데뷔조를 선택해주세요 : ");
		int pickIndex = Integer.parseInt(scanner.nextLine());
		
		//고객이 입력한 인덱스의 데뷔조를 리스트에서 선택
		Debut selectedTeam = debutList.get(pickIndex - 1);
		
		System.out.println("✔️ 수정할 항목을 선택해주세요 : 1. 등급 수정   2. 데뷔 예정일 수정");
		int picNo = Integer.parseInt(scanner.nextLine());
		
		
		switch(picNo) {
		case 1 : { //1. 등급 수정
			System.out.println("등급을 입력해주세요 : ");
			String newGrade = scanner.nextLine();
			selectedTeam.setGrade(newGrade);
			debutDao.update(selectedTeam);
			System.out.println("수정이 완료되었습니다!👍");
			break;
		}
		case 2 : { //2. 데뷔 예정일 수정
			System.out.println("데뷔 예정일을 YYYY-MM-DD의 형식으로 입력해주세요 : ");
			String newDateInput = scanner.nextLine();
			Date newDebutDate = Date.valueOf(newDateInput);
			selectedTeam.setDebutDate(newDebutDate);
			debutDao.update(selectedTeam);
			System.out.println("수정이 완료되었습니다!👍");
			break;
		}
		}
		
		
		// 1. 등급 수정 2. 데뷔 예정일 수정 출력해서
		// 사용자가 선택한 번호와 입력 받은 값으로 해당 Debut 객체 set 메소드로 수정
		// JDBCDebutDAO.update에 수정한 객체 넘겨서 호출
		// true 반환 받으면 성공 메시지 출력

		// 여기도 debut_date가 오늘 이후인지 한 번 체크하고 아니라면 입력 다시 받기 기능이 있어야 하는데
		// 요부분 살짝 까다로워서 하실 수 있으면 해주시고 아니면 그냥 값 받은대로 넣어주세요!

		// 이해가 잘 안될까봐 밑에가 콘솔에 뜨는 순서입니당

		// 전체 데뷔조 목록 출력 ...
		// 데뷔조를 선택해주세요 :
		// (사용자 값 입력 받기 - 숫자)
		// 1. 등급 수정 2. 데뷔 예정일 수정
		// 수정할 정보를 선택해주세요 :
		// (사용자 값 입력 받기 - 숫자)
		// 수정할 값을 입력해주세요 :
		// (사용자 값 입력 받기)
		// 수정 완료 되었습니다!
	}

}
