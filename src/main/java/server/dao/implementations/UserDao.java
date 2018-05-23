package server.dao.implementations;

import server.dao.GenericDao;
import server.exceptions.DbException;
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

    public User getByEmail(String email) throws DbException {
        try {
            return (User)entityManager.createQuery("select u from User u where u.email=:email")
                    .setParameter("email", email)
                    .getResultList()
                    .get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting user with email = " + email);
        }
    }

    public User getByName(String username) {
        try {
            return (User)entityManager.createQuery("select c from User c where c.username=:username")
                    .setParameter("username", username)
                    .getResultList()
                    .get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
