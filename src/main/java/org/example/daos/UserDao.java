package org.example.daos;

import org.example.entity.User;
import org.example.exception.GenericException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDao extends GenericDao<User, Long> {
    private Session session;

    public UserDao(Session session) {
        super(session, User.class);
        this.session = session;
    }

    public User create(User user) {
        if (user.getId() != null) {
            throw GenericException.idNotNull();
        } else if (findByUsername(user.getUsername()) != null) {
            throw GenericException.UsernameExists(user.getUsername());
        } else {
            return super.create(user);
        }
    }

    public User update(User user) {
        if (user.getId() == null && findById(user.getId()) == null) {
            throw GenericException.idShouldNotBeNull();
        } else if (findByUsername(user.getUsername()) != null) {
            throw GenericException.UsernameExists(user.getUsername());
        } else if (findByUsername(user.getUsername()) != null && user.getId().equals(findByUsername(user.getUsername()).getId())) {
            return super.update(user);
        } else {
            return super.update(user);
        }
    }

    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            super.delete(user);
        }

    }

    public User findByUsername(String username) {
        String query = "select user from User user where user.username = :username";
        Query<User> findByUserrnameQuery = session.createQuery(query, User.class);
        findByUserrnameQuery.setParameter("username", username);
        return findByUserrnameQuery.getSingleResultOrNull();
    }
}
