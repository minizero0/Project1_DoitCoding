package teamProject1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class DetailPage extends JFrame {			//상세화면
	Font font1;
	JTextArea jta_title;
	JTextArea jta_price;
	JTextArea jta_content;
	BoardProduct bp = new BoardProduct();;
	
	public DetailPage(){
		
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
		JLabel category_1 = new JLabel("카테고리명");  		   // 카테고리데이터 불러오기
		category_1.setForeground(Color.blue);
		category_1.setBounds(90, 31, 100, 15);
		jp1.add(category_1);
		
		JLabel custid = new JLabel("작성자 : ");
		custid.setFont(font1);
		custid.setBounds(190, 30, 67, 15);
		jp1.add(custid);
		JLabel custid_1 = new JLabel("코딩두잇");  		        // custid데이터 불러오기
		custid_1.setForeground(Color.blue);
		custid_1.setBounds(250, 31, 100, 15);
		jp1.add(custid_1);
		
		JLabel date = new JLabel("게시일 : ");
		date.setFont(font1);
		date.setBounds(190, 63, 67, 15);
		jp1.add(date);
		JLabel date_1 = new JLabel("2022년 9월 22일");     // 게시일데이터 불러오기
		date_1.setForeground(Color.blue);
		date_1.setBounds(250, 64, 100, 15);
		jp1.add(date_1);
		
		JLabel title = new JLabel("제목 : ");
		title.setFont(font1);
		title.setBounds(190, 96, 67, 15);
		jp1.add(title);
		JLabel title_1 = new JLabel("제목 정보 불러오기"); 		// title데이터 불러오기
		title_1.setForeground(Color.blue);
		title_1.setBounds(250,97,130,15); 
		jp1.add(title_1);
		
		JLabel price = new JLabel("가격 : ");						
		price.setBounds(190, 129, 67, 15);
		price.setFont(font1);
		jp1.add(price);
		JLabel price_1 = new JLabel("(원)");						// price데이터 불러오기
		price_1.setForeground(Color.blue);
		price_1.setBounds(250, 125, 90, 25);
		jp1.add(price_1);
		
		JLabel content = new JLabel("내용 : ");
		content.setBounds(190, 162, 67, 15);
		content.setFont(font1);
		jp1.add(content);
		JLabel content_1 = new JLabel("내용 불러오기");		// 내용데이터 불러오기
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
				bp.BoardDelete(17);
				
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
	public static void main(String[] args) {
	new DetailPage();
	}
}