package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;

public class blob {

	  public  static final String URL = "jdbc:mysql://localhost:3306/blogging_db";
	  public  static final String USER = "root";
	  public  static final String PASSWORD = "vamsi";

	    public static Connection getConnection() throws SQLException {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            
				
				Object connection = DriverManager.getConnection(URL, USER, PASSWORD);
				
					
				} catch (SQLException e) {
				
					e.printStackTrace();
				
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            
	        }
			return null;
	    
	    }
	    public static User getUserById(int userId) {
	        String query = "SELECT id, username, password, firstName, lastName, email, role, isActive FROM users WHERE id = ?";
	        try (Connection connection = getConnection();
	             PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setInt(1, userId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return new User(
	                    rs.getInt("id"),
	                    rs.getString("username"),
	                    rs.getString("password"),
	                    rs.getString("firstName"),
	                    rs.getString("lastName"),
	                    rs.getString("email"),
	                    rs.getString("role"),
	                    rs.getBoolean("isActive")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static void updateUser(User user) {
	        String query = "UPDATE users SET firstName = ?, lastName = ?, email = ? WHERE id = ?";
	        try (Connection connection = getConnection();
	             PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, user.getFirstName());
	            stmt.setString(2, user.getLastName());
	            stmt.setString(3, user.getEmail());
	            stmt.setInt(4, user.getId());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	}