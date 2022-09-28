package teamProject1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class DetailPage extends JFrame {			//상세화면
	Font font1, font2;
	JTextArea jta_title;
	JTextArea jta_price;
	JTextArea jta_content;
	BoardProduct bp = new BoardProduct();
	ProductVO pv = new ProductVO();
	CategoryVO cv = new CategoryVO();
	CartDAO CartDAO = new CartDAO();
	ProductDAO pd = new ProductDAO();
	
	public DetailPage(int board_proid, String login_custid){
		pd.getData(pv, cv, board_proid);
		font1 = new Font("맑은 고딕", Font.BOLD,12);
		font2 = new Font("굴림체", Font.BOLD,12);
		JButton btn_cart = new JButton("장바구니 담기");
		JButton btn_delete = new JButton("삭제");
		JButton btn_update = new JButton("수정");
		
		URL url;
		ImageIcon icon_detailpage_before;
		Image image_before;
		Image image_after;
		ImageIcon icon_detailpage_after;
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		
		try {
			url = new URL(pv.getImg());
			
			icon_detailpage_before = new ImageIcon(url);
			image_before = icon_detailpage_before.getImage();
			image_after = image_before.getScaledInstance(150, 200, image_before.SCALE_SMOOTH);  //이미지 사이즈 맞추기
			icon_detailpage_after = new ImageIcon(image_after);
		
			JLabel image = new JLabel(icon_detailpage_after);						// 이미지데이터 불러오기
			image.setBounds(5,40,200,250);
			image.setForeground(Color.blue);
			jp1.add(image);
			
		}catch(Exception e) {
			System.out.println("예외" + e.getMessage());}
		
		JLabel category = new JLabel("카테고리 : ");
		category.setFont(font1);
		category.setBounds(30, 30, 67, 15);
		jp1.add(category);
		JLabel category_1 = new JLabel(cv.getCategoryname()+"");  		   // 카테고리데이터 불러오기
		category_1.setForeground(Color.blue);
		category_1.setBounds(90, 31, 100, 15);
		jp1.add(category_1);
		
		JLabel custid = new JLabel("작성자 : ");
		custid.setFont(font1);
		custid.setBounds(200, 30, 67, 15);
		jp1.add(custid);
		JLabel custid_1 = new JLabel(pv.getCustid());  		        // custid데이터 불러오기
		custid_1.setForeground(Color.blue);
		custid_1.setBounds(260, 31, 100, 15);
		jp1.add(custid_1);
		
		JLabel date = new JLabel("게시일 : ");
		date.setFont(font1);
		date.setBounds(200, 63, 67, 15);
		jp1.add(date);
		JLabel date_1 = new JLabel(pv.getBoarddate()+"");     // 게시일데이터 불러오기
		date_1.setForeground(Color.blue);
		date_1.setBounds(260, 64, 100, 15);
		jp1.add(date_1);
		
		JLabel title = new JLabel("제목 : ");
		title.setFont(font1);
		title.setBounds(200, 96, 67, 15);
		jp1.add(title);
		JLabel title_1 = new JLabel(pv.getTitle()); 		// title데이터 불러오기
		title_1.setForeground(Color.blue);
		title_1.setBounds(260,95,200,20); 
		jp1.add(title_1);
		
		JLabel price = new JLabel("가격 : ");						
		price.setBounds(200, 129, 67, 15);
		price.setFont(font1);
		jp1.add(price);
		JLabel price_1 = new JLabel(pv.getPrice() + "(원)");						// price데이터 불러오기
		price_1.setForeground(Color.blue);
		price_1.setBounds(260, 125, 90, 25);
		jp1.add(price_1);
		
		JLabel content = new JLabel("내용 : ");
		content.setBounds(200, 162, 67, 15);
		content.setFont(font1);
		jp1.add(content);
		//JLabel content_1 = new JLabel(pv.getContent());		// 내용데이터 불러오기
		JTextArea content_1 = new JTextArea(pv.getContent());	
		content_1.setForeground(Color.blue);
		content_1.setFont(font2);
		content_1.setBounds(200, 185, 220, 82);
		jp1.add(content_1);
		content_1.setEditable(false);
		
		 JLabel user = new JLabel("장바구니에 담은 사용자 수 : ");
	    user.setBounds(150, 280, 200, 15);
	    user.setFont(font1);
	    jp1.add(user);
	    JLabel user_1 = new JLabel(pd.count_custid(pv.getProid())+"");       // 사용자 수 데이터 불러오기
	    user_1.setForeground(Color.blue);
	    user_1.setBounds(310, 237, 180, 100);
	    jp1.add(user_1);
		
		JPanel jp2 = new JPanel();
		jp2.add(btn_cart);
		jp2.add(btn_delete);
		jp2.add(btn_update);
		
		btn_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bp.BoardDelete(board_proid, login_custid);
			}
		});
		
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = pv.getTitle();
				int price = pv.getPrice();
				String img = pv.getImg();
				String content = pv.getContent();
				bp.BoardUpdate(board_proid, login_custid, title, price, img, content);
				
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
		
		setSize(470,385);
		setTitle("Detail Page");
		setVisible(true);
		setLocationRelativeTo(null);  			//화면을 가운데에 배치
		setDefaultCloseOperation(MainFrame.DISPOSE_ON_CLOSE);
	}
}