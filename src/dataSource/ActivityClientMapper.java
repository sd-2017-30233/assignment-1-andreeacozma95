package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ActivityClientMapper {
	private Connection connection;
	private PreparedStatement preparedStatement;
	ResultSet rs = null;
	
	public ActivityClient map()
	{
		ActivityClient activityClient = new ActivityClient();
		
		try {
			rs.beforeFirst();
			while(rs.next())
			{
				int idActivityClient = rs.getInt("id_activity");
				int idClient = rs.getInt("id_client");
				int idEmployee = rs.getInt("id_employee");
			    Date date = new java.util.Date(rs.getDate("date_of_activity").getTime());
			    String description = rs.getString("description");
			    
			    activityClient.setIdActivity(idActivityClient);	    
			    
			    EmployeeMapper employeeMapper = new EmployeeMapper();
			    employeeMapper.getEmployee(idEmployee);
			    Employee employee = employeeMapper.map();
			    activityClient.setEmployee(employee);
			    
			    ClientMapper clientMapper = new ClientMapper();
			    clientMapper.getClient(idClient);
			    Client client = clientMapper.map();
			    activityClient.setClient(client);
			    
			    activityClient.setDateOfActivity(date);
			    activityClient.setDescription(description);
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    
		return activityClient;
	}	

	
	public void getActivityClient(int idActivityClient) 
	{
		String query = "SELECT * FROM ActivityClient WHERE id_activity=?" ;
		
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, idActivityClient);
				
				rs = preparedStatement.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void create (ActivityClient activityClient)
	{
		
		PreparedStatement preparedStatement = null;
		String query = "insert into activityClient(id_activity,id_employee,id_client,date_of_activity,description)"
				+ " values(?,?,?,?,?) ON DUPLICATE KEY UPDATE id_employee=?,id_client=?,date_of_activity=?,description=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,activityClient.getIdActivity());
				preparedStatement.setInt(2, activityClient.getEmployee().getIdEmployee());
				preparedStatement.setInt(3, activityClient.getClient().getIdClient());
				preparedStatement.setDate(4, new java.sql.Date(activityClient.getDateOfActivity().getTime()));
				preparedStatement.setString(5, activityClient.getDescription());
			
				preparedStatement.setInt(6, activityClient.getEmployee().getIdEmployee());
				preparedStatement.setInt(7, activityClient.getClient().getIdClient());
				preparedStatement.setDate(8, new java.sql.Date(activityClient.getDateOfActivity().getTime()));
				preparedStatement.setString(9, activityClient.getDescription());
				
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
	
	public void update (ActivityClient activityClient)
	{
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM activityAccount WHERE id_activity=?" ;
		ResultSet rs = null;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE);
				preparedStatement.setInt(1,activityClient.getIdActivity());
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					rs.updateInt(3, activityClient.getEmployee().getIdEmployee());
					rs.updateInt(2,  activityClient.getClient().getIdClient());
					rs.updateDate(4,new java.sql.Date(activityClient.getDateOfActivity().getTime()));
					rs.updateString(5, activityClient.getDescription());
					
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
	
	public void delete (ActivityClient activityClient)
	{
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM ActivityClient WHERE id_activity=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,activityClient.getIdActivity());
				
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
	
	public List<ActivityClient> getActivityClients() 
	{
		String query = "SELECT * FROM ActivityClient" ;
		List<ActivityClient> activityClients = new ArrayList();
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
								
				rs = preparedStatement.executeQuery();
				
					while(rs.next())
					{
						ActivityClient activityClient = new ActivityClient();
				
						int idActivityClient = rs.getInt("id_activity");
						int idClient = rs.getInt("id_client");
						int idEmployee = rs.getInt("id_employee");
					    Date date = new java.util.Date(rs.getDate("date_of_activity").getTime());
					    String description = rs.getString("description");
					    
					    activityClient.setIdActivity(idActivityClient);	    
					    
					    EmployeeMapper employeeMapper = new EmployeeMapper();
					    employeeMapper.getEmployee(idEmployee);
					    Employee employee = employeeMapper.map();
					    activityClient.setEmployee(employee);
					    
					    ClientMapper clientMapper = new ClientMapper();
					    clientMapper.getClient(idClient);
					    Client client = clientMapper.map();
					    activityClient.setClient(client);
					    
					    activityClient.setDateOfActivity(date);
					    activityClient.setDescription(description);
					
					    activityClients.add(activityClient);
					}
					rs.close();
					
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return activityClients;
	}
	
}
