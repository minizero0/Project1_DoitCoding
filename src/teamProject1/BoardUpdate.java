package teamProject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BoardUpdate extends JFrame{			//게시물 수정 클래스
	String cate,title,content,custid,boarddate,img;
	int price, proid;
	
	ProductDAO pd = new ProductDAO();
	ProductVO pv = new ProductVO();
	
	
	public BoardUpdate(String login_custid) {
		
		JButton btn_update = new JButton("수정하기");
		
		if(pd.confirm_id(login_custid))
			pd.board_update(login_custid);
		else
			JOptionPane.showMessageDialog(null, "삭제할 권한이 없습니다");
	}
}
