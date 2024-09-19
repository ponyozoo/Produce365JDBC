package space.produce.lesson;

import java.util.List;

public interface LessonDao {
	boolean insert(Lesson lesson);
	boolean deleteById(int id);
	List<Lesson> selectAll();
}
