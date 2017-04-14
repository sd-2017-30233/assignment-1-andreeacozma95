package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeMapper {
	private Connection connection;
	private PreparedStatement preparedStatement;
	ResultSet rs = null;
	
	public Employee map()
	{
		Employee employee = new Employee();
		
		try {
			rs.beforeFirst();
			while(rs.next())
			{
				 int idEmployee;
				
					idEmployee = rs.getInt("id_employee");
					String employeeName = rs.getString("employee_name");
					String password = rs.getString("password");
			        long pnc = rs.getLong("personal_numerical_code");
			        String address = rs.getString("address");
			         
			        employee.setIdEmployee(idEmployee);
			        employee.setEmployeeName(employeeName);
			        employee.setPassword(password);
			        employee.setPersonalNumericalCode(pnc);
			        employee.setAddress(address);
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    
		return employee;
	}	

	
	public void getEmployee(int idEmployee) 
	{
		String query = "SELECT * FROM Employee WHERE id_employee=?" ;
		
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, idEmployee);
				
				rs = preparedStatement.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	public boolean getEmployeeByName(String nameEmployee,String password) 
	{
		String query = "SELECT * FROM Employee WHERE employee_name=? and password=?" ;
		
			try {
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, nameEmployee);
				preparedStatement.setString(2, password);
				
				rs = preparedStatement.executeQuery();
				
				if(!rs.first())
					return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		return true;
	}
	
	public void create (Employee employee)
	{
		PreparedStatement preparedStatement = null;
		String query = "insert into Employee(id_employee,employee_name,password,personal_numerical_code,address)"
				+ " values(?,?,?,?,?) ON DUPLICATE KEY UPDATE employee_name=?,password=?,personal_numerical_code=?,address=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,employee.getIdEmployee());
				preparedStatement.setString(2, employee.getEmployeeName());
				preparedStatement.setString(3, employee.getPassword());
				preparedStatement.setLong(4, employee.getPersonalNumericalCode());
				preparedStatement.setString(5, employee.getAddress());
				preparedStatement.setString(6, employee.getEmployeeName());
				preparedStatement.setString(7, employee.getPassword());
				preparedStatement.setLong(8, employee.getPersonalNumericalCode());
				preparedStatement.setString(9,  employee.getAddress());
				
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
	
	public void update (Employee employee)
	{
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM Employee WHERE id_employee=?" ;
		ResultSet rs = null;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE);
				preparedStatement.setInt(1,employee.getIdEmployee());
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					rs.updateString(2, employee.getEmployeeName());
					rs.updateString(3, employee.getPassword());
					rs.updateLong(4,employee.getPersonalNumericalCode());
					rs.updateString(5, employee.getAddress());
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
	
	public void delete (Employee employee)
	{
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM Employee WHERE id_employee=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,employee.getIdEmployee());
				
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
	
	public List<Employee> getEmployees() 
	{
		String query = "SELECT * FROM Employee" ;
		List<Employee> employees = new ArrayList();
			try {		
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
								
				rs = preparedStatement.executeQuery();
				
					while(rs.next())
					{
						 int idEmployee;
						 	Employee employee = new Employee();
						 
							idEmployee = rs.getInt("id_employee");
							String employeeName = rs.getString("employee_name");
							String password = rs.getString("password");
					        long pnc = rs.getLong("personal_numerical_code");
					        String address = rs.getString("address");
					         
					        employee.setIdEmployee(idEmployee);
					        employee.setEmployeeName(employeeName);
					        employee.setPassword(password);
					        employee.setPersonalNumericalCode(pnc);
					        employee.setAddress(address);
					        
					        employees.add(employee);
					}
					rs.close();
					
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return employees;
	}
	
	public static void main(String []args){
		EmployeeMapper mapper = new EmployeeMapper();
		System.out.println(mapper.getEmployeeByName("Andreea","andre"));
		
		mapper.getEmployee(2);
		Employee employee = mapper.map();
		System.out.println(employee);
	}
}
