package space.produce.menu;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import space.produce.debutMember.DebutMember;
import space.produce.debutMember.JDBCDebutMemberDao;
import space.produce.rating.JDBCRatingDao;
import space.produce.rating.Rating;
import space.produce.rating.RatingCategory;
import space.produce.trainee.JDBCTraineeDao;
import space.produce.trainee.Trainee;
import space.produce.util.MyScanner;
import space.produce.util.Util;

public class TraineeMenu {

	private JDBCTraineeDao traineeDao = new JDBCTraineeDao();
	private JDBCRatingDao ratingDao = new JDBCRatingDao();
	private JDBCDebutMemberDao debutMemberDao = new JDBCDebutMemberDao();
	private MyScanner scanner = new MyScanner(new Scanner(System.in));
	private Util util = new Util();

	
	public void selectTraineeMenu() {
		while (true) {
			System.out.println("""
					
			__________________________________________________🕺💃_________________________________________________
			
			                                              [ 연습생 관리 ]
			
			           1. 연습생 조회  2. 연습생 추가  3. 연습생 삭제  4. 연습생 정보 수정  5. 연습생 등급 수정  6. 뒤로 가기             
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
					selectReadTraineeMenu();
					break;
				}
				case 2: {
					addTrainee();
					break;
				}
				case 3: {
					deleteTrainee();
					break;
				}
				case 4: {
					updateTrainee();
					break;
				}
				case 5: {
					updateGrade();
					break;
				}
				case 6: {
					return;
				}
			}
		}
	}
	
	public void selectReadTraineeMenu() {
		while (true) {
			System.out.println("""
					
			__________________________________________________🕺💃_________________________________________________
			
			                                              [ 연습생 조회 ]
			
			        1. 전체 조회   2. 성별 조회   3. 국적별 조회   4. 종합 등급별 조회   5. 데뷔조 여부에 따른 조회  6. 뒤로 가기               
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
					readTraineeAll();
					break;
				}
				case 2: {
					readTraineeBySex();
					break;
				}
				case 3: {
					readTraineeByNationality();
					break;
				}
				case 4: {
					readTraineeByTodalGrade();
					break;
				}
				case 5: {
					readTraineeByDebut();
					break;
				}
				case 6: {
					return;
				}
			}
		}
	}

	public void readTraineeAll() {
		List<Trainee> trainees = traineeDao.selectAll();

		if (trainees.isEmpty()) {
			System.out.println("📢 연습생이 없습니다");
			return ;
		}

		for (int i = 0; i < trainees.size(); i++) {
			Trainee trainee = trainees.get(i);
			System.out.println((i + 1) + ". " + trainee);
		}

		this.printSelectedTrainee(trainees);
	}

	public void readTraineeBySex() {
		int sexChoice;
		String sex = "M";

		sexChoice = scanner.takeIntCycle("✅ 확인할 연습생 성별을 선택해주세요\n1. 남성  2. 여성 : ", 1, 2);
		
		if (sexChoice == 1) {
			sex = "M";
		} else if (sexChoice == 2) {
			sex = "F";
		}

		List<Trainee> trainees = traineeDao.selectBySex(sex);

		System.out.println("");
		if (trainees.isEmpty()) {
			System.out.println("📢 해당 성별에 해당하는 연습생이 없습니다");
		} else {
			for (int i = 0; i < trainees.size(); i++) {
				System.out.println((i + 1) + ". " + trainees.get(i));
			}
			this.printSelectedTrainee(trainees);
		}
	}

	public void readTraineeByNationality() {
		List<String> nationalities = traineeDao.selectDistinctNationality();

		for (int i = 0; i < nationalities.size(); i++) {
			System.out.println((i + 1) + ". " + nationalities.get(i));
		}

		int nationalityChoice;
		String nationality = null;

		nationalityChoice = scanner.takeIntCycle("\n국적을 선택해주세요 : ", 1, nationalities.size());
		nationality = nationalities.get(nationalityChoice - 1);

		List<Trainee> trainees = traineeDao.selectByNationality(nationality);

		System.out.println("");
		
		for (int i = 0; i < trainees.size(); i++) {
			System.out.println((i + 1) + ". " + trainees.get(i));
		}
		this.printSelectedTrainee(trainees);
	}

	public void readTraineeByTodalGrade() {
		String grade = "";
		while (true) {
			System.out.print("A부터 F 사이의 등급을 입력해주세요 : ");
			grade = util.checkGrade(scanner.takeStr());
			if (!grade.equals("")) {
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		System.out.println("");

		List<Rating> totalGrade = ratingDao.selectByTotalGrade(grade);

		if (totalGrade.isEmpty()) {
			System.out.println("📢 해당 등급에 해당하는 연습생이 없습니다");
			return;
		}

		List<Trainee> trainees = new ArrayList<Trainee>();
		for (int i = 0; i < totalGrade.size(); i++) {
			System.out.println((i + 1) + ". ➤ 등급: " + totalGrade.get(i).getGrade() + " " + totalGrade.get(i).getTrainee());
			trainees.add(totalGrade.get(i).getTrainee());
		}
		this.printSelectedTrainee(trainees);
	}

	public void readTraineeByDebut() {
		int selectedNum = scanner.takeIntCycle("✅ 확인할 연습생 분류를 선택해주세요\n1. 데뷔조 연습생  2. 비데뷔조 연습생 : ", 1, 2);

		System.out.println("");

		if (selectedNum == 1) {
			List<DebutMember> debutMembers = debutMemberDao.selectAll();
			
	    	if (debutMembers.isEmpty()) {
	    		System.out.println("📢 데뷔조에 속한 연습생이 없습니다");
	    		return ;
	    	}

			List<Trainee> trainees = new ArrayList<Trainee>();
			for (int i = 0; i < debutMembers.size(); i++) {
				System.out.println((i + 1) + ". ➤ 데뷔조명: " + debutMembers.get(i).getGroup().getName() + " " + debutMembers.get(i).getTrainee());
				trainees.add(debutMembers.get(i).getTrainee());
			}
			this.printSelectedTrainee(trainees);
		} else {
			List<Trainee> notDebutMembers = traineeDao.selectNoDebut();
			
	    	if (notDebutMembers.isEmpty()) {
	    		System.out.println("📢 데뷔조가 아닌 연습생이 없습니다");
	    		return ;
	    	}

			for (int i = 0; i < notDebutMembers.size(); i++) {
				System.out.println((i + 1) + ". " + notDebutMembers.get(i));
			}
			this.printSelectedTrainee(notDebutMembers);			
		}
	}

	public void addTrainee() {
		String name = scanner.takeStrCycle("이름을 입력해주세요 : ");
		
		String sex;
		while (true) {
			System.out.print("성별을 입력해주세요 (M/F) : ");
			sex = scanner.takeStr().toUpperCase();
			if (sex.equals("M") || sex.equals("F"))
				break ;
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}
		
		Date birth;
		while (true) {
    		System.out.print("생년월일을 입력해주세요 : ");
    		String birthStr = scanner.takeStr();
    		try {
	    		birth = Date.valueOf(birthStr);
    		} catch (Exception e) {
    			System.out.println("🚨 올바른 값을 입력해주세요 (날짜는 YYYY-MM-DD 형식입니다)\n");
    			continue ;
    		}
    		break ;
    	}
		
		int height = scanner.takeIntCycle("키를 입력하세요: ", 0, Integer.MAX_VALUE);
		int weight = scanner.takeIntCycle("몸무게를 입력하세요: ", 0, Integer.MAX_VALUE);
		String nationality = scanner.takeStrCycle("국적을 입력하세요: ");
		
		Date hireDate;
		while (true) {
    		System.out.print("입사일을 입력해주세요 (오늘이라면 엔터를 눌러주세요) : ");
    		String hireDateStr = scanner.takeStr();
    		try {
	    		hireDate = hireDateStr == "" ? null : Date.valueOf(hireDateStr);
    		} catch (Exception e) {    			
    			System.out.println("🚨 올바른 값을 입력해주세요 (날짜는 YYYY-MM-DD 형식입니다)\n");
    			continue ;
    		}
    		break ;
    	}
		
		int random;
		while (true) {
			random = util.generateRandomNumber();
			Trainee trainees = traineeDao.selectById(random);
			if (trainees == null) {
				break;
			}
		}

		Trainee trainee = new Trainee();
		trainee.setId(random);
		trainee.setName(name);
		trainee.setSex(sex);
		trainee.setBirth(birth);
		trainee.setHeight(height);
		trainee.setWeight(weight);
		trainee.setNationality(nationality);
		trainee.setHireDate(hireDate);
		
		if (traineeDao.insert(trainee))
			System.out.println("\n✔️ 등록 완료");
		else
			System.out.println("\n❌ 등록 실패");
	}

	public void deleteTrainee() {
		List<Trainee> trainees = traineeDao.selectAll();
		
    	if (trainees.isEmpty()) {
    		System.out.println("📢 연습생이 없습니다");
    		return ;
    	}

		for (int i = 0; i < trainees.size(); i++) {
			System.out.println((i + 1) + ". " + trainees.get(i));
		}

		int id = scanner.takeIntCycle("\n삭제할 연습생을 선택해주세요 : ", 1, trainees.size());

		if (traineeDao.deleteById(trainees.get(id - 1).getId()))
			System.out.println("\n✔️ 삭제 완료");
		else
			System.out.println("\n❌ 삭제 실패");
	}

	public void updateTrainee() {
		List<Trainee> trainees = traineeDao.selectAll();
		
    	if (trainees.isEmpty()) {
    		System.out.println("📢 연습생이 없습니다");
    		return ;
    	}

		for (int i = 0; i < trainees.size(); i++) {
			System.out.println((i + 1) + ". " + trainees.get(i));
		}

		int id = scanner.takeIntCycle("\n수정할 연습생을 선택해주세요 : ", 1, trainees.size());

		Trainee trainee = trainees.get(id - 1);

		int option = scanner.takeIntCycle("\n✅ 수정할 정보를 선택해주세요\n1. 이름  2. 키  3. 몸무게  4. 국적 : ", 1, 4);

		switch (option) {
			case 1:
				String newName = scanner.takeStrCycle("\n수정할 이름을 입력해주세요 : ");
				trainee.setName(newName);
				break;
			case 2:
				int newHeight = scanner.takeIntCycle("\n수정할 키를 입력해주세요 : ", 0, Integer.MAX_VALUE);
				trainee.setHeight(newHeight);
				break;
			case 3:
				int newWeight = scanner.takeIntCycle("\n수정할 몸무게를 입력해주세요 : ", 0, Integer.MAX_VALUE);
				trainee.setWeight(newWeight);
				break;
			case 4:
				String newNationality = scanner.takeStrCycle("\n수정할 국적을 입력하세요 : ");
				trainee.setNationality(newNationality);
				break;
		}

		boolean isUpdated = traineeDao.update(trainee);

		if (isUpdated)
			System.out.println("\n✔️ 수정 완료");
		else
			System.out.println("\n❌ 수정 실패");

	}

	public void updateGrade() {
		List<Trainee> trainees = traineeDao.selectAll();
		
    	if (trainees.isEmpty()) {
    		System.out.println("📢 연습생이 없습니다");
    		return ;
    	}

		for (int i = 0; i < trainees.size(); i++) {
			System.out.println((i + 1) + ". " + trainees.get(i));
		}

		int id1 = scanner.takeIntCycle("\n수정할 연습생을 선택해주세요 : ", 1, trainees.size());

		Trainee trainee = trainees.get(id1 - 1);
		Rating rating = new Rating();

		int option = scanner.takeIntCycle("\n✅ 수정할 분야를 선택해주세요\n1. VOCAL  2. RAP  3. DANCE  4. TOTAL : ", 1, 4);

		String grade = "";
		while (true) { 
			System.out.print("\n등급을 입력해주세요 (A~F) : ");
			grade = scanner.takeStr();
			if (!grade.equals(""))
				break ;
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		String subject = null;

		switch (option) {
			case 1:
				subject = "VOCAL";
				break;
			case 2:
				subject = "RAP";
				break;
			case 3:
				subject = "DANCE";
				break;
			case 4:
				subject = "TOTAL";
				break;
		}

		rating.setCategory(subject);
		rating.setGrade(grade);
		rating.setTrainee(trainee);

		boolean isUpdated = ratingDao.update(rating);

		if (isUpdated)
			System.out.println("\n✔️ 수정 완료");
		else
			System.out.println("\n❌ 수정 실패");
	}

	private void printSelectedTrainee(List<Trainee> trainees) {
		int selectedNum = scanner.takeIntCycle("\n상세정보를 확인할 연습생 번호를 선택해주세요 (넘어가려면 0 입력) : ", 0, trainees.size());
		if (selectedNum != 0) {
			List<Rating> ratings = ratingDao.selectByTraineeId(trainees.get(selectedNum - 1).getId());
			RatingCategory ratingByTrainee = new RatingCategory();
			for (Rating rating : ratings) {
				switch (rating.getCategory()) {
					case "TOTAL" :
						ratingByTrainee.setTotal(rating.getGrade());
						break ;
					case "VOCAL" :
						ratingByTrainee.setVocal(rating.getGrade());
						break ;
					case "DANCE" :
						ratingByTrainee.setDance(rating.getGrade());
						break ;
					case "RAP" :
						ratingByTrainee.setRap(rating.getGrade());
						break ;
				}
			}
			
			System.out.println("\n" + trainees.get(selectedNum - 1).toStringAll());
			System.out.println(ratingByTrainee);
		}
	}
}
