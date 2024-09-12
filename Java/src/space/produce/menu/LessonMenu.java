package space.produce.menu;

import java.util.List;
import java.util.Scanner;

import space.produce.lesson.JDBCLessonDao;
import space.produce.lesson.Lesson;
import space.produce.util.MyScanner;

public class LessonMenu {
	
	private	JDBCLessonDao dao = new JDBCLessonDao();
	private MyScanner scanner = new MyScanner(new Scanner(System.in));

    public void readLesson() {
    	List<Lesson> allLesson = dao.selectAll();
    	
    	for (Lesson lesson : allLesson) {
    		System.out.println(lesson);
    	}
    }

    public void addLesson() {
    	System.out.print("수업명을 입력해주세요 : ");
    	String category = scanner.takeStr();
    	
    	System.out.print("선생님 이름을 입력해주세요 : ");
    	String trainer = scanner.takeStr();
    	
    	while (true) {
    		System.out.print("수업에 필요한 총 시간을 입력해주세요 : ");
    		int time = scanner.takeInt(0, Integer.MAX_VALUE);
    		if (time != -1) {
    			if (dao.insert(new Lesson(0, trainer, category, time)))
    				System.out.println("✔️ 등록 완료");
    			else
    				System.out.println("❌ 등록 실패");
    			break ;
    		}
    		System.out.println("🚨 올바른 값을 입력해주세요");
    	}    	
    }

    public void deleteLesson() {
    	List<Lesson> allLesson = dao.selectAll();
    	
    	for (int i = 0; i < allLesson.size(); i++) {
    		System.out.println((i + 1) + ". " + allLesson.get(i));
    	}
    	
    	while (true) {
    		System.out.print("삭제할 수업을 선택해주세요 : ");
    		int num = scanner.takeInt(1, allLesson.size());
    		if (num != -1) {
    			if (dao.deleteById(allLesson.get(num - 1).getId()))
    				System.out.println("✔️ 삭제 완료");
				else
					System.out.println("❌ 삭제 실패");
    			break ;
			}
    		System.out.println("🚨 올바른 값을 입력해주세요");
    	}
    }
    
}
