package com.example.cruddemo.service;

import com.example.cruddemo.entity.Fine;
import java.util.List;

public interface FineService {
    List<Fine> findAll();
    Fine findById(int theId);
    Fine findByReference(String theReference);
    Fine save(Fine theFine);
    Fine update(Fine theFine);
    void deleteById(int theId);
}
