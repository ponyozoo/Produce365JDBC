package space.produce.careHistory;


import java.util.List;

public class JDBCCareHistoryDao implements CareHistoryDAO {

	@Override
	public boolean insert(CareHistory careHistory) {
		return false;
	}

	@Override
	public List<CareHistory> selectAll() {
		return null;
	}

	@Override
	public List<CareHistory> selectByTraineeId(int id) {
		return null;
	}

	@Override
	public List<CareHistory> selectByCareId(int id) {
		return null;
	}
	
}
