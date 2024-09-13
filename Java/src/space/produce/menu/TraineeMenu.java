package space.produce.menu;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import space.produce.lesson.Lesson;
import space.produce.rating.JdbcRatingDao;
import space.produce.rating.Rating;
import space.produce.trainee.JdbcTraineeDao;
import space.produce.trainee.Trainee;
import space.produce.util.MyScanner;
import space.produce.util.Util;

public class TraineeMenu {

	private JdbcTraineeDao traineeDao = new JdbcTraineeDao();
	private JdbcRatingDao ratingDao = new JdbcRatingDao();
	private MyScanner scanner = new MyScanner(new Scanner(System.in));

	public void readTraineeAll() {
		// JDBCTraineeDAO.selectAll 사용해서 받아온 list 한 줄씩 출력
		// 컬럼이 너무 많으므로 trainee 하나 당 id, name, sex, birth만 출력해주세요!

		List<Trainee> trainees = traineeDao.selectAll();

		System.out.println("사번 | 이름 | 성별 | 생일");

		for (int i = 0; i < trainees.size(); i++) {
			Trainee trainee = trainees.get(i);
			System.out.printf("%d | %s | %s | %s\n", trainee.getId(), trainee.getName(), trainee.getSex(),
					trainee.getBirth());
		}
	}

	public void addTrainee() {

		// 사용자 입력으로 idx, name, birth, sex, height, weight, nationality, hire_date 받은 후
		// 그 값으로 Trainee 객체 생성해서
		// JDBCTraineeDAO.insert 호출하기
		// true 반환 받으면 성공 메시지 출력

		// 원래 idx는 난수를 생성해서 넣어줄건데 기존 trainee table에서 중복 확인이 필요하므로

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

		Util util = new Util();
		int random = util.generateRandomNumber();

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

		boolean inserts = traineeDao.insert(trainee);

		if (inserts) {
			System.out.println("연습생 등록 완료되었습니다.");
		} else {
			System.out.println("연습생 등록 실패했습니다.");
		}

	}

	public void deleteTrainee() {
		// JDBCTraineeDAO.selectAll으로 받아온 list 출력 후 (넘버링해서)
		// 사용자가 선택한 번호의 연습생 id를 JDBCTraineeDAO.delete에 넘겨 호출
		// true 반환 받으면 성공 메시지 출력

		List<Trainee> trainees = traineeDao.selectAll();

//		for (int i = 0; i < trainees.size(); i++) {
//			Trainee trainee = trainees.get(i);
//			System.out.printf("사번 : %d | 이름 : %s  | 성별 : %s | 생일 : %s\n", (i + 1), trainee.getId(), trainee.getName(),
//					trainee.getSex(), trainee.getBirth());
//		}

		for (int i = 0; i < trainees.size(); i++) {
			System.out.println((i + 1) + "번: " + trainees.get(i));
		}

		System.out.print("삭제할 연습생을 입력하세요: ");
		int id = scanner.takeInt(1, trainees.size());

		for (int i1 = 0; i1 < trainees.size(); i1++) {
			System.out.println("----------------");
			System.out.println(trainees.get(i1).getId());

			if (id != -1) {
				if (traineeDao.deleteById(trainees.get(id - 1).getId()))
					System.out.println("✔️ 삭제 완료");
				else
					System.out.println("❌ 삭제 실패");
				break;
			}
			System.out.println("연습생을 찾을 수 없습니다.");
		}
	}

	public void updateTrainee() {

		List<Trainee> trainees = traineeDao.selectAll();

		System.out.println("전체 연습생 목록");

		for (int i = 0; i < trainees.size(); i++) {
			System.out.println((i + 1) + "번: " + trainees.get(i));
		}

		int id = 0;

		while (true) {
			System.out.println("(☞ﾟヮﾟ)☞ 수정할 연습생을 선택해 주세요");
			id = scanner.takeInt(1, trainees.size());

			if (id != -1) {
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요");
		}

		Trainee trainee = trainees.get(id - 1);

		System.out.println("1. 이름  2. 키  3. 몸무게  4. 국적");
		System.out.print("수정할 정보를 선택해 주세요: ");
		int option = scanner.takeInt(1, 4);

		switch (option) {
		case 1:
			System.out.print("수정할 이름을 입력하세요: ");
			String newName = scanner.takeStr();
			trainee.setName(newName);
			break;
		case 2:
			System.out.print("수정할 키를 입력하세요: ");
			int newHeight = scanner.takeInt(0, Integer.MAX_VALUE);
			trainee.setHeight(newHeight);
			break;
		case 3:
			System.out.print("수정할 몸무게를 입력하세요: ");
			int newWeight = scanner.takeInt(0, Integer.MAX_VALUE);
			trainee.setWeight(newWeight);
			break;
		case 4:
			System.out.print("수정할 국적을 입력하세요: ");
			String newNationality = scanner.takeStr();
			trainee.setNationality(newNationality);
			break;
		default:
			System.out.println("잘못된 입력입니다.");

			return;
		}

		boolean isUpdated = traineeDao.update(trainee);

		if (isUpdated) {
			System.out.println("✅ 수정 완료 되었습니다!");
		} else {
			System.out.println("🚨 수정에 실패했습니다.");
		}

	}

	public void updateGrade() {

		List<Trainee> trainees = traineeDao.selectAll();

		System.out.println("전체 연습생 목록");

		for (int i = 0; i < trainees.size(); i++) {
			System.out.println((i + 1) + "번: " + trainees.get(i));
		}

		int id1 = 0;

		while (true) {
			System.out.println("(☞ﾟヮﾟ)☞ 수정할 연습생을 선택해 주세요");
			id1 = scanner.takeInt(1, trainees.size());

			if (id1 != -1) {
				break;
			}
			System.out.println("🚨 올바른 값을 입력해주세요");
		}

		Trainee trainee = trainees.get(id1 - 1);

		Rating rating = new Rating();

//		for (Trainee trainee : trainees) {
//			if (trainee.getId() == id) {
//				selectedTrainee = trainee;
//				break;
//			}
//		}

		System.out.println("1. VOCAL  2. RAP  3. DANCE  4. TOTAL");
		System.out.print("종목을 선택해 주세요: ");
		int option = scanner.takeInt(1, 4);
		;
		scanner.takeStr();

		System.out.print("A부터 F사이의 등급 중 선택해 주세요: ");

		String grade = scanner.takeStr();

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

		default:
			System.out.println("🚨 올바른 값을 입력해주세요");
			return;
		}

		rating.setCategory(subject);
		rating.setGrade(grade);
		rating.setTrainee(trainee);

		boolean isUpdated = ratingDao.update(rating);

		if (isUpdated) {
			System.out.println("" + subject + "를 " + grade + " 등급으로 업데이트 완료했습니다");
		} else {
			System.out.println("등급 업데이트에 실패했습니다.");
		}
	}
}
