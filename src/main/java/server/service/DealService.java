package server.service;

import server.dao.implementations.DealDao;
import server.dao.implementations.UserDao;
import server.exceptions.DbException;
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

    public Deal save(Deal deal) throws DbException {
        try {
            return dealDao.create(deal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in saving new deal transaction");
        }
    }

    public Deal update(Long id, Deal updateDeal) throws DbException {
        try {
            Deal deal = dealDao.getById(id);
            if (updateDeal.getDescription() != null) {
                deal.setDescription(updateDeal.getDescription());
            }
            if (updateDeal.getAttachments() != null) {
                deal.setAttachments(updateDeal.getAttachments());
            }
            if (updateDeal.getChat() != null) {
                deal.setChat(updateDeal.getChat());
            }
            if (updateDeal.getMembers() != null) {
                deal.setMembers(updateDeal.getMembers());
            }
            if (updateDeal.getType() != null) {
                deal.setType(updateDeal.getType());
            }
            if (updateDeal.getConditions() != null) {
                deal.setConditions(updateDeal.getConditions());
            }
            return dealDao.update(deal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception in updating deal transaction");
        }
    }
}
