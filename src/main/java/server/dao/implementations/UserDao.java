package server.dao.implementations;

import server.dao.GenericDao;
import server.dbException.DbException;
import server.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao implements GenericDao<User, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User create(User user) throws DbException {
        try {
            entityManager.persist(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while persisting new user");
        }
    }

    @Override
    public User update(User user) throws DbException {
        try {
            return entityManager.merge(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while updating user with id = " + user.getId());
        }
    }

    @Override
    public void delete(User user) throws DbException {
        try {
            entityManager.remove(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while deleting user with id = " + user.getId());
        }
    }

    @Override
    public List<User> getAll() throws DbException {
        try {
            return entityManager.createQuery("from User c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting list of all users");
        }
    }

    @Override
    public User getById(Long id) throws DbException {
        try {
            return entityManager.find(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting user with id = " + id);
        }
    }

    public User getByPhoneNumber(long phoneNumber) throws DbException {
        try {
            return entityManager.find(User.class, phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting user with phone number = " + phoneNumber);
        }
    }

    public User getByName(String name) {
        try {
            return entityManager.find(User.class, name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
