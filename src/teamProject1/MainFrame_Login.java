package teamProject1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame_Login extends JFrame {			//로그인시 보여지는 메인 프레임
	JTextField jtf;
	JTabbedPane jtp;
	JTextArea jta1;
	JTextArea jta2;
	JTextArea jta3;
	JComboBox<String> jcb;
	CategoryDAO cd = new CategoryDAO();

	public MainFrame_Login(String custid) {
		JButton btn_logout = new JButton("로그아웃");
		JButton btn_search = new JButton("검색");
		JButton btn_write = new JButton("글쓰기");
		JButton btn_list = new JButton("장바구니");		
		
		jcb = new JComboBox<String>(cd.listCate());
		
		jtf = new JTextField(20);
		
		jta1 = new JTextArea();
		jta2 = new JTextArea();
		jta3 = new JTextArea();
		
		jtp = new JTabbedPane();
		jtp.add("도서",jta1);
		jtp.add("의류",jta2);
		jtp.add("전자",jta3);
		JScrollPane jsp = new JScrollPane(jtp);
		
		JPanel jp1 = new JPanel();
		jp1.add(jtf);
		jp1.add(jcb);
		jp1.add(btn_search);
		jp1.add(btn_write);
		jp1.add(btn_list);
		
		JPanel jp2 = new JPanel();
		jp2.add(new JLabel(custid + " 님, 환영합니다.     "));
		jp2.add(btn_logout);
	
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
				new BoardWrite();
			}
		});
	}
}