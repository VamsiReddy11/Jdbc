package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class program3 {
	public static Connection connection;
	public static Statement statement;

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password = "vamsi";
	
		String sql = "insert into `student`(`id`,`name`,`email`,`dept`,`salary`) values(5,'jani','jani@gmail.com','finance',25000)";
		String sql1 = "insert into `student`(`id`,`name`,`email`,`dept`,`salary`) values(6,'vani','vani@gmail.com','IT',205000)";
		String sql2 = "insert into `student`(`id`,`name`,`email`,`dept`,`salary`) values(7,'sandy','sandy@gmail.com','IT',45000)";
		String sql3 = "insert into `student`(`id`,`name`,`email`,`dept`,`salary`) values(8,'bali','bali@gmail.com','finance',55000)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, username, password);
			
			jdbc2.display(null, statement,connection);
			
			statement = connection.createStatement();
			
			statement.addBatch(sql);
			statement.addBatch(sql1);
			statement.addBatch(sql2);
			statement.addBatch(sql3);
		
			int[] ar=statement.executeBatch();
			
			for(int i =0;i<ar.length;i++)
			{
				System.out.println(ar[i] +" ");
			}
			System.out.println();
			jdbc2.display(null, statement,connection);
			
			System.out.println();
			jdbc2.display(null, statement,connection);

			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	

}


}
