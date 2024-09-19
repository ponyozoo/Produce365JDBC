package space.produce.lessonHistory;

import java.util.List;

public interface LessonHistoryDao {
	boolean insert(LessonHistory lessonHistory);
	List<LessonHistory> selectAll();
	List<LessonHistory> selectByTraineeId(int id);
	List<LessonHistory> selectByLessonId(int id);
}
