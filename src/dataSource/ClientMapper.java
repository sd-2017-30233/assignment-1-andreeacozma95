package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientMapper {
	private Connection connection;
	private PreparedStatement preparedStatement;
	ResultSet rs = null;
	
	public Client map()
	{
		Client client = new Client();
		
		try {
			rs.beforeFirst();
			while(rs.next())
			{
				int idclient = rs.getInt("id_client");
				String clientName = rs.getString("client_name");
			    long pnc = rs.getLong("personal_numerical_code");
			    String address = rs.getString("address");
			         
			    client.setIdClient(idclient);
			    client.setClientName(clientName);
			    client.setPersonalNumericalCode(pnc);
			    client.setAddress(address);
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    
		return client;
	}	

	
	public void getClient(int idClient) 
	{
		String query = "SELECT * FROM Client WHERE id_client=?" ;
		
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, idClient);
				
				rs = preparedStatement.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	public boolean getClientByName(String nameClient) 
	{
		String query = "SELECT * FROM Client WHERE client_name=?" ;
		
			try {
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, nameClient);
								
				rs = preparedStatement.executeQuery();
				
				if(!rs.first())
					return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		return true;
	}
	
	public boolean getClientByPNC(Long pnc) 
	{
		String query = "SELECT * FROM Client WHERE personal_numerical_code=?" ;
		
			try {
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setLong(1, pnc);
								
				rs = preparedStatement.executeQuery();
				
				if(!rs.first())
					return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		return true;
	}
	
	public void create (Client client)
	{
		PreparedStatement preparedStatement = null;
		String query = "insert into Client(id_client,client_name,personal_numerical_code,address)"
				+ " values(?,?,?,?) ON DUPLICATE KEY UPDATE client_name=?,personal_numerical_code=?,address=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,client.getIdClient());
				preparedStatement.setString(2, client.getClientName());
				preparedStatement.setLong(3, client.getPersonalNumericalCode());
				preparedStatement.setString(4, client.getAddress());
				preparedStatement.setString(5, client.getClientName());
				preparedStatement.setLong(6, client.getPersonalNumericalCode());
				preparedStatement.setString(7,  client.getAddress());
				
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
	
	public void update (Client client)
	{
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM Client WHERE id_client=?" ;
		ResultSet rs = null;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE);
				preparedStatement.setInt(1,client.getIdClient());
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					rs.updateString(2, client.getClientName());
					rs.updateLong(3,client.getPersonalNumericalCode());
					rs.updateString(4, client.getAddress());
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
	
	public void delete (Client client)
	{
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM Client WHERE id_client=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,client.getIdClient());
				
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
	
	public List<Client> getClients() 
	{
		String query = "SELECT * FROM client" ;
		List<Client> clients = new ArrayList();
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
								
				rs = preparedStatement.executeQuery();
				
					while(rs.next())
					{
						 int idClient;
						 	Client client = new Client();
						 
							idClient = rs.getInt("id_client");
							String clientName = rs.getString("client_name");
					        long pnc = rs.getLong("personal_numerical_code");
					        String address = rs.getString("address");
					         
					        client.setIdClient(idClient);
					        client.setClientName(clientName);
					        client.setPersonalNumericalCode(pnc);
					        client.setAddress(address);
					        
					        clients.add(client);
					}
					rs.close();
					
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return clients;
	}
	
	public static void main(String []args){
		ClientMapper mapper = new ClientMapper();
		System.out.println(mapper.getClientByName("Andreea"));
		
		mapper.getClient(2);
		Client client = mapper.map();
		System.out.println(client);
	}
	
	
}
