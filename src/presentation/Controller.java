package presentation;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business.ActivityService;
import business.BillService;
import business.ClientService;
import business.EmployeeService;
import business.InputValidation;
import business.LogInService;
import business.TransferMoney;


public class Controller {
	
	Login window;
	AdministratorFrame adminFrame;
	EmployeeFrame employeeFrame;
	EmployeeService eService;
	ClientService cService;
	String selectedData[];
	String nameLogIn;
	String passwordLogIn;
	
	public Controller()
	{
		try {
			window = new Login();
			window.setVisible();
		} catch (Exception e) {
			e.printStackTrace();
		}
			window.addActionListenerButtonLogIn(new ActionListenerButtonLogIn());
	}
	protected class ActionListenerButtonLogIn implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
				LogInService logIn = new LogInService();
				nameLogIn = window.getTextField();
				passwordLogIn = window.getPasswordField();
				boolean exist = logIn.existEmployee(window.getTextField(), window.getPasswordField());
				if(exist)
				{
					employeeFrame = new EmployeeFrame();
					cService = new ClientService();
					employeeFrame.setTable(cService.getClients(),cService.getFields());
					employeeFrame.addListenerTable(new ListenerTableClients());
					employeeFrame.addActionListenerButtonDelete(new ActionListenerButtonDeleteClient() );
					employeeFrame.addActionListenerButtonSearch(new ActionListenerButtonSearchAccount() );
					employeeFrame.addActionListenerButtonTransfer(new ActionListenerButtonTransfer() );
					employeeFrame.addActionListenerButtonBill(new ActionListenerButtonBill() );
					employeeFrame.addActionListenerButtonAddClient(new ActionListenerButtonAddClient());
					employeeFrame.setFrameVisible();
					window.setInvisible();
				}
				
				else
				{
					exist = logIn.existAdmin(window.getTextField(), window.getPasswordField());
					if(exist)
					{
						adminFrame = new AdministratorFrame();
						eService = new EmployeeService();
						adminFrame.setTable(eService.getEmployees(),eService.getFields());
						adminFrame.addListenerTable(new ListenerTable());
						adminFrame.addActionListenerButtonDelete(new ActionListenerButtonDeleteEmployee() );
						adminFrame.addActionListenerButtonAddEmployee(new ActionListenerButtonAddEmployee() );
						adminFrame.addActionListenerButtonAccountReport(new ActionListenerButtonAccountReport() );
						adminFrame.addActionListenerButtonClientReport(new ActionListenerButtonClientReport() );
						adminFrame.setFrameVisible();
						window.setInvisible();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "User/password incorrect!");
						window.resetFields();
					}
				}					
		}		
    }
	
	protected class ListenerTable implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent e) {
		     int selectedRow = adminFrame.getSelectedRow();
		     int selectedColumns = adminFrame.getSelectedColumn();
		     if (selectedColumns>=0 && selectedRow>=0)
		    	 selectedData = adminFrame.getTableData(selectedRow, selectedColumns);
		}
	}
	
	protected class ListenerTableClients implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent e) {
		     int selectedRow = employeeFrame.getSelectedRow();
		     int selectedColumns = employeeFrame.getSelectedColumn();
		     if (selectedColumns>=0 && selectedRow>=0)
		    	 selectedData = employeeFrame.getTableData(selectedRow, selectedColumns);
		}
	}
	
	protected class ActionListenerButtonDeleteEmployee implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			eService.deleteEmployee(selectedData);
			adminFrame.refreshTable(eService.getEmployees(),eService.getFields());
		}
		
	}
	protected class ActionListenerButtonDeleteClient implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			cService.deleteClient(selectedData);
			employeeFrame.refreshTable(cService.getClients(),cService.getFields());
			ActivityService activityService = new ActivityService();
			activityService.addActivityClientWrapper(nameLogIn, passwordLogIn, selectedData[1], "Delete client");
		}
		
	}
	
	protected class ActionListenerButtonSearchAccount implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			Long val = InputValidation.validatePNC(employeeFrame.getTextField());
			if(val!=null)
			{
				String [][]date = cService.getAccounts(val);
				employeeFrame.setListAccounts(date);
				ActivityService activityService = new ActivityService();
				activityService.addActivityClientWrapperPNC(nameLogIn, passwordLogIn, employeeFrame.getTextField(), "Search client's accounts");				
			}
			else
				JOptionPane.showMessageDialog(null, "PNC invalid!");
			employeeFrame.setTextField("");
		}
		
	}
	
	protected class ActionListenerButtonTransfer implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			Integer nr1 = InputValidation.validateNumber(employeeFrame.getTextFieldSourceAccount());
			Integer nr2 = InputValidation.validateNumber(employeeFrame.getTextFieldTargetAccount());
			Integer sum = InputValidation.validateNumber(employeeFrame.getTextFieldValue());
			boolean transfer = false;
			if (nr1 !=null && nr2 !=null && sum!=null)
			{
				TransferMoney t = new TransferMoney();
				transfer = t.transferMoney(nr1, nr2, sum);
			}
			else
				JOptionPane.showMessageDialog(null, "Transerul nu s-a putut efectua");
			
			if (transfer==false)
				JOptionPane.showMessageDialog(null, "Transerul nu s-a putut efectua");
			else 
			{
				JOptionPane.showMessageDialog(null, "Transferul s-a efectuat!");
				ActivityService activityService = new ActivityService();
				activityService.addActivityAccount(window.getTextField(), window.getPasswordField(), nr1, "S-a extras din cont suma: "+sum);
				activityService.addActivityAccount(window.getTextField(), window.getPasswordField(), nr1, "S-a adaugat in cont suma: "+sum);
			}
			
			employeeFrame.setTextFieldSourceAccount("");
			employeeFrame.setTextFieldTargetAccount("");
			employeeFrame.setTextFieldValue("");
		}
	}
	
	protected class ActionListenerButtonBill implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			Integer accountNumber = InputValidation.validateNumber(employeeFrame.getTextField3());
			Integer billNumber = InputValidation.validateNumber(employeeFrame.getTextField4());
			Integer value = InputValidation.validateNumber(employeeFrame.getTextField6());
			
			boolean processBill = false;
			if (accountNumber !=null && billNumber !=null && value!=null)
			{
				BillService bill = new BillService();
				processBill = bill.processBill(accountNumber, billNumber, value, employeeFrame.getTextField7());
				ActivityService activityService = new ActivityService();
				activityService.addActivityAccount(window.getTextField(), window.getPasswordField(), accountNumber, "Procesare factura nr.: "+billNumber);
			}
			else
				JOptionPane.showMessageDialog(null, "Factura nu s-a putut procesa");
			
			if (processBill==false)
				JOptionPane.showMessageDialog(null, "Factura nu s-a putut procesa");
			
			employeeFrame.setTextField3("");
			employeeFrame.setTextField4("");
			employeeFrame.setTextField6("");
			employeeFrame.setTextField7("");
		}
	}
	
	protected class ActionListenerButtonAddClient implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			Long pnc = InputValidation.validatePNC(employeeFrame.getTextField9());
			if (pnc!=null)
			{
				cService.addClient(employeeFrame.getTextField8(), pnc, employeeFrame.getTextField10());
				JOptionPane.showMessageDialog(null, "Client adaugat");
				ActivityService activityService = new ActivityService();
				activityService.addActivityClientWrapperPNC(nameLogIn, passwordLogIn, employeeFrame.getTextField9(), "Client adaugat");
				employeeFrame.refreshTable(cService.getClients(),cService.getFields());
			}
			else
				JOptionPane.showMessageDialog(null, "Personal numerical code incorrect!");
			
			employeeFrame.setTextField10("");
			employeeFrame.setTextField8("");
			employeeFrame.setTextField9("");
		}
	}
	
	
	protected class ActionListenerButtonAddEmployee implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			Long pnc = InputValidation.validatePNC(adminFrame.getTextField1());
			if (pnc!=null)
			{
				eService.addEmployee(adminFrame.getTextField(), pnc, adminFrame.getTextField2(),adminFrame.getPasswordField());
				JOptionPane.showMessageDialog(null, "Employee adaugat");
			}
			else
				JOptionPane.showMessageDialog(null, "Personal numerical code incorrect!");
			
			adminFrame.setTextField1("");
			adminFrame.setTextField2("");
			adminFrame.setTextField("");
			adminFrame.setPasswordField("");
		}
	}
	
	protected class ActionListenerButtonAccountReport implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(InputValidation.validateDate(adminFrame.getTextField3())&&InputValidation.validateDate(adminFrame.getTextField4()))
			{
				ActivityService activityService = new ActivityService();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
				String info = null;
				try {
					info = activityService.getAccountsByDate(dateFormat.parse(adminFrame.getTextField3()), dateFormat.parse(adminFrame.getTextField4()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				adminFrame.setTextArea(info);
			}
			else
				JOptionPane.showMessageDialog(null, "Formatul pentru data este yyyy-mm-dd!");
			//adminFrame.setTextField3("");
			//adminFrame.setTextField4("");
		}
	}
	protected class ActionListenerButtonClientReport implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(InputValidation.validateDate(adminFrame.getTextField3())&&InputValidation.validateDate(adminFrame.getTextField4()))
			{
				ActivityService activityService = new ActivityService();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
				String info = null;
				try {
					info = activityService.getClientsReportByDate(dateFormat.parse(adminFrame.getTextField3()), dateFormat.parse(adminFrame.getTextField4()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				adminFrame.setTextArea(info);
			}
			else
				JOptionPane.showMessageDialog(null, "Formatul pentru data este yyyy-mm-dd!");
			//adminFrame.setTextField3("");
			//adminFrame.setTextField4("");
		}
	}
	
	public static void main(String[] args) {
		Controller controller = new Controller();
	}
}
