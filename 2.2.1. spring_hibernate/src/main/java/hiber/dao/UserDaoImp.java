package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCarAndModel(String model, int series) {
      String query = "from User where car.model = :model and car.series = :series";
      TypedQuery<User> query1 = sessionFactory
              .getCurrentSession()
              .createQuery(query, User.class)
              .setParameter("model", model)
              .setParameter("series", series);
      List<User> users = query1.getResultList();
      if(!users.isEmpty()) {
         return users.get(0);
      }
      return null;
   }
}
