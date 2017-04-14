package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AccountMapper {
	private Connection connection;
	private PreparedStatement preparedStatement;
	ResultSet rs = null;
	
	public Account map()
	{
		Account account = new Account();
		
		try {
			rs.beforeFirst();
			while(rs.next())
			{
				int idaccount = rs.getInt("id_account");
				int idClient = rs.getInt("id_client");
				Float amount = rs.getFloat("amount");
			    Date date = new java.util.Date(rs.getDate("date_of_creation").getTime());
			         
			    account.setIdAccount(idaccount);
			    
			    ClientMapper clientMapper = new ClientMapper();
			    clientMapper.getClient(idClient);
			    Client client = clientMapper.map();
			    account.setClient(client);
			    account.setAmount(amount);
			    account.setDateOfCreation(date);
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    
		return account;
	}	

	
	public boolean getAccount(int idAccount) 
	{
		String query = "SELECT * FROM Account WHERE id_account=?" ;
		
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, idAccount);
				
				rs = preparedStatement.executeQuery();
				if(!rs.first())
					return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return true;
	}
	
	public void create (Account account)
	{
		PreparedStatement preparedStatement = null;
		String query = "insert into account(id_account,id_client,amount,date_of_creation)"
				+ " values(?,?,?,?) ON DUPLICATE KEY UPDATE id_client=?,amount=?,date_of_creation=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,account.getIdAccount());
				preparedStatement.setInt(2, account.getClient().getIdClient());
				preparedStatement.setFloat(3, account.getAmount());
				preparedStatement.setDate(4, new java.sql.Date(account.getDateOfCreation().getTime()));
				preparedStatement.setInt(5, account.getClient().getIdClient());
				preparedStatement.setFloat(6, account.getAmount());
				preparedStatement.setDate(7, new java.sql.Date(account.getDateOfCreation().getTime()));
				
				preparedStatement.executeUpdate();
				
		    }catch (SQLException e) {
		       System.out.println(e);
		    } finally {
		        if (preparedStatement != null)
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
		    }
	}
	
	public void update (Account account)
	{
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM account WHERE id_account=?" ;
		ResultSet rs = null;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE);
				preparedStatement.setInt(1,account.getIdAccount());
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					rs.updateInt(2, account.getClient().getIdClient());
					rs.updateFloat(3, account.getAmount());
					rs.updateDate(4,new java.sql.Date(account.getDateOfCreation().getTime()));
				
					rs.updateRow();
				}
				
				rs.close();
		    }catch (SQLException e) {
		       System.out.println(e);
		    } finally {
		        if (preparedStatement != null)
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
		    }
	}
	
	
	public List<Account> getAccountsByClient(Client client)
	{
		String query = "SELECT * FROM Account WHERE id_client=?" ;
		List<Account> accounts = new ArrayList();
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				
				preparedStatement = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE);
				preparedStatement.setInt(1,client.getIdClient());
			
				rs = preparedStatement.executeQuery();
				
					while(rs.next())
					{
						Account account = new Account();
				
					    int idaccount = rs.getInt("id_account");
						int idClient = rs.getInt("id_client");
						Float amount = rs.getFloat("amount");
					    Date date = new java.util.Date(rs.getDate("date_of_creation").getTime());
					         
					    account.setIdAccount(idaccount);
					    
					    ClientMapper clientMapper = new ClientMapper();
					    clientMapper.getClient(idClient);
					    Client cl = clientMapper.map();
					    account.setClient(cl);
					    account.setAmount(amount);
					    account.setDateOfCreation(date);
					
					    accounts.add(account);
					}
					rs.close();
					
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return accounts;
	}
	
	
	public void delete (Account account)
	{
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM Account WHERE id_account=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,account.getIdAccount());
				
				preparedStatement.executeUpdate();
				
			
		    }catch (SQLException e) {
		       System.out.println(e);
		    } finally {
		        if (preparedStatement != null)
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
		    }
	}
	
	
	public List<Account> getAccounts() 
	{
		String query = "SELECT * FROM Account" ;
		List<Account> accounts = new ArrayList();
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
								
				rs = preparedStatement.executeQuery();
				
					while(rs.next())
					{
						Account account = new Account();
				
					    int idaccount = rs.getInt("id_account");
						int idClient = rs.getInt("id_client");
						Float amount = rs.getFloat("amount");
					    Date date = new java.util.Date(rs.getDate("date_of_creation").getTime());
					         
					    account.setIdAccount(idaccount);
					    
					    ClientMapper clientMapper = new ClientMapper();
					    clientMapper.getClient(idClient);
					    Client client = clientMapper.map();
					    account.setClient(client);
					    account.setAmount(amount);
					    account.setDateOfCreation(date);
					
					    accounts.add(account);
					}
					rs.close();
					
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return accounts;
	}
	
	public static void main(String []args){
		AccountMapper mapper = new AccountMapper();
		
		mapper.getAccount(2);
		Account account = mapper.map();
		System.out.println(account);
		
		List<Account> accounts = mapper.getAccounts();
		for (Account account2 : accounts) {
			System.out.println(account2);
		}
	}
	
}
