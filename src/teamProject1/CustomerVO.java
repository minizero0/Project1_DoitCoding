package teamProject1;

public class CustomerVO {
	String custid;
	String custpwd;
	String name;
	String phone;
	String addr;
	String birth;
	
	public CustomerVO(String custid, String custpwd, String name, String phone, String addr, String birth) {
		super();
		this.custid = custid;
		this.custpwd = custpwd;
		this.name = name;
		this.phone = phone;
		this.addr = addr;
		this.birth = birth;
	}
	public CustomerVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getCustpwd() {
		return custpwd;
	}
	public void setCustpwd(String custpwd) {
		this.custpwd = custpwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
}