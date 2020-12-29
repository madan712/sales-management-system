package com.mascene.sms.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mascene.sms.model.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public User getUser(String username) {
		Session session = sessionFactory.openSession();
		
		String hql = " from User u where u.username=:username";
		List<User> userList;
		try {
			Query<User> query = session.createQuery(hql, User.class);
			query.setParameter("username", username);
			userList = query.list();
			if (userList != null && userList.size() == 1) {
				return userList.get(0);
			}
		} finally {
			session.close();
		}
		
		return null;
	}

}
