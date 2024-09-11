package space.produce.careHistory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import space.produce.care.Care;
import space.produce.trainee.Trainee;
import space.produce.util.DataSource;

public class JDBCCareHistoryDao implements CareHistoryDAO {

	@Override
	public boolean insert(CareHistory careHistory) {
		boolean result = false;

	    try ( Connection connection = DataSource.getDataSource();
	        PreparedStatement pStatement = 

    		connection.prepareStatement("INSERT INTO CARE_HISTORY ( CARE_DATE, CARE_ID, TRAINEE_ID ) VALUES ( ?, ?, ? )") ) {
	
	    	pStatement.setDate(1, careHistory.getCareDate());
	        pStatement.setInt(2, careHistory.getCare().getId()); 
	        pStatement.setInt(3, careHistory.getTrainee().getId());  
        
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
	public List<CareHistory> selectAll() {
		
		List<CareHistory> careHistories = new ArrayList<CareHistory>(); 
		
		try ( Connection connection = DataSource.getDataSource();
	          PreparedStatement pStatement = connection.prepareStatement("SELECT IDX, CARE_DATE, CARE_ID, TRAINEE_ID FROM CARE_HISTORY ORDER BY IDX"); 
			  ResultSet rs = pStatement.executeQuery()) 
		{
		
			while( rs.next() ) {
				CareHistory careHistory = new CareHistory();
				careHistory.setIdx(rs.getInt("IDX"));
				careHistory.setCareDate(rs.getDate("CARE_DATE"));
				careHistory.setCare(new Care(rs.getInt("CARE_ID")));
				careHistory.setTrainee(new Trainee(rs.getInt("TRAINEE_ID")));
				
				careHistories.add(careHistory); 
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return careHistories; 
	}

	@Override
	public List<CareHistory> selectByTraineeId(int id) {
		
		List<CareHistory> careHistories = new ArrayList<CareHistory>(); 
		
		String sql = ("SELECT IDX, CARE_DATE, CARE_ID, TRAINEE_ID FROM CARE_HISTORY WHERE TRAINEE_ID = ? ORDER BY IDX"); 
		
		try ( Connection connection = DataSource.getDataSource();
	          PreparedStatement pStatement = connection.prepareStatement(sql)){
			  pStatement.setInt(1, id);
			  ResultSet rs = pStatement.executeQuery();
			  
			  while( rs.next() ) {
					CareHistory careHistory = new CareHistory();
					
					careHistory.setIdx(rs.getInt("IDX"));
					careHistory.setCareDate(rs.getDate("CARE_DATE"));
					careHistory.setCare(new Care(rs.getInt("CARE_ID")));
					careHistory.setTrainee(new Trainee(rs.getInt("TRAINEE_ID")));

					careHistories.add(careHistory); 
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return careHistories; 
	}

	@Override
	public List<CareHistory> selectByCareId(int id) {
		
		List<CareHistory> careHistories = new ArrayList<CareHistory>(); 
		
		String sql = ("SELECT IDX, CARE_DATE, CARE_ID, TRAINEE_ID FROM CARE_HISTORY WHERE CARE_ID = ? ORDER BY IDX"); 
		
		try ( Connection connection = DataSource.getDataSource();
	          PreparedStatement pStatement = connection.prepareStatement(sql)){
			  pStatement.setInt(1, id);
			  ResultSet rs = pStatement.executeQuery();
			  
			  while( rs.next() ) {
					CareHistory careHistory = new CareHistory();
					
					careHistory.setIdx(rs.getInt("IDX"));
					careHistory.setCareDate(rs.getDate("CARE_DATE"));
					careHistory.setCare(new Care(rs.getInt("CARE_ID")));
					careHistory.setTrainee(new Trainee(rs.getInt("TRAINEE_ID")));

					careHistories.add(careHistory); 
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return careHistories; 
	}
}
