package com.example.cruddemo.rest;

import com.example.cruddemo.entity.Fine;
import com.example.cruddemo.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FineRestController {

    private FineService fineService;

    @Autowired
    FineRestController(FineService theFineService) {
        fineService = theFineService;
    }

    // expose "/fines" and return a list of fines
    @GetMapping("/fines")
    public List<Fine> findAll() {
       return fineService.findAll();
    }

    // add mapping for GET /fine/{fineId}
    @GetMapping("/fine/{fineId}")
    public Fine findById(@PathVariable int fineId) {
        Fine theFine = fineService.findById(fineId);
        if(theFine == null) {
            throw new RuntimeException("Fine id not found, " + fineId);
        }
        return theFine;
    }

    // add mapping for GET /fine/reference/{fineReference}
    @GetMapping("/fine/reference/{fineReference}")
    public Fine findByReference(@PathVariable String fineReference) {
        Fine theFine = fineService.findByReference(fineReference);
        if(theFine == null) {
            throw new RuntimeException("Fine reference not found, " + fineReference);
        }
        return theFine;
    }

    // add mapping for POST /fines - add new fine
    @PostMapping("/fines")
    public Fine addFine(@RequestBody Fine theFine) {
        // to handle any id set in JSON - as this is to add NEW fine data
        theFine.setId(0);
        Fine dbFine = fineService.save(theFine);
        return theFine;
    }

    // add mapping for PUT /fine/{fineId} - update fine
    @PutMapping("/fines")
    public Fine updateFine(@RequestBody Fine theFine) {
        Fine dbFine = fineService.update(theFine);
        return dbFine;
    }

    @DeleteMapping("/fine/{fineId}")
    public String deleteFine(@PathVariable int fineId) {
        Fine tempFine = fineService.findById(fineId);
        if(tempFine == null) {
            throw new RuntimeException("Fine id not found - " + fineId);
        }
        fineService.deleteById(fineId);
        return "Fine deleted = " + fineId;
    }

}
