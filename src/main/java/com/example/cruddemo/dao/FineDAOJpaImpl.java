package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Fine;
import jakarta.persistence.EntityManager;
// import jakarta.persistence.Id;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FineDAOJpaImpl implements FineDAO {

    // define fields for entitymanager
    private EntityManager entityManager;

    // setup constructor injection
    @Autowired
    public FineDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Fine> findAll() {

        // create a query
        TypedQuery<Fine> theQuery = entityManager.createQuery(
                "SELECT f FROM Fine f", Fine.class
        );
        // execute the query
        List<Fine> fines = theQuery.getResultList();

        //return the results
        return fines;
    }

    @Override
    public Fine findById(int theId) {
        // get the fine
        Fine theFine = entityManager.find(Fine.class, theId);
        // return the fine
        return theFine;
    }

    public Fine findByReference(String theReference) {
        // get the fine

        // create a query
        TypedQuery<Fine> theQuery = entityManager.createQuery(
                "SELECT f FROM Fine f WHERE f.reference=:theReference", Fine.class
        );
        theQuery.setParameter("theReference", theReference);

        // execute the query
        Fine theFine = theQuery.getSingleResult();

        // return the fine
        return theFine;
    }

    @Override
    public Fine save(Fine theFine) {
        // save the fine
        Fine dbFine = entityManager.merge(theFine);

        // return the dbFine
        return dbFine;
    }

    @Override
    public Fine update(Fine theFine) {
        // save the fine
        Fine dbFine = entityManager.merge(theFine);

        // return the dbFine
        return dbFine;
    }

    @Override
    public void deleteById(int theId) {
        Fine theFine = entityManager.find(Fine.class, theId);
        entityManager.remove(theFine);
    }

}
