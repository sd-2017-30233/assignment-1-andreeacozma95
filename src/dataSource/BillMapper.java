package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BillMapper {
	private Connection connection;
	private PreparedStatement preparedStatement;
	ResultSet rs = null;
	
	public Bill map()
	{
		Bill bill = new Bill();
		
		try {
			rs.beforeFirst();
			while(rs.next())
			{
				int idBill = rs.getInt("id_bill");
				Float sum = rs.getFloat("sum");
				String description = rs.getString("description");
			    int idAccount = rs.getInt("id_account");
			        
			    bill.setIdBill(idBill);
			    bill.setSum(sum);
			    bill.setDescription(description);
			     
			    AccountMapper accountMapper = new AccountMapper();
				accountMapper.getAccount(idAccount);
				Account account = accountMapper.map();
				bill.setAccount(account);
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    
		return bill;
	}	

	
	public void getBill(int idBill) 
	{
		String query = "SELECT * FROM Bill WHERE id_bill=?" ;
		
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, idBill);
				
				rs = preparedStatement.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void create (Bill bill)
	{
		PreparedStatement preparedStatement = null;
		String query = "insert into Bill(id_bill,sum,description,id_account)"
				+ " values(?,?,?,?) ON DUPLICATE KEY UPDATE sum=?,description=?,id_account=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,bill.getIdBill());
				preparedStatement.setFloat(2, bill.getSum());
				preparedStatement.setString(3, bill.getDescription());
				preparedStatement.setInt(4, bill.getAccount().getIdAccount());
				
				preparedStatement.setFloat(5, bill.getSum());
				preparedStatement.setString(6, bill.getDescription());
				preparedStatement.setInt(7, bill.getAccount().getIdAccount());
				
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
	
	public void update (Bill bill)
	{
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM Bill WHERE id_bill=?" ;
		ResultSet rs = null;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE);
				preparedStatement.setInt(1,bill.getIdBill());
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					rs.updateFloat(2, bill.getSum());
					rs.updateString(3, bill.getDescription());
					rs.updateInt(4,bill.getAccount().getIdAccount());
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
	
	public void delete (Bill bill)
	{
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM Bill WHERE id_bill=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,bill.getIdBill());
				
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
	
	public List<Bill> getBills() 
	{
		String query = "SELECT * FROM Bill" ;
		List<Bill> bills = new ArrayList();
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
								
				rs = preparedStatement.executeQuery();
				
					while(rs.next())
					{

						Bill bill = null;
						
						int idBill = rs.getInt("id_bill");
						Float sum = rs.getFloat("sum");
						String description = rs.getString("description");
					    int idAccount = rs.getInt("id_account");
					        
					    bill.setIdBill(idBill);
					    bill.setSum(sum);
					    bill.setDescription(description);
					     
					    AccountMapper accountMapper = new AccountMapper();
						accountMapper.getAccount(idAccount);
						Account account = accountMapper.map();
						bill.setAccount(account);
						
						bills.add(bill);
					}		        
					rs.close();
					
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return bills;
	}

}
