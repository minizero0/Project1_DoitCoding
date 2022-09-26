package teamProject1;

public class CartVO {
	String login_custid;
	int proid;
	int cartid;
	
	public CartVO() {
		super();
	}

	public CartVO(String login_custid, int proid, int cartid) {
		super();
		this.login_custid = login_custid;
		this.proid = proid;
		this.cartid = cartid;
	}

	public String getLogin_custid() {
		return login_custid;
	}

	public void setLogin_custid(String login_custid) {
		this.login_custid = login_custid;
	}

	public int getProid() {
		return proid;
	}

	public void setProid(int proid) {
		this.proid = proid;
	}

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	
	
}