package space.produce.menu;

public class DebutMenu {
    
    public void readDebut() {
        // JDBCDebutDAO.selectAll 사용해서 받아온 list 한 줄씩 출력
    }

    public void addDebut() {
        // 사용자 입력으로 name, member_count, concept, grade, debut_date 받은 후
        // 그 값으로 Debut 객체 생성해서
        // JDBCDebutDAO.insert 호출하기
        // true 반환 받으면 성공 메시지 출력

        // debut_date가 오늘 이후인지 한 번 체크하고 아니라면 입력 다시 받기 기능이 있어야 하는데
        // 요부분 살짝 까다로워서 하실 수 있으면 해주시고 아니면 그냥 값 받은대로 넣어주세요!
    }

    public void deleteDebut() {
        // JDBCDebutDAO.selectAll으로 받아온 list 출력 후 (넘버링해서)
        // 사용자가 선택한 번호의 연습생 id를 JDBCTraineeDAO.delete에 넘겨 호출
        // true 반환 받으면 성공 메시지 출력
    }

    public void updateDebut() {
        // JDBCDebutDAO.selectAll으로 받아온 list 출력 후 (넘버링해서)
        // 사용자가 선택한 번호의 데뷔조를 수정할 것임
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
