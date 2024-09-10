package space.produce.careHistory;

import java.util.List;

public interface CareHistoryDAO {
	boolean insert(CareHistory careHistory); 
	List<CareHistory> selectAll(); 
	List<CareHistory> selectByTraineeId(int id); 
	List<CareHistory> selectByCareId(int id); 
}
