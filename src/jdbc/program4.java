package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class program4 {

	private static Connection connection;
	private static PreparedStatement Statement;

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password = "vamsi";
		String sql = "update `student` set `salary`=`salary`+? where `dept`= ?";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, username, password);
			jdbc2.display(null, Statement, connection);
			
			Statement = connection.prepareStatement(sql);
			
			Statement.setInt(1, scan.nextInt());
			Statement.setString(2, scan.next());
			
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
