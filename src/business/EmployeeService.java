package business;

import java.lang.reflect.Field;
import java.util.List;

import dataSource.Client;
import dataSource.ClientMapper;
import dataSource.Employee;
import dataSource.EmployeeMapper;

public class EmployeeService {

	public String[][] getEmployees()
	{
		Field []f = Employee.class.getDeclaredFields();
		String [] coloane=new String[f.length-1];
		
		EmployeeMapper mapper = new EmployeeMapper();
		List<Employee> employees = mapper.getEmployees();
		String [][] date=new String[employees.size()][f.length-1];
		
		int i=0;
		for ( Field field : f )
		{
			if (!field.getName().equals("idEmployee"))
			{
				coloane[i]=field.getName();
				i++;
			}			
		}
	
		i=0;
		for(int k=0;k<employees.size();k++)
		{
			i=0;
			for ( Field field : f )	
			{
				if (!field.getName().equals("idEmployee"))
				{
					try {
						field.setAccessible(true);
						date[k][i]=field.get(employees.get(k)).toString();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					i++;
				}
			}
		}
			
		return date;
	}
	
	public String[] getFields()
	{
		Field []f = Employee.class.getDeclaredFields();
		String [] coloane=new String[f.length-1];
		
		int i=0;
		for ( Field field : f )
		{
			if (!field.getName().equals("idEmployee"))
			{
				coloane[i]=field.getName();
				i++;
			}			
		}		
		return coloane;
	}
	
	public void deleteEmployee(String []dataEmployee){
		EmployeeMapper employeeMapper = new EmployeeMapper();
		Employee employee=null;
		if(employeeMapper.getEmployeeByName(dataEmployee[0], dataEmployee[1]))
			employee = employeeMapper.map();
		
		employeeMapper.delete(employee);
	}
	
	public void addEmployee(String name, Long pnc, String address,String password){
		EmployeeMapper employeeMapper = new EmployeeMapper();
		
		List<Employee> employees = employeeMapper.getEmployees();
		int max = Integer.MIN_VALUE;
		for (Employee employee : employees) {
			if (employee.getIdEmployee()>max)
				max = employee.getIdEmployee();
		}
		
		employeeMapper.create(new Employee(max+1,name,password,pnc,address));
	
	}
	
	public Employee getEmployee(String nume,String password){
		EmployeeMapper employeeMapper = new EmployeeMapper();
		Employee employee= null;
		if(employeeMapper.getEmployeeByName(nume, password))
			employeeMapper.map();
		
		return employee;
	}
}
