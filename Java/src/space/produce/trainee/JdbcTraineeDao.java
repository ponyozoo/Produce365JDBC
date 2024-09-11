package space.produce.trainee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import space.produce.util.DataSource;

public class JdbcTraineeDao implements TraineeDao {

	@Override
	public boolean insert(Trainee trainee) {
		boolean result = false;

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement = connection.prepareStatement(
						"INSERT INTO TRAINEE (ID ,NAME ,BIRTH ,SEX ,HEIGHT ,WEIGHT ,NATIONALITY ,HIRE_DATE) "
								+ "VALUES (? , ? , ? , ? , ? , ? , ? , ?)")) {

			pStatement.setInt(1, trainee.getId());
			pStatement.setString(2, trainee.getName());
			pStatement.setDate(3, trainee.getBirth());
			pStatement.setString(4, trainee.getSex());
			pStatement.setInt(5, trainee.getHeight());
			pStatement.setInt(6, trainee.getWeight());
			pStatement.setString(7, trainee.getNationality());
			pStatement.setDate(8, trainee.getHireDate());

			int rows = pStatement.executeUpdate();

			if (rows > 0) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public boolean update(Trainee trainee) {
		boolean result = false;

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement = connection.prepareStatement(
						"UPDATE TRAINEE SET NAME = ? , HEIGHT = ? , WEIGHT = ? , NATIONALITY = ? WHERE ID = ? ")) {

			pStatement.setString(1, trainee.getName());
			pStatement.setInt(2, trainee.getHeight());
			pStatement.setInt(3, trainee.getWeight());
			pStatement.setString(4, trainee.getNationality());
			pStatement.setInt(5, trainee.getId());

			int rows = pStatement.executeUpdate();

			if (rows > 0) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean deleteById(int id) {

		boolean result = false;

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement = connection.prepareStatement("DELETE FROM TRAINEE WHERE ID = ?")) {

			pStatement.setInt(1, id);

			int rows = pStatement.executeUpdate();

			if (rows > 0) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Trainee> selectAll() {
		List<Trainee> trainees = new ArrayList<Trainee>();

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatment = connection.prepareStatement("SELECT * FROM TRAINEE ORDER BY ID DESC");
				ResultSet rs = pStatment.executeQuery()) {

			while (rs.next()) {
				Trainee trainee = new Trainee(rs.getInt("ID"), rs.getString("NAME"), rs.getDate("BIRTH"),
						rs.getString("SEX"), rs.getInt("HEIGHT"), rs.getInt("WEIGHT"), rs.getString("NATIONALITY"),
						rs.getDate("HIRE_DATE"));

				trainees.add(trainee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trainees;
	}

	@Override
	public List<Trainee> selectBySex(String sex) {

		List<Trainee> trainees = new ArrayList<>();

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM TRAINEE WHERE SEX = ?")) {

			pStatement.setString(1, sex);

			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				Trainee trainee = new Trainee();
				trainee.setId(rs.getInt("ID"));
				trainee.setName(rs.getString("NAME"));
				trainee.setBirth(rs.getDate("BIRTH"));
				trainee.setSex(rs.getString("SEX"));
				trainee.setHeight(rs.getInt("HEIGHT"));
				trainee.setWeight(rs.getInt("WEIGHT"));
				trainee.setNationality(rs.getString("NATIONALITY"));
				trainee.setHireDate(rs.getDate("HIRE_DATE"));

				trainees.add(trainee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trainees;
	}

	@Override

	public List<Trainee> selectByNationality(String nationality) {

		List<Trainee> trainees = new ArrayList<>();

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement = connection
						.prepareStatement("SELECT * FROM TRAINEE WHERE NATIONALITY = ?")) {

			pStatement.setString(1, nationality);

			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				Trainee trainee = new Trainee();
				trainee.setId(rs.getInt("ID"));
				trainee.setName(rs.getString("NAME"));
				trainee.setBirth(rs.getDate("BIRTH"));
				trainee.setSex(rs.getString("SEX"));
				trainee.setHeight(rs.getInt("HEIGHT"));
				trainee.setWeight(rs.getInt("WEIGHT"));
				trainee.setNationality(rs.getString("NATIONALITY"));
				trainee.setHireDate(rs.getDate("HIRE_DATE"));

				trainees.add(trainee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trainees;
	}

}