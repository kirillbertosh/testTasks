package server.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import server.dao.implementations.UserDao;
import server.dbException.DbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.Collections.emptyList;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public server.entities.User create(server.entities.User user) throws DbException {
        try {
            return userDao.create(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in creating new user transaction");
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
            if (updateUser.getFirstName() != null) {
                user.setFirstName(updateUser.getFirstName());
            }
            if (updateUser.getLastName() != null) {
                user.setLastName(updateUser.getLastName());
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        server.entities.User user = userDao.getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getLogin(), user.getPassword(), emptyList());
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
