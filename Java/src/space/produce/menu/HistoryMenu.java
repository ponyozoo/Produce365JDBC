package space.produce.menu;

import java.util.List;
import java.util.Scanner;

import space.produce.careHistory.CareHistory;
import space.produce.careHistory.CareHistoryDAO;
import space.produce.careHistory.JDBCCareHistoryDao;

public class HistoryMenu {
	
    public void readCareHistory() {
        CareHistoryDAO careHistoryDao = new JDBCCareHistoryDao(); 
    	
    	List<CareHistory> careHistories = careHistoryDao.selectAll(); 
    	 
    	for ( int i = 0; i < careHistories.size(); i++ ) {
    		System.out.println((i+1) + ": " + careHistories.get(i) );
    	}
    }

    public void readLessonHistory() {

    }

    public void addCareHistory() {
        // 이건 나중에
    }

    public void addLessonHistory() {

    }

}
