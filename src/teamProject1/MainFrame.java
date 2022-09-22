package teamProject1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame{			//처음 보여지는 메인 프레임
	JComboBox<String> jcb;
	Vector<String> data;
	JTextField jtf;
	JTabbedPane jtp;
	JTextArea jta1, jta2, jta3;
	String id;
	
	public MainFrame() {
	
		JButton btn_signUp = new JButton("회원가입");
		JButton btn_login = new JButton("로그인");
		JButton btn_search = new JButton("검색");
		JButton btn_write = new JButton("글쓰기");
		
		jtf = new JTextField(20);
		jta1 = new JTextArea();
		jta2 = new JTextArea();
		jta3 = new JTextArea();
		//JButton btn_list = new JButton("장바구니");	   장바구니 JButton 숨기기	
		
		jtp = new JTabbedPane();
		jtp.add("도서",jta1);
		jtp.add("의류",jta2);
		jtp.add("전자",jta3);
		JScrollPane jsp = new JScrollPane(jtp);
		
		JPanel jp1 = new JPanel();
//		jp1.add(jcb);	콤보박스 
		jp1.add(jtf);
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
		
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();	
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
	
		setTitle("메인화면");
		setSize(700,500);
		setVisible(true);
		setLocationRelativeTo(null);  			//화면을 가운데에 배치
		setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
	
	}
	public static void main(String[] args) {
		new MainFrame();
	}

}
