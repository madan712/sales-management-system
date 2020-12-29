package com.mascene.sms.component;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mascene.sms.constant.Action;
import com.mascene.sms.dao.ProductDao;
import com.mascene.sms.model.Product;

@Component
public class ProductForm {

	@Autowired
	private ProductDao productDao;

	public JPanel getPanel(Product product, Action action, JDialog modelDialog, AdminScreen adminScreen, JFrame jFrame) {

		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel();
		if (action == Action.EDIT) {
			lblNewLabel.setText("Edit product");
		} else {
			lblNewLabel.setText("Add new product");
		}
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Barcode : ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JTextField barcodeField = new JTextField(product.getBarcode());
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(barcodeField, gbc_textField);
		barcodeField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Product name : ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JTextField productNameField = new JTextField(product.getProductname());
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel.add(productNameField, gbc_textField_1);
		productNameField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Color : ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JTextField colorField = new JTextField(product.getColor());
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		panel.add(colorField, gbc_textField_2);
		colorField.setColumns(10);

		JLabel lblProductDesc = new JLabel("Product desc : ");
		GridBagConstraints gbc_lblProductDesc = new GridBagConstraints();
		gbc_lblProductDesc.anchor = GridBagConstraints.EAST;
		gbc_lblProductDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductDesc.gridx = 0;
		gbc_lblProductDesc.gridy = 4;
		panel.add(lblProductDesc, gbc_lblProductDesc);

		JTextArea productDescField = new JTextArea(product.getProductdesc());
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 1;
		gbc_textArea_1.gridy = 4;
		panel.add(productDescField, gbc_textArea_1);

		JLabel lblNewLabel_4 = new JLabel("Unavailable reason : ");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 5;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JTextArea unavailableField = new JTextArea(product.getUnavailablereason());
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 5;
		panel.add(unavailableField, gbc_textArea);

		JLabel lblNewLabel_5 = new JLabel("Price : ");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 6;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JTextField priceField = new JTextField(product.getPrice().toPlainString());
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.WEST;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 6;
		panel.add(priceField, gbc_textField_4);
		priceField.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Quntity : ");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 7;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		JTextField quantityField = new JTextField(product.getQuantity() + "");
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 7;
		panel.add(quantityField, gbc_textField_3);
		quantityField.setColumns(10);

		JButton btnNewButton = new JButton(action.name());

		btnNewButton.addActionListener((ActionEvent ae) -> {

			setProductFields(product, barcodeField, productNameField, colorField, productDescField,
					unavailableField, priceField, quantityField);
			
			if (action == Action.EDIT) {
				productDao.updateProduct(product);
			} else {
				productDao.addProduct(product);
			}

			modelDialog.setVisible(false);
			adminScreen.getFrame().setVisible(true);
			jFrame.dispose();
		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 8;
		panel.add(btnNewButton, gbc_btnNewButton);

		return panel;
	}

	private void setProductFields(Product product, JTextField barcodeField, JTextField productNameField,
			JTextField colorField, JTextArea productDescField, JTextArea unavailableField, JTextField priceField,
			JTextField quantityField) {
		product.setBarcode(barcodeField.getText());
		product.setProductname(productNameField.getText());
		product.setColor(colorField.getText());
		product.setProductdesc(productDescField.getText());
		product.setUnavailablereason(unavailableField.getText());
		product.setPrice(new BigDecimal(priceField.getText()));
		product.setQuantity(Long.parseLong(quantityField.getText()));
	}

}
