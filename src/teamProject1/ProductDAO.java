package teamProject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDAO {
	int proid;
	String custid;
	
	public boolean confirm_id(String login_custid) {
		boolean check_login = false;						
		String sql = "select custid from customer where proid = ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.30.1.3:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, proid);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				custid = rs.getString(1);
			}
			if(custid.equals(login_custid))
				check_login = true;
			conn.close();
			pstmt.close();
			rs.close();
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return check_login;
	}
	
	public void BoardWrite() {
		
	}
	
	public void BoardDelete() {
		
	}
	
	public void BoardUpdate() {
		
	}
	
	
}
