package space.produce.menu;

public class CareMenu {

    public void readCare() {
        // JDBCCareDAO.selectAll 사용해서 받아온 list 한 줄씩 출력
    }

    public void addCare() {
        // 사용자 입력으로 카테고리, 가격 받은 후 Care 객체 생성하여 JDBCCareDAO.insert 호출
        // true 받으면 완료 메시지 출력
    }

    public void deleteCare() {
        // JDBCCareDAO.selectAll으로 받아온 list 출력 후 (넘버링 해서)
        // 사용자가 선택한 Care 객체의 id로 JDBCCareDAO.deleteById 호출
        // true 받으면 완료 메시지 출력
    }

}
