package space.produce.menu;

import java.util.List;
import java.util.Scanner;

import space.produce.trainee.JdbcTraineeDao;
import space.produce.trainee.Trainee;

public class TraineeMenu {

	private JdbcTraineeDao traineeDao = new JdbcTraineeDao();
	private Scanner scanner = new Scanner(System.in);

	public void readTraineeAll() {
		// JDBCTraineeDAO.selectAll 사용해서 받아온 list 한 줄씩 출력
		// 컬럼이 너무 많으므로 trainee 하나 당 id, name, sex, birth만 출력해주세요!

		List<Trainee> trainees = traineeDao.selectAll();

		System.out.println("ID | 이름 | 성별 | 생일");

		for (int i = 0; i < trainees.size(); i++) {
			Trainee trainee = trainees.get(i);
			System.out.printf("%d | %s | %s | %s\n", 
					trainee.getId(), 
					trainee.getName(), 
					trainee.getSex(),
					trainee.getBirth());
		}
	}

	public void addTrainee() {

		// 사용자 입력으로 idx, name, birth, sex, height, weight, nationality, hire_date 받은 후
		// 그 값으로 Trainee 객체 생성해서
		// JDBCTraineeDAO.insert 호출하기
		// true 반환 받으면 성공 메시지 출력

		// 원래 idx는 난수를 생성해서 넣어줄건데 기존 trainee table에서 중복 확인이 필요하므로
		// 이 부분은 나중에 구현하도록 합시다!
	}
	
	

	public void deleteTrainee() {
		// JDBCTraineeDAO.selectAll으로 받아온 list 출력 후 (넘버링해서)
		// 사용자가 선택한 번호의 연습생 id를 JDBCTraineeDAO.delete에 넘겨 호출
		// true 반환 받으면 성공 메시지 출력

		List<Trainee> trainees = traineeDao.selectAll();

		for (int i = 0; i < trainees.size(); i++) {
			Trainee trainee = trainees.get(i);
			System.out.printf("ID : %d | 이름 : %s  | 성별 : %s | 생일 : %s\n", 
					trainee.getId(), 
					trainee.getName(),
					trainee.getSex(), 
					trainee.getBirth());
		}

		System.out.print("삭제할 연습생의 ID를 입력하세요: ");
		int id = scanner.nextInt();

		boolean isDeleted = traineeDao.deleteById(id);

		for (int i = 0; i < trainees.size(); i++) {
			System.out.println("----------------");
			System.out.println(trainees.get(i).getId());

			if (trainees.get(i).getId() == id) {
				trainees.remove(i);
				System.out.println("😥연습생이 삭제되었습니다.");
				return;
			}
		}
		System.out.println("연습생을 찾을 수 없습니다.");
	}

	public void updateTrainee() {

		// JDBCTraineeDAO.selectAll으로 받아온 list 출력 후 (넘버링해서)
		// 사용자가 선택한 번호의 연습생을 수정할 것임
		// 1. 이름 2. 키 3. 몸무게 4. 국적 출력해서
		// 사용자가 선택한 번호와 입력 받은 값으로 해당 Trainee 객체 set 메소드로 수정
		// JDBCTraineeDAO.update에 수정한 객체 넘겨서 호출
		// true 반환 받으면 성공 메시지 출력

		// 이해가 잘 안될까봐 밑에가 콘솔에 뜨는 순서입니당

		// 전체 연습생 목록 출력 ...
		// 연습생을 선택해주세요 :
		// (사용자 값 입력 받기 - 숫자)
		// 1. 이름 2. 키 3. 몸무게 4. 국적
		// 수정할 정보를 선택해주세요 :
		// (사용자 값 입력 받기 - 숫자)
		// 수정할 값을 입력해주세요 :
		// (사용자 값 입력 받기)
		// 수정 완료 되었습니다!

		List<Trainee> trainees = traineeDao.selectAll();

		System.out.println("전체 연습생 목록");
		for (int i = 0; i < trainees.size(); i++) {
			Trainee trainee = trainees.get(i);
			System.out.printf("ID : %d | 이름 : %s  | 성별 : %s | 생일 : %s\n", 
					trainee.getId(), 
					trainee.getName(),
					trainee.getSex(), 
					trainee.getBirth());
		}

		System.out.println("(☞ﾟヮﾟ)☞ 수정할 연습생을 선택해 주세요");
		int id = scanner.nextInt();
		Trainee selectedTrainee = null;

		for (Trainee trainee : trainees) {
			if (trainee.getId() == id) {
				selectedTrainee = trainee;
				break;
			}
		}

		if (selectedTrainee == null) {
			System.out.println("해당 ID의 연습생을 찾을 수 없습니다.");
			return;
		}

		System.out.println("1. 이름  2. 키  3. 몸무게  4. 국적");
		System.out.print("수정할 정보를 선택해 주세요: ");
		int option = scanner.nextInt();
		scanner.nextLine();

		switch (option) {
		case 1:
			System.out.print("수정할 이름을 입력하세요: ");
			String newName = scanner.nextLine();
			selectedTrainee.setName(newName);
			break;
		case 2:
			System.out.print("수정할 키를 입력하세요: ");
			int newHeight = scanner.nextInt();
			selectedTrainee.setHeight(newHeight);
			break;
		case 3:
			System.out.print("수정할 몸무게를 입력하세요: ");
			int newWeight = scanner.nextInt();
			selectedTrainee.setWeight(newWeight);
			break;
		case 4:
			System.out.print("수정할 국적을 입력하세요: ");
			String newNationality = scanner.nextLine();
			selectedTrainee.setNationality(newNationality);
			break;
		default:
			System.out.println("잘못된 입력입니다.");
			return;
		}

		boolean isUpdated = traineeDao.update(selectedTrainee);

		if (isUpdated) {
			System.out.println("✅ 수정 완료 되었습니다!");
		} else {
			System.out.println("🚨 수정에 실패했습니다.");
		}
	}

	public void updateGrade() {
		// 이거는 나중에
	}
}
