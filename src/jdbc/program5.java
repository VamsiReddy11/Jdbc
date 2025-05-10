package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class program5 {
	private static Connection connection;
	private static PreparedStatement Statement;

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password = "vamsi";
		String sql = "delete from `student` where `id`=?";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, username, password);
			jdbc2.display(null, Statement, connection);
			
			Statement = connection.prepareStatement(sql);
			
			Statement.setInt(1, scan.nextInt());
		
			
			int i =Statement.executeUpdate();
			System.out.println(i);
			
			System.out.println();
			
			jdbc2.display(null, Statement, connection);
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

}
