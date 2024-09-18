package space.produce.menu;

import java.sql.Date;
import java.time.LocalDate;
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
import space.produce.util.MyScanner;
import space.produce.util.Util;

public class DebutMenu {
	private DebutDao debutDao = new JDBCDebutDao();
	private DebutMemberDao debutMemberDao = new JDBCDebutMemberDao();
	private TraineeDao traineeDao = new JdbcTraineeDao();
	private MyScanner scanner = new MyScanner(new Scanner(System.in));
	private Util util = new Util();

	public void selectDebutMenu() {

		while (true) {
			System.out.println("""
					
			__________________________________________________🕺💃_________________________________________________
			
			                                              [ 데뷔조 관리 ]
			
			         1. 데뷔조 조회   2. 데뷔조 추가   3. 데뷔조 삭제   4. 데뷔조 정보 수정   5. 데뷔조 멤버 수정   6. 뒤로 가기                 
			_______________________________________________________________________________________________________
					                
			""");
			int selectMenu = scanner.takeInt(1, 6);

			if (selectMenu == -1) {
				System.out.println("🚨 올바른 값을 입력해주세요");
				continue;
			}
			
			System.out.println("");
			
			switch (selectMenu) {
				case 1: {
					readDebut();
					break;
				}
				case 2: {
					addDebut();
					break;
				}
				case 3: {
					deleteDebut();
					break;
				}
				case 4: {
					updateDebut();
					break;
				}
				case 5: {
					updateDebutMember();
					break;
				}
				case 6: {
					return;
				}
			}
		}
		
	}

	public void readDebut() {
		List<Debut> debutList = debutDao.findAll();

		for (int i = 0; i < debutList.size(); i++) {
			System.out.println((i + 1) + ". " + debutList.get(i));
		}
		
		int selectedNum = 0;
		while (true) {
			System.out.print("\n멤버를 확인할 데뷔조를 선택해주세요 : ");
			selectedNum = scanner.takeInt(1, debutList.size());
			if (selectedNum != -1) {
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}
		
		List<DebutMember> memberList = debutMemberDao.selectByGroup(debutList.get(selectedNum - 1).getId());
		
		System.out.println("");
		
		for (int i = 0; i < memberList.size(); i++) {
			System.out.println(memberList.get(i).getTrainee());
		}
	}

	public void addDebut() {
		String name;
		while (true) {
			System.out.print("그룹명 입력을 입력해주세요 : ");
			name = scanner.takeStr();
			if (!name.equals("")) {
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		int memberCount = 0;
		while (true) {
			System.out.print("인원 수를 입력해주세요 : ");
			memberCount = scanner.takeInt(1, Integer.MAX_VALUE);
			if (memberCount != -1) {
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		String concept;
		while (true) {
			System.out.print("컨셉을 입력해주세요 : ");
			concept = scanner.takeStr();
			if (!concept.equals("")) {
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		String grade;
		while (true) {
			System.out.print("종합 평가 등급을 입력해주세요 (A~F) : ");
			grade = util.checkGrade(scanner.takeStr());
			if (!grade.equals("")) {
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		Date debutDate = null;
		while (true) {
			System.out.print("데뷔 예정일을 입력해주세요 (오늘 이후의 날짜만 가능합니다) : ");
			String inputDate = scanner.takeStr();
			try {
				debutDate = Date.valueOf(inputDate);
				if (debutDate.compareTo(Date.valueOf(LocalDate.now())) <= 0)
					throw new Exception();
				break;
			} catch (Exception e) {
				System.out.println("🚨 올바른 값을 입력해주세요 (날짜는 YYYY-MM-DD 형식입니다)\n");				
			}
		}
			
		Debut debut = new Debut();
		debut.setName(name);
		debut.setMemberCount(memberCount);
		debut.setConcept(concept);
		debut.setGrade(grade);
		debut.setDebutDate(debutDate);

		if (debutDao.insert(debut) == true)
			System.out.println("✔️ 추가 완료");
		else
			System.out.println("❌ 추가 실패");
	}

	public void deleteDebut() {
		List<Debut> debutList = debutDao.findAll();
		
		for (int i = 0; i < debutList.size(); i++) {
			System.out.println((i + 1) + ". " + debutList.get(i));
		}

		int pickIndex = 0;
		while (true) {
			System.out.print("\n삭제할 데뷔조를 선택해주세요 : ");
			pickIndex = scanner.takeInt(1, debutList.size());
			if (pickIndex != -1)
				break;
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		int pickId = debutList.get(pickIndex - 1).getId();

		int pickDel = 0;
		while (true) {
			System.out.print("⚠️ 해당 팀을 정말로 삭제하시겠습니까?\n1.예  2.아니오 : ");
			pickDel = scanner.takeInt(1, 2);

			if (pickDel == 1) {
				if (debutDao.deleteById(pickId))
					System.out.println("✔️ 삭제 완료");
				else
					System.out.println("❌ 추가 실패");					
				break;
			} else if (pickDel == 2) {
				System.out.println("📢 이전 메뉴로 돌아갑니다");
				break ;
			} else {
				System.out.println("🚨 올바른 값을 입력해주세요\n");
			}
		}
	}

	public void updateDebut() {
		int picNo = 0;
		while (true) {
			System.out.println("""
			__________________________________________________🕺💃_________________________________________________
			
			                                            [ 데뷔조 정보 수정 ]
			
			                                1. 등급 수정   2. 데뷔 예정일 수정   3. 뒤로 가기                 
			_______________________________________________________________________________________________________
					                
			""");
			picNo = scanner.takeInt(1, 3);
			
			if (picNo != -1)
				break ;
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}
		
		if (picNo == 3)
			return ;
		
		System.out.println("");
		
		List<Debut> debutList = debutDao.findAll();

		for (int i = 0; i < debutList.size(); i++) {
			System.out.println((i + 1) + ". " + debutList.get(i));
		}

		int pickIndex = 0;
		while (true) {
			System.out.print("\n수정할 데뷔조를 선택해주세요 : ");
			pickIndex = scanner.takeInt(1, debutList.size());
			if (pickIndex != -1)
				break;
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		Debut selectedTeam = debutList.get(pickIndex - 1);

		switch (picNo) {
			case 1: { 
				String newGrade;
				while (true) {
					System.out.print("등급을 입력해주세요 (A~F) : ");
					newGrade = util.checkGrade(scanner.takeStr());
					if (!newGrade.equals("")) {
						break;
					}
					System.out.println("🚨 올바른 값을 입력해주세요\n");
				}
				
				selectedTeam.setGrade(newGrade);
				
				if (debutDao.update(selectedTeam)) {
					System.out.println("✔️ 수정 완료");
				} else {
					System.out.println("❌ 수정 실패");
				}
				
				break;
			}
			case 2: {
				while (true) {
					System.out.print("데뷔 예정일을 입력해주세요 : ");
					String newDateInput = scanner.takeStr();
					Date newDebutDate = null;
					
					try {
						newDebutDate = Date.valueOf(newDateInput);
						if (newDebutDate.compareTo(Date.valueOf(LocalDate.now())) <= 0)
							throw new Exception();
						selectedTeam.setDebutDate(newDebutDate);
						break ;
					} catch (Exception e) {
						System.out.println("🚨 올바른 값을 입력해주세요 (날짜는 YYYY-MM-DD 형식입니다)\n");				
					}
				}
				
				if (debutDao.update(selectedTeam)) {
					System.out.println("✔️ 수정 완료");
				} else {
					System.out.println("❌ 수정 실패");
				}
				break;
			}
		}
	}

	public void updateDebutMember() {
		int selectMenu = 0;
		while (true) {
			System.out.println("""
			__________________________________________________🕺💃_________________________________________________
			
			                                            [ 데뷔조 멤버 수정 ]
			
			                              1. 데뷔조 멤버 추가   2. 데뷔조 멤버 삭제   3. 뒤로 가기                 
			_______________________________________________________________________________________________________
					                
			""");
			selectMenu = scanner.takeInt(1, 3);
			
			if (selectMenu != -1)
				break ;
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}
		
		if (selectMenu == 3)
			return ;
		
		System.out.println("");
		
		List<Debut> debutList = debutDao.findAll();
		for (int i = 0; i < debutList.size(); i++) {
			System.out.println((i + 1) + ". " + debutList.get(i));
		}

		int selectTeam = 0;
		while (true) {
			System.out.print("\n데뷔조를 선택해주세요 : ");
			selectTeam = scanner.takeInt(1, debutList.size());
			if (selectTeam != -1) {
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}
		Debut pickTeam = debutList.get(selectTeam - 1);

		if (selectMenu == 1) {
			System.out.println("");
			
			List<Trainee> traineeNoDebutList = traineeDao.selectNoDebut();
			for (int i = 0; i < traineeNoDebutList.size(); i++) {
				System.out.println((i + 1) + ". " + traineeNoDebutList.get(i));
			}

			System.out.print("\n데뷔조에 추가할 연습생을 선택해주세요 : ");

			int pickTrIndex = 0;
			while (true) {
				pickTrIndex = scanner.takeInt(1, traineeNoDebutList.size());
				if (pickTrIndex != -1) {
					break;
				}
				System.out.println("🚨 올바른 값을 입력해주세요\n");
			}
			
			Trainee pickTrainee = traineeNoDebutList.get(pickTrIndex - 1);
			DebutMember newDebutMember = new DebutMember();
			newDebutMember.setGroup(pickTeam);
			newDebutMember.setTrainee(pickTrainee);

			int putDebut = 0;
			while (true) {
				System.out.print("\n⚠️ 해당 인물을 데뷔조에 넣으시겠습니까?\n1. 예   2. 아니오 : ");
				putDebut = scanner.takeInt(1, 2);
				if (putDebut != -1) {
					break;
				}
				System.out.println("🚨 올바른 값을 입력해주세요\\n");
			}
			
			if (putDebut == 1) {
				if (debutMemberDao.insert(newDebutMember)) {
					System.out.println("✔️ 수정 완료");
				} else {
					System.out.println("❌ 수정 실패");
				}
			} else if (putDebut == 2) {
				System.out.println("📢 이전 메뉴로 돌아갑니다");
				return ;
			}
		} else if (selectMenu == 2) {
			System.out.println("");

			List<DebutMember> debutMembers = debutMemberDao.selectByGroup(pickTeam.getId());
			for (int i = 0; i < debutMembers.size(); i++) {
				System.out.println((i + 1) + ". " + debutMembers.get(i).getTrainee());
			}

			int pickTrIndex = 0;
			while (true) {
				System.out.print("\n데뷔조에서 삭제할 연습생을 선택해주세요 : ");
				pickTrIndex = scanner.takeInt(1, debutMembers.size());
				if (selectTeam != -1) {
					break;
				}
				System.out.println("🚨 올바른 값을 입력해주세요\n");
			}

			int outDebut = 0;
			while (true) {
				System.out.print("\n⚠️ 해당 인물을 정말 데뷔조에서 삭제하시겠습니까?\n1. 예   2. 아니오 : ");
				outDebut = scanner.takeInt(1, 2);
				if (outDebut != -1) {
					break;
				}
				System.out.println("🚨 잘못된 입력입니다.\n");
			}

			if (outDebut == 1) {
				if (debutMemberDao.deleteById(debutMembers.get(pickTrIndex - 1).getIdx())) {
					System.out.println("✔️ 수정 완료");
				} else {
					System.out.println("❌ 수정 실패");
				} 
			} else if (outDebut == 2) {
				System.out.println("📢 이전 메뉴로 돌아갑니다");
			}
		}
	}
}
