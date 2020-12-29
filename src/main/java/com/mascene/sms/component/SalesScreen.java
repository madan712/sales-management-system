package com.mascene.sms.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mascene.sms.constant.Role;
import com.mascene.sms.dao.ProductDao;
import com.mascene.sms.model.Product;
import com.mascene.sms.service.AppService;

@Component
public class SalesScreen extends Screen {

	@Autowired
	private LoginScreen loginScreen;
	
	@Autowired
	private AdminScreen adminScreen;
	
	@Autowired
	private OrderScreen orderScreen;

	@Autowired
	private AppService appService;

	@Autowired
	private ProductDao productDao;
	
	
	private JButton orderButton;
	

	@Override
	public JFrame getFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("MA Scene Inc | Store Front");
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
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Store Front  - Welcome : " + appService.getUser().getUsername());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginScreen.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
		
		JLabel lblNewLabel_2 = new JLabel("Today's date : " + date);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		orderButton = new JButton();
		setOrderButtonText();
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderScreen.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		
		if(appService.getUser().getRole() == Role.ADMIN) {
			gbc_btnNewButton_2.gridx = 2;
		} else {
			gbc_btnNewButton_2.gridx = 3;
		}

		gbc_btnNewButton_2.gridy = 1;
		panel_1.add(orderButton, gbc_btnNewButton_2);
		
		if(appService.getUser().getRole() == Role.ADMIN) {
			JButton btnNewButton_1 = new JButton("Admin");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					adminScreen.getFrame().setVisible(true);
					frame.dispose();
				}
			});
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton_1.gridx = 3;
			gbc_btnNewButton_1.gridy = 1;
			panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
		}
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 4;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		panel_1.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		addProduct(panel_2);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		return frame;
	}
	
	private void addProduct(JPanel panel) {
		
		
		System.out.println("Fetching products from DB");
		List<Product> productList = productDao.getAllProducts();
		
		AtomicInteger index = new AtomicInteger(0);
		
		System.out.println("Found "+productList.size()+" products");
		
		productList.forEach(product -> {
			
			JPanel panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			//panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_3.setBackground(Color.WHITE);
			panel.add(panel_3);
			
			JPanel panel_6 = new JPanel();
			panel_6.setPreferredSize(new Dimension(30, 100));
			panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_3.add(panel_6);
			
			String sr = new Integer(index.getAndIncrement() + 1).toString();
			
			JLabel lblNewLabel_3 = new JLabel(sr);
			panel_6.add(lblNewLabel_3);
			
			JPanel panel_7 = new JPanel();
			panel_7.setPreferredSize(new Dimension(150, 100));
			panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_3.add(panel_7);
			panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
			
			JLabel lblNewLabel_4 = new JLabel("Title : "+product.getProductname());
			panel_7.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Color : "+product.getColor());
			panel_7.add(lblNewLabel_5);
			
			JPanel panel_5 = new JPanel();
			panel_5.setPreferredSize(new Dimension(300, 100));
			panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_3.add(panel_5);
			panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
			
			JLabel lblNewLabel_6 = new JLabel("Barcode number : "+product.getBarcode());
			panel_5.add(lblNewLabel_6);
			
			JLabel productQuantityLabel = new JLabel("Quantity : "+getAvailableQuantity(product));
			panel_5.add(productQuantityLabel);
			
			JLabel lblNewLabel_9 = new JLabel("Price : $"+product.getPrice());
			panel_5.add(lblNewLabel_9);
			
			JPanel panel_9 = new JPanel();
			panel_9.setPreferredSize(new Dimension(350, 100));
			panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_3.add(panel_9);
			panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
			
			JLabel lblNewLabel_10 = new JLabel("<html>Description : "+product.getProductdesc()+"</html>");
			panel_9.add(lblNewLabel_10);
			
			JLabel lblNewLabel_7 = new JLabel("Reason unavailable : "+product.getUnavailablereason());
			panel_9.add(lblNewLabel_7);
			
			JButton btnNewButton_3 = new JButton("Add");
			
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					
					int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to add?", "Confirm", dialogButton);
					
					if(dialogResult == 0) {
						appService.addPoductToCart(product);
						productQuantityLabel.setText("Quantity : "+getAvailableQuantity(product));
						setOrderButtonText();
						
						if(getAvailableQuantity(product) == 0) {
							btnNewButton_3.setEnabled(false);
						}
					}
				}
			});
			
			if(getAvailableQuantity(product) == 0) {
				btnNewButton_3.setEnabled(false);
			}
			
			panel_3.add(btnNewButton_3);
			
			
		});
		
		
		
	}

	private long getAvailableQuantity(Product product) {
		return product.getQuantity() - appService.getCart().stream().filter(p -> p.getProductid() == product.getProductid()).count();
	}
	
	public void setOrderButtonText() {
		String orderButtonText = appService.getCart().isEmpty() ? "Order"
				: "Order (" + appService.getCart().size() + ")";
		orderButton.setText(orderButtonText);
	}

}
