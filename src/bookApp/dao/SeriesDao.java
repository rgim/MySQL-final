package bookApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookApp.entity.Series;

public class SeriesDao {
	
	public List<Series> listSeries() throws SQLException{
		String sql = "Select seriesId, seriesName from series order by seriesName";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Series> seriesList = new ArrayList<>();
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int seriesId = resultSet.getInt("seriesId");
				String seriesName = resultSet.getString("seriesName");
				Series list = new Series(seriesId, seriesName);
				seriesList.add(list);
			}
			return seriesList;
		}
		finally {
			if(resultSet != null) {
				resultSet.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(statement != null) {
				connection.close();
			}
		}			
	}		 
	
	public void addSeries(String seriesName) throws SQLException {
		String sql = "INSERT INTO series (seriesName) VALUES (?)";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, seriesName);			
			
			statement.executeUpdate();
		}
		finally {
			if(statement != null) {
				statement.close();
			}
			if(statement != null) {
				connection.close();
			}
		}
	}
	
	public void updateSeries(int seriesId, String name) throws SQLException {
		String sql = "UPDATE series SET seriesName = ? WHERE seriesId = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);		
			statement.setString(1, name);
			statement.setInt(2, seriesId);
			
			statement.executeUpdate();
		}
		finally {
			if(statement != null) {
				statement.close();
			}
			if(statement != null) {
				connection.close();
			}
		}
	}
	
	public void deleteSeries(int seriesId) throws SQLException {
		String sql = "DELETE FROM series WHERE seriesId = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, seriesId);
					
			statement.executeUpdate();
		}
		finally {
			if(statement != null) {
				statement.close();
			}
			if(statement != null) {
				connection.close();
			}
		}
	}
	
	/*
	 * public String getSeriesName(int seriesId) throws SQLException{ String sql =
	 * "SELECT seriesName from series where seriesId = ?"; Connection connection =
	 * null; PreparedStatement statement = null; ResultSet resultSet = null;
	 * 
	 * try { connection = DbConnection.getInstance().getConnection(); statement =
	 * connection.prepareStatement(sql); statement.setInt(1, seriesId);
	 * 
	 * resultSet =statement.executeQuery();
	 * 
	 * String seriesName = resultSet.getString("seriesName");
	 * 
	 * return seriesName; } finally { if(statement != null) { statement.close(); }
	 * if(statement != null) { connection.close(); } } }
	 */
}
