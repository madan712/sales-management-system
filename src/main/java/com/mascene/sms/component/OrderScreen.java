package com.mascene.sms.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mascene.sms.constant.Action;
import com.mascene.sms.dao.OrderDao;
import com.mascene.sms.model.OrderItem;
import com.mascene.sms.model.Orders;
import com.mascene.sms.model.Product;
import com.mascene.sms.service.AppService;

@Component
public class OrderScreen extends Screen {

	@Autowired
	private SalesScreen salesScreen;

	@Autowired
	private AppService appService;

	@Autowired
	private OrderDao orderDao;

	private BigDecimal total = BigDecimal.ZERO;

	@Override
	public JFrame getFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("MA Scene Inc | Orders");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("MA Scene Inc");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		panel.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNewLabel_1 = new JLabel("Order history :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel_1.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		// Adding order history
		addOrderHistory(panel_2, frame);

		JLabel lblNewLabel_2 = new JLabel("Current Order(s) :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JPanel orderListPanel = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 3;
		panel_1.add(orderListPanel, gbc_panel_3);
		orderListPanel.setLayout(new BoxLayout(orderListPanel, BoxLayout.Y_AXIS));

		// Adding cart item
		addCartItems(orderListPanel, frame);

		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_8.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		orderListPanel.add(panel_8);

		JLabel lblNewLabel_8 = new JLabel("Grand Total : $" + getTotal());
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_8.add(lblNewLabel_8);

		JPanel panel_9 = new JPanel();
		orderListPanel.add(panel_9);

		JButton btnNewButton_4 = new JButton("Add");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salesScreen.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		panel_9.add(btnNewButton_4);

		if (!appService.getCart().isEmpty()) {
			JButton btnNewButton_2 = new JButton("Cancel ");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appService.clearCart();
					salesScreen.getFrame().setVisible(true);
					frame.dispose();
				}
			});
			panel_9.add(btnNewButton_2);

			JButton btnNewButton_3 = new JButton("Complete purchase");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					boolean isSuccess = appService.createOrder(appService.getCart(), appService.getUser());
					if (isSuccess) {
						appService.clearCart();
						OrderScreen.this.getFrame().setVisible(true);
						frame.dispose();
						JOptionPane.showMessageDialog(null, "Order placed successfully!", null,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Something went wrong!", null, JOptionPane.ERROR_MESSAGE);
					}

				}
			});
			panel_9.add(btnNewButton_3);
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		return frame;
	}

	private void addOrderHistory(JPanel panel_2, JFrame frame) {

		AtomicInteger index = new AtomicInteger(0);

		orderDao.getOrderList().forEach(orders -> {

			String sr = new Integer(index.getAndIncrement() + 1).toString();

			String date = orders.getOrderat().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm a"));

			JLabel lblNewLabel_3 = new JLabel("<html>" + sr + ". " + date + " <a href=''>[VIEW]</a></html>");
			lblNewLabel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			final JDialog orderHistoryDialog = createOrderHistoryDialog(orders, frame);

			lblNewLabel_3.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					orderHistoryDialog.setVisible(true);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}
			});

			panel_2.add(lblNewLabel_3);
		});

	}

	private JDialog createOrderHistoryDialog(Orders orders, final JFrame frame) {

		final JDialog modelDialog = new JDialog(frame, "Order History", Dialog.ModalityType.DOCUMENT_MODAL);
		modelDialog.setBounds(100, 100, 300, 200);
		modelDialog.setSize(new Dimension(600, 500));

		Container dialogContainer = modelDialog.getContentPane();

		dialogContainer.setLayout(new BorderLayout());

		dialogContainer.add(getOrderHistoryPannel(orders), BorderLayout.CENTER);

		return modelDialog;
	}

	private JPanel getOrderHistoryPannel(Orders orders) {

		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_2 = new JLabel("Order history");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		String date = orders.getOrderat().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm a"));

		JLabel lblNewLabel = new JLabel("Order at : " + date);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("User : " + orders.getUser().getUsername());
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		String column[] = { "Sr.", "Product name", "Quantity", "Sub total", "Tax", "Total" };

		Set<OrderItem> orderItems = orders.getOrderItems();

		Object[][] data = getTableData(orderItems);

		JTable table = new JTable(data, column);

		JScrollPane sp = new JScrollPane(table);

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 2;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 3;
		panel.add(sp, gbc_table);
		
		
		
		JLabel totalLabel = new JLabel("Grand total : " + orders.getTotalprice());
		GridBagConstraints gbc_totalLabel = new GridBagConstraints();
		gbc_totalLabel.anchor = GridBagConstraints.WEST;
		gbc_totalLabel.insets = new Insets(0, 0, 5, 5);
		gbc_totalLabel.gridx = 1;
		gbc_totalLabel.gridy = 4;
		panel.add(totalLabel, gbc_totalLabel);

		return panel;
	}

	private Object[][] getTableData(Set<OrderItem> orderItems) {
		Object data[][] = new Object[orderItems.size()][6];

		AtomicInteger index = new AtomicInteger(0);

		orderItems.forEach(orderItem -> {
			int i = index.getAndIncrement();

			List<String> row = new ArrayList<>();
			row.add((i + 1) + "");
			row.add(orderItem.getProduct().getProductname());
			row.add(orderItem.getQuantity() + "");
			row.add(orderItem.getSubtotal().toString());
			row.add(orderItem.getTax().toString());
			row.add(orderItem.getTotal().toString());

			data[i] = row.toArray();

		});
		return data;
	}

	private BigDecimal getTotal() {
		return this.total;
	}

	public void addCartItems(JPanel orderListPanel, JFrame frame) {

		AtomicInteger index = new AtomicInteger(0);

		// Reset total
		total = BigDecimal.ZERO;

		List<Product> productList = appService.getCart();

		if (productList.isEmpty()) {

			JPanel panel_4 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel_4.setBackground(Color.WHITE);
			panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
			orderListPanel.add(panel_4);

			JPanel panel_5 = new JPanel();

			panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_4.add(panel_5);

			JLabel label = new JLabel("No items in Cart!");
			panel_5.add(label);

		} else {

			productList.forEach(product -> {

				JPanel panel_4 = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				panel_4.setBackground(Color.WHITE);
				panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
				orderListPanel.add(panel_4);

				JPanel panel_5 = new JPanel();
				panel_5.setPreferredSize(new Dimension(30, 100));
				panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_4.add(panel_5);

				String sr = new Integer(index.getAndIncrement() + 1).toString();

				JLabel label = new JLabel(sr);
				panel_5.add(label);

				JPanel panel_6 = new JPanel();
				panel_6.setPreferredSize(new Dimension(200, 100));
				panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
				panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_4.add(panel_6);

				JLabel lblNewLabel_6 = new JLabel("Item : " + product.getProductname());
				panel_6.add(lblNewLabel_6);

				JLabel lblNewLabel_6_1 = new JLabel("Barcode number : " + product.getBarcode());
				panel_6.add(lblNewLabel_6_1);

				JPanel panel_7 = new JPanel();
				panel_7.setPreferredSize(new Dimension(350, 100));
				panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
				panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_4.add(panel_7);

				JLabel lblNewLabel_7 = new JLabel("Price : $" + product.getPrice());
				panel_7.add(lblNewLabel_7);

				JLabel lblNewLabel_7_1 = new JLabel("Tax : $" + appService.getProductTax(product));
				panel_7.add(lblNewLabel_7_1);

				BigDecimal productTotal = appService.getProductTotal(product);
				total = total.add(productTotal);
				JLabel lblNewLabel_7_2 = new JLabel("Total : $" + productTotal);
				panel_7.add(lblNewLabel_7_2);

				JButton btnNewButton = new JButton("Remove");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						appService.removePoductFromCart(product);
						OrderScreen.this.getFrame().setVisible(true);
						frame.dispose();
					}
				});
				panel_4.add(btnNewButton);

			});

		}

	}

}
