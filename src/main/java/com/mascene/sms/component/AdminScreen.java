package com.mascene.sms.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mascene.sms.constant.Action;
import com.mascene.sms.dao.ProductDao;
import com.mascene.sms.model.Product;
import com.mascene.sms.service.AppService;

@Component
public class AdminScreen extends Screen {

	@Autowired
	private SalesScreen salesScreen;

	@Autowired
	private AppService appService;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductForm productForm;

	@Override
	public JFrame getFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("MA Scene Inc | Store Backend");
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
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNewLabel_1 = new JLabel("Store Backend  - Welcome : " + appService.getUser().getUsername());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JButton btnNewButton = new JButton("Store");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salesScreen.getFrame().setVisible(true);
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

		JButton btnNewButton_1 = new JButton("Add");
		
		final JDialog addProductDialog = createAddProductDialog(frame, getBlankProductObject());
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProductDialog.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 4;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		panel_1.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		addProduct(panel_2, frame);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		return frame;
	}

	private Product getBlankProductObject() {
		Product product = new Product();
		product.setPrice(BigDecimal.ZERO);
		
		return product;
	}

	private void addProduct(JPanel panel, JFrame frame) {

		System.out.println("Fetching products from DB");
		List<Product> productList = productDao.getAllProducts();

		AtomicInteger index = new AtomicInteger(0);

		System.out.println("Found " + productList.size() + " products");

		productList.forEach(product -> {

			JPanel panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			// panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
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

			JLabel lblNewLabel_4 = new JLabel("Title : " + product.getProductname());
			panel_7.add(lblNewLabel_4);

			JLabel lblNewLabel_5 = new JLabel("Color : " + product.getColor());
			panel_7.add(lblNewLabel_5);

			JPanel panel_5 = new JPanel();
			panel_5.setPreferredSize(new Dimension(300, 100));
			panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_3.add(panel_5);
			panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

			JLabel lblNewLabel_6 = new JLabel("Barcode number : " + product.getBarcode());
			panel_5.add(lblNewLabel_6);

			JLabel productQuantityLabel = new JLabel("Quantity : " + getAvailableQuantity(product));
			panel_5.add(productQuantityLabel);

			JLabel lblNewLabel_9 = new JLabel("Price : $" + product.getPrice());
			panel_5.add(lblNewLabel_9);

			JPanel panel_9 = new JPanel();
			panel_9.setPreferredSize(new Dimension(350, 100));
			panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_3.add(panel_9);
			panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

			JLabel lblNewLabel_10 = new JLabel("<html>Description : " + product.getProductdesc() + "</html>");
			panel_9.add(lblNewLabel_10);

			JLabel lblNewLabel_7 = new JLabel("Reason unavailable : " + product.getUnavailablereason());
			panel_9.add(lblNewLabel_7);

			JButton btnNewButton_3 = new JButton("Edit");

			final JDialog editProductDialog = createEditProductDialog(frame, product);

			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editProductDialog.setVisible(true);
				}
			});

			panel_3.add(btnNewButton_3);

			JButton btnNewButton_4 = new JButton("Delete");

			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					productDao.deleteProduct(product);
					AdminScreen.this.getFrame().setVisible(true);
					frame.dispose();
				}
			});

			panel_3.add(btnNewButton_4);

		});

	}

	private JDialog createEditProductDialog(final JFrame frame, Product product) {

		final JDialog modelDialog = new JDialog(frame, "Edit", Dialog.ModalityType.DOCUMENT_MODAL);
		modelDialog.setBounds(100, 100, 300, 200);
		modelDialog.setSize(new Dimension(600, 500));

		Container dialogContainer = modelDialog.getContentPane();

		dialogContainer.setLayout(new BorderLayout());

		dialogContainer.add(productForm.getPanel(product, Action.EDIT, modelDialog, this, frame), BorderLayout.CENTER);

		return modelDialog;
	}
	
	
	private JDialog createAddProductDialog(final JFrame frame, Product product) {

		final JDialog modelDialog = new JDialog(frame, "Add", Dialog.ModalityType.DOCUMENT_MODAL);
		modelDialog.setBounds(100, 100, 300, 200);
		modelDialog.setSize(new Dimension(600, 500));

		Container dialogContainer = modelDialog.getContentPane();

		dialogContainer.setLayout(new BorderLayout());

		dialogContainer.add(productForm.getPanel(product, Action.SAVE, modelDialog, this, frame), BorderLayout.CENTER);

		return modelDialog;
	}

	private long getAvailableQuantity(Product product) {
		return product.getQuantity()
				- appService.getCart().stream().filter(p -> p.getProductid() == product.getProductid()).count();
	}

}
