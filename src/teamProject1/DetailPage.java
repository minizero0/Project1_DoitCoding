package teamProject1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class DetailPage extends JFrame {			//상세화면
	Font font1;
	JTextArea jta_title;
	JTextArea jta_price;
	JTextArea jta_content;
	BoardProduct bp = new BoardProduct();
	ProductVO pv = new ProductVO();
	CartDAO CartDAO = new CartDAO();
	
	public void getData(int board_proid) {
		String sql = "select proid, custid, p.categoryid, title, price, boarddate, img, content, categoryname from product p, category c "
				+ "where p.categoryid = c.categoryid and proid = ?";
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
				pv.setCategoryid(rs.getInt(3));
				pv.setTitle(rs.getString(4));
				pv.setPrice(rs.getInt(5));
				pv.setBoarddate(rs.getDate(6));
				pv.setImg(rs.getString(7));
				pv.setContent(rs.getString(8));
				pv.setCategoryname(rs.getString(9));
			}			
			conn.close();
			pstmt.close();
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	
	public DetailPage(int board_proid, String login_custid){
		getData(board_proid);
		font1 = new Font("맑은 고딕", Font.BOLD,12);
		JButton btn_cart = new JButton("장바구니 담기");
		JButton btn_delete = new JButton("삭제");
		JButton btn_update = new JButton("수정");
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		//jp1.setBorder(new TitledBorder("Info"));
		
		JLabel image = new JLabel("이미지");						// 이미지데이터 불러오기
		image.setBounds(80,80,200,200);
		image.setForeground(Color.blue);
		jp1.add(image);
		
		JLabel category = new JLabel("카테고리 : ");
		category.setFont(font1);
		category.setBounds(30, 30, 67, 15);
		jp1.add(category);
		JLabel category_1 = new JLabel(pv.getCategoryname());  		   // 카테고리데이터 불러오기
		category_1.setForeground(Color.blue);
		category_1.setBounds(90, 31, 100, 15);
		jp1.add(category_1);
		
		JLabel custid = new JLabel("작성자 : ");
		custid.setFont(font1);
		custid.setBounds(190, 30, 67, 15);
		jp1.add(custid);
		JLabel custid_1 = new JLabel(pv.getCustid());  		        // custid데이터 불러오기
		custid_1.setForeground(Color.blue);
		custid_1.setBounds(250, 31, 100, 15);
		jp1.add(custid_1);
		
		JLabel date = new JLabel("게시일 : ");
		date.setFont(font1);
		date.setBounds(190, 63, 67, 15);
		jp1.add(date);
		JLabel date_1 = new JLabel(pv.getBoarddate()+"");     // 게시일데이터 불러오기
		date_1.setForeground(Color.blue);
		date_1.setBounds(250, 64, 100, 15);
		jp1.add(date_1);
		
		JLabel title = new JLabel("제목 : ");
		title.setFont(font1);
		title.setBounds(190, 96, 67, 15);
		jp1.add(title);
		JLabel title_1 = new JLabel(pv.getTitle()); 		// title데이터 불러오기
		title_1.setForeground(Color.blue);
		title_1.setBounds(250,97,130,15); 
		jp1.add(title_1);
		
		JLabel price = new JLabel("가격 : ");						
		price.setBounds(190, 129, 67, 15);
		price.setFont(font1);
		jp1.add(price);
		JLabel price_1 = new JLabel(pv.getPrice() + "(원)");						// price데이터 불러오기
		price_1.setForeground(Color.blue);
		price_1.setBounds(250, 125, 90, 25);
		jp1.add(price_1);
		
		JLabel content = new JLabel("내용 : ");
		content.setBounds(190, 162, 67, 15);
		content.setFont(font1);
		jp1.add(content);
		JLabel content_1 = new JLabel(pv.getContent());		// 내용데이터 불러오기
		content_1.setForeground(Color.blue);
		content_1.setBounds(190, 185, 180, 120);
		jp1.add(content_1);
		
		JPanel jp2 = new JPanel();
		jp2.add(btn_cart);
		jp2.add(btn_delete);
		jp2.add(btn_update);
		
		btn_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bp.BoardDelete(board_proid, login_custid);
				dispose();
			}
		});
		
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bp.BoardUpdate(board_proid, login_custid);
			}
		});
		
		btn_cart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(login_custid == null)
					JOptionPane.showMessageDialog(null, "로그인부터 해주세요");
				else {
					if(CartDAO.confirm_cart(login_custid, board_proid))
						CartDAO.cart_insert(login_custid, board_proid);
					else
						JOptionPane.showMessageDialog(null, "이미 장바구니에 담긴 상품입니다.");
				}
					
					
				
			}
		});
		
		setLayout(new BorderLayout());
		add(jp1, BorderLayout.CENTER);
		add(jp2, BorderLayout.SOUTH);
		
		setSize(400,390);
		setTitle("Detail Page");
		setVisible(true);
		setLocationRelativeTo(null);  			//화면을 가운데에 배치
		setDefaultCloseOperation(MainFrame.DISPOSE_ON_CLOSE);
	}
}