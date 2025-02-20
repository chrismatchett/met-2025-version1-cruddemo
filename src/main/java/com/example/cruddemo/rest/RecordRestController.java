package com.example.cruddemo.rest;

import com.example.cruddemo.entity.Record;
import com.example.cruddemo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecordRestController {

    private RecordService recordService;

    @Autowired
    public RecordRestController(RecordService theRecordService) {
        recordService = theRecordService;
    }

    // expose "/records" and return a list of records
    @GetMapping("/records")
    public List<Record> findAll() {
        return recordService.findAll();
    }

    // add mapping for GET /record/{recordId}
    @GetMapping("/record/{recordId}")
    public Record findById(@PathVariable int recordId) {
        Record theRecord = recordService.findById(recordId);
        if (theRecord == null) {
            throw new RuntimeException("Record ID not found: " + recordId);
        }
        return theRecord;
    }

    // add mapping for GET /record/reference/{recordReference}
    @GetMapping("/record/reference/{recordReference}")
    public Record findByReference(@PathVariable String recordReference) {
        Record theRecord = recordService.findByReference(recordReference);
        if (theRecord == null) {
            throw new RuntimeException("Record reference not found: " + recordReference);
        }
        return theRecord;
    }

    // add mapping for POST /records - add new record
    @PostMapping("/records")
    public Record addRecord(@RequestBody Record theRecord) {
        // to handle any ID set in JSON - as this is to add NEW record data
        theRecord.setId(0);
        return recordService.save(theRecord);
    }

    // add mapping for PUT /records - update record
    @PutMapping("/records")
    public Record updateRecord(@RequestBody Record theRecord) {
        return recordService.update(theRecord);
    }

    // add mapping for DELETE /record/{recordId}
    @DeleteMapping("/record/{recordId}")
    public String deleteRecord(@PathVariable int recordId) {
        Record tempRecord = recordService.findById(recordId);
        if (tempRecord == null) {
            throw new RuntimeException("Record ID not found: " + recordId);
        }
        recordService.deleteById(recordId);
        return "Record deleted: " + recordId;
    }
}
