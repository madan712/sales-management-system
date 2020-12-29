package com.mascene.sms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mascene.sms.dao.OrderDao;
import com.mascene.sms.dao.ProductDao;
import com.mascene.sms.model.OrderItem;
import com.mascene.sms.model.Orders;
import com.mascene.sms.model.Product;
import com.mascene.sms.model.User;

@Service
public class AppService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductDao productDao;

	private User user;

	private List<Product> cart = new ArrayList<>();

	private static final BigDecimal HUNDRED = new BigDecimal(100);

	private static final BigDecimal PERCENTAGE = new BigDecimal(13);

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addPoductToCart(Product product) {
		cart.add(product);
	}

	public void removePoductFromCart(Product product) {
		if (cart.contains(product)) {
			cart.remove(cart.lastIndexOf(product));
		}
	}

	public List<Product> getCart() {
		return cart;
	}

	public void clearCart() {
		cart.clear();
	}

	@Transactional
	public boolean createOrder(List<Product> productList, User user) {

		Orders newOrder = new Orders();

		Set<OrderItem> orderItems = new HashSet<>();

		productList.forEach(product -> {
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(product);
			// At present it only support to store one quantity at a time
			orderItem.setQuantity(1);

			orderItem.setSubtotal(product.getPrice());
			orderItem.setTax(getProductTax(product));
			orderItem.setTotal(getProductTotal(product));

			orderItems.add(orderItem);

		});

		newOrder.setTotalquantity(productList.size());
		newOrder.setTotalprice(orderItems.stream().map(OrderItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
		newOrder.setUser(user);
		newOrder.setOrderat(LocalDateTime.now());
		newOrder.setOrderItems(orderItems);
		
		try {
			orderDao.saveOrders(newOrder);
			// TODO reduce product quantity
			return true;
		} catch (Exception e) {
			System.out.println("Error while saving order");
			e.printStackTrace();
			return false;
		}

		
	}

	public BigDecimal getProductTax(Product product) {
		return product.getPrice().multiply(PERCENTAGE).divide(HUNDRED);
	}

	public BigDecimal getProductTotal(Product product) {
		return product.getPrice().add(getProductTax(product));
	}

}
