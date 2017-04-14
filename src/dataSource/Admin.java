package dataSource;

public class Admin {
	
	int idAdmin;
	String adminName;
	String password;
	long personalNumericalCode;
	String address;
	
	public Admin() {
		super();
	}
	public Admin(int idAdmin, String adminName, String password, long personalNumericalCode, String address) {
		super();
		this.idAdmin = idAdmin;
		this.adminName = adminName;
		this.password = password;
		this.personalNumericalCode = personalNumericalCode;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Admin [idAdmin=" + idAdmin + ", adminName=" + adminName + ", password=" + password
				+ ", personalNumericalCode=" + personalNumericalCode + ", address=" + address + "]";
	}
	public int getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPersonalNumericalCode() {
		return personalNumericalCode;
	}
	public void setPersonalNumericalCode(long personalNumericalCode) {
		this.personalNumericalCode = personalNumericalCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
