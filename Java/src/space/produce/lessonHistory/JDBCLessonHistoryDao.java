package space.produce.lessonHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import space.produce.lesson.Lesson;
import space.produce.trainee.Trainee;
import space.produce.util.DataSource;

public class JDBCLessonHistoryDao implements LessonHistoryDAO {

	@Override
	public boolean insert(LessonHistory lessonHistory) {
		boolean result = false;
		
		try (Connection conn = DataSource.getDataSource();
				PreparedStatement pStatement = conn.prepareStatement("INSERT INTO LESSON_HISTORY (LESSON_DATE, LESSON_ID, TRAINEE_ID) "
						+ "VALUES (?, ?, ?)")) {
			pStatement.setDate(1, lessonHistory.getLessonDate());
			pStatement.setInt(2, lessonHistory.getLesson().getId());
			pStatement.setInt(3, lessonHistory.getTrainee().getId());			
			result = pStatement.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<LessonHistory> selectAll() {
		List<LessonHistory> result = null;
		
		try (Connection conn = DataSource.getDataSource();
				PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM LESSON_HISTORY");
				ResultSet queryResult = pStatement.executeQuery()) {
			result = new ArrayList<LessonHistory>();
			while (queryResult.next()) {
				result.add(new LessonHistory(queryResult.getInt("IDX"), queryResult.getDate("LESSON_DATE"), 
						new Lesson(queryResult.getInt("LESSON_ID")), new Trainee(queryResult.getInt("TRAINEE_ID"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<LessonHistory> selectByTraineeId(int id) {
		List<LessonHistory> result = null;
		
		try (Connection conn = DataSource.getDataSource();
				PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM LESSON_HISTORY WHERE TRAINEE_ID = ?")) {
			pStatement.setInt(1, id);
			ResultSet queryResult = pStatement.executeQuery();
			result = new ArrayList<LessonHistory>();
			while (queryResult.next()) {
				result.add(new LessonHistory(queryResult.getInt("IDX"), queryResult.getDate("LESSON_DATE"), 
						new Lesson(queryResult.getInt("LESSON_ID")), new Trainee(queryResult.getInt("TRAINEE_ID"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<LessonHistory> selectByLessonId(int id) {
		List<LessonHistory> result = null;
		
		try (Connection conn = DataSource.getDataSource();
				PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM LESSON_HISTORY WHERE LESSON_ID = ?")) {
			pStatement.setInt(1, id);
			ResultSet queryResult = pStatement.executeQuery();
			result = new ArrayList<LessonHistory>();
			while (queryResult.next()) {
				result.add(new LessonHistory(queryResult.getInt("IDX"), queryResult.getDate("LESSON_DATE"), 
						new Lesson(queryResult.getInt("LESSON_ID")), new Trainee(queryResult.getInt("TRAINEE_ID"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
