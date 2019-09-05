package ru.levchenko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.levchenko.models.User;

import javax.persistence.FetchType;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class UsersDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory;

    public UsersDaoHibernateImpl() {
        final StandardServiceRegistry registryBuilder = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registryBuilder).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registryBuilder);
        }
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public List<User> findAllByAge(Integer minx, Integer maxx) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User user where user.age>=?1 and user.age<=?2");
        query.setParameter(1, minx);
        query.setParameter(2, maxx);
        List<User> userList = query.getResultList();
        session.flush();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public Optional<User> find(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        User user = session.find(User.class, id);
        transaction.commit();
        session.close();
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        List<User> userList = session.createQuery("FROM User", User.class).list();
        transaction.commit();
        session.close();

        return userList;
    }

    @Override
    public void save(User model) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        FetchType fetch = FetchType.EAGER;
        transaction = session.beginTransaction();
        session.save(model);
        transaction.commit();
        session.close();

    }

    @Override
    public void update(User model) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        FetchType fetch = FetchType.EAGER;
        transaction = session.beginTransaction();
        session.saveOrUpdate(model);
        transaction.commit();
        session.close();

    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        User user;
        session = sessionFactory.getCurrentSession();
        FetchType fetchType = FetchType.EAGER;
        Transaction transaction = null;
        transaction = session.beginTransaction();
        user = (User) session.load(User.class, id);
        session.delete(user);

        //This makes the pending delete to be done
        session.flush();
        transaction.commit();
        session.close();

    }
}

