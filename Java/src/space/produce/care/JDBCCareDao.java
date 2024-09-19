package space.produce.care;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import space.produce.util.DataSource;

public class JDBCCareDao implements CareDao {

	@Override
	public boolean insert(Care care) {
		
		boolean result = false;

	    try ( Connection connection = DataSource.getDataSource();
	        PreparedStatement pStatement = connection.prepareStatement("INSERT INTO CARE (CATEGORY, COST) VALUES (? , ? )") ) {

	        pStatement.setString(1, care.getCategory());
	        pStatement.setInt(2, care.getCost());

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

	    try ( Connection connection = DataSource.getDataSource();
	          PreparedStatement pStatement = connection.prepareStatement("DELETE FROM CARE WHERE ID = ?") ) {

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
	public List<Care> selectAll() {
		
		List<Care> cares = new ArrayList<Care>(); 
		
		try ( Connection connection = DataSource.getDataSource();
	          PreparedStatement pStatement = connection.prepareStatement("SELECT ID, CATEGORY, COST FROM CARE ORDER BY ID"); 
			  ResultSet rs = pStatement.executeQuery()) 
		{
		
			while( rs.next() ) {
				Care care = new Care(); 
				care.setId(rs.getInt("ID"));
				care.setCategory(rs.getString("CATEGORY"));
				care.setCost(rs.getInt("COST"));
				
				cares.add(care); 
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cares; 
	}
}
