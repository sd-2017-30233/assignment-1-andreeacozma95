package presentation;
import java.awt.Color;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.TextArea;

import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextArea;

public class EmployeeFrame {

	private JFrame frame;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	JButton btnDeleteClient;
	JButton btnSearch;
	JButton btnTransferMoney;
	
	JPanel panel_1;
	
	JList<String> list;
	
	JPanel panel;
	JPanel panelAddClient;
	JScrollPane scroll;
	private JTextField textField_5;
	
	JTextArea textArea;
	private JTextField textField_6;
	private JLabel lblValue_1;
	private JLabel lblDescription;
	private JTextField textField_7;
	
	JButton button;
	private JButton btnAddClient;
	private JTextField textField_8;
	private JLabel label_2;

	private JLabel lblNume;
	private JTextField textField_9;
	private JLabel lblAddress;
	private JTextField textField_10;
	private JTable table_1;

	
	public EmployeeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Clients", null, panel, null);
		panel.setLayout(null);
		
		btnDeleteClient = new JButton("Delete client");
		btnDeleteClient.setBounds(66, 5, 176, 23);
		panel.add(btnDeleteClient);
			
		
		panelAddClient = new JPanel();
		tabbedPane.addTab("Add clients", null, panelAddClient, null);
		panel.setLayout(new FlowLayout());
		
		//table_1 = new JTable();
		//table_1.setBounds(371, 196, -318, -120);
		//panel.add(table_1);
		
		//table_1 = new JTable();
		//panel.add(table_1);
		panelAddClient.setLayout(null);
		
		label_2 = new JLabel("Personal numerical code");
		label_2.setBounds(96, 74, 162, 14);
		panelAddClient.add(label_2);
		
		btnAddClient = new JButton("Add Client");
		btnAddClient.setBounds(143, 207, 115, 23);
		panelAddClient.add(btnAddClient);
		
		textField_8 = new JTextField();
		textField_8.setBounds(143, 47, 115, 20);
		panelAddClient.add(textField_8);
		textField_8.setColumns(10);
		
		lblNume = new JLabel("Nume");
		lblNume.setBounds(214, 22, 64, 14);
		panelAddClient.add(lblNume);
		
		textField_9 = new JTextField();
		textField_9.setBounds(143, 99, 115, 20);
		panelAddClient.add(textField_9);
		textField_9.setColumns(10);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(177, 130, 81, 14);
		panelAddClient.add(lblAddress);
		
		textField_10 = new JTextField();
		textField_10.setBounds(143, 155, 115, 20);
		panelAddClient.add(textField_10);
		textField_10.setColumns(10);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Accounts", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Personal numerical code of client:");
		label_1.setBounds(10, 28, 197, 14);
		panel_1.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(217, 25, 86, 20);
		panel_1.add(textField);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(338, 24, 89, 23);
		panel_1.add(btnSearch);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 92, 293, 117);
		panel_1.add(textArea);
		
		//list = new JList();
		//list.setBounds(89, 199, -84, -129);
		//panel_1.add(list);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Transfer money", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Source account - identification number");
		label.setBounds(39, 8, 240, 14);
		panel_2.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(270, 5, 86, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDestionationAccount = new JLabel("Destionation account - identification number");
		lblDestionationAccount.setBounds(10, 39, 255, 14);
		panel_2.add(lblDestionationAccount);
		
		textField_2 = new JTextField();
		textField_2.setBounds(270, 36, 86, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		btnTransferMoney = new JButton("Transfer money");
		btnTransferMoney.setBounds(219, 121, 146, 23);
		panel_2.add(btnTransferMoney);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(194, 67, 46, 14);
		panel_2.add(lblValue);
		
		textField_5 = new JTextField();
		textField_5.setBounds(270, 64, 86, 20);
		panel_2.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Process bill", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblAccountIdentification = new JLabel("Account - identification number");
		lblAccountIdentification.setBounds(10, 11, 177, 14);
		panel_3.add(lblAccountIdentification);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 36, 148, 20);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		button = new JButton("Process bill");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(140, 186, 130, 23);
		panel_3.add(button);
		
		JLabel lblBillNumber = new JLabel("Bill number");
		lblBillNumber.setBounds(10, 67, 148, 14);
		panel_3.add(lblBillNumber);
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 92, 145, 20);
		panel_3.add(textField_4);
		textField_4.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(224, 36, 86, 20);
		panel_3.add(textField_6);
		textField_6.setColumns(10);
		
		lblValue_1 = new JLabel("Value");
		lblValue_1.setBounds(248, 11, 46, 14);
		panel_3.add(lblValue_1);
		
		lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 123, 65, 14);
		panel_3.add(lblDescription);
		
		textField_7 = new JTextField();
		textField_7.setBounds(10, 148, 398, 20);
		panel_3.add(textField_7);
		textField_7.setColumns(10);
	
	}
	
	public void setFrameVisible()
	{
		frame.setVisible(true);
	}
	
	
	public void setTable(String [][] date,String coloane[])
	{		
		DefaultTableModel model = new DefaultTableModel(date,coloane);		
		table_1 =new JTable(model);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );

		for(int j=0; j < coloane.length; j++){
			table_1.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder title = BorderFactory.createTitledBorder(
                blackline, "Clients");
		title.setTitleJustification(TitledBorder.CENTER);

		panel.setBorder(title);
		scroll =new JScrollPane(table_1);
		panel.add(scroll);
	}


	public void addListenerTable(ListSelectionListener a){
		table_1.getSelectionModel().addListSelectionListener(a);
	}
	
	public int getSelectedRow(){
		return table_1.getSelectedRow();
	}
	
	public void refreshTable(String [][] date,String coloane[]){
		
		DefaultTableModel model = new DefaultTableModel(date,coloane);
	    table_1.setModel(model);
	    
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );

		for(int j=0; j < coloane.length; j++){
            table_1.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
		
	}
	
	public int getSelectedColumn(){
		return table_1.getSelectedColumn();
	}
	
	public String[] getTableData(int row, int column){
		String[] data=new String[table_1.getColumnCount()];
		
		for (int i=0;i<2;i++)		
			data[i] = table_1.getValueAt(row, i).toString();
				
		return data;
	}
	public void addActionListenerButtonDelete(ActionListener a)
	{
		btnDeleteClient.addActionListener(a);
	}
	public void addActionListenerButtonSearch(ActionListener a)
	{
		btnSearch.addActionListener(a);
	}
	
	public void addActionListenerButtonTransfer(ActionListener a)
	{
		btnTransferMoney.addActionListener(a);
	}
	
	public void addActionListenerButtonBill(ActionListener a)
	{
		button.addActionListener(a);
	}
	
	public void addActionListenerButtonAddClient(ActionListener a)
	{
		btnAddClient.addActionListener(a);
	}

	public String getTextField() {
		return textField.getText();
	}

	public void setTextField(String textField) {
		this.textField.setText(textField); 
	}
	
	public String getTextFieldSourceAccount() {
		return textField_1.getText();
	}

	public void setTextFieldSourceAccount(String textField) {
		this.textField_1.setText(textField); 
	}
	
	public String getTextFieldTargetAccount() {
		return textField_2.getText();
	}

	public void setTextFieldTargetAccount(String textField) {
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
	public String getTextField6() {
		return textField_6.getText();
	}

	public void setTextField6(String textField) {
		this.textField_6.setText(textField); 
	}
	public String getTextField7() {
		return textField_7.getText();
	}

	public void setTextField7(String textField) {
		this.textField_7.setText(textField); 
	}
	
	public String getTextField8() {
		return textField_8.getText();
	}

	public void setTextField8(String textField) {
		this.textField_8.setText(textField); 
	}
	public String getTextField9() {
		return textField_9.getText();
	}

	public void setTextField9(String textField) {
		this.textField_9.setText(textField); 
	}
	
	public String getTextField10() {
		return textField_10.getText();
	}

	public void setTextField10(String textField) {
		this.textField_10.setText(textField); 
	}
	public String getTextFieldValue() {
		return textField_5.getText();
	}

	public void setTextFieldValue(String textField) {
		this.textField_5.setText(textField); 
	}
	
	public void setListAccounts(String[][] date)
	{
		 DefaultListModel demoList = new DefaultListModel();
		 StringBuilder sb = new StringBuilder();
		 
		 for(int i=0;i<date.length;i++)
		 {
			 demoList.addElement(date[i][0]+" - "+date[i][1]);
			 sb.append(date[i][0]+" - "+date[i][1]);
			 sb.append("\n");
		 }
		 
		list = new JList<String>(demoList);
		list.setBackground(Color.black);
		panel_1.add(list);
		textArea.setText(sb.toString());
		
		
	}
}
