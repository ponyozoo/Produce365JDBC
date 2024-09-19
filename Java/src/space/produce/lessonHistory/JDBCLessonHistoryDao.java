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

public class JDBCLessonHistoryDao implements LessonHistoryDao {

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
				PreparedStatement pStatement = conn.prepareStatement("SELECT H.IDX, H.LESSON_DATE, L.SUBJECT, T.NAME FROM LESSON_HISTORY H, LESSON L, TRAINEE T WHERE H.LESSON_ID = L.ID AND H.TRAINEE_ID = T.ID ORDER BY LESSON_DATE");
				ResultSet queryResult = pStatement.executeQuery()) {
			result = new ArrayList<LessonHistory>();
			while (queryResult.next()) {
				result.add(new LessonHistory(queryResult.getInt("IDX"), queryResult.getDate("LESSON_DATE"), 
						new Lesson(queryResult.getString("SUBJECT")), new Trainee(queryResult.getString("NAME"))));
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
				PreparedStatement pStatement = conn.prepareStatement("SELECT H.IDX, H.LESSON_DATE, L.SUBJECT, T.NAME FROM LESSON_HISTORY H, LESSON L, TRAINEE T WHERE H.LESSON_ID = L.ID AND H.TRAINEE_ID = T.ID AND H.TRAINEE_ID = ? ORDER BY LESSON_DATE")) {
			pStatement.setInt(1, id);
			ResultSet queryResult = pStatement.executeQuery();
			result = new ArrayList<LessonHistory>();
			while (queryResult.next()) {
				result.add(new LessonHistory(queryResult.getInt("IDX"), queryResult.getDate("LESSON_DATE"), 
						new Lesson(queryResult.getString("SUBJECT")), new Trainee(queryResult.getString("NAME"))));
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
				PreparedStatement pStatement = conn.prepareStatement("SELECT H.IDX, H.LESSON_DATE, L.SUBJECT, T.NAME FROM LESSON_HISTORY H, LESSON L, TRAINEE T WHERE H.LESSON_ID = L.ID AND H.TRAINEE_ID = T.ID AND H.LESSON_ID = ? ORDER BY LESSON_DATE")) {
			pStatement.setInt(1, id);
			ResultSet queryResult = pStatement.executeQuery();
			result = new ArrayList<LessonHistory>();
			while (queryResult.next()) {
				result.add(new LessonHistory(queryResult.getInt("IDX"), queryResult.getDate("LESSON_DATE"), 
						new Lesson(queryResult.getString("SUBJECT")), new Trainee(queryResult.getString("NAME"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
