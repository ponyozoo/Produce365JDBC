package space.produce.rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import space.produce.trainee.Trainee;
import space.produce.util.DataSource;

public class JdbcRatingDao implements RatingDao {

	@Override
	public boolean insert(Rating rating) {

		boolean result = false;

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement = connection
						.prepareStatement("INSERT INTO RATING (CATEGORY , TRAINEE_ID , GRADE) VALUES (? , ? , ?)")) {

			pStatement.setString(1, rating.getCategory());
			pStatement.setInt(2, rating.getTrainee().getId()); // TRAINEE_ID
			pStatement.setString(3, rating.getGrade());

			int Rows = pStatement.executeUpdate();

			if (Rows > 0) {
				result = true;
			}

			System.out.println("insert 성공");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(Rating rating) {

		boolean result = false;

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatment = connection
						.prepareStatement("UPDATE RATING SET TRAINEE_ID = ? , GRADE = ? WHERE CATEGORY = ?")) {

			pStatment.setInt(1, 12);
			pStatment.setString(2, "A");
			pStatment.setString(3, "RAP");

			int rows = pStatment.executeUpdate();

			if (rows > 0) {
				result = true;
				System.out.println("Update 성공");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Rating> selectByTotalGrade(String grade) {

		List<Rating> ratings = new ArrayList<>();

		String sql1 = ("SELECT * FROM RATING WHERE CATEGORY = 'TOTAL'");

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement = connection.prepareStatement(sql1)) {
			{

				ResultSet rs = pStatement.executeQuery();

				while (rs.next()) {
					Rating rating = new Rating();
					rating.setCategory(rs.getString("CATEGORY"));
					rating.setTrainee(new Trainee(rs.getInt("TRAINEE_ID")));
					rating.setGrade(rs.getString("GRADE"));

					ratings.add(rating);
				}

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return ratings;

	}

	@Override
	public List<Rating> selectByTraineeId(int id) {

		List<Rating> ratings = new ArrayList<>();

		String sql1 = ("SELECT * FROM RATING WHERE TRAINEE_ID = ?");

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement = connection.prepareStatement(sql1)) {
			{
				pStatement.setInt(1, id);
				ResultSet rs = pStatement.executeQuery();

				while (rs.next()) {
					Rating rating = new Rating();
					rating.setCategory(rs.getString("CATEGORY"));
					rating.setTrainee(new Trainee(rs.getInt("TRAINEE_ID")));
					rating.setGrade(rs.getString("GRADE"));

					ratings.add(rating);
				}

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return ratings;

	}

}
