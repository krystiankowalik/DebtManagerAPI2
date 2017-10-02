package com.kryx07.debtmanager2.controller;


import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        List<Transaction> transactions = transactionService.findAll();
        return !transactions.isEmpty() ?
                new ResponseEntity<>(transactions, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(name = "/", value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Transaction> findOne(@PathVariable int id) {
        return transactionService.exists(id) ?
                new ResponseEntity<>(transactionService.findOne(id), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(name = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Transaction> add(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.add(transaction);
        return newTransaction != null ?
                new ResponseEntity<>(newTransaction, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(name = "/", value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Transaction> delete(@PathVariable int id) {
        if (!transactionService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        boolean deleted = transactionService.delete(id);
        return deleted ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(name = "/", value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Transaction> update(@PathVariable int id, Transaction transaction) {
        if (!transactionService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        transaction.setId(id);
        Transaction updatedTransaction = transactionService.update(transaction);
        return updatedTransaction.equals(transaction) ?
                new ResponseEntity<>(updatedTransaction, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
