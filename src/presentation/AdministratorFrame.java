package presentation;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class AdministratorFrame {

	private JFrame frame;
	private JTable table;
	JPanel panel;
	JPanel panel2;
	JPanel panel3;
	JScrollPane scroll;
	JButton btnDeleteSelectedEmploy;
	private JLabel lblName;
	private JTextField textField;
	private JLabel lblPersonalNumericalCode;
	private JTextField textField_1;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JTextField textField_2;
	JButton btnAddEmployee;
	private JTextField textField_3;
	JButton btnAccountReport;
	JButton btnClientReport;
	JTextArea textArea;
	private JLabel lblBeginDate;
	private JLabel lblEndDate;
	private JTextField textField_4;
	
	public AdministratorFrame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 365);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Employes", null, panel, null);
		panel.setLayout(new FlowLayout());
		
		btnDeleteSelectedEmploy = new JButton("Delete selected employ");
		btnDeleteSelectedEmploy.setBounds(10, 151, 156, 23);
		panel.add(btnDeleteSelectedEmploy);
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		panel2 = new JPanel();
		tabbedPane.addTab("Add Employee", null, panel2, null);
		panel2.setLayout(null);
		
		lblName = new JLabel("Name");
		lblName.setBounds(120, 11, 46, 14);
		panel2.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 156, 20);
		panel2.add(textField);
		textField.setColumns(10);
		
		lblPersonalNumericalCode = new JLabel("Personal numerical code");
		lblPersonalNumericalCode.setBounds(10, 67, 156, 14);
		panel2.add(lblPersonalNumericalCode);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 83, 156, 20);
		panel2.add(textField_1);
		textField_1.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(77, 108, 89, 14);
		panel2.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 133, 156, 20);
		panel2.add(passwordField);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(96, 164, 70, 14);
		panel2.add(lblAddress);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 189, 156, 20);
		panel2.add(textField_2);
		textField_2.setColumns(10);
		
		btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.setBounds(215, 188, 132, 23);
		panel2.add(btnAddEmployee);
		panel.setLayout(new FlowLayout());
		
		panel3 = new JPanel();
		tabbedPane.addTab("Generate report", null, panel3, null);
		panel3.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setBounds(122, 8, 86, 20);
		panel3.add(textField_3);
		textField_3.setColumns(10);
		
		btnAccountReport = new JButton("Account report");
		
		btnAccountReport.setBounds(255, 7, 142, 23);
		panel3.add(btnAccountReport);
		
		btnClientReport = new JButton("Client report");
		btnClientReport.setBounds(255, 41, 142, 23);
		panel3.add(btnClientReport);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 83, 390, 126);
		panel3.add(textArea);
		
		lblBeginDate = new JLabel("Begin Date");
		lblBeginDate.setBounds(10, 11, 80, 14);
		panel3.add(lblBeginDate);
		
		lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(20, 45, 60, 14);
		panel3.add(lblEndDate);
		
		textField_4 = new JTextField();
		textField_4.setBounds(122, 42, 86, 20);
		panel3.add(textField_4);
		textField_4.setColumns(10);
		
	}
	
	public void setFrameVisible()
	{
		frame.setVisible(true);
	}
	
	public void setTable(String [][] date,String coloane[])
	{		
		DefaultTableModel model = new DefaultTableModel(date,coloane);		
		table =new JTable(model);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );

		for(int j=0; j < coloane.length; j++){
            table.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder title = BorderFactory.createTitledBorder(
                blackline, "Employees");
		title.setTitleJustification(TitledBorder.CENTER);

		panel.setBorder(title);
		scroll =new JScrollPane(table);
		panel.add(scroll);
	}


	public void addListenerTable(ListSelectionListener a){
		table.getSelectionModel().addListSelectionListener(a);
	}
	
	public int getSelectedRow(){
		return table.getSelectedRow();
	}
	
	public void refreshTable(String [][] date,String coloane[]){
		
		DefaultTableModel model = new DefaultTableModel(date,coloane);
	    table.setModel(model);
	    
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );

		for(int j=0; j < coloane.length; j++){
            table.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
		
	}
	
	public int getSelectedColumn(){
		return table.getSelectedColumn();
	}
	
	public String[] getTableData(int row, int column){
		String[] data=new String[table.getColumnCount()];
		
		for (int i=0;i<2;i++)		
			data[i] = table.getValueAt(row, i).toString();
				
		return data;
	}
	public void addActionListenerButtonDelete(ActionListener a)
	{
		btnDeleteSelectedEmploy.addActionListener(a);
	}
	public String getTextField() {
		return textField.getText();
	}

	public void setTextField(String textField) {
		this.textField.setText(textField); 
	}
	public String getTextField1() {
		return textField_1.getText();
	}

	public void setTextField1(String textField) {
		this.textField_1.setText(textField); 
	}
	public String getTextArea() {
		return textArea.getText();
	}
	
	public void setTextArea(String text) {
		this.textArea.setText(text);
	}
	public String getTextField2() {
		return textField_2.getText();
	}

	public void setTextField2(String textField) {
		this.textField_2.setText(textField); 
	}
	
	public String getTextField3() {
		return textField_3.getText();
	}

	public void setTextField3(String textField) {
		this.textField_3.setText(textField); 
	}
	
	public String getTextField4() {
		return textField_4.getText();
	}

	public void setTextField4(String textField) {
		this.textField_4.setText(textField); 
	}
	
	public void setPasswordField(String text){
		passwordField.setText(text);
	}
	public String getPasswordField(){
		return passwordField.getText();
	}
	
	public void addActionListenerButtonAddEmployee(ActionListener a)
	{
		btnAddEmployee.addActionListener(a);
	}
	public void addActionListenerButtonAccountReport(ActionListener a)
	{
		btnAccountReport.addActionListener(a);
	}
	public void addActionListenerButtonClientReport(ActionListener a)
	{
		btnClientReport.addActionListener(a);
	}
}
