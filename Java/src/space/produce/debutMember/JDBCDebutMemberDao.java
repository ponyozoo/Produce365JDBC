package space.produce.debutMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import space.produce.debut.Debut;
import space.produce.util.DataSource;

public class JDBCDebutMemberDao implements DebutMemberDao {

	@Override
	public boolean insert(DebutMember debutMember) {
		boolean result = false;
		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement 
				= connection.prepareStatement("INSERT INTO DEBUT_MEMBER (GROUP_ID, TRAINEE_ID) VALUES (? , ?")) {
			
			pStatement.setInt(1,debutMember.getGroup().getId());
			pStatement.setInt(2, debutMember.getTrainee().getId());
			
			int affectedRows = pStatement.executeUpdate();
			
			if(affectedRows > 0) {
				result = true;
			}
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteById(int idx) {
		boolean result = false;
		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement 
				= connection.prepareStatement("DELETE FROM DEBUT_MEMBER WHERE IDX = ?")) {
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<DebutMember> selectAll() {
		
		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement 
				= connection.prepareStatement("SELECT * FROM DEBUT_MEMBER ORDER BY IDX")) {
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DebutMember> selectByGroup() {
		
		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement 
				= connection.prepareStatement("SELECT * FROM DEBUT_MEMBER WHERE GROUP_ID = ? ORDER BY IDX")) {
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
