package repository;

import org.hibernate.Session;
import org.hibernate.query.*;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;

import org.hibernate.*;

import data.User;

public class UserRepository {
	public UserRepository(Session session) {
		this.session = session;
	}

	private Session session;
	
	public void addUser(User user){
	    session.beginTransaction();
        session.persist(user);
	    session.getTransaction().commit();
	    session.close();

 	}
	
	public String getUserPassword(String mail)
	{
		session.beginTransaction();
		
		TypedQuery<User> query=session.createQuery("select s from User s where s.mail='"+mail+"'",User.class);
		User user=query.getSingleResult();
	//	System.out.println("imie"+user.getFirstName());
		session.close();
		return user.getPassword();
	}

	public void deleteUser(Long id){
		session.beginTransaction();
        User user = (User) session.get(User.class,id);
        session.delete(user);
	    session.getTransaction().commit();
	    session.close();

	}
}
	
  


