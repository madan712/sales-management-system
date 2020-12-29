package com.mascene.sms.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mascene.sms.model.Product;

@Repository
public class ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Product> getAllProducts() {

		Session session = sessionFactory.openSession();

		List<Product> contacts;
		try {
			CriteriaQuery<Product> criteriaQuery = session.getCriteriaBuilder().createQuery(Product.class);
			criteriaQuery.from(Product.class);
			contacts = session.createQuery(criteriaQuery).getResultList();
		} finally {
			session.close();
		}
		return contacts;

	}
	
	public void deleteProduct(Product product) {
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(product);
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
	
	public void addProduct(Product product) {
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.persist(product);
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
	
	public void updateProduct(Product product) {
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(product);
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
