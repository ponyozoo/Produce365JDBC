package space.produce.menu;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import space.produce.care.Care;
import space.produce.care.CareDao;
import space.produce.care.JDBCCareDao;
import space.produce.careHistory.CareHistory;
import space.produce.careHistory.CareHistoryDao;
import space.produce.careHistory.JDBCCareHistoryDao;
import space.produce.lesson.JDBCLessonDao;
import space.produce.lesson.Lesson;
import space.produce.lesson.LessonDao;
import space.produce.lessonHistory.JDBCLessonHistoryDao;
import space.produce.lessonHistory.LessonHistory;
import space.produce.lessonHistory.LessonHistoryDao;
import space.produce.trainee.JDBCTraineeDao;
import space.produce.trainee.Trainee;
import space.produce.trainee.TraineeDao;
import space.produce.util.MyScanner;

public class HistoryMenu {
	
	private CareHistoryDao careHistoryDao = new JDBCCareHistoryDao();
	private CareDao careDao = new JDBCCareDao(); 
	private LessonHistoryDao lessonHistoryDao = new JDBCLessonHistoryDao();
	private LessonDao lessonDao = new JDBCLessonDao();
	private TraineeDao traineeDao = new JDBCTraineeDao();
	private MyScanner scanner = new MyScanner(new Scanner(System.in));
	
	public void selectHistoryMenu() {
		while (true) {
			System.out.println("""
					
			__________________________________________________🕺💃_________________________________________________
			
			                                            [ 매니징 아카이브 ]
			
			               1. 케어 기록 조회   2. 케어 기록 추가   3. 수업 기록 조회   4. 수업 기록 추가   5. 뒤로 가기                 
			_______________________________________________________________________________________________________
					                
			""");
			
			int selectMenu = scanner.takeInt(1, 5);
			if (selectMenu == -1) {
				System.out.println("🚨 올바른 값을 입력해주세요");
				continue;
			}
			
			System.out.println("");
			
			switch (selectMenu) {
				case 1: 
					readCareHistory();
					break;
				case 2: 
					addCareHistory();
					break;
				case 3: 
					readLessonHistory();
					break;
				case 4: 
					addLessonHistory();
					break;
				case 5: 
					return;
			}
		}
	}
	
    public void readCareHistory() {
    	while (true) {
        	System.out.println("""
        			
			__________________________________________________🕺💃_________________________________________________
			
			                                             [ 케어 기록 조회 ]
			
			                         1. 전체 조회    2. 연습생별 조회    3. 항목별 조회    4. 뒤로 가기                    
			_______________________________________________________________________________________________________
					                
			""");
    		
    		int selectMenu = scanner.takeInt(1, 4);
    		if (selectMenu == -1) {
    			System.out.println("🚨 올바른 값을 입력해주세요");
    			continue;
    		}
    		
    		System.out.println("");

    		switch (selectMenu) {
    		case 1:
    			readCareHistoryAll();
    			break;
    		case 2: 
    			readCareHistoryByTrainee();
    			break;
    		case 3: 
    			readCareHistoryByCategory();
    			break;
    		case 4:
    			return;
    		}
    	}
    }
    
    public void readLessonHistory() {
    	while (true) {
        	System.out.println("""
			
			__________________________________________________🕺💃_________________________________________________
			
			                                             [ 수업 기록 조회 ]
			
			                         1. 전체 조회    2. 연습생별 조회    3. 수업별 조회    4. 뒤로 가기                    
			_______________________________________________________________________________________________________
					                
			""");
        	
        	int selectMenu = scanner.takeInt(1, 4);
        	if (selectMenu == -1) {
        		System.out.println("🚨 올바른 값을 입력해주세요");
        		continue;
        	}
        	
        	System.out.println("");
			
        	switch (selectMenu) {
	        	case 1:
	        		readLessonHistoryAll();
	        		break;
	        	case 2: 
	        		readLessonHistoryByTrainee();
	        		break;
	        	case 3: 
	        		readLessonHistoryBySubject();
	        		break;
	        	case 4:
	        		return;
        	}
    	}
    }
    
    public void readCareHistoryAll() {
    	List<CareHistory> careHistories = careHistoryDao.selectAll();
    	
    	if (careHistories.isEmpty()) {
    		System.out.println("📢 케어 기록이 없습니다");
    		return ;
    	}
   	 
    	for ( int i = 0; i < careHistories.size(); i++ ) {
    		System.out.println(careHistories.get(i));
    	}
    }
    
    public void readCareHistoryByTrainee() {
    	List<Trainee> trainees = traineeDao.selectAll();
    	
    	if (trainees.isEmpty()) {
    		System.out.println("📢 연습생이 없습니다");
    		return ;
    	}
    	
    	for ( int i = 0; i < trainees.size(); i++ ) {
    		System.out.println((i+1) + ". " + trainees.get(i));
    	}
    	
    	int traineeNum = scanner.takeIntCycle("\n연습생을 선택해주세요 : ", 1, trainees.size());
    
    	System.out.println("");

    	List<CareHistory> careHistories = careHistoryDao.selectByTraineeId(trainees.get(traineeNum - 1).getId());
    	
    	if (careHistories.isEmpty()) {
    		System.out.println("📢 해당 연습생의 케어 기록이 없습니다");
    		return ;
    	}
    	
    	for (int i = 0; i < careHistories.size(); i++) {
    		System.out.println(careHistories.get(i));
    	}
    }
    
    public void readCareHistoryByCategory() {
    	List<Care> cares = careDao.selectAll();
    	
    	if (cares.isEmpty()) {
    		System.out.println("📢 케어 정보가 없습니다");
    		return ;
    	}
    	
    	for ( int i = 0; i < cares.size(); i++ ) {
    		System.out.println((i+1) + ". " + cares.get(i));
    	}
    	
    	int careNum = scanner.takeIntCycle("\n케어 항목을 선택해주세요 : ", 1, cares.size());

    	System.out.println("");
    	
    	List<CareHistory> careHistories = careHistoryDao.selectByCareId(cares.get(careNum - 1).getId());
    	
    	if (careHistories.isEmpty()) {
    		System.out.println("📢 해당 케어의 기록이 없습니다");
    		return ;
    	}
    	
    	for (int i = 0; i < careHistories.size(); i++) {
    		System.out.println(careHistories.get(i));
    	}
    }
    
    public void readLessonHistoryAll() {
    	List<LessonHistory> lessonHistories = lessonHistoryDao.selectAll();
    	
    	if (lessonHistories.isEmpty()) {
    		System.out.println("📢 수업 기록이 없습니다");
    		return ;
    	}
    	
    	for (int i = 0; i < lessonHistories.size(); i++) {
    		System.out.println(lessonHistories.get(i));
    	}    	
    }
    
    public void readLessonHistoryByTrainee() {
    	List<Trainee> trainees = traineeDao.selectAll();
    	
    	if (trainees.isEmpty()) {
    		System.out.println("📢 연습생이 없습니다");
    		return ;
    	}
    	
    	for ( int i = 0; i < trainees.size(); i++ ) {
    		System.out.println((i+1) + ". " + trainees.get(i));
    	}
    	
    	int traineeNum = scanner.takeIntCycle("\n연습생을 선택해주세요 : ", 1, trainees.size());
    	
    	System.out.println("");

    	List<LessonHistory> lessonHistories = lessonHistoryDao.selectByTraineeId(trainees.get(traineeNum - 1).getId());
    	
    	if (lessonHistories.isEmpty()) {
    		System.out.println("📢 해당 연습생의 수업 기록이 없습니다");
    		return ;
    	}
    	
    	for (int i = 0; i < lessonHistories.size(); i++) {
    		System.out.println(lessonHistories.get(i));
    	}
    }
    
    public void readLessonHistoryBySubject() {
    	List<Lesson> lessons = lessonDao.selectAll(); 
    	
    	if (lessons.isEmpty()) {
    		System.out.println("📢 수업이 없습니다");
    		return ;
    	}
    	
    	for ( int i = 0; i < lessons.size(); i++ ) {
    		System.out.println((i+1) + ". " + lessons.get(i));
    	}
    	
    	int lessonNum = scanner.takeIntCycle("\n수업을 선택해주세요 : ", 1, lessons.size());

    	System.out.println("");
    	
    	List<LessonHistory> lessonHistories = lessonHistoryDao.selectByLessonId(lessons.get(lessonNum - 1).getId());
    	
    	if (lessonHistories.isEmpty()) {
    		System.out.println("📢 해당 수업의 기록이 없습니다");
    		return ;
    	}
    	
    	for (int i = 0; i < lessonHistories.size(); i++) {
    		System.out.println(lessonHistories.get(i));
    	}
    }

    public void addCareHistory() {    	
    	List<Care> cares = careDao.selectAll();
    	
    	for ( int i = 0; i < cares.size(); i++ ) {
       		System.out.println((i+1) + ". " + cares.get(i));
       	}
    	
    	int careNum = scanner.takeIntCycle("\n케어 종류를 선택해주세요 : ", 1, cares.size());
 
    	System.out.println("");
    	    	
    	List<Trainee> trainees = traineeDao.selectAll(); 
    	
    	for ( int i = 0; i < trainees.size(); i++ ) {
       		System.out.println((i+1) + ". " + trainees.get(i));
       	}
    	
    	int traineeNum = scanner.takeIntCycle("\n연습생을 선택해주세요 : ", 1, trainees.size());
    	    	
    	while (true) {
    		System.out.print("케어 받은 날짜를 입력해 주세요 (오늘이라면 엔터를 눌러주세요) : ");
    		String careHistDateStr = scanner.takeStr();
    		try {
	    		Date careHistDate = careHistDateStr == "" ? null : Date.valueOf(careHistDateStr);
				if (careHistoryDao.insert(new CareHistory(0, careHistDate, cares.get(careNum - 1), trainees.get(traineeNum - 1))))
					System.out.println("✔️ 등록 완료");
				else
					System.out.println("❌ 등록 실패");
    		} catch (Exception e) {    			
    			System.out.println("🚨 올바른 값을 입력해주세요 (날짜는 YYYY-MM-DD 형식입니다)\n");
    			continue ;
    		}
    		break ;
    	}
    }

    public void addLessonHistory() {
    	List<Lesson> lessons = lessonDao.selectAll();
    	List<Trainee> trainees = traineeDao.selectAll();
    	
    	for (int i = 0; i < lessons.size(); i++) {
    		System.out.println((i + 1) + ". " + lessons.get(i));
    	}
    	
    	int lessonNum = scanner.takeIntCycle("\n수업을 선택해주세요 : ", 1, lessons.size());

    	System.out.println("");

    	for (int i = 0; i < trainees.size(); i++) {
    		System.out.println((i + 1) + ". " + trainees.get(i));
    	}
    	
    	int traineeNum = scanner.takeIntCycle("\n연습생을 선택해주세요 : ", 1, trainees.size());
    	
    	while (true) {
    		System.out.print("수업 진행 날짜를 입력해주세요 (오늘이라면 엔터를 눌러주세요) : ");
    		String lessonDateStr = scanner.takeStr();
    		try {
	    		Date lessonDate = lessonDateStr == "" ? null : Date.valueOf(lessonDateStr);
				if (lessonHistoryDao.insert(new LessonHistory(0, lessonDate, lessons.get(lessonNum - 1), trainees.get(traineeNum - 1))))
					System.out.println("✔️ 등록 완료");
				else
					System.out.println("❌ 등록 실패");
    		} catch (Exception e) {    			
    			System.out.println("🚨 올바른 값을 입력해주세요 (날짜는 YYYY-MM-DD 형식입니다)\n");
    			continue ;
    		}
    		break ;
    	}
    }
}
