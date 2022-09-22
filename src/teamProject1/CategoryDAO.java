package teamProject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class CategoryDAO {
	public Vector<String> listCate(){
		Vector<String> list = new Vector<>();
		String sql = "select categoryname from category";
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			
			conn.close();
			stmt.close();
			rs.close();
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
		
	}
}
