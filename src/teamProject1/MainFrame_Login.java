package teamProject1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame_Login extends JFrame {			//로그인시 보여지는 메인 프레임
	JTextField jtf_search;
	JTabbedPane jtp;
	JTextArea jta1;
	JTextArea jta2;
	JTextArea jta3;
	JComboBox<String> jcb;
	CategoryDAO cd = new CategoryDAO();
	CategoryVO CategoryVO = new CategoryVO();
	BoardProduct bp = new BoardProduct();
	ProductDAO ProductDAO = new ProductDAO();
	JTable jta;
	

	public MainFrame_Login(String login_custid) {
		JButton btn_logout = new JButton("로그아웃");
		JButton btn_search = new JButton("검색");
		JButton btn_write = new JButton("글쓰기");
		JButton btn_cart = new JButton("장바구니");
		JButton btn_new = new JButton("새로고침");
		
		jcb = new JComboBox<String>(cd.listCate());
		
		jtf_search = new JTextField(20);
		
		jta1 = new JTextArea();
		jta2 = new JTextArea();
		jta3 = new JTextArea();
		
		jtp = new JTabbedPane();
		jtp.add("도서",jta1);
		jtp.add("의류",jta2);
		jtp.add("전자",jta3);
//		JScrollPane jsp = new JScrollPane(jtp);
		
		Vector<String> colName = new Vector<>();
		colName.add("proid");
		colName.add("custid");
		colName.add("categoryname");
		colName.add("title");
		colName.add("price");
		colName.add("date");
		colName.add("img");
		colName.add("content");
		
		jta = new JTable(ProductDAO.get_item(), colName);
		JScrollPane jsp = new JScrollPane(jta);
		
		jta.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = jta.getSelectedRow();
				Vector<String> v= (Vector<String>)ProductDAO.get_item().get(row);	
				int board_proid = Integer.parseInt(v.get(0));
				new DetailPage(board_proid, login_custid);
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
			}
		});
		
		
		JPanel jp1 = new JPanel();
		jp1.add(jtf_search);
		jp1.add(jcb);
		jp1.add(btn_search);
		jp1.add(btn_write);
		jp1.add(btn_cart);
		
		JPanel jp2 = new JPanel();
		jp2.add(new JLabel(login_custid + " 님, 환영합니다.     "));
		jp2.add(btn_logout);
		jp2.add(btn_new);
	
		setLayout(new BorderLayout());
		add(jp1, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(jp2, BorderLayout.SOUTH);	
	
		setSize(700,500);
		setTitle("로그인 화면");
		setVisible(true);
		setLocationRelativeTo(null);  			//화면을 가운데에 배치	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainFrame();
			}
		});
		
		btn_write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BoardProduct().BoardWrite(login_custid);
			}
		});
		
		btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String search_name = jtf_search.getText();
				String item = jcb.getSelectedItem().toString();
				CategoryVO.setCategoryname(item);
				ProductDAO.Search_keyword_MainFrame(CategoryVO, search_name);
				jta.updateUI();
			}
		});
		
		btn_cart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Cart(login_custid);
			}
		});
		
		btn_new.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductDAO.get_item();
				jta.updateUI();
			}
		});
	}
}