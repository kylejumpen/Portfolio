package fr.kaf.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import fr.kaf.elearning.Address;
import fr.kaf.elearning.Competence;
import fr.kaf.elearning.User;
import fr.kaf.elearning.Vehicle;

public class HibernateTest {

	public static void main(String[] args){
		
		User user = new User();
		user.setName("First User");
		user.setBirthday(new Date());
		user.getAddress().add(new Address("Horloge", "Rouen", "Normandie", "pi"));
		user.setDescription("I tried a lot");
		
		
		User user2 = new User();
		user2.setName("Third User");
		user2.setBirthday(new Date());
		user2.getAddress().add(new Address("Street 2", "London", "Manchester", "go"));
		user2.getAddress().add(new Address("Street 1", "Paris", "IDF", "youhou"));
		user2.setDescription("I ain't coming wit u pal");
		
		user2.setPartner(user);
		user.setPartner(user2);
		
		Vehicle auto  = new Vehicle();
		auto.setBrand("Ferrari");
		
		Vehicle auto2  = new Vehicle();
		auto2.setBrand("Lamborghini");
		
		Competence spring = new Competence();
		spring.setName("Spring Framework");
		spring.setDescription("Discover the new version of spring framework");
		Competence aspNet = new Competence();
		aspNet.setName("ASP.NET/C#");
		aspNet.setDescription("Discover the new version of .Net Core");
		
		user.getCompetences().add(aspNet);
		user.getCompetences().add(spring);
		user.getVehicle().add(auto);
		user.getVehicle().add(auto2);
		auto.setOwner(user);
		auto2.setOwner(user);
		user2.getCompetences().add(aspNet);
		user2.getCompetences().add(spring);
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
//		session.persist(user);
//		session.persist(user2);
//		session.persist(spring);
//		session.persist(aspNet);
//		user.setName("Igo");
		
		// HQL No need to put a select
		Query query = session.createQuery("from User"); // find All
		
		//String idMin = 5;
		//query = session.createQuery("from User where id > ?"); // specific query, use the class and the field name
		//query.setInteger(0,Integer.parseInt(idMin));
		//query = session.createQuery("Select id, name from User"); // gives a list of list with what's is requested
		//query = session.createQuery("Select new Map(id, name) from User"); // we can configure to have a structure but first level will still be a list
		//query = session.createQuery("Select max(id) from User"); // works too, a lot of sql function can be found in HQL too -> See documentation
		
//		query = session.getNamedQuery("User.byId"); // retrieve and use named queries
//		query.setParameter(0, 22);

//		query = session.getNamedQuery("User.byName"); // retrieve and use named queries
//		query.setParameter(0, "Igo");
		
//		/** TODO : Find the new way as this way is deprecated**/
//		Criteria criteria = session.createCriteria(User.class);
//		criteria.add(Restrictions.eq("name", "Igo"));
//		
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> query2 = criteriaBuilder.createQuery(User.class);
		Root<User> userRoot = query2.from(User.class);

		query2.select(userRoot.get("id")).
		where(criteriaBuilder.and(criteriaBuilder.between(userRoot.get("id"), 10, 100),criteriaBuilder.equal(userRoot.get("name"), "Igo")))
		.distinct(true);
		
		query = session.createQuery(query2);
		
		//query.setFirstResult(5); // Defines the offset for the first result to be shown
		//query.setMaxResults(5); // Specify the number of record neeeded -> can do pagination from both functions mentionned above
		List<User> users = query.list();
		//--------------------------------				
		
		// Java 8 test all //
		List<User> filteredUser = users.stream()
				.filter((temp) -> temp.getName().equals(users.get(0).getName()))
				.filter((temp) -> temp.getId() % 2 == 1)
				.sorted((u1,u2) -> u1.getId() - u2.getId())
				.collect(Collectors.toCollection(ArrayList::new));
		filteredUser.forEach(System.out::println);
		// Java 8 //
		
//		session.delete(user2);
//		session.delete(user);
		session.getTransaction().commit();
		session.close();
		
		user = new User();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
	}
}
