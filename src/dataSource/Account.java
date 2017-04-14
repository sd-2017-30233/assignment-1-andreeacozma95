package dataSource;

import java.util.Date;

public class Account {
	
	public Account() {
		super();
	}

	int idAccount;
	Client client;
	float amount;
	Date dateOfCreation;
	
	public Account(int idAccount, Client client, float amount, Date dateOfCreation) {
		super();
		this.idAccount = idAccount;
		this.client = client;
		this.amount = amount;
		this.dateOfCreation = dateOfCreation;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public void deposit(float value){
		this.amount = this.amount +value;
	}
	
	public boolean withdraw(float value){
		if(this.amount - value>0)
		{
			this.amount = this.amount - value;
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "Account [idAccount=" + idAccount + ", client=" + client + ", amount=" + amount + ", dateOfCreation="
				+ dateOfCreation + "]";
	}
	
}
