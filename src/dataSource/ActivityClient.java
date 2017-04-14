package dataSource;

import java.util.Date;

public class ActivityClient extends Activity{

	Client client;
	
	public ActivityClient() {
	}
	
	public ActivityClient(int idActivity, Employee employee, Date dateOfActivity, String description, Client client) {
		super(idActivity, employee, dateOfActivity, description);
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	

}
