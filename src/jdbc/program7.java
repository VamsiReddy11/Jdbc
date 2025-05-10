package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class program7 {

	private static Connection connection;
	private static PreparedStatement Statement;
	private static String choice;
	private static int insert;
	private static String s;
	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password = "vamsi";
		
		
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, username, password);
			displayTable(connection);
			
			do {
				System.out.println("1.Insert | 2.Delete |3.Update |4.Exit");
				
				int a=scan.nextInt();
				
				if(a==1)
				{
					insert();
				}
				
				else if(a==2)
				{
					delete();
				}
				else if(a==3)
				{
					update();
				}
				else if(a==4)
				{
					System.out.println("Exit Succesfully........");
					System.exit(0);
				}
				System.out.println("do you want to perform one more operation :");
				s = scan.next();
			}
			while(s.equals("yes"));
			
			
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
	
	static void displayTable(Connection connection) throws SQLException{
		String s ="select * from `student`";
		
		PreparedStatement Statement1 = connection.prepareStatement(s);
		ResultSet res = Statement1.executeQuery();
		
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
		
	}
	static void insert()
	
	{
		String sql = "insert into `student`(`id`,`name`,`email`,`dept`,`salary`) values(? ,?,?,?,?)";
		Scanner scan = new Scanner(System.in);
		try {
			Statement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		do
		{
		
		System.out.println("id:");
		try {
			Statement.setInt(1, scan.nextInt());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("name:");
		try {
			Statement.setString(2, scan.next());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("email:");
		try {
			Statement.setString(3, scan.next());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("dept:");
		try {
			Statement.setString(4, scan.next());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("salary:");
		try {
			Statement.setInt(5, scan.nextInt());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		try {
			Statement.addBatch();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		//int i =Statement.executeUpdate();
		//System.out.println(i);
		
		System.out.println("Do you want more details ?? (yes/No)");
		choice = scan.next();
		}
		while(choice.equals("yes"));
		
		
		
		int[] ar =null;
		try {
			ar = Statement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i =0;i<ar.length;i++)
		{
			System.out.println(ar[i]+" ");
	
		}
		
		System.out.println();
		
		try {
			displayTable(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void delete()
	{
		Scanner scan = new Scanner(System.in);
		
		String sql1="DELETE from `student` where `id` =?";
		try {
			Statement = connection.prepareStatement(sql1);
			do
			{
				System.out.println("Enter ID:");
				Statement.setInt(1, scan.nextInt());
				int i =Statement.executeUpdate();
				displayTable(connection);
				System.out.println();
				System.out.println("Do you want more details ?? (yes/No)");
				choice = scan.next();
			}
			while(choice.equals("yes"));
			System.out.println();
			displayTable(connection);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	static void update()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("what do you want to update");
		
		System.out.println("1.Name  2.Email   3.Dept  4.Salary");
		String s1=scan.next();
		if(s1.equals("name"))
		{
			String sql2="update `student` set `name`=? where `id`=?";
			try {
				Statement = connection.prepareStatement(sql2);
					System.out.println("Enter name:");
					Statement.setString(1, scan.next());
					System.out.println("Enter Id:");
					Statement.setInt(2, scan.nextInt());
					
					int i = Statement.executeUpdate();
					displayTable(connection);
					System.out.println("Do you want more details ?? (yes/No)");
					choice = scan.next();	
				displayTable(connection);
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		else if(s1.equals("email"))
		{
			String sql3="update `student` set `email`=? where `id`=?";
			try {
				Statement = connection.prepareStatement(sql3);
				
					System.out.println("Enter Email:");
					Statement.setString(1, scan.next());
					System.out.println("Enter id:");
					Statement.setInt(2, scan.nextInt());
					
					int i = Statement.executeUpdate();
					displayTable(connection);
					System.out.println("Do you want more details ?? (yes/No)");
					choice = scan.next();	
				
				System.out.println();
				displayTable(connection);
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		else if(s1.equals("dept"))
		{
			String sql3="update `student` set `dept`=? where `id`=?";
			try {
				Statement = connection.prepareStatement(sql3);
				
					System.out.println("Enter Dept:");
					Statement.setString(1, scan.next());
					System.out.println("Enter id:");
					Statement.setInt(2, scan.nextInt());
					
					int i = Statement.executeUpdate();
					displayTable(connection);
					System.out.println("Do you want more details ?? (yes/No)");
					choice = scan.next();	
				
				System.out.println();
				displayTable(connection);
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		else if(s1.equals("salary"))
		{
			String sql4="update `student` set `salary`=? where `id`=?";
			try {
				Statement = connection.prepareStatement(sql4);
				
					System.out.println("Enter Salary:");
					Statement.setString(1, scan.next());
					System.out.println("Enter id:");
					Statement.setString(2, scan.next());
					
					
					int i = Statement.executeUpdate();
					displayTable(connection);
					System.out.println("Do you want more details ?? (yes/No)");
					choice = scan.next();	
				
				System.out.println();
				displayTable(connection);
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}

}
