package teamProject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CustomerDAO {
	JTextField jtf_id,jtf_pw;
	JTextField jtf_name,jtf_phone,jtf_addr,jtf_birth;
	
	
	//아이디 삽입 메소드
	public boolean addUsers(CustomerVO CustomerVO) {

		String sql = "insert into customer values(?,?,?,?,?,?)"; 
		boolean flag_addUsers = false;
		try {
			String custid = CustomerVO.getCustid();
			String custpwd = CustomerVO.getCustpwd();
			String name = CustomerVO.getName();
			String phone = CustomerVO.getPhone();
			String addr = CustomerVO.getAddr();
			String birth = CustomerVO.getBirth();
			
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
				flag_addUsers = true;
				
			}
			
			pstmt.close();
			conn.close();
			
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "아이디, 비밀번호, 이름은 필수입력사항입니다.");
		}
		return flag_addUsers;
	}
	
	//아이디 중복성 확인 메소드
	public boolean confirm_id(CustomerVO CustomerVO) {
		String sql = "select custid from customer where custid = ?";
		boolean signUp_Flag = true;
		try {
			
			String custid = CustomerVO.getCustid();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.120:1521:XE", 
					"c##project1", "project1");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, custid);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
                signUp_Flag = false;
            }
            if(signUp_Flag)
                JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
            else
                JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다.");
            conn.close();
            pstmt.close();
            
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return signUp_Flag;
	}
	
	//로그인 아이디와 비밀번호가 동일한지 확인 
	public boolean confirm_login(CustomerVO CustomerVO) {

		boolean login_Flag = false;
        
        String custid = CustomerVO.getCustid();
		String custpwd = CustomerVO.getCustpwd();
		
		String sql = "select custpwd from customer where custid = ?";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@192.168.0.120:1521:XE", 
                    "c##project1", "project1");
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, custid);
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                if(rs.getString(1).equals(custpwd)) 
                    login_Flag = true;
            }
            if(login_Flag) {
                JOptionPane.showMessageDialog(null, "로그인에 성공했습니다"+custid+"님");
            }
            else
            	JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 옳바르지 않습니다.");
            
            conn.close();
            pstmt.close();
            rs.close();
        }catch (Exception e) {
            System.out.println("예외발생:"+e.getMessage());
        }
        return login_Flag;
        
    }
}