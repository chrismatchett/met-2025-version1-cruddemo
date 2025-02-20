package com.example.cruddemo.service;

import com.example.cruddemo.dao.RecordDAO;
import com.example.cruddemo.entity.Record;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    private RecordDAO recordDAO;

    @Autowired
    public RecordServiceImpl(RecordDAO theRecordDAO) {
        recordDAO = theRecordDAO;
    }

    @Override
    public List<Record> findAll() {
        return recordDAO.findAll();
    }

    @Override
    public Record findById(int theId) {
        return recordDAO.findById(theId);
    }

    @Override
    public Record findByReference(String theReference) { 
        return recordDAO.findByReference(theReference); 
    }

    @Override
    @Transactional
    public Record save(Record theRecord) {
        return recordDAO.save(theRecord);
    }

    @Override
    @Transactional
    public Record update(Record theRecord) {
        return recordDAO.update(theRecord);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        recordDAO.deleteById(theId);
    }
}
