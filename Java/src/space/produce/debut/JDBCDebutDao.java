package space.produce.debut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import space.produce.util.DataSource;

public class JDBCDebutDao implements DebutDao {

	@Override
	public boolean insert(Debut debut) {
		boolean result = false;

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement = connection.prepareStatement(
						"INSERT INTO DEBUT(NAME,MEMBER_COUNT,CONCEPT,GRADE,DEBUT_DATE) VALUES (?,?,?,?,?)")) {

			pStatement.setString(1, debut.getName());
			pStatement.setInt(2, debut.getMemberCount());
			pStatement.setString(3, debut.getConcept());
			pStatement.setString(4, debut.getGrade());
			pStatement.setDate(5, debut.getDebutDate());

			int affectedRows = pStatement.executeUpdate();

			if (affectedRows > 0) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Debut debut) {
		boolean result = false;
		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement = connection
						.prepareStatement("UPDATE DEBUT SET NAME = ? , MEMBER_COUNT = ? , "
								+ "CONCEPT = ? , GRADE = ? , DEBUT_DATE = ? WHERE ID = ?")) {

			pStatement.setString(1, debut.getName());
			pStatement.setInt(2, debut.getMemberCount());
			pStatement.setString(3, debut.getConcept());
			pStatement.setString(4, debut.getGrade());
			pStatement.setDate(5, debut.getDebutDate());
			pStatement.setInt(6, debut.getId());

			int affectedRows = pStatement.executeUpdate();

			if (affectedRows > 0) {
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
				PreparedStatement pStatement 
				= connection.prepareStatement("DELETE FROM DEBUT WHERE ID = ?")) {
			
			pStatement.setInt(1, id);
			
			int affectedRows = pStatement.executeUpdate();

			if (affectedRows > 0) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Debut> findAll() {
		List<Debut> debuts = new ArrayList<Debut>();
		
		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement 
				= connection.prepareStatement("SELECT * FROM DEBUT ORDER BY ID ASC");
				ResultSet rs = pStatement.executeQuery()) {
		
			while(rs.next()) {
				Debut debut = new Debut(
						rs.getInt("ID"),
						rs.getString("NAME"),
						rs.getInt("MEMBER_COUNT"),
						rs.getString("CONCEPT"),
						rs.getString("GRADE"),
						rs.getDate("DEBUT_DATE")
						);
				
				debuts.add(debut);
				
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return debuts;
	}

}
