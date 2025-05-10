package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class class2 {
	public static Connection connection;
	public static Statement statement;

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password = "vamsi";
		String sql = "DELETE from `student` where `id`='3'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			
			jdbc2.display(null, statement,connection);
			
			statement = connection.createStatement();
			
		
			int i=statement.executeUpdate(sql);
			
			
			System.out.println(i);
			
			System.out.println();
			jdbc2.display(null, statement,connection);

			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

}
