package space.produce.rating;

import java.util.List;

public interface RatingDao {

	boolean insert(Rating rating);

	boolean update(Rating rating);

	List<Rating> selectByTotalGrade(String grade); // join

	List<Rating> selectByTraineeId(int id);
	
}
