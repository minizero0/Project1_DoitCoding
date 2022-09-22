package teamProject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BoardUpdate extends JFrame{			//게시물 수정 클래스
	String cate,title,content,custid,boarddate,img;
	int price;
	
	public void board_write() {
		CustomerVO cv = new CustomerVO();
		String custid = cv.getCustid();

		String sql = "update product set cate = ?, title = ?, ";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, custid);
			pstmt.setString(2, cate);
			pstmt.setString(3, title);
			pstmt.setInt(4, price);
			pstmt.setString(5, boarddate);
			pstmt.setString(6, img);
			pstmt.setString(7, content);
			int re = pstmt.executeUpdate();
			if (re == 1) {
				JOptionPane.showMessageDialog(null, "게시글 수정 완료!");
				dispose();
			}
			conn.close();
			pstmt.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	
	public BoardUpdate() {
		
		
		JButton btn_update = new JButton("수정하기");
		add(btn_update);
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		setSize(300,400);
		setVisible(true);
		setLocationRelativeTo(null);  			//화면을 가운데에 배치
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
}
