package teamProject1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Login extends JFrame{          //로그인 클래스
    
    JTextField jtf_id,jtf_pw;
    JLabel jl;
    MainFrame mainframe;
    boolean login_Flag;
    String custid;
    
    CustomerDAO cd = new CustomerDAO();
    CustomerVO cv = new CustomerVO();
    
    public Login(MainFrame f) {
    	mainframe = f;
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
        
        btn_login.addActionListener(new ActionListener() {          //로그인 버튼
            public void actionPerformed(ActionEvent e) {
            	String custid = jtf_id.getText();
                String custpwd = jtf_pw.getText();
                
            	cv.setCustid(custid);
                cv.setCustpwd(custpwd);
            	
                if (cd.confirm_login(cv)) {
	                mainframe.setVisible(false);
	                JOptionPane.showMessageDialog(null, "접속하였습니다.");
	                dispose();
	                new MainFrame_Login(custid);
                }
            }
        });
        btn_singUp.addActionListener(new ActionListener() {         //회원가입 버튼
            public void actionPerformed(ActionEvent e) {
                new SignUp();
            }
        });
        setSize(300,150);
        setTitle("로그인");
        setVisible(true);
        setLocationRelativeTo(null);            //화면을 가운데에 배치     
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
}