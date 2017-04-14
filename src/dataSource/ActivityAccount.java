package dataSource;

import java.util.Date;

public class ActivityAccount extends Activity{

	Account account;
	
	public ActivityAccount() {
	}
	
	public ActivityAccount(int idActivity, Employee employee, Date datOfActivity, String description, Account account) {
		super(idActivity, employee, datOfActivity, description);
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
	
	

	
}
