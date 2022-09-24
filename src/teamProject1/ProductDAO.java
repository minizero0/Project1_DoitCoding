package teamProject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class ProductDAO {
	int proid;
	String title,content,custid,boarddate,img;
	int price, cate;
	
	
	public boolean confirm_id(int board_proid,String login_custid) { 		//로그인한 사용자 아이디와 게시물을 작성한 사용자 아이디를 확인
		boolean check_login = false;
		String sql = "select custid from product where proid = ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.30.1.86:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_proid);
			
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
	
	public void board_write(String login_custid) {					//게시물 작성
		
		System.out.println(login_custid);

		String sql = "insert into product values(seq_proid.nextval,?,?,?,?,sysdate,?,?)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.30.1.86:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, login_custid);
			pstmt.setInt(2, cate);
			pstmt.setString(3, title);
			pstmt.setInt(4, price);
			pstmt.setString(5, img);
			pstmt.setString(6, content);
			int re = pstmt.executeUpdate();
			if (re == 1) {
				JOptionPane.showMessageDialog(null, "게시글 작성 완료!");
			}
			conn.close();
			pstmt.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public void board_delete(int board_proid) {					//게시물 삭제
		String sql = "delete product where proid = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.30.1.86:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_proid);
			
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
	
	public void board_update(ProductVO vo) {					//게시물 수정
		String sql = "update product set categoryid = ?, title = ?,price = ?, img = ?, content = ? where proid = ? ";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.30.1.86:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getCategoryid());
			pstmt.setString(2, vo.getTitle());
			pstmt.setInt(3, vo.getPrice());
			pstmt.setString(4, vo.getImg());
			pstmt.setString(5, vo.getContent());
			pstmt.setInt(6, vo.getProid());
			
			int re = pstmt.executeUpdate();
			if (re == 1) {
				JOptionPane.showMessageDialog(null, "게시글 수정 완료!");
			}
			conn.close();
			pstmt.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public void board_select() {						//게시물 조회 
		String sql = "select * product";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.30.1.86:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
			}
			conn.close();
			pstmt.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	
}
