package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

    public UserDaoImpl() {
    }

//    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
//    private static Map<Integer, User> users = new HashMap<>();
//
//    static{
//        User user = new User();
//        user.setId(AUTO_ID.getAndIncrement());
//        user.setName("IVAN");
//        user.setAge("27");
//        users.put(user.getId(),user);
//    }

//    @Autowired
//    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public List<User> allUsers() {


        List<User> resultList = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        return resultList;

    }

    @Override
    public void add(User user) {

        User managed = entityManager.merge(user);
        entityManager.persist(managed);
    }

    @Transactional
    @Override
    public void delete(User user) {
        User managed = entityManager.merge(user);
        entityManager.remove(managed);
    }

    @Override
    public void edit(User user) {
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }
}
