package bookApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookApp.entity.Author;

public class AuthorDao {
	
	public List<Author> listAuthors() throws SQLException{
		String sql = "Select authorId, firstName, lastName from author order by lastName";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Author> authorList = new ArrayList<>();
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int authorId = resultSet.getInt("authorId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				Author author = new Author(authorId, firstName, lastName);
				authorList.add(author);
			}
			return authorList;
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
	
	public void addAuthor(String firstName, String lastName) throws SQLException {
		String sql = "INSERT INTO author (firstName, lastName) VALUES (?,?)";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			
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
	
	public void updateAuthorFn(String name, int authorId) throws SQLException {
		String sql = "UPDATE author SET firstname = ? WHERE authorId = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setInt(2, authorId);
			
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
		return;
	}
	
	public void updateAuthorLn(String name, int authorId) throws SQLException {
		String sql = "UPDATE author SET lastname = ? WHERE authorId = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setInt(2, authorId);
			
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
		return;
	}
	
	public void deleteAuthor(int authorId) throws SQLException {
		String sql = "DELETE FROM author WHERE authorId = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DbConnection.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, authorId);
					
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
