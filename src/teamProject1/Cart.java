package teamProject1;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Cart extends JFrame {				//장바구니 클래스 
	JTable table;
	JLabel jlb;
	CartDAO CartDAO = new CartDAO();
	//String login_custid;
	
	
	
	public Cart(String login_custid) {                         //메인 페이지에서 장바구니 버튼 클릭하면 보이는 화면
		Vector<String> colNames = new Vector<String>();
		colNames.add("게시물번호");
		colNames.add("장바구니번호");
		colNames.add("카테고리");
		colNames.add("상품명");
		colNames.add("가격");
		colNames.add("게시일");
		colNames.add("판매자 아이디");
		table = new JTable(CartDAO.listInfo(login_custid), colNames);
		JScrollPane jsp = new JScrollPane(table);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow();
				Vector<String> v= (Vector<String>)CartDAO.listInfo(login_custid).get(row);	
				int cartid = Integer.parseInt(v.get(1));
				int re = JOptionPane.showConfirmDialog(null, "장바구니에서 삭제하시겠습니까?");
				if (re == 0) {
					CartDAO.cart_delete(cartid);
					CartDAO.listInfo(login_custid);
					table.updateUI();
				}
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
				// TODO Auto-generated method stub
				
			}
		});
		
		
		setTitle("장바구니");
		jlb = new JLabel("내가 담은 상품");
		setLayout(new FlowLayout());
		add(jlb);
		add(jsp);
		setSize(500,400);
		setVisible(true);
	}
}