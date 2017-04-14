package dataSource;

public class Bill {
	  
	 int idBill;
	 float sum;
	 String description;
	 Account account;
	
	 public Bill(float sum, String description, Account account) {
		super();
		this.sum = sum;
		this.description = description;
		this.account = account;
	}
	public Bill() {
		super();
	}
	public Bill(int idBill, float sum, String description, Account account) {
		super();
		this.idBill = idBill;
		this.sum = sum;
		this.description = description;
		this.account = account;
	}
	public int getIdBill() {
		return idBill;
	}
	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}

