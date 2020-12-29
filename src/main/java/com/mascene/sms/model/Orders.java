package com.mascene.sms.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderid")
	private long orderid;
	
	@Column(name = "totalquantity")
	private int totalquantity;
	
	@Column(name = "totalprice")
	private BigDecimal totalprice;
	
	@ManyToOne
	@JoinColumn(name = "userid", nullable = false)
	private User user;
	
	@Column(name = "orderat")
	private LocalDateTime orderat;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "orderid", nullable = false)
	private Set<OrderItem> orderItems;

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public int getTotalquantity() {
		return totalquantity;
	}

	public void setTotalquantity(int totalquantity) {
		this.totalquantity = totalquantity;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getOrderat() {
		return orderat;
	}

	public void setOrderat(LocalDateTime orderat) {
		this.orderat = orderat;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
