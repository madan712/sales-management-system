package com.mascene.sms.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mascene.sms.dao.UserDao;
import com.mascene.sms.model.User;
import com.mascene.sms.service.AppService;

@Component
public class LoginScreen extends Screen {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SalesScreen salesScreen;
	
	@Autowired
	private AppService appService;

	@Override
	public JFrame getFrame() {
		
		JFrame frame = new JFrame();
		frame.setTitle("MA Scene Inc | Login");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("MA Scene Inc");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Username : ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JTextField textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setText("test");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		panel_1.add(passwordField, gbc_textField_1);
		passwordField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		
		setLoginAction(frame, textField, passwordField, loginButton);
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 3;
		panel_1.add(loginButton, gbc_btnNewButton);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		return frame;
	}

	private void setLoginAction(JFrame frame, JTextField textField, JPasswordField passwordField,
			JButton loginButton) {
		loginButton.addActionListener((ActionEvent ae) -> {
			String name = textField.getText();
			String pwd = String.valueOf(passwordField.getPassword());
			System.out.println(pwd);
			
			User user = userDao.getUser(name);
			
			System.out.println(user);
			
			if (user != null && user.getPassword().equals(pwd)) {
				appService.setUser(user);
				salesScreen.getFrame().setVisible(true);
				frame.dispose();
			} else {

				JOptionPane.showMessageDialog(null, "Wrong Password / Username");
				textField.setText("");
				passwordField.setText("");
				textField.requestFocus();
			}
		});
	}
	
}
