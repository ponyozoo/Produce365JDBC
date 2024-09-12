package space.produce.menu;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import space.produce.debut.Debut;
import space.produce.debut.DebutDao;
import space.produce.debut.JDBCDebutDao;
import space.produce.debutMember.DebutMember;
import space.produce.debutMember.DebutMemberDao;
import space.produce.debutMember.JDBCDebutMemberDao;
import space.produce.trainee.JdbcTraineeDao;
import space.produce.trainee.Trainee;
import space.produce.trainee.TraineeDao;

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
		//insert의 반환값이 true면
		
		if(debutDao.insert(debut) == true) {
			System.out.println("🆗 데뷔조 생성 완료!");
		}else
		{
			System.out.println("⚠️ 데뷔조 생성 실패!");
		}
		
		
		
		
		//실패면, "입력에 실패하였습니다." 출력.

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
		System.out.println("✅ 삭제할 데뷔조를 선택해주세요 : ");
		int pickIndex = Integer.parseInt(scanner.nextLine());
		
		int pickId = debutList.get(pickIndex-1).getId();
		
		
		System.out.println("⚠️ 해당 팀을 정말로 삭제하시겠습니까? : 1.예  2.아니오");
		int pickDel = Integer.parseInt(scanner.nextLine());
		if(pickDel == 1) {
			debutDao.deleteById(pickId);
			// true 반환 받으면 성공 메시지 출력
			System.out.println("🆗 삭제 완료!");
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
		System.out.println("✅ 수정할 데뷔조를 선택해주세요 : ");
		int pickIndex = Integer.parseInt(scanner.nextLine());
		
		//고객이 입력한 인덱스의 데뷔조를 리스트에서 선택
		Debut selectedTeam = debutList.get(pickIndex - 1);
		
		System.out.println("✅ 수정할 항목을 선택해주세요 : 1. 등급 수정   2. 데뷔 예정일 수정");
		int picNo = Integer.parseInt(scanner.nextLine());
		
		
		switch(picNo) {
		case 1 : { //1. 등급 수정
			System.out.println("등급을 입력해주세요 : ");
			String newGrade = scanner.nextLine();
			selectedTeam.setGrade(newGrade);
			debutDao.update(selectedTeam);
			System.out.println("🆗 수정 완료!");
			break;
		}
		case 2 : { //2. 데뷔 예정일 수정
			System.out.println("데뷔 예정일을 YYYY-MM-DD의 형식으로 입력해주세요 : ");
			String newDateInput = scanner.nextLine();
			Date newDebutDate = Date.valueOf(newDateInput);
			selectedTeam.setDebutDate(newDebutDate);
			debutDao.update(selectedTeam);
			System.out.println("🆗 수정 완료!");
			break;
		}
		}


		
		
		
		
		
	}

	public void updateDebutMember() {
		/* 0. 멤버 추가인지 삭제인지 선택.
		 * 1. 멤버 추가
		 * 1-1. 데뷔조목록 출력
		 * 1-2. index로 멤버 추가 혹은 삭제해줄 데뷔조 select.
		 * 1-3. 추가 선택시, sql문으로 데뷔조에 포함 안 된 연습생목록 출력
		 * 1-4. index로 select하고 get ID
		 * 1-5. 변수에 get한 ID넣어 데뷔조 insert메소드 실행.
		 * 
		 * 2. 멤버 삭제
		 * 2-1  삭제 선택시, sql문으로 데뷔멤버 테이블에 있는 연습생목록 출력
		 * 2-2. index로 select
		 * 2-3. delete메소드 실행.
		 */
		
		//0.멤버 추가인지 삭제인지 선택.
		System.out.println("✅ 수정할 데뷔조 멤버 설정을 선택해주세요 : 1.데뷔조 멤버 추가  2. 데뷔조 멤버 삭제 ");
		int selectMenu = Integer.parseInt(scanner.nextLine());
		
		if(selectMenu ==1) {//1. 멤버 추가
			//1-1. 데뷔조목록 출력
			DebutDao debutDao = new JDBCDebutDao();
			List<Debut> debutList = debutDao.findAll();
			for (int i = 0; i < debutList.size(); i++) {
				System.out.println((i + 1) + ": " + debutList.get(i));
			}
			System.out.println("✅ 데뷔조를 선택해주세요 : ");
			
			//1-2. index로 멤버 추가해줄 데뷔조 select.
			int selectTeam = Integer.parseInt(scanner.nextLine());
			Debut pickTeam = debutList.get(selectTeam-1);
			
			//1-3. sql문으로 데뷔조에 포함 안 된 연습생목록 출력
			TraineeDao traineeDao = new JdbcTraineeDao();
			List<Trainee> traineeNoDebutList = traineeDao.selectNoDebut();
			for(int i = 0; i < traineeNoDebutList.size(); i++) {
				System.out.println((i + 1) + ": " + traineeNoDebutList.get(i));
			}
			System.out.println("✅ 데뷔조에 추가할 연습생을 선택해주세요 : ");
			
			//1-4. index로 select하고 get debutMember 객체
			int pickTrIndex = Integer.parseInt(scanner.nextLine());
			Trainee pickTrainee = traineeNoDebutList.get(pickTrIndex-1);
			
			DebutMember newDebutMember = new DebutMember();
			newDebutMember.setGroup(pickTeam);
			newDebutMember.setTrainee(pickTrainee);
			
			//1-5. 변수에 get한 DebutMember 객체넣어 데뷔조 insert메소드 실행
			System.out.println("✅ 해당 인물을 데뷔조에 넣으시겠습니까? : 1. 예   2. 아니오 ");
			int putDebut = Integer.parseInt(scanner.nextLine());
			if(putDebut == 1) {
			DebutMemberDao debutMemberDao = new JDBCDebutMemberDao();
			if(debutMemberDao.insert(newDebutMember)) {
				System.out.println("🆗 추가 완료!");
			}else {
				System.out.println("⚠️ 추가 실패!");
			}
			
			}else if(putDebut == 2) {
				//while문 활용하여 연습생 선택 창으로 돌아가기.
			}else {
				System.out.println("🚨 잘못된 입력입니다.");
			}
			
			
		}else if(selectMenu ==2) {//2.멤버 삭제
			
			//2-1. 멤버를 삭제할 데뷔조 목록 출력
			DebutDao debutDao = new JDBCDebutDao();
			List<Debut> debutList = debutDao.findAll();
			for (int i = 0; i < debutList.size(); i++) {
				System.out.println((i + 1) + ": " + debutList.get(i));
			}
			
			//2-2. index로 멤버 삭제해줄 데뷔조 select.
			int selectTeam = Integer.parseInt(scanner.nextLine());
			int pickTeam = debutList.get(selectTeam-1).getId();
			
			
			//2-3 sql문으로 데뷔멤버 테이블에 있는 연습생목록 출력
			DebutMemberDao DebutMemberDao = new JDBCDebutMemberDao();
			List<DebutMember> debutMembers = DebutMemberDao.selectByGroup(pickTeam);
			for(int i = 0; i < debutMembers.size(); i++) {
				System.out.println((i + 1) + ": " + debutMembers.get(i));
			}
			
			//2-4. index로 삭제할 연습생 select
			int pickTrIndex = Integer.parseInt(scanner.nextLine());
			
			//2-3. 선택한 index의 연습생 객체의 idx를 변수로 넣어 deleteById 메소드 실행
			DebutMemberDao debutMemberDao = new JDBCDebutMemberDao();
			System.out.println("✅ 해당 인물을 정말 데뷔조에서 삭제하시겠습니까? : 1. 예   2. 아니오 ");
			int outDebut = Integer.parseInt(scanner.nextLine());
			if(outDebut == 1) {
				if(debutMemberDao.deleteById(debutMembers.get(pickTrIndex-1).getIdx())) {
					System.out.println("🆗 삭제 완료!");
				}else {
					System.out.println("⚠️ 삭제 실패!");
				}
			}else if(outDebut == 2) {
				//while문 활용하여 연습생 선택 창으로 돌아가기.
			}else {
				System.out.println("🚨 잘못된 입력입니다.");
			}
			
			
		}else {
			System.out.println("🚨 잘못된 입력입니다.");
		}
		
		
		
		
		
		
	}
	
	
}
