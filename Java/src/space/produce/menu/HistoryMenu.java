package space.produce.menu;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import space.produce.careHistory.CareHistory;
import space.produce.careHistory.CareHistoryDAO;
import space.produce.careHistory.JDBCCareHistoryDao;
import space.produce.lesson.JDBCLessonDao;
import space.produce.lesson.Lesson;
import space.produce.lesson.LessonDAO;
import space.produce.lessonHistory.JDBCLessonHistoryDao;
import space.produce.lessonHistory.LessonHistory;
import space.produce.lessonHistory.LessonHistoryDAO;
import space.produce.trainee.JdbcTraineeDao;
import space.produce.trainee.Trainee;
import space.produce.trainee.TraineeDao;
import space.produce.util.MyScanner;

public class HistoryMenu {
	
	public CareHistoryDAO careHistoryDao = new JDBCCareHistoryDao(); 
	public LessonHistoryDAO lessonHistoryDao = new JDBCLessonHistoryDao();
	private LessonDAO lessonDao = new JDBCLessonDao();
	public TraineeDao traineeDao = new JdbcTraineeDao();
	private MyScanner scanner = new MyScanner(new Scanner(System.in));
	
    public void readCareHistory() {
    	List<CareHistory> careHistories = careHistoryDao.selectAll(); 
    	 
    	for ( int i = 0; i < careHistories.size(); i++ ) {
    		System.out.println((i+1) + ": " + careHistories.get(i) );
    	}
    }

    public void readLessonHistory() {
    	List<LessonHistory> lessonHistories = lessonHistoryDao.selectAll();
    	
    	for (int i = 0; i < lessonHistories.size(); i++) {
    		System.out.println((i + 1) + ". " + lessonHistories.get(i));
    	}
    }

    public void addCareHistory() {
    	
    }

    public void addLessonHistory() {
    	List<Lesson> lessons = lessonDao.selectAll();
    	List<Trainee> trainees = traineeDao.selectAll();
    	
    	for (int i = 0; i < lessons.size(); i++) {
    		System.out.println((i + 1) + ". " + lessons.get(i));
    	}
    	
    	int lessonNum = 0;
    	while (true) {
    		System.out.print("수업을 선택해주세요 : ");
    		lessonNum = scanner.takeInt(1, lessons.size());
    		if (lessonNum != -1)
    			break ;
    		System.out.println("🚨 올바른 값을 입력해주세요");
    	}
    	
    	for (int i = 0; i < trainees.size(); i++) {
    		System.out.println((i + 1) + ". " + trainees.get(i));
    	}
    	
    	int traineeNum = 0;
    	while (true) {
    		System.out.print("연습생을 선택해주세요 : ");
    		traineeNum = scanner.takeInt(1, trainees.size());
    		if (traineeNum != -1)
    			break ;
			System.out.println("🚨 올바른 값을 입력해주세요");
    	}    	
    	
    	while (true) {
    		System.out.print("수업 날짜를 입력해주세요 (오늘이라면 엔터를 눌러주세요) : ");
    		String lessonDateStr = scanner.takeStr();
    		try {
	    		Date lessonDate = lessonDateStr == "" ? null : Date.valueOf(lessonDateStr);
				if (lessonHistoryDao.insert(new LessonHistory(0, lessonDate, lessons.get(lessonNum - 1), trainees.get(traineeNum - 1))))
					System.out.println("✔️ 등록 완료");
				else
					System.out.println("❌ 등록 실패");
    		} catch (Exception e) {    			
    			System.out.println("🚨 올바른 값을 입력해주세요");
    			continue ;
    		}
    		break ;
    	}
    }
    
}
