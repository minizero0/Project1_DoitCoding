package teamProject1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Login extends JFrame{			//로그인 클래스
	
	JTextField jtf_id,jtf_pw;
	JLabel jl;
	
	public void confirm_login() {
		CustomerVO cv = new CustomerVO();
		String usrid = jtf_id.getText();
		String usrpwd = jtf_pw.getText();
		String sql = "select custid, custpwd from customer";
		
		boolean login_Flag = false;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString(1).equals(usrid) && rs.getString(2).equals(usrpwd)) {
					login_Flag = true;
					cv.setCustid(rs.getString(1)); 	//로그인된 아이디 세터
				}
			}
			if(login_Flag) {
				JOptionPane.showMessageDialog(null, "로그인에 성공했습니다"+usrid+"님");
				dispose();		// system.exit()는 모든 프레임 즉,프로그램을 종료시킨다 
			}					// 반면 dispose()는 현재 프레임만 종료시킨다.
			else
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 옳바르지 않습니다.");
			conn.close();
			stmt.close();
			rs.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public Login() {
		jl = new JLabel("                  아이디/비밀번호를 입력하세요.");
		jtf_id = new JTextField();
		jtf_pw = new JTextField();
		JButton btn_singUp = new JButton("회원가입");
		JButton btn_login = new JButton("로그인");
	
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2,2,10,10));
		jp1.add(new JLabel("아이디"));
		jp1.add(jtf_id);
		jp1.add(new JLabel("비밀번호"));
		jp1.add(jtf_pw);
			
		JPanel jp2 = new JPanel();
		jp2.add(btn_login);
		jp2.add(btn_singUp);	
		
		add(jl,BorderLayout.NORTH);
		add(jp1, BorderLayout.CENTER);
		add(jp2, BorderLayout.SOUTH);		
		
		btn_login.addActionListener(new ActionListener() {			//로그인 버튼
			public void actionPerformed(ActionEvent e) {
				confirm_login();
//				new MainFrame_Login();
			}
		});
		btn_singUp.addActionListener(new ActionListener() {			//회원가입 버튼
			public void actionPerformed(ActionEvent e) {
				new SignUp();
			}
		});
		setSize(300,150);
		setTitle("로그인");
		setVisible(true);
		setLocationRelativeTo(null);  			//화면을 가운데에 배치		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
}
