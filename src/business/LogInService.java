package business;

import dataSource.*;

public class LogInService {

	public boolean existEmployee(String name, String password){
		EmployeeMapper mapper = new EmployeeMapper();
		return mapper.getEmployeeByName(name, password);
	}
	
	public boolean existAdmin(String name, String password){
		AdminMapper mapper = new AdminMapper();
		return mapper.getadminByName(name, password);
	}
}
