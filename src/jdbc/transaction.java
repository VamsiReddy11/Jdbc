package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class transaction {
	
	
	public static Connection connection;
	public static PreparedStatement Statement;
	public static Scanner scan = new Scanner(System.in);
	public static PreparedStatement statement2;

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password = "vamsi";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			 connection = DriverManager.getConnection(url, username, password);
			System.out.println();
			program7.displayTable(connection);
			
		
			transaction();
			System.out.println("\n");
			program7.displayTable(connection);
			
			
			
			 
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	static void transaction() {
		
		  try {
			  
			  
			connection.setAutoCommit(false);
			
			System.out.println("Enter sender name");
			  String sender = scan.next();
			  System.out.println("Enter reciever name");
			  String receiver = scan.next();
			  System.out.println("Enter Amount");
			  int amount = scan.nextInt();
		
			  int i = updateAmount(sender,-amount);
			  int j = updateAmount(receiver,amount);
			  
			  
			  if(isConfirm(i,j)) { 
				  connection.commit();  
				  System.out.println("Transaction Succesful...");
			  }
			  else{
				 connection.rollback(); 
				 System.out.println("transaction falied.....");
			  }
		} catch (SQLException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  
		
		
	}
	static int updateAmount(String sender,int amount) throws SQLException {
		String sql = "update student set salary = salary+? where name =?";
		
		
		 statement2 = connection.prepareStatement(sql);
		 statement2.setInt(1,amount);
		 statement2.setString(2,sender);
		 
		 int i = statement2.executeUpdate();
		 return i;
		
		
	}
	static boolean isConfirm(int i,int j) {
		System.out.println("Do yoy want to confirm the transaction(yes/no)");
		String choice = scan.next();
		return choice.equals("yes")&& i==1 && j==1;
		
		
		
		
	}

}






























