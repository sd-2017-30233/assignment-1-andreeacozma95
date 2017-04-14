package dataSource;

public class Client {
	
	int idClient;
	String clientName;
	long personalNumericalCode;
	String address;
	
	public Client() {
		super();
	}

	public Client(String clientName, long personalNumericalCode, String address) {
		super();
		this.clientName = clientName;
		this.personalNumericalCode = personalNumericalCode;
		this.address = address;
	}

	public Client(int idClient, String clientName, long personalNumericalCode, String address) {
		super();
		this.idClient = idClient;
		this.clientName = clientName;
		this.personalNumericalCode = personalNumericalCode;
		this.address = address;
	}

	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
}
