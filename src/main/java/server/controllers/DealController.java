package server.controllers;

import server.dbException.DbException;
import server.entities.Deal;
import server.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/v1")
public class DealController {

    @Autowired
    private DealService service;

    @GetMapping("/deals")
    public ResponseEntity getAll() throws DbException {
        List list = service.getAll();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/deals/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws DbException {
        Deal deal = service.getById(id);
        if (deal != null) {
            return new ResponseEntity<>(deal, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deals")
    public ResponseEntity<Deal> create(@RequestBody Deal deal) throws DbException {
        deal = service.create(deal);
        return new ResponseEntity<>(deal, HttpStatus.CREATED);
    }

    @PutMapping("/deals/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Deal deal) throws DbException {
        deal = service.update(id, deal);
        if (deal != null) {
            return new ResponseEntity<>(deal, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deals/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if(service.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
