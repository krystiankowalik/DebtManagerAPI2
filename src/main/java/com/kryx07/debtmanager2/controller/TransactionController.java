package com.kryx07.debtmanager2.controller;


import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(name = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Transaction>> findAll() {
        return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(name = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Transaction> add(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.add(transaction);
        return newTransaction != null ? new ResponseEntity<>(newTransaction, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(name = "/delete", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Transaction> delete(@RequestParam int id) {
        if(!transactionService.exists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean deleted = transactionService.delete(id);
        return deleted ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
