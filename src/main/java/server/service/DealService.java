package server.service;

import server.dao.implementations.DealDao;
import server.dao.implementations.UserDao;
import server.dbException.DbException;
import server.entities.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.entities.User;

import java.util.*;

@Service
@Transactional
public class DealService {

    @Autowired
    private DealDao dealDao;
    @Autowired
    private UserDao userDao;

    public Deal create(Deal deal) throws DbException {
        try {
            List<User> list = deal.getCustomers();
            deal.setCustomers(new ArrayList<>());
            if (list != null) {
                for (User user : list) {
                    deal.getCustomers().add(userDao.getByName(user.getEmail()));
                }
            }
            if (deal.getSeller() != null) {
                deal.setSeller(userDao.getByName(deal.getSeller().getEmail()));
            }
            return dealDao.create(deal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in creating new deal transaction");
        }
    }

    public Deal update(long id, Deal updateDeal) throws DbException {
        try {
            Deal deal = dealDao.getById(id);
            deal.setName(updateDeal.getName());
            deal.setDate(updateDeal.getDate());
            deal.setSeller(updateDeal.getSeller());
            deal.setSum(updateDeal.getSum());
            return dealDao.update(deal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in updating deal transaction");
        }
    }

    public Deal addUserToDeal(long id, User user) throws DbException {
        try {
            Deal deal = dealDao.getById(id);
            if (deal != null) {
                if (user.getEmail() != null) {
                    deal.getCustomers().add(userDao.getByName(user.getEmail()));
                    return deal;
                } else {
                    return deal;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in adding user to deal transaction");
        }
    }

    public boolean delete(long id) throws DbException {
        try {
            Deal deal = dealDao.getById(id);
            if (deal != null) {
                dealDao.delete(deal);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception id deleting deal transaction");
        }
    }

    public List getAll() throws DbException {
        try {
            return dealDao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in creating deal list transaction");
        }
    }

    public Deal getById(long id) throws DbException {
        try {
            return dealDao.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in getting deal by id transaction");
        }
    }
}
