package space.produce.menu;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import space.produce.debutMember.DebutMember;
import space.produce.debutMember.JDBCDebutMemberDao;
import space.produce.rating.JdbcRatingDao;
import space.produce.rating.Rating;
import space.produce.trainee.JdbcTraineeDao;
import space.produce.trainee.Trainee;
import space.produce.util.MyScanner;
import space.produce.util.Util;

public class TraineeMenu {

	private JdbcTraineeDao traineeDao = new JdbcTraineeDao();
	private JdbcRatingDao ratingDao = new JdbcRatingDao();
	private JDBCDebutMemberDao debutMemberDao = new JDBCDebutMemberDao();
	private MyScanner scanner = new MyScanner(new Scanner(System.in));
	private Util util = new Util();

	public void selectTrainneeMenu() {

		while (true) {
			System.out.println("""

			__________________________________________________🕺💃_______________________________________________

															[ 연습생 관리 ]

						1. 전체 조회   2. 성별 조회   3. 국적별 조회   4. 종합 등급별 조회   5. 데뷔조 여부에 따른 조회
			____________________________________________________________________________________________________

			""");
			
			int selectMenu = scanner.takeInt(1, 5);
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
		String sex;

		while (true) {
			System.out.print("✅ 확인할 연습생 성별을 선택해주세요\n1. 남성  2. 여성 : ");
			sexChoice = scanner.takeInt(1, 2);

			if (sexChoice == 1) {
				sex = "M";
				break;
			} else if (sexChoice == 2) {
				sex = "F";
				break;
			} else {
				System.out.println("🚨 올바른 값을 입력해주세요\n");
			}
		}

		List<Trainee> trainees = traineeDao.selectBySex(sex);

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

		while (true) {
			System.out.print("국적 번호를 선택해주세요 : ");
			nationalityChoice = scanner.takeInt(1, 3);
			if (nationalityChoice != -1) {
				nationality = nationalities.get(nationalityChoice - 1);
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		List<Trainee> trainees = traineeDao.selectByNationality(nationality);

		if (trainees.isEmpty()) {
			System.out.println("📢 해당 국적에 해당하는 연습생이 없습니다.");
		} else {
			for (int i = 0; i < trainees.size(); i++) {
				System.out.println((i + 1) + ". " + trainees.get(i));
			}
			this.printSelectedTrainee(trainees);
		}
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

		for (Rating rating : totalGrade) {
			System.out.println("➤ 등급: " + rating.getGrade() + " " + rating.getTrainee());
		}
	}

	public void readTraineeByDebut() {
		int selectedNum = 0;
		while (true) {
			System.out.print("✅ 확인할 연습생 분류를 선택해주세요\n1. 데뷔조 연습생  2. 비데뷔조 연습생 : ");
			selectedNum = scanner.takeInt(1, 2);
			if (selectedNum != -1)
				break;
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		System.out.println("");

		if (selectedNum == 1) {
			List<DebutMember> debutMembers = debutMemberDao.selectAll();

			for (DebutMember member : debutMembers) {
				System.out.println("➤ 데뷔조명: " + member.getGroup().getName() + " " + member.getTrainee());
			}
		} else {
			List<Trainee> notDebutMembers = traineeDao.selectNoDebut();

			for (Trainee trainee : notDebutMembers) {
				System.out.println(trainee);
			}
		}
	}

	public void addTrainee() {
		System.out.println("이름을 입력하세요: ");
		String name = scanner.takeStr();

		System.out.println("성별을 입력하세요: ");
		String sex = scanner.takeStr();

		System.out.println("생일 \"YYYY-MM-DD\"을 입력하세요: ");
		String birth = scanner.takeStr();

		System.out.println("키를 입력하세요: ");
		int height = scanner.takeInt(0, Integer.MAX_VALUE);

		System.out.println("몸무게를 입력하세요: ");
		int weight = scanner.takeInt(0, Integer.MAX_VALUE);

		System.out.println("국적을 입력하세요: ");
		String nationality = scanner.takeStr();

		System.out.println("입사일 \"YYYY-MM-DD\"을 입력하세요: ");
		String hireDate = scanner.takeStr();

		int random = util.generateRandomNumber();

		int randomNo;
		while (true) {
			randomNo = util.generateRandomNumber();
			Trainee trainees = traineeDao.selectById(randomNo);
			if (trainees == null) {
				break;
			}
		}

		Date DateInput1 = Date.valueOf(birth);
		Date DateInput2 = Date.valueOf(hireDate);
		JdbcTraineeDao jdbcTraineeDao = new JdbcTraineeDao();
		Trainee trainee = new Trainee();
		trainee.setId(random);
		trainee.setName(name);
		trainee.setSex(sex);
		trainee.setBirth(Date.valueOf(birth));
		trainee.setHeight(height);
		trainee.setWeight(weight);
		trainee.setNationality(nationality);
		trainee.setHireDate(Date.valueOf(hireDate));

		if (traineeDao.insert(trainee)) {
			System.out.println("연습생 등록 완료되었습니다.");
		} else {
			System.out.println("연습생 등록 실패했습니다.");
		}
	}

	public void deleteTrainee() {
		List<Trainee> trainees = traineeDao.selectAll();

		for (int i = 0; i < trainees.size(); i++) {
			System.out.println((i + 1) + ". " + trainees.get(i));
		}

		int id = 0;
		while (true) { 
			System.out.print("삭제할 연습생을 선택해주세요 : ");
			id = scanner.takeInt(1, trainees.size());
			if (id != -1)
				break ;
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		if (traineeDao.deleteById(trainees.get(id - 1).getId()))
			System.out.println("✔️ 삭제 완료");
		else
			System.out.println("❌ 삭제 실패");
	}

	public void updateTrainee() {
		List<Trainee> trainees = traineeDao.selectAll();

		for (int i = 0; i < trainees.size(); i++) {
			System.out.println((i + 1) + ". " + trainees.get(i));
		}

		int id = 0;
		while (true) {
			System.out.println("수정할 연습생을 선택해주세요 : ");
			id = scanner.takeInt(1, trainees.size());

			if (id != -1) {
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		Trainee trainee = trainees.get(id - 1);

		int option = 0;
		while (true) { 
			System.out.print("✅ 수정할 정보를 선택해주세요\n1. 이름  2. 키  3. 몸무게  4. 국적 : ");

			option = scanner.takeInt(1, 4);
			if (option != -1)
				break ;
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		switch (option) {
			case 1:
				System.out.print("수정할 이름을 입력하세요 : ");
				String newName = scanner.takeStr();
				trainee.setName(newName);
				break;
			case 2:
				System.out.print("수정할 키를 입력하세요 : ");
				int newHeight = scanner.takeInt(0, Integer.MAX_VALUE);
				trainee.setHeight(newHeight);
				break;
			case 3:
				System.out.print("수정할 몸무게를 입력하세요 : ");
				int newWeight = scanner.takeInt(0, Integer.MAX_VALUE);
				trainee.setWeight(newWeight);
				break;
			case 4:
				System.out.print("수정할 국적을 입력하세요 : ");
				String newNationality = scanner.takeStr();
				trainee.setNationality(newNationality);
				break;
		}

		boolean isUpdated = traineeDao.update(trainee);

		if (isUpdated)
			System.out.println("✔️ 수정 완료");
		else
			System.out.println("❌ 수정 실패");

	}

	public void updateGrade() {
		List<Trainee> trainees = traineeDao.selectAll();

		for (int i = 0; i < trainees.size(); i++) {
			System.out.println((i + 1) + ". " + trainees.get(i));
		}

		int id1 = 0;

		while (true) {
			System.out.print("\n수정할 연습생을 선택해주세요 : ");
			id1 = scanner.takeInt(1, trainees.size());

			if (id1 != -1) {
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요");
		}

		Trainee trainee = trainees.get(id1 - 1);
		Rating rating = new Rating();

		int option = 0;
		while (true) { 
			System.out.print("✅ 수정할 분야를 선택해주세요\n1. VOCAL  2. RAP  3. DANCE  4. TOTAL");
			option = scanner.takeInt(1, 4);
			if (option != -1)
				break ;
			System.out.println("🚨 올바른 값을 입력해주세요\n");
		}

		String grade = "";
		while (true) { 
			System.out.print("등급을 입력해주세요 (A~F) : ");
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
			System.out.println("✔️ 수정 완료");
		else
			System.out.println("❌ 수정 실패");
	}

	private void printSelectedTrainee(List<Trainee> trainees) {
		int selectedNum = 0;
		while (true) { 
			System.out.print("\n상세정보를 확인할 연습생 번호를 선택해주세요 (넘어가려면 0 입력) : ");
			selectedNum = scanner.takeInt(0, trainees.size());
			if (selectedNum != -1)
				break ;
			System.out.println("🚨 올바른 값을 입력해주세요");			
		}

		if (selectedNum != 0)
			System.out.println(trainees.get(selectedNum - 1).toStringAll());
	}
}
