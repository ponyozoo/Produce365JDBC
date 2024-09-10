package space.produce.debutMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import space.produce.debut.Debut;
import space.produce.trainee.Trainee;
import space.produce.util.DataSource;

public class JDBCDebutMemberDao implements DebutMemberDao {

	@Override
	public boolean insert(DebutMember debutMember) {
		
		
		boolean result = false;
		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement 
				= connection.prepareStatement("INSERT INTO DEBUT_MEMBER (GROUP_ID, TRAINEE_ID) VALUES (? , ?)")) {

			
			pStatement.setInt(1,debutMember.getGroup().getId());
			pStatement.setInt(2, debutMember.getTrainee().getId());
			
			int Rows = pStatement.executeUpdate();
			
			if(Rows > 0) {
	
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
			
			pStatement.setInt(1, idx);
			
			int Rows = pStatement.executeUpdate();
			
			if(Rows > 0) {
				result = true;
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<DebutMember> selectAll() {
		List<DebutMember> debutMembers = new ArrayList<DebutMember>();
		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement 
				= connection.prepareStatement("SELECT * FROM DEBUT_MEMBER ORDER BY IDX");
				ResultSet rs = pStatement.executeQuery()) {
		
			while(rs.next()) {
				DebutMember debutMember = new DebutMember();
				
				debutMember.setGroup(new Debut(rs.getInt("GROUP_ID")));
				debutMember.setTrainee(new Trainee(rs.getInt("TRAINEE_ID")));
							
				debutMembers.add(debutMember);
					
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return debutMembers;
	}

	@Override
	public List<DebutMember> selectByGroup(int groupId) {
		List<DebutMember> debutMembers = new ArrayList<DebutMember>();
		DebutMember debutMember = new DebutMember();
		
		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement 
				= connection.prepareStatement("SELECT * FROM DEBUT_MEMBER WHERE GROUP_ID = ? ORDER BY IDX");
				){
		
			//select하는데 필요한 debutMember 의 groupId debut객체의 id를 가져다가 pStatement안의 쿼리문 속 첫번째 ?에 넣어야 함..
			pStatement.setInt(1, groupId);
			ResultSet rs = pStatement.executeQuery();
			
			while(rs.next()) {
				debutMember.setIdx(rs.getInt("IDX"));
				debutMember.setGroup(new Debut(rs.getInt("GROUP_ID")));
				debutMember.setTrainee(new Trainee(rs.getInt("TRAINEE_ID")));	
					
				debutMembers.add(debutMember);		
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return debutMembers;
	}

}
