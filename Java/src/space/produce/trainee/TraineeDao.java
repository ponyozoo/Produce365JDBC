package space.produce.trainee;

import java.util.List;

public interface TraineeDao {

	boolean insert(Trainee trainee);

	boolean update(Trainee trainee);

	boolean deleteById(int id);

	List<Trainee> selectAll();

	Trainee selectById(int id);

	List<Trainee> selectBySex(String sex); 

	List<Trainee> selectByNationality(String Nationality);

	List<Trainee> selectNoDebut();

	List<String> selectDistinctNationality();

}
