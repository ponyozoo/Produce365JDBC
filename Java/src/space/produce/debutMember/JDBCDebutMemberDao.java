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
				PreparedStatement pStatement = connection
						.prepareStatement("INSERT INTO DEBUT_MEMBER (GROUP_ID, TRAINEE_ID) VALUES (? , ?)")) {

			pStatement.setInt(1, debutMember.getGroup().getId());
			pStatement.setInt(2, debutMember.getTrainee().getId());

			int Rows = pStatement.executeUpdate();

			if (Rows > 0) {

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
				PreparedStatement pStatement = connection.prepareStatement("DELETE FROM DEBUT_MEMBER WHERE IDX = ?")) {

			pStatement.setInt(1, idx);

			int Rows = pStatement.executeUpdate();

			if (Rows > 0) {
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
				PreparedStatement pStatement = connection.prepareStatement(
						"SELECT IDX, GROUP_ID, D.NAME AS GROUP_NAME, T.ID AS TRAINEE_ID, T.NAME AS TRAINEE_NAME, BIRTH, SEX, HEIGHT, WEIGHT, NATIONALITY, HIRE_DATE "
								+ "FROM DEBUT_MEMBER M, DEBUT D, TRAINEE T "
								+ "WHERE M.GROUP_ID = D.ID AND M.TRAINEE_ID = T.ID ORDER BY GROUP_NAME, TRAINEE_ID");
				ResultSet rs = pStatement.executeQuery()) {

			while (rs.next()) {
				DebutMember debutMember = new DebutMember(rs.getInt("IDX"));
				Debut debut = new Debut(rs.getInt("GROUP_ID"));
				Trainee trainee = new Trainee(rs.getInt("TRAINEE_ID"));

				debut.setName(rs.getString("GROUP_NAME"));
				trainee.setName(rs.getString("TRAINEE_NAME"));
				trainee.setBirth(rs.getDate("BIRTH"));
				trainee.setSex(rs.getString("SEX"));
				trainee.setHeight(rs.getInt("HEIGHT"));
				trainee.setWeight(rs.getInt("WEIGHT"));
				trainee.setNationality(rs.getString("NATIONALITY"));
				trainee.setHireDate(rs.getDate("HIRE_DATE"));

				debutMember.setGroup(debut);
				debutMember.setTrainee(trainee);

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

		try (Connection connection = DataSource.getDataSource();
				PreparedStatement pStatement = connection.prepareStatement("SELECT D.ID" + "		, M.IDX"
						+ "     , D.NAME AS GROUP_NAME" + "     , D.DEBUT_DATE" + "     , M.TRAINEE_ID"
						+ "     , T.NAME" + "     , T.BIRTH" + "     , T.SEX" + "     , T.HEIGHT" + "     , T.WEIGHT"
						+ "     , T.NATIONALITY" + "     , T.HIRE_DATE" + "  FROM DEBUT D , DEBUT_MEMBER M , TRAINEE T"
						+ "  WHERE D.ID = M.GROUP_ID AND M.TRAINEE_ID = T.ID" + "  AND D.ID = ?"
						+ "  ORDER BY M.TRAINEE_ID");) {

			pStatement.setInt(1, groupId);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				DebutMember debutMember = new DebutMember();
				Debut debut = new Debut();
				Trainee trainee = new Trainee();

				debutMember.setIdx(rs.getInt("IDX"));

				debut.setId(rs.getInt("ID"));
				debut.setName(rs.getString("GROUP_NAME"));
				debut.setDebutDate(rs.getDate("DEBUT_DATE"));
				debutMember.setGroup(debut);

				trainee.setId(rs.getInt("TRAINEE_ID"));
				trainee.setName(rs.getString("NAME"));
				trainee.setBirth(rs.getDate("BIRTH"));
				trainee.setSex(rs.getString("SEX"));
				trainee.setHeight(rs.getInt("HEIGHT"));
				trainee.setWeight(rs.getInt("WEIGHT"));
				trainee.setNationality(rs.getString("NATIONALITY"));
				trainee.setHireDate(rs.getDate("HIRE_DATE"));
				debutMember.setTrainee(trainee);

				debutMembers.add(debutMember);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return debutMembers;
	}

}
