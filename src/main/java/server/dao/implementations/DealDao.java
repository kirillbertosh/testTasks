package server.dao.implementations;

import server.dao.GenericDao;
import server.dbException.DbException;
import server.entities.Deal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DealDao implements GenericDao<Deal, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Deal create(Deal deal) throws DbException {
        try {
            entityManager.persist(deal);
            return deal;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while persisting new deal");
        }
    }

    @Override
    public Deal update(Deal deal) throws DbException {
        try {
            return entityManager.merge(deal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while updating deal with id = " + deal.getId());
        }
    }

    @Override
    public void delete(Deal deal) throws DbException {
        try {
            entityManager.remove(deal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while deleting deal with id = " + deal.getId());
        }
    }

    @Override
    public List getAll() throws DbException {
        try {
            return entityManager.createQuery("from Deal c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting list of all deals");
        }
    }

    @Override
    public Deal getById(Long id) throws DbException {
        try {
            return entityManager.find(Deal.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DbException("Exception while getting deal with id = " + id);
        }
    }
}
