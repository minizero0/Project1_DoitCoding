package teamProject1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class CartDAO {
	
	Vector<Vector<String>> rowData = new Vector<>();
	
	public boolean confirm_cart(String login_custid,int proid) {
		boolean is_Flag = true;
		String sql = "select cartid, c.proid from cart c, product p where p.proid = c.proid and c.custid = ? and c.proid = ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, login_custid);
			pstmt.setInt(2, proid);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				is_Flag = false;
			
			pstmt.close();
			conn.close();
			rs.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return is_Flag;
	}

	public Vector listInfo(String login_custid) {
		rowData.clear();
		String sql = "select p.proid, categoryname, title, price, boarddate, p.custid "
				+ "from customer cust, product p, category cat, cart c where cat.categoryid = p.categoryid "
				+ "and cust.custid = c.custid and p.proid = c.proid and c.custid = '"+login_custid+"' order by c.proid";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getInt(1)+"");
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getInt(4)+"");
				v.add(rs.getDate(5)+"");
				v.add(rs.getString(6));
				rowData.add(v);
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception: "+e.getMessage());
		}
		return rowData;
	}
	
	public void cart_insert(String login_custid, int proid) {					//detailpage에서 장바구니에 상품 추가하기

		String sql = "insert into cart values(seq_cartid.nextval,?,?)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, login_custid);
			pstmt.setInt(2, proid);
				
			int re = pstmt.executeUpdate();
			if (re == 1) {
				JOptionPane.showMessageDialog(null, "장바구니에 담기 완료!");
			}
			conn.close();
			pstmt.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public void cart_delete(String login_custid, int proid) {					//장바구니에서 물품 삭제
		
		String sql = "delete cart where cartid = (select cartid from cart where custid = ? and proid = ?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, login_custid);
			pstmt.setInt(2, proid);
			
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
