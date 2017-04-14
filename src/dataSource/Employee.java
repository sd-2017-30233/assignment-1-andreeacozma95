package dataSource;

public class Employee {
	
	public Employee(String employeeName, String password, long personalNumericalCode, String address) {
		super();
		this.employeeName = employeeName;
		this.password = password;
		this.personalNumericalCode = personalNumericalCode;
		this.address = address;
	}

	int idEmployee;
	String employeeName;
	String password;
	long personalNumericalCode;
	String address;
	
	public Employee(int idEmployee, String employeeName, String password, long personalNumericalCode, String address) {
		super();
		this.idEmployee = idEmployee;
		this.employeeName = employeeName;
		this.password = password;
		this.personalNumericalCode = personalNumericalCode;
		this.address = address;
	}
	
	public Employee() {
	}
	

	public int getIdEmployee() {
		return idEmployee;
	}


	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public long getPersonalNumericalCode() {
		return personalNumericalCode;
	}


	public void setPersonalNumericalCode(long personalNumericalCode) {
		this.personalNumericalCode = personalNumericalCode;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [idEmployee=" + idEmployee + ", employeeName=" + employeeName + ", password=" + password
				+ ", personalNumericalCode=" + personalNumericalCode + ", address=" + address + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
