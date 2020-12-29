package com.mascene.sms.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mascene.sms.model.Orders;

@Repository
public class OrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Orders> getOrderList() {

		Session session = sessionFactory.openSession();

		List<Orders> ordersList;
		try {
			CriteriaQuery<Orders> criteriaQuery = session.getCriteriaBuilder().createQuery(Orders.class);
			criteriaQuery.from(Orders.class);
			ordersList = session.createQuery(criteriaQuery).getResultList();
		} finally {
			session.close();
		}

		return ordersList;
	}

	public void saveOrders(Orders orders) {
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.persist(orders);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}

	}

}
