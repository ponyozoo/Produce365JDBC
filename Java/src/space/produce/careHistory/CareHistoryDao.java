package space.produce.careHistory;

import java.util.List;

public interface CareHistoryDao {
	boolean insert(CareHistory careHistory); 
	List<CareHistory> selectAll(); 
	List<CareHistory> selectByTraineeId(int id); 
	List<CareHistory> selectByCareId(int id); 
}
