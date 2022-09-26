package teamProject1;
import java.sql.Date;
public class ProductVO {
	int proid;
	String custid;
	int categoryid;
	String title;
	int price;
	Date boarddate;
	String img;
	String content;
	String categoryname;
	
	public ProductVO(int proid, String custid, int categoryid, String title, int price, Date boarddate, String img,
			String content, String categoryname) {
		super();
		this.proid = proid;
		this.custid = custid;
		this.categoryid = categoryid;
		this.title = title;
		this.price = price;
		this.boarddate = boarddate;
		this.img = img;
		this.content = content;
		this.categoryname = categoryname;
	}
	public ProductVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getProid() {
		return proid;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getBoarddate() {
		return boarddate;
	}
	public void setBoarddate(Date boarddate) {
		this.boarddate = boarddate;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
}