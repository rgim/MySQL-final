package bookApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookApp.entity.SearchList;

public class SearchListDao {

	public List<SearchList> listAll() throws SQLException{
		String sql = "SELECT bookId, Title, concat(firstname, ' ', lastName) as Author, seriesName as Series "
				+ "FROM books b join author a on b.authorId = a.authorId "
				+ "join series s on b.seriesId = s.seriesId ";
			//	+ "Order by series, title";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<SearchList> tempList = new ArrayList<>();
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String seriesName = resultSet.getString("series");
				SearchList newList = new SearchList(bookId, title, author, seriesName);
				tempList.add(newList);
			}
			return tempList;
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

	
	/*
	 * These are not going to be used in the program at this time
	 * but I am leaving them here so I can build upon this app later
	 */
	
	public List<SearchList> listByAuthor(int authorId) throws SQLException{
		String sql = "SELECT bookId, Title, concat(firstname, ' ', lastName) as Author, seriesName as Series "
				+ "FROM books b join author a on b.authorId = a.authorId "
				+ "join series s on b.seriesId = s.seriesId "
				+ "Where authorId = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<SearchList> tempList = new ArrayList<>();
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, authorId);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String title = resultSet.getString("title");
				String author = resultSet.getString("Author");
				String seriesName = resultSet.getString("series");
				SearchList newList = new SearchList(bookId, title, author, seriesName);
				tempList.add(newList);
			}
			return tempList;
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
	
	public List<SearchList> listBySeries(int seriesId) throws SQLException{
		String sql = "SELECT bookId, Title, concat(firstname, ' ', lastName) as Author, seriesName as Series "
				+ "FROM books b join author a on b.authorId = a.authorId "
				+ "join series s on b.seriesId = s.seriesId "
				+ "Where seriesId = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<SearchList> tempList = new ArrayList<>();
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, seriesId);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String title = resultSet.getString("title");
				String author = resultSet.getString("Author");
				String seriesName = resultSet.getString("series");
				SearchList newList = new SearchList(bookId, title, author, seriesName);
				tempList.add(newList);
			}
			return tempList;
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
}

