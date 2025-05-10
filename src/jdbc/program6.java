package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class program6 {

	private static Connection connection;
	private static PreparedStatement Statement;
	private static String choice;
	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password = "vamsi";
		String sql = "insert into `student`(`id`,`name`,`email`,`dept`,`salary`) values(? ,?,?,?,?)";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, username, password);
			jdbc2.display(null, Statement, connection);
			Statement = connection.prepareStatement(sql);
			do
			{
			
			System.out.println("id:");
			Statement.setInt(1, scan.nextInt());
			System.out.println("name:");
			Statement.setString(2, scan.next());
			System.out.println("email:");
			Statement.setString(3, scan.next());
			System.out.println("dept:");
			Statement.setString(4, scan.next());
			System.out.println("salary:");
			Statement.setInt(5, scan.nextInt());
			
			Statement.addBatch();
			
			//int i =Statement.executeUpdate();
			//System.out.println(i);
			
			System.out.println("Do you want more details ?? (yes/No)");
			choice = scan.next();
			}
			while(choice.equals("yes"));
			
			
			
			int[] ar = Statement.executeBatch();
			for(int i =0;i<ar.length;i++)
			{
				System.out.println(ar[i]+" ");
		
			}
			
			System.out.println();
			
			jdbc2.display(null, Statement, connection);
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				if(connection !=null)
				{
					connection.close();
				}
				if(Statement != null)
				{
					Statement.close();
				}
				
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}

	}


}


