package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ActivityAccountMapper {
	private Connection connection;
	private PreparedStatement preparedStatement;
	ResultSet rs = null;
	
	public ActivityAccount map()
	{
		ActivityAccount activityAccount = new ActivityAccount();
		
		try {
			rs.beforeFirst();
			while(rs.next())
			{
				int idActivityAccount = rs.getInt("id_activity");
				int idAccount = rs.getInt("id_account");
				int idEmployee = rs.getInt("id_employee");
			    Date date = new java.util.Date(rs.getDate("date_of_activity").getTime());
			    String description = rs.getString("description");
			    
			    activityAccount.setIdActivity(idActivityAccount);	    
			    
			    EmployeeMapper employeeMapper = new EmployeeMapper();
			    employeeMapper.getEmployee(idEmployee);
			    Employee employee = employeeMapper.map();
			    activityAccount.setEmployee(employee);
			    
			    AccountMapper accountMapper = new AccountMapper();
			    accountMapper.getAccount(idAccount);
			    Account account = accountMapper.map();
			    activityAccount.setAccount(account);
			    
			    activityAccount.setDateOfActivity(date);
			    activityAccount.setDescription(description);
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    
		return activityAccount;
	}	

	
	public void getActivityAccount(int idActivityAccount) 
	{
		String query = "SELECT * FROM ActivityAccount WHERE id_activity=?" ;
		
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, idActivityAccount);
				
				rs = preparedStatement.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void getActivityAccountByDate(Date date) 
	{
		String query = "SELECT * FROM ActivityAccount WHERE date_of_activity=?" ;
		
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setDate(1, new java.sql.Date(date.getTime()));
				
				rs = preparedStatement.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void create (ActivityAccount activityAccount)
	{
		
		PreparedStatement preparedStatement = null;
		String query = "insert into activityAccount(id_activity,id_account,id_employee,date_of_activity,description)"
				+ " values(?,?,?,?,?) ON DUPLICATE KEY UPDATE id_account=?,id_employee=?,date_of_activity=?,description=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,activityAccount.getIdActivity());
				preparedStatement.setInt(2, activityAccount.getAccount().getIdAccount());
				preparedStatement.setInt(3, activityAccount.getEmployee().getIdEmployee());
				preparedStatement.setDate(4, new java.sql.Date(activityAccount.getDateOfActivity().getTime()));
				preparedStatement.setString(5, activityAccount.getDescription());
				
				preparedStatement.setInt(6, activityAccount.getAccount().getIdAccount());
				preparedStatement.setInt(7, activityAccount.getEmployee().getIdEmployee());
				preparedStatement.setDate(8, new java.sql.Date(activityAccount.getDateOfActivity().getTime()));
				preparedStatement.setString(9, activityAccount.getDescription());
				
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
	
	public void update (ActivityAccount activityAccount)
	{
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM activityAccount WHERE id_activity=?" ;
		ResultSet rs = null;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE);
				preparedStatement.setInt(1,activityAccount.getIdActivity());
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					rs.updateInt(2,  activityAccount.getAccount().getIdAccount());
					rs.updateInt(3, activityAccount.getEmployee().getIdEmployee());
					rs.updateDate(4,new java.sql.Date(activityAccount.getDateOfActivity().getTime()));
					rs.updateString(5, activityAccount.getDescription());
					
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
	
	public void delete (ActivityAccount activityAccount)
	{
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM ActivityAccount WHERE id_activity=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,activityAccount.getIdActivity());
				
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
	
	public List<ActivityAccount> getactivityAccounts() 
	{
		String query = "SELECT * FROM ActivityAccount" ;
		List<ActivityAccount> activityAccounts = new ArrayList();
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
								
				rs = preparedStatement.executeQuery();
				
					while(rs.next())
					{
						ActivityAccount activityAccount = new ActivityAccount();
				
						int idActivityAccount = rs.getInt("id_activity");
						int idAccount = rs.getInt("id_account");
						int idEmployee = rs.getInt("id_employee");
					    Date date = rs.getDate("date_of_activity");
					    String description = rs.getString("description");
					    
					    activityAccount.setIdActivity(idActivityAccount);	    
					    
					    EmployeeMapper employeeMapper = new EmployeeMapper();
					    employeeMapper.getEmployee(idEmployee);
					    Employee employee = employeeMapper.map();
					    activityAccount.setEmployee(employee);
					    
					    AccountMapper accountMapper = new AccountMapper();
					    accountMapper.getAccount(idAccount);
					    Account account = accountMapper.map();
					    activityAccount.setAccount(account);
					    
					    activityAccount.setDateOfActivity(date);
					    activityAccount.setDescription(description);
					
					    activityAccounts.add(activityAccount);
					}
					rs.close();
					
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return activityAccounts;
	}
	
	public static void main(String []args){
		ActivityAccountMapper mapper = new ActivityAccountMapper();
		
		mapper.getActivityAccount(2);
		ActivityAccount activityAccount = mapper.map();
		System.out.println(activityAccount);
		
		List<ActivityAccount> activityAccounts = mapper.getactivityAccounts();
		for (ActivityAccount activityAccount2 : activityAccounts) {
			System.out.println(activityAccount2);
		}
	}
	
}
