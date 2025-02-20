package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Record;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordDAOJpaImpl implements RecordDAO {

    // define fields for entity manager
    private EntityManager entityManager;

    // setup constructor injection
    @Autowired
    public RecordDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Record> findAll() {
        // create a query
        TypedQuery<Record> theQuery = entityManager.createQuery(
                "SELECT r FROM Record r", Record.class
        );
        // execute the query
        return theQuery.getResultList();
    }

    @Override
    public Record findById(int theId) {
        // get the record
        return entityManager.find(Record.class, theId);
    }

    @Override
    public Record findByReference(String theReference) {
        // create a query
        TypedQuery<Record> theQuery = entityManager.createQuery(
                "SELECT r FROM Record r WHERE r.reference=:theReference", Record.class
        );
        theQuery.setParameter("theReference", theReference);

        // execute the query
        return theQuery.getSingleResult();
    }

    @Override
    public Record save(Record theRecord) {
        // save the record
        return entityManager.merge(theRecord);
    }

    @Override
    public Record update(Record theRecord) {
        // update the record
        return entityManager.merge(theRecord);
    }

    @Override
    public void deleteById(int theId) {
        Record theRecord = entityManager.find(Record.class, theId);
        if (theRecord != null) {
            entityManager.remove(theRecord);
        }
    }
}
