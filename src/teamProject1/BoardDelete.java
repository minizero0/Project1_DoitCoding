package teamProject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class BoardDelete {			//게시물 삭제 클래스
	
	public BoardDelete(String custid) {
		
		String sql = "delete product where proid = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.30.1.3:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
//			pstmt.setInt(1, proid);
			
			int re = pstmt.executeUpdate();
			if (re > 0) {
				JOptionPane.showMessageDialog(null, "삭제성공!");
			}
			conn.close();
			pstmt.close();
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
}
