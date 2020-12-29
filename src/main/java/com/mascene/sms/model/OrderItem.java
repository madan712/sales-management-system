package com.mascene.sms.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderitem")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderitemid")
	private long orderitemid;
	
	@ManyToOne
	@JoinColumn(name = "productid", nullable = false)
	private Product product;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "subtotal")
	private BigDecimal subtotal;
	
	@Column(name = "tax")
	private BigDecimal tax;
	
	@Column(name = "total")
	private BigDecimal total;

	public long getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(long orderitemid) {
		this.orderitemid = orderitemid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
