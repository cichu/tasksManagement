package Application;

import org.hibernate.Session;

import data.User;
import repository.HibernateUtil;
import repository.UserRepository;

public class App 
{
    public static void main( String[] args )
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	
    	
  User user = new User();
  user.setMail("aaaaa");
  user.setFirstName("aaaa");
  User user2 = new User();
  user2.setMail("aaaaa2");
  user2.setFirstName("aaaa2");
  User user3 = new User();
  user3.setMail("aaaaa3");
  user3.setFirstName("aaaa3");
  User user4 = new User();
  user4.setMail("aaaaa4");
  user4.setFirstName("aaaa4");
  
  UserRepository userRepo = new UserRepository(session);
    	userRepo.addUser(user2);
    	userRepo.addUser(user4);
    	//userRepo.deleteUser(1L);
    	
    	
    	
  	  session.close();
  	 // HibernateUtil.shutdown();
    }
}

