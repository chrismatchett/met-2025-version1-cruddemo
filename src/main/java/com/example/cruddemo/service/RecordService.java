package com.example.cruddemo.service;

import com.example.cruddemo.entity.Record;
import java.util.List;

public interface RecordService {
    List<Record> findAll();
    Record findById(int theId);
    Record findByReference(String theReference);
    Record save(Record theRecord);
    Record update(Record theRecord);
    void deleteById(int theId);
}
