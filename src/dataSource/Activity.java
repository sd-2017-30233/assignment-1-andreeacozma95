package dataSource;

import java.util.Date;

public abstract class Activity {
	public Activity() {
	}
	int idActivity;
	Employee employee;
	Date dateOfActivity;
	String description;
	
	public Activity(int idActivity, Employee employee, Date dateOfActivity, String description) {
		super();
		this.idActivity = idActivity;
		this.employee = employee;
		this.dateOfActivity = dateOfActivity;
		this.description = description;
	}
	
	public int getIdActivity() {
		return idActivity;
	}
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Date getDateOfActivity() {
		return dateOfActivity;
	}
	public void setDateOfActivity(Date datOfActivity) {
		this.dateOfActivity = datOfActivity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
