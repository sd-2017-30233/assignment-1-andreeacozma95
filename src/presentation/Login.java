package presentation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import business.LogInService;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogIn;

	public Login() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogIn = new JLabel("Log in");
		lblLogIn.setBounds(129, 11, 44, 39);
		frame.getContentPane().add(lblLogIn);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(62, 73, 71, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(62, 101, 71, 14);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(143, 70, 92, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(143, 98, 92, 20);
		frame.getContentPane().add(passwordField);
		
		btnLogIn = new JButton("Log in");
		
			
		btnLogIn.setBounds(143, 149, 89, 23);
		frame.getContentPane().add(btnLogIn);
	}
	
	public void setInvisible()
	{
		frame.setVisible(false);
	}
	public void setVisible()
	{
		frame.setVisible(true);
	}
	
	public void addActionListenerButtonLogIn(ActionListener a)
	{
		btnLogIn.addActionListener(a);
	}

	public String getTextField() {
		return textField.getText();
	}

	public void setTextField(String textField) {
		this.textField.setText(textField);
	}

	public String getPasswordField() {
		return passwordField.getText();
	}

	public void setPasswordField(String passwordField) {
		this.passwordField.setText(passwordField);
	}
	public void resetFields()
	{
		textField.setText("");
		passwordField.setText("");
	}
	
}
