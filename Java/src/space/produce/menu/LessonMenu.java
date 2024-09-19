package space.produce.menu;

import java.util.List;
import java.util.Scanner;

import space.produce.lesson.JDBCLessonDao;
import space.produce.lesson.Lesson;
import space.produce.util.MyScanner;

public class LessonMenu {
	
	private	JDBCLessonDao dao = new JDBCLessonDao();
	private MyScanner scanner = new MyScanner(new Scanner(System.in));
	
	public void selectLessonMenu() {
		while (true) {
			System.out.println("""
					
			__________________________________________________🕺💃_________________________________________________
			
			                                               [ 수업 관리 ]
			
			                        1. 전체 수업 조회     2. 수업 추가     3. 수업 삭제     4. 뒤로 가기                 
			_______________________________________________________________________________________________________
					                
			""");
			
			int selectedNum = scanner.takeInt(1, 4);
			if (selectedNum == -1) {
				System.out.println("🚨 올바른 값을 입력해주세요");
				continue ;
			}
			
			System.out.println("");
			switch (selectedNum) {
				case 1 : 
					readLesson();
					break ;
				case 2 :
					addLesson();
					break ;
				case 3 :
					deleteLesson();
					break ;
				case 4 :
					return ;
			}
		}
	}

    public void readLesson() {
    	List<Lesson> allLesson = dao.selectAll();
    	
    	if (allLesson.isEmpty()) {
    		System.out.println("📢 수업이 없습니다");
    		return ;
    	}
    	
    	for (Lesson lesson : allLesson) {
    		System.out.println(lesson);
    	}
    }

    public void addLesson() {
    	String category = scanner.takeStrCycle("수업명을 입력해주세요 : ");
    	String trainer = scanner.takeStrCycle("선생님 이름을 입력해주세요 : ");
    	
    	while (true) {
    		System.out.print("수업에 필요한 총 시간을 입력해주세요 : ");
    		Float time = 0f;
    		try {
    			time = Float.parseFloat(scanner.takeStr());    			
    			if (time >= 0) {
    				if (dao.insert(new Lesson(0, trainer, category, time)))
    					System.out.println("✔️ 등록 완료");
    				else
    					System.out.println("❌ 등록 실패");
    				break ;
    			}
    			System.out.println("🚨 올바른 값을 입력해주세요\n");    			
    		} catch (Exception e) {
    			System.out.println("🚨 올바른 값을 입력해주세요\n");    			
    		}
    	}    	
    }

    public void deleteLesson() {
    	List<Lesson> allLesson = dao.selectAll();
    	
    	if (allLesson.isEmpty()) {
    		System.out.println("📢 수업이 없습니다");
    		return ;
    	}
    	
    	for (int i = 0; i < allLesson.size(); i++) {
    		System.out.println((i + 1) + ". " + allLesson.get(i));
    	}
    	
		int num = scanner.takeIntCycle("\n삭제할 수업을 선택해주세요 : ", 1, allLesson.size());
		if (dao.deleteById(allLesson.get(num - 1).getId()))
			System.out.println("✔️ 삭제 완료");
		else
			System.out.println("❌ 삭제 실패");
    }
    
}
