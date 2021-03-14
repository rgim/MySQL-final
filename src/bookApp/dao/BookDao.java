package bookApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookApp.entity.Books;

public class BookDao {
	
	public List<Books> listBooks() throws SQLException{
		String sql = "SELECT b.bookId as ID#, b.title as Title, "
				+ "concat(a.firstName, ' ', a.lastname) as Author,"
				+ "s.series as Series FROM books"
				+ "inner join author a on b.authorId = a.authorId"
				+ "inner join series s on b.seriesId = s.seriesId"
				+ "order by a.firstName, s.series"; 
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Books> bookList = new ArrayList<>();
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int bookId = resultSet.getInt("b.bookId");
				String title = resultSet.getString("b.title");
				Books book = new Books(bookId, title);
				bookList.add(book);
			}
			return bookList;
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
	
	public void addBook(String title, int authorId, int seriesId) throws SQLException {
		String sql = "INSERT INTO books (title, authorId, seriesId) VALUES (?,?,?)";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
		connection = DbConnection.getInstance().getConnection();
		statement = connection.prepareStatement(sql);
		statement.setString(1, title);
		statement.setInt(2, authorId);
		statement.setInt(3, seriesId);
		
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
	
	public void updateBookTitle(int bookId, String title) throws SQLException {
		String sql = "UPDATE books SET title = ? WHERE bookId = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
		connection = DbConnection.getInstance().getConnection();
		statement = connection.prepareStatement(sql);
		statement.setString(1, title);
		statement.setInt(2, bookId);
		
		
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
	
	public void updateBookAuthor(int bookId, int authorId) throws SQLException {
		String sql = "UPDATE books SET authorId = ? WHERE bookId = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
		connection = DbConnection.getInstance().getConnection();
		statement = connection.prepareStatement(sql);
		statement.setInt(1, authorId);	
		statement.setInt(2, bookId);
		
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
	
	public void updateBookSeries(int bookId, int seriesId) throws SQLException {
		String sql = "UPDATE books SET seriesId = ? WHERE bookId = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
		connection = DbConnection.getInstance().getConnection();
		statement = connection.prepareStatement(sql);
		statement.setInt(1, seriesId);
		statement.setInt(2, bookId);
		
		
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
	
	public void deleteBook(int bookId) throws SQLException {
		String sql = "DELETE FROM books WHERE bookId = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
		connection = DbConnection.getInstance().getConnection();
		statement = connection.prepareStatement(sql);
		statement.setInt(1, bookId);
		
		
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
}
