package teamProject1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUp extends JFrame{			//회원가입 클래스
	
	JTextField jtf_name,jtf_phone,jtf_addr,jtf_id,jtf_pw,jtf_birth;
	
	JLabel comment;
	
	JButton btn_confirm_id = new JButton("중복확인");
	JButton btn_confirm_pwd = new JButton("비밀번호확인");
	JButton btn_check = new JButton("회원가입");
	JButton btn_cancel = new JButton("가입취소");
	
	
	public void addUsers() {
		
		String custid = jtf_id.getText();   
		String custpwd = jtf_pw.getText();  
		String name = jtf_name.getText();  
		String phone = jtf_phone.getText();  
		String addr = jtf_addr.getText();
		String birth = jtf_birth.getText();
		
		String sql = "insert into customer values(?,?,?,?,?,?)"; 
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			
			pstmt.setString(1, custid);
			pstmt.setString(2, custpwd);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			pstmt.setString(5, addr);
			pstmt.setString(6, birth);
					
			int re = pstmt.executeUpdate();
			if(re == 1) {
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
			}else {
				JOptionPane.showMessageDialog(null, "(*)표시부분은 필수입력사항입니다.");
			}
			pstmt.close();
			conn.close();
		}catch (Exception ex) {
			System.out.println("예외발생:"+ex.getMessage());
		}			
	}
	
	public void confirm_id() {
		String custid = jtf_id.getText();
		String sql = "select custid from customer";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@192.168.0.120:1521:XE";
			String usr = "c##madang";
			String pwd = "madang";
			
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString(1).equals(custid))
					JOptionPane.showMessageDialog(null, "이미사용중인 아이디입니다. 다른 아이디를 입력해주세요");
				else
					JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
			}
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public SignUp() {
			
		setLayout(new GridLayout(8,2));
		comment = new JLabel("                              *** 정보를 입력하세요 ***");
		jtf_name = new JTextField(10);
		jtf_phone = new JTextField(12);
		jtf_addr = new JTextField(30);
		jtf_id = new JTextField(10);
		jtf_pw = new JTextField(10);
		jtf_birth = new JTextField(10);
		
		JPanel jp1 = new JPanel();
		jp1.add(new JLabel("(*)이름: "));
		jp1.add(jtf_name);
		
		JPanel jp2 = new JPanel();
		jp2.add(new JLabel("전화번호: "));
		jp2.add(jtf_phone);
		
		JPanel jp3 = new JPanel();
		jp3.add(new JLabel("주소: "));
		jp3.add(jtf_addr);
		
		JPanel jp4 = new JPanel();
		jp4.add(new JLabel("(*)아이디: "));
		jp4.add(jtf_id);
		jp4.add(btn_confirm_id);
		
		JPanel jp5 = new JPanel();
		jp5.add(new JLabel("(*)비밀번호: "));
		jp5.add(jtf_pw);
		
		JPanel jp6 = new JPanel();
		jp6.add(new JLabel("생년월일(6자리): "));
		jp6.add(jtf_birth);
		jp6.add(new JLabel("예시) 990909"));
		
		JPanel btn = new JPanel();
		btn.add(btn_check);
		btn.add(btn_cancel);	
	
		add(comment);
		add(jp4);
		add(jp5);
		add(jp1);
		add(jp2);
		add(jp3);
		add(jp6);
		add(btn);
		
		btn_confirm_id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custId = jtf_id.getText();
				String regex = "^[a-z0-9]*$";			//아이디 적합성 판단 후 중복판단 메소드로 전달.
				if (Pattern.matches(regex, custId)) 		//소문자와 숫자만 들어있으면 true 반환 아니면 false
					confirm_id();
				else
					JOptionPane.showMessageDialog(null, "적합하지 않습니다. 소문자와 숫자를 조합해서 만들어주세요");
			}
		});
		btn_confirm_pwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String custPwd = jtf_pw.getText();
				String regex = "^[a-z0-9]*$";			//비밀번호 적합성 판단 소문자 숫자 만 사용가능.
				if (Pattern.matches(regex, custPwd)) 		//소문자와 숫자만 들어있으면 true 반환 아니면 false
					JOptionPane.showMessageDialog(null, "확인되었습니다.");
				else
					JOptionPane.showMessageDialog(null, "적합하지 않습니다. 소문자와 숫자를 조합해서 만들어주세요");
			}
		});
		btn_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUsers();
			}
		});
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		setSize(350,380);
		setTitle("회원가입");
		setVisible(true);
		setLocationRelativeTo(null);  			//화면을 가운데에 배치
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	

}
