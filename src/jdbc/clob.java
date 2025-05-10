package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class clob {

	static Connection connection;
	private static PreparedStatement Statement2;
	static  Scanner scan = new Scanner(System.in);
	private static PreparedStatement statement;
	public static FileInputStream fis;
	private static FileReader fileReader;
	private static PreparedStatement statement3;
	 
	 

	public static void main(String[] args) throws FileNotFoundException {
		
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password = "vamsi";
		String sql="update student set intro =? where id=?";
		Scanner scan = new Scanner(System.in);
		
		
		
			try {
				fileReader = new FileReader("C:\\Users\\vamsi\\eclipse-workspace\\jdbc\\images\\myintro.txt");
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url, username, password);
				 statement3 = connection.prepareStatement(sql);
				
				 statement3.setCharacterStream(1, fileReader);
				 statement3.setInt(2, scan.nextInt());
				 
				 int i = statement3.executeUpdate();
			
				 System.out.println(i);
			
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
}
}


