package server.service;

import server.dao.implementations.UserDao;
import server.dbException.DbException;
import server.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public User create(User user) throws DbException {
        try {
            return userDao.create(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in creating new user transaction");
        }
    }

    public User update(long id, User updateUser) throws DbException {
        try {
            User user = userDao.getById(id);
            if (updateUser.getPhoneNumber() != null) {
                user.setPhoneNumber(updateUser.getPhoneNumber());
            }
            if (updateUser.getName() != null) {
                user.setName(updateUser.getName());
            }
            return userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in updating user transaction");
        }
    }

    public boolean delete(long id) throws DbException {
        try {
            User user = userDao.getById(id);
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

    public User getById(long id) throws DbException {
        try {
            return userDao.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in getting user by id transaction");
        }
    }

    public User getByPhoneNumber(long phoneNumber) throws DbException {
        try {
            return userDao.getByPhoneNumber(phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in getting user by phone number transaction");
        }
    }

    public User getByName(String name) {
        try {
            return userDao.getByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
