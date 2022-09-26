package teamProject1;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Cart extends JFrame {				//장바구니 클래스 
	JTable table;
	JLabel jlb;
	//String login_custid;
	Vector<Vector<String>> rowData;
	
	public void listInfo(String login_custid) {
		String sql = "select categoryname, title, price, boarddate, p.custid from customer cust, product p, category cat, cart c where cat.categoryid = p.categoryid and cust.custid = c.custid and p.proid = c.proid and c.custid = '"+login_custid+"'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String categoryname = rs.getString(1);
				String title = rs.getString(2);
				int price = rs.getInt(3);
				Date boarddate = rs.getDate(4);
				String p_custid = rs.getString(5);
				Vector<String> v = new Vector<String>();
				v.add(categoryname);
				v.add(title);
				v.add(price+"");
				v.add(boarddate+"");
				v.add(p_custid);
				rowData.add(v);
			}
			table.updateUI();
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception: "+e.getMessage());
		}
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
				System.out.println("true");
				JOptionPane.showMessageDialog(null, "장바구니에 담기 완료!");
			}
			System.out.println("false");
			conn.close();
			pstmt.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public Cart(String login_custid) {                         //메인 페이지에서 장바구니 버튼 클릭하면 보이는 화면
		Vector<String> colNames = new Vector<String>();
		colNames.add("카테고리");
		colNames.add("상품명");
		colNames.add("가격");
		colNames.add("게시일");
		colNames.add("판매자 아이디");
		
		rowData = new Vector<Vector<String>>();
		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
//				int row = table.getSelectedRow();
//				Vector<String> v= (Vector<String>)ProductDAO.get_item().get(row);	
//				int board_proid = Integer.parseInt(v.get(0));
//				new DetailPage(board_proid, login_custid);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		listInfo(login_custid);
		setTitle("장바구니");
		jlb = new JLabel("내가 담은 상품");
		setLayout(new FlowLayout());
		add(jlb);
		add(jsp);
		setSize(500,400);
		setVisible(true);
	}
}