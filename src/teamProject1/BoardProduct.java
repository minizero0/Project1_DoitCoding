package teamProject1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardProduct extends JFrame{
	CategoryDAO cd = new CategoryDAO();
	JComboBox<String> jcb = new JComboBox<String>(cd.listCate("other"));
	JLabel jl;
	JTextField jtf_title;
	JTextField jtf_price;
	JTextArea jtf_content;
	JTextArea jtf_imageurl;
	Vector<Vector<String>> vector = new Vector<>();
	
	
	ProductDAO pd = new ProductDAO();
	ProductVO pv = new ProductVO();
	
	//게시물 삭제
	public void BoardDelete(int board_proid, String login_custid) {  //String login_custid
//		pd.board_delete(board_proid);
		if(pd.confirm_id(board_proid, login_custid)) {
			pd.board_delete(board_proid);
			pd.get_item();
			dispose();
		}
		else
			JOptionPane.showMessageDialog(null, "삭제할 권한이 없습니다");
	}
	
	//게시물 업데이트
	public void BoardUpdate(int board_proid, String login_custid, String get_title, int get_price, String get_img, String get_content) {
		
		jtf_title = new JTextField(40);
		jtf_price = new JTextField(10);
		jtf_content = new JTextArea();
		jtf_imageurl = new JTextArea();
		JButton btn_update = new JButton("글수정");
		
		
		JPanel jp1 = new JPanel();
		jl = new JLabel("   상품에 대한 정보를 작성하세요.");
		jp1.add(jcb);
		jp1.add(jl);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(null);
				
		JLabel title = new JLabel("제목: ");
		title.setBounds(20, 30, 67, 15);
		jp2.add(title);
		jtf_title.setBounds(70, 26, 150, 25);
		jtf_title.setText(get_title);
		jp2.add(jtf_title);
		
		JLabel price = new JLabel("가격: ");
		price.setBounds(20, 65, 67, 15);
		jp2.add(price);
		jtf_price.setBounds(70, 61, 90, 25);
		jtf_price.setText(get_price+"");
		jp2.add(jtf_price);
		JLabel price_won = new JLabel("(원) ");
		price_won.setBounds(170, 65, 67, 15);
		jp2.add(price_won);		
		
		JLabel imageurl = new JLabel("이미지주소: ");
		imageurl.setBounds(20, 58, 100, 100);
		jp2.add(imageurl);	
		JScrollPane jsp2 = new JScrollPane(jtf_imageurl);
		jsp2.setBounds(100, 98, 250, 35);
		jtf_imageurl.setText(get_img);
		jp2.add(jsp2);
				
		JLabel content = new JLabel("내용: ");
		content.setBounds(20, 140, 67, 15);
		jp2.add(content);	
		JScrollPane jsp = new JScrollPane(jtf_content);
		jsp.setBounds(70, 140, 285, 140);
		jtf_content.setText(get_content);
		jp2.add(jsp);
			
		JPanel jp3 = new JPanel();
		jp3.add(btn_update);
		
		
		
		if(pd.confirm_id(board_proid, login_custid)) {
			setVisible(true);
			btn_update.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					pv.setCategoryid(jcb.getSelectedIndex()+2);  
					pv.setTitle(jtf_title.getText());
					pv.setPrice(Integer.parseInt(jtf_price.getText())); 
					pv.setImg(jtf_imageurl.getText()); 
					pv.setContent(jtf_content.getText()); 
					pv.setCustid(login_custid);
					pv.setProid(board_proid);
					pd.board_update(pv);
					pd.get_item();
				}
			});
		}
		else {
			JOptionPane.showMessageDialog(null, "수정할 권한이 없습니다");
			setVisible(false);
		}
		
				
		setLayout(new BorderLayout());
		add(jp1, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
		add(jp3, BorderLayout.SOUTH);
		
		setSize(400,400);
		setTitle("글 수정페이지");
		setLocationRelativeTo(null);  			//화면을 가운데에 배치
		setDefaultCloseOperation(MainFrame.DISPOSE_ON_CLOSE);
		
	}
	
	//게시물 작성
	public void BoardWrite(String login_custid) {
		pd = new ProductDAO();
		
		jtf_title = new JTextField(40);
		jtf_price = new JTextField(10);
		jtf_content = new JTextArea();
		jtf_imageurl = new JTextArea();
		JButton btn_register = new JButton("글등록");
		
		JPanel jp1 = new JPanel();
		jl = new JLabel("   상품에 대한 정보를 작성하세요.");
		jp1.add(jl);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(null);
				
		JLabel title = new JLabel("제목: ");
		title.setBounds(20, 30, 67, 15);
		jp2.add(title);
		jtf_title.setBounds(85, 26, 285, 25);
		jp2.add(jtf_title);
		
		JLabel category = new JLabel("카테고리: ");
		category.setBounds(20, 65, 67, 15);
		jp2.add(category);
		jcb.setBounds(85, 61, 100, 25);
		jp2.add(jcb);
		
		JLabel price = new JLabel("가격: ");
		price.setBounds(20, 100, 67, 15);
		jp2.add(price);
		jtf_price.setBounds(85, 96, 90, 25);
		jp2.add(jtf_price);
		JLabel price_won = new JLabel("(원) ");
		price_won.setBounds(180, 100, 67, 15);
		jp2.add(price_won);		
		
		JLabel imageurl = new JLabel("이미지: ");
		imageurl.setBounds(20, 92, 100, 100);
		jp2.add(imageurl);	
		JScrollPane jsp2 = new JScrollPane(jtf_imageurl);
		jsp2.setBounds(85, 131, 285, 35);
		jp2.add(jsp2);		
		
		JLabel content = new JLabel("내용: ");
		content.setBounds(20, 175, 67, 15);
		jp2.add(content);	
		JScrollPane jsp = new JScrollPane(jtf_content);
		jsp.setBounds(85, 175, 285, 115);
		jp2.add(jsp);
			
		JPanel jp3 = new JPanel();
		jp3.add(btn_register);
		
		btn_register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pv.setCustid(login_custid);
				pv.setCategoryid(jcb.getSelectedIndex()+2);  
				pv.setTitle(jtf_title.getText()); 
				pv.setPrice(Integer.parseInt(jtf_price.getText())); 
				pv.setImg(jtf_imageurl.getText()); 
				pv.setContent(jtf_content.getText()); 
				pd.board_write(pv);
				pd.get_item();
				
				dispose();
			}
		});
				
				
		setLayout(new BorderLayout());
		add(jp1, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
		add(jp3, BorderLayout.SOUTH);
		
		setSize(400,400);
		setTitle("글 작성페이지");
		setVisible(true);
		setLocationRelativeTo(null);  			//화면을 가운데에 배치
		setDefaultCloseOperation(MainFrame.DISPOSE_ON_CLOSE);
		
	}
	
	
}
