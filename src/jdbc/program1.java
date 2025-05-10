package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class program1 {
	public static Connection connection;
	public static Statement statement;

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password = "vamsi";
		String sql = "update `student` set `salary` = `salary`+5000 where `dept` = 'IT'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			
			int i=statement.executeUpdate(sql);
			
			
			System.out.println();
			System.out.println(i);
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

}
