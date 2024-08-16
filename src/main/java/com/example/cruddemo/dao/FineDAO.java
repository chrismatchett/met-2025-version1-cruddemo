package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Fine;
import java.util.List;

public interface FineDAO {
    List<Fine> findAll();
    Fine findById(int theId);
    Fine findByReference(String theReference);
    Fine save(Fine theFine);
    Fine update(Fine theFine);
    void deleteById(int theId);
}
