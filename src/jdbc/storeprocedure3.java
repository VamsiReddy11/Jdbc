package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class storeprocedure3 {
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
		
			call = connection.prepareCall("{ call get_mployees(?)}");
			
			System.out.println("Enter salary");
			call.setInt(1, scan.nextInt());
			call.execute();
			ResultSet res = call.getResultSet();
			
			System.out.println("+-----+-------------------+---------------------------+-----------------+----------+");
			System.out.println("|ID   |  Name             |   Email                   |   Dept          | Salary   |");
			System.out.println("+-----+-------------------+---------------------------+-----------------+----------+");
			
			while(res.next())
			{
				int id = res.getInt("id");
				String name=res.getString("name");
				String email=res.getString("email");
				String dept=res.getString("dept");
				int salary = res.getInt("salary");
				System.out.format("| %-3d | %-17s | %-25s | %-15s | %-8d |\n", id, name, email, dept, salary);
			
				
			}
			System.out.println("+-----+-------------------+---------------------------+-----------------+----------+");
			
			
		
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
