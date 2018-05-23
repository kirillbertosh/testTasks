package server.controllers;

import server.exceptions.DbException;
import server.entities.Deal;
import server.entities.User;
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

    @DeleteMapping("/deals/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws DbException {
        if(service.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deals")
    public ResponseEntity save(@RequestBody Deal deal) throws DbException{
        deal = service.save(deal);
        return new ResponseEntity<>(deal, HttpStatus.OK);
    }

    @PutMapping("/deals/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Deal updateDeal) throws DbException {
        updateDeal = service.update(id, updateDeal);
        if (updateDeal != null) {
            return new ResponseEntity<>(updateDeal, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
