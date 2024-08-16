package com.example.cruddemo.service;

import com.example.cruddemo.dao.FineDAO;
import com.example.cruddemo.entity.Fine;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FineServiceImpl implements FineService {

    private FineDAO fineDAO;

    @Autowired
    FineServiceImpl(FineDAO theFineDAO) {
        fineDAO = theFineDAO;
    }

    @Override
    public List<Fine> findAll() {
        return fineDAO.findAll();
    }

    @Override
    public Fine findById(int theId) {
        return fineDAO.findById(theId);
    }

    @Override
    public Fine findByReference(String theReference) { return fineDAO.findByReference(theReference); }

    @Override
    @Transactional
    public Fine save(Fine theFine) {
        return fineDAO.save(theFine);
    }

    @Override
    @Transactional
    public Fine update(Fine theFine) {
        return fineDAO.update(theFine);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        fineDAO.deleteById(theId);
    }
}
