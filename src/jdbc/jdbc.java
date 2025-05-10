package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		String username ="root";
		String password ="vamsi";
		String sql = "select * from student";
		
		Connection myCon=null;
		Statement statement=null;
		ResultSet res=null;
		
		try {
			 myCon = DriverManager.getConnection(url,username,password);
	
			statement = myCon.createStatement();
			
			res = statement.executeQuery(sql);
			while(res.next()) {
				int id=res.getInt("id");
				String name = res.getString("name");
				String email = res.getString("email");
				String dept = res.getString("dept");
				int salary = res.getInt("salary");
				System.out.println(id+" "+name+" "+email+" "+dept+" "+salary);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try{
				if(myCon !=null)
				{
					myCon.close();	
				}
				
				
			}
			catch(SQLException e){
				e.printStackTrace();
				
			}
			try{
				if(statement !=null)
				{
					statement.close();
				}
				
				
			}
			catch(SQLException e){
				e.printStackTrace();
				
			}
			try{
				if(res!=null)
				{
					res.close();	
				}
				
				
			}
			catch(SQLException e){
				e.printStackTrace();
				
			}
			
		}
		
		
			
			}
	
}
