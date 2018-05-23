package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.dao.implementations.UserDao;
import server.entities.User;
import server.exceptions.DbException;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private UserDao userDao;

    public User save(User user) {
        try {
            User result = dao.create(user);
            return result;
        } catch (DbException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean existsByEmail(String email) {
        try {
            User user = dao.getByEmail(email);
            if (user == null) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } catch (DbException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean existsByUsername(String username) {
        User user = dao.getByName(username);
        if (user == null) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public server.entities.User update(long id, server.entities.User updateUser) throws DbException {
        try {
            server.entities.User user = userDao.getById(id);
            if (updateUser.getPassword() != null) {
                user.setPassword(updateUser.getPassword());
            }
            if (updateUser.getEmail() != null) {
                user.setEmail(updateUser.getEmail());
            }
            return userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in updating user transaction");
        }
    }

    public boolean delete(long id) throws DbException {
        try {
            server.entities.User user = userDao.getById(id);
            if (user != null) {
                userDao.delete(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception id deleting user transaction");
        }
    }

    public List getAll() throws DbException {
        try {
            return userDao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in creating user list transaction");
        }
    }

    public server.entities.User getById(long id) throws DbException {
        try {
            return userDao.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in getting user by id transaction");
        }
    }

    public server.entities.User getByEmail(String email) throws DbException {
        try {
            return userDao.getByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in getting user by email transaction");
        }
    }


    public server.entities.User getByName(String name) {
        try {
            return userDao.getByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
