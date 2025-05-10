package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class program9 {

	
	 static Connection connection;
	private static PreparedStatement Statement2;
	static  Scanner scan = new Scanner(System.in);
	 
	 

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password = "vamsi";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, username, password);
			program7.displayTable(connection);
			
			transaction();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		
		
		

	}
	static void transaction() throws SQLException {
		
		connection.setAutoCommit(false);
		System.out.println("Enter sender name:");
		String sender =scan.next();
		System.out.println("Enter reciever name:");
		String reciever =scan.next();
		System.out.println("Enter Amount::");
		int amount =scan.nextInt();
		
		
		int i = updateAmount(sender,-amount);
		int j = updateAmount(reciever,amount);
		
		
		if(isConform(i,j))
		{
			connection.commit();
			System.out.println("transaction succesful...");
			program7.displayTable(connection);
		}
		else
		{
			connection.rollback();
			System.out.println("transaction failed...");
			
		}
		
	}
	
	
	
	 static boolean isConform(int i,int j) {
		System.out.println("do you want confirm transaction ???(yes/no");
		String choice=scan.next();
		
		return choice.equalsIgnoreCase("yes") && i==1 && j==1;
		
		
	}
	static int updateAmount(String sender,int amount) throws SQLException {
		 
		String sql ="update `student` set `salary` = `salary` + ? where name = ? ";
		 Statement2 = connection.prepareStatement(sql);
		 
		 
		 Statement2.setInt(1,amount);
		 Statement2.setString(2, sender);
		
		 int i = Statement2.executeUpdate();
		 
		 return i;
	}
	
	

}
