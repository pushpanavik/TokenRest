package com.bridgeit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.model.User;

@Repository
@Transactional
public class UserDaoImpl implements IUserDao {
	
	public UserDaoImpl() {
		System.out.println("Dao created");
	}

	
	@Autowired
	private SessionFactory factory;
	
	public int addUser(User user) {
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		int id=(Integer)session.save(user);
		
		tx.commit();
		
		
		return id;
	}

	public User validateUser(User user) {
		
		Session session=factory.getCurrentSession();
		Criteria crite=session.createCriteria(User.class);
		Criterion email=Restrictions.eq("email", user.getEmail());
		Criterion password=Restrictions.eq("password", user.getPassword());
		Criterion bothArePresent=Restrictions.and(email,password);
		crite.add(bothArePresent);
		
		User reg=(User) crite.uniqueResult();
		//System.out.println(reg.getUsername()+ " "+ reg.getEmail()+ " "+ reg.getPassword());
		
		return reg;
	}

	public List<User> checkEmailId(String Email) {
		
		Session session = factory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", Email));
		List<User> userList = criteria.list();
		return userList;
	}

	@Override
	public User getUserByEmaiId(String email) {
		
		System.out.println("goes inside getuser by email id");
		
		Session session=factory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria criteria=session.createCriteria(User.class);
		
		criteria.add(Restrictions.eq("email",email));
		
		User userobj=(User)criteria.uniqueResult();
				return userobj;
	}

	
}
