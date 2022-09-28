package teamProject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ProductDAO {
	int proid,count;
	String title,content,custid,boarddate,img;
	int price, cate;
	Vector<Vector<String>> vector = new Vector<>();
	
	
	public boolean confirm_id(int board_proid,String login_custid) { 		//로그인한 사용자 아이디와 게시물을 작성한 사용자 아이디를 확인
		boolean check_login = false;
		String sql = "select custid from product where proid = ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
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
	
	public void board_write(ProductVO pv) {					//게시물 작성
		

		String sql = "insert into product values(seq_proid.nextval,?,?,?,?,sysdate,?,?)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pv.getCustid());
			pstmt.setInt(2, pv.getCategoryid());
			pstmt.setString(3, pv.getTitle());
			pstmt.setInt(4, pv.getPrice());
			pstmt.setString(5, pv.getImg());
			pstmt.setString(6, pv.getContent());
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
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
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
	
	public void board_update(ProductVO pv) {					//게시물 수정
		String sql = "update product set categoryid = ?, title = ?,price = ?, img = ?, content = ? where proid = ? ";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pv.getCategoryid());
			pstmt.setString(2, pv.getTitle());
			pstmt.setInt(3, pv.getPrice());
			pstmt.setString(4, pv.getImg());
			pstmt.setString(5, pv.getContent());
			pstmt.setInt(6, pv.getProid());
			
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
	
	
	public Vector get_item() {
		vector.clear();
		String sql = "select p.proid, p.custid, categoryname, title, price, boarddate, count(cat.proid) "
				+ "from product p left outer join category c on p.categoryid = c.categoryid left outer join "
				+ "cart cat on p.proid = cat.proid "
				+ "group by p.proid, p.custid, categoryname, title, price, boarddate order by p.proid";
		
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				Connection conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
						"c##project1", "project1");
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					Vector<String> vc = new Vector<>();
					vc.add(rs.getInt(1)+"");
					vc.add(rs.getString(2));
					vc.add(rs.getString(3));
					vc.add(rs.getString(4));
					vc.add(rs.getInt(5)+"");
					vc.add(rs.getDate(6)+"");
					vc.add(rs.getInt(7)+"");
					vector.add(vc);
				}
				conn.close();
				rs.close();
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return vector;
	}
	
	public Vector select_item(CategoryVO cv) {
		vector.clear();
		String categoryname = cv.getCategoryname();
		String sql = "select p.proid, p.custid, categoryname, title, price, boarddate, count(cat.proid)"
				+ " from product p left outer join category c on p.categoryid = c.categoryid left outer join cart cat on p.proid = cat.proid"
				+ " where categoryname = ?"
				+ " group by p.proid, p.custid, categoryname, title, price, boarddate order by p.proid";
		
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				Connection conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
						"c##project1", "project1");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, categoryname);
				
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Vector<String> vc = new Vector<>();
					vc.add(rs.getInt(1)+"");
					vc.add(rs.getString(2));
					vc.add(rs.getString(3));
					vc.add(rs.getString(4));
					vc.add(rs.getInt(5)+"");
					vc.add(rs.getDate(6)+"");
					vc.add(rs.getInt(7)+"");
					vector.add(vc);
				}
				conn.close();
				rs.close();
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return vector;
	}
	
	
	public void Search_keyword_MainFrame(CategoryVO cv, String search_name) { 
		vector.clear();
		
		String categoryname = cv.getCategoryname();
		String sql;
		System.out.println(categoryname);
		if(categoryname.equals("all")) {
			sql =  "select p.proid, p.custid, categoryname, title, price, boarddate, count(cat.proid)"
					+ " from product p left outer join category c on p.categoryid = c.categoryid left outer join cart cat on p.proid = cat.proid"
					+ " where title like ?"
					+ " group by p.proid, p.custid, categoryname, title, price, boarddate order by p.proid";
		}
		else {
			sql = "select p.proid, p.custid, categoryname, title, price, boarddate, count(cat.proid)"
					+ " from product p left outer join category c on p.categoryid = c.categoryid left outer join cart cat on p.proid = cat.proid"
					+ " where title like ? and categoryname = ?"
					+ " group by p.proid, p.custid, categoryname, title, price, boarddate order by p.proid";
		}
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+search_name+"%");
			if(!categoryname.equals("all")) {
				pstmt.setString(2, categoryname);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector<String> vc = new Vector<>();
				vc.add(rs.getInt(1)+"");
                vc.add(rs.getString(2));
                vc.add(rs.getString(3));
                vc.add(rs.getString(4));
                vc.add(rs.getInt(5)+"");
                vc.add(rs.getDate(6)+"");
                vc.add(rs.getInt(7)+"");
				vector.add(vc);
			}
			conn.close();
			pstmt.close();
			rs.close();
			
		}catch(Exception e) {
			System.out.println("예외" + e.getMessage());
			}	
	}
	
	public void getData(ProductVO pv, CategoryVO cv, int board_proid) {
		String sql = "select p.proid, p.custid, categoryname, title, price, boarddate, img, content from product p, category c where p.categoryid = c.categoryid and proid = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_proid);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				pv.setProid(rs.getInt(1));
				pv.setCustid(rs.getString(2));
				cv.setCategoryname(rs.getString(3));
				pv.setTitle(rs.getString(4));
				pv.setPrice(rs.getInt(5));
				pv.setBoarddate(rs.getDate(6));
				pv.setImg(rs.getString(7));
				pv.setContent(rs.getString(8));
			}			
			conn.close();
			pstmt.close();
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
	}
	
	public Integer count_custid(int board_proid) {
		String sql = "select p.proid, count(c.custid) from product p, cart c where p.proid = c.proid and p.proid = ? group by p.proid";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_proid);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				proid = rs.getInt(1);
				count = rs.getInt(2);
			}
			
			conn.close();
			pstmt.close();
			rs.close();
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return count;
	}
	
	
}
