package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class storedprocedure2 {
	public static Connection connection;
	public static PreparedStatement Statement;
	public static CallableStatement call;
	public static Scanner scan =new Scanner(System.in);
	

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password = "vamsi";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		
			call = connection.prepareCall("{ call salary_count(?)}");
			
			System.out.println("Enter salary");
			call.setInt(1, scan.nextInt());
			call.registerOutParameter(1, Types.INTEGER);
			call.execute();
			int count = call.getInt(1);
			System.out.println(count);
			
			
		
		
		
		
		
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
