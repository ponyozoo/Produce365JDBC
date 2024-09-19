package space.produce.care;

import java.util.List;

public interface CareDao {
	boolean insert(Care care); 
	boolean deleteById(int id); 
	List<Care> selectAll(); 
}
