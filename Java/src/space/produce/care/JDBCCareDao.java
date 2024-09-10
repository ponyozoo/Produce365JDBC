package space.produce.care;

import java.util.List;

public class JDBCCareDao implements CareDAO {

	@Override
	public boolean insert(Care care) {
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		return false;
	}

	@Override
	public List<Care> selectAll() {
		return null;
	}
}
