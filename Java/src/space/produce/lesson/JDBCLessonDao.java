package space.produce.lesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import space.produce.util.DataSource;

public class JDBCLessonDao implements LessonDAO {

	@Override
	public boolean insert(Lesson lesson) {
		boolean result = false;
		
		try (Connection conn = DataSource.getDataSource();
				PreparedStatement pStatement = conn.prepareStatement("INSERT INTO LESSON (TRAINER, SUBJECT, TIME) "
						+ "VALUES (?, ?, ?)")) {
			pStatement.setString(1, lesson.getTrainer());
			pStatement.setString(2, lesson.getSubject());
			pStatement.setFloat(3, lesson.getTime());			
			result = pStatement.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean deleteById(int id) {
		boolean result = false;
		
		try (Connection conn = DataSource.getDataSource();
				PreparedStatement pStatement = conn.prepareStatement("DELETE FROM LESSON WHERE ID = ?")) {
			pStatement.setInt(1, id);
			result = pStatement.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<Lesson> selectAll() {
		List<Lesson> result = null;
		
		try (Connection conn = DataSource.getDataSource();
				PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM LESSON");
				ResultSet queryResult = pStatement.executeQuery()) {
			result = new ArrayList<Lesson>();
			while (queryResult.next()) {
				result.add(new Lesson(queryResult.getInt("ID"), queryResult.getString("TRAINER"), 
						queryResult.getString("SUBJECT"), queryResult.getFloat("TIME")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
