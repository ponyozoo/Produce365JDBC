package space.produce.menu;

public class TraineeMenu {

    public void readTraineeAll() {
        // JDBCTraineeDAO.selectAll 사용해서 받아온 list 한 줄씩 출력
        // 컬럼이 너무 많으므로 trainee 하나 당 id, name, sex, birth만 출력해주세요!
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
    }

    public void updateGrade() {
        // 이거는 나중에
    }
}
