package space.produce.menu;

public class LessonMenu {

    public void readLesson() {
        // JDBCLessonDAO.selectAll 사용해서 받아온 list 한 줄씩 출력
    }

    public void addLesson() {
        // 사용자 입력으로 카테고리, 가격 받은 후 Lesson 객체 생성하여 JDBCLessonDAO.insert 호출
        // true 받으면 완료 메시지 출력
    }

    public void deleteLesson() {
        // JDBCLessonDAO.selectAll으로 받아온 list 출력 후 (넘버링 해서)
        // 사용자가 선택한 Lesson 객체의 id로 JDBCLessonDAO.deleteById 호출
        // true 받으면 완료 메시지 출력
    }
    
}
