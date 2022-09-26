package teamProject1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame{			//처음 보여지는 메인 프레임
	JTextField jtf_search;
	JTabbedPane jtp;
	JTextArea jta1, jta2, jta3;
	MainFrame f;
	JComboBox jcb;
	JTable jta;
	String login_custid;
	
	public MainFrame() {
		CategoryDAO CategoryDAO = new CategoryDAO();
		CategoryVO CategoryVO = new CategoryVO();
		ProductDAO ProductDAO = new ProductDAO();
		f = this;
		
		jcb = new JComboBox<String>(CategoryDAO.listCate("main"));
	
		JButton btn_signUp = new JButton("회원가입");
		JButton btn_login = new JButton("로그인");
		JButton btn_search = new JButton("검색");
		JButton btn_write = new JButton("글쓰기");
		//JButton btn_list = new JButton("장바구니");	   장바구니 JButton 숨기기	
		
		Vector<String> colName = new Vector<>();
		
		colName.add("proid");
		colName.add("custid");
		colName.add("categoryname");
		colName.add("title");
		colName.add("price");
		colName.add("date");
		colName.add("content");
		jta = new JTable(ProductDAO.get_item(), colName);
		
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
		
		JScrollPane jsp = new JScrollPane(jta);
		
		
		
		jtf_search = new JTextField(20);
		jta1 = new JTextArea();
		jta2 = new JTextArea();
		jta3 = new JTextArea();
		
		
		jtp = new JTabbedPane();
		jtp.add("도서",jta1);
		jtp.add("의류",jta2);
		jtp.add("전자",jta3);
//		JScrollPane jsp = new JScrollPane(jtp);
		
		
		JPanel jp1 = new JPanel();
		jp1.add(jtf_search);
		jp1.add(jcb);
		jp1.add(btn_search);
		jp1.add(btn_write);
		//jp1.add(btn_list);    장바구니 JButton 숨기기
		
		JPanel jp_1 = new JPanel();
		jp_1.setLayout(new BorderLayout());
		
		JPanel jp2 = new JPanel();
		jp2.add(btn_signUp);
		jp2.add(btn_login);
		
		setLayout(new BorderLayout());
		add(jp1, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		add(jp2, BorderLayout.SOUTH);	
				
		setTitle("메인화면");
		setSize(700,500);
		setVisible(true);
		setLocationRelativeTo(null);  			//화면을 가운데에 배치
		setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
	
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login(f);
			}
		});
		
		btn_signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignUp();
			}
		});
		
		btn_write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "권한이 없습니다.");
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
	}
	public static void main(String[] args) {
		new MainFrame();
	}
}
