package business;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataSource.Account;
import dataSource.AccountMapper;
import dataSource.ActivityAccount;
import dataSource.ActivityAccountMapper;
import dataSource.ActivityClient;
import dataSource.ActivityClientMapper;
import dataSource.Client;
import dataSource.ClientMapper;
import dataSource.Employee;
import dataSource.EmployeeMapper;

public class ActivityService {

	public void addActivityClient(String employeeName,String employeePassword,Client client,String description){
		EmployeeMapper employeeMapper = new EmployeeMapper();
		employeeMapper.getEmployeeByName(employeeName, employeePassword);
		Employee employee = employeeMapper.map();
		
		ActivityClientMapper activityMapper = new ActivityClientMapper();
		List<ActivityClient> activities = activityMapper.getActivityClients();
		int max = Integer.MIN_VALUE;
		for (ActivityClient activityClient : activities) {
			if (activityClient.getIdActivity()>max)
				max = activityClient.getIdActivity();
		}
		if(activities.size()==0)
			max=0;
		ActivityClient activity = new ActivityClient(max+1,employee,new Date(),description,client);
		activityMapper.create(activity);
	}
	
	public void addActivityAccount(String employeeName,String employeePassword,int idAccount,String description){
		EmployeeMapper employeeMapper = new EmployeeMapper();
		employeeMapper.getEmployeeByName(employeeName, employeePassword);
		Employee employee = employeeMapper.map();
		
		Account account = null;
		AccountMapper accountMapper = new AccountMapper();
		if (accountMapper.getAccount(idAccount))
			account = accountMapper.map();
		
		ActivityAccountMapper activityMapper = new ActivityAccountMapper();
		List<ActivityAccount> activities = activityMapper.getactivityAccounts();
		int max = Integer.MIN_VALUE;
		for (ActivityAccount activityAccount : activities) {
			if (activityAccount.getIdActivity()>max)
				max = activityAccount.getIdActivity();
		}
		if(activities.size()==0)
			max=0;
		ActivityAccount activity = new ActivityAccount(max+1,employee,new Date(),description,account);
		activityMapper.create(activity);
	}
	
	public String getAccountsByDate(Date beginDate,Date endDate)
	{
		ActivityAccountMapper activityMapper = new ActivityAccountMapper();
		List<ActivityAccount> activities = activityMapper.getactivityAccounts();
		StringBuilder sb = new StringBuilder();
		for (ActivityAccount activityAccount : activities) 
		{	System.out.println(activityAccount.getDateOfActivity());
			if(activityAccount.getDateOfActivity().after(beginDate)&&activityAccount.getDateOfActivity().after(endDate))
			{
				sb.append("Account no: "+activityAccount.getAccount().getIdAccount()+", Date: "+activityAccount.getDateOfActivity());
				sb.append("\n\t"+activityAccount.getDescription()+", Employee name: "+activityAccount.getEmployee().getEmployeeName());
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public String getClientsReportByDate(Date beginDate,Date endDate)
	{
		ActivityClientMapper activityMapper = new ActivityClientMapper();
		List<ActivityClient> activities = activityMapper.getActivityClients();
		StringBuilder sb = new StringBuilder();
		for (ActivityClient activityClient : activities) 
		{	System.out.println(activityClient.getDateOfActivity());
			if(activityClient.getDateOfActivity().after(beginDate)&&activityClient.getDateOfActivity().after(endDate))
			{
				sb.append("Client name: "+activityClient.getClient().getClientName()+", Date: "+activityClient.getDateOfActivity());
				sb.append("\n\t"+activityClient.getDescription()+", Employee name: "+activityClient.getEmployee().getEmployeeName());
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public void addActivityClientWrapper(String employeeName,String employeePassword,String nume,String description){
		ClientMapper clientMapper = new ClientMapper();
		clientMapper.getClientByName(nume);
		Client client = clientMapper.map();
		addActivityClient(employeeName, employeePassword, client, description);
	}
	
	public void addActivityClientWrapperPNC(String employeeName,String employeePassword,String pnc,String description){
		ClientMapper clientMapper = new ClientMapper();
		Client client=null;
		if(clientMapper.getClientByPNC(Long.parseLong(pnc)))
			client = clientMapper.map();
		addActivityClient(employeeName, employeePassword, client, description);
	}
}
