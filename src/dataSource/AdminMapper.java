package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminMapper {
	private Connection connection;
	private PreparedStatement preparedStatement;
	ResultSet rs = null;
	   
	public Admin map()
	{
		Admin admin = new Admin();
		
		try {
			while(rs.next())
			{
				 int idAdmin;
				
					idAdmin = rs.getInt("id_admin");
					String adminName = rs.getString("admin_name");
					String password = rs.getString("password");
			        long pnc = rs.getLong("personal_numerical_code");
			        String address = rs.getString("address");
			         
			        admin.setIdAdmin(idAdmin);
			        admin.setAdminName(adminName);
			        admin.setPassword(password);
			        admin.setPersonalNumericalCode(pnc);
			        admin.setAddress(address);
			}
			rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    
		return admin;
	}	

	
	public void getAdmin(int idAdmin) 
	{
		String query = "SELECT * FROM Admin WHERE id_admin=?" ;
				
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, idAdmin);
				
				rs = preparedStatement.executeQuery();
						
				//preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public boolean getadminByName(String nameAdmin,String password) 
	{
		String query = "SELECT * FROM admin WHERE admin_name=? and password=?" ;
				
			try {
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, nameAdmin);
				preparedStatement.setString(2, password);
				
				rs = preparedStatement.executeQuery();
				
				//preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void create (Admin admin)
	{
		PreparedStatement preparedStatement = null;
		String query = "insert into Admin(id_admin,admin_name,password,personal_numerical_code,address)"
				+ " values(?,?,?,?,?) ON DUPLICATE KEY UPDATE admin_name=?,password=?,personal_numerical_code=?,address=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,admin.getIdAdmin());
				preparedStatement.setString(2, admin.getAdminName());
				preparedStatement.setString(3, admin.getPassword());
				preparedStatement.setLong(4, admin.getPersonalNumericalCode());
				preparedStatement.setString(5, admin.getAddress());
				preparedStatement.setString(6, admin.getAdminName());
				preparedStatement.setString(7, admin.getPassword());
				preparedStatement.setLong(8, admin.getPersonalNumericalCode());
				preparedStatement.setString(9,  admin.getAddress());
				
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
	
	public void update (Admin admin)
	{
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM Admin WHERE id_admin=?" ;
		ResultSet rs = null;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE);
				preparedStatement.setInt(1,admin.getIdAdmin());
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					rs.updateString(2, admin.getAdminName());
					rs.updateString(3, admin.getPassword());
					rs.updateLong(4,admin.getPersonalNumericalCode());
					rs.updateString(5, admin.getAddress());
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
	
	public void delete (Admin admin)
	{
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM Admin WHERE id_admin=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,admin.getIdAdmin());
				
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
	
	public static void main(String []args){
		AdminMapper mapper = new AdminMapper();
		System.out.println(mapper.getadminByName("Andreea","andre"));
		
		mapper.getAdmin(2);
		Admin admin = mapper.map();
		System.out.println(admin);
	}
}
