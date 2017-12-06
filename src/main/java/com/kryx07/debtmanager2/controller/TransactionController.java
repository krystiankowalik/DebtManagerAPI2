package com.kryx07.debtmanager2.controller;


import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import com.kryx07.debtmanager2.service.DueService;
import com.kryx07.debtmanager2.service.GroupService;
import com.kryx07.debtmanager2.service.TransactionService;
import com.kryx07.debtmanager2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final DueService dueService;
    private final GroupService groupService;
    private final UsersService usersService;

    @Autowired
    public TransactionController(TransactionService transactionService, DueService dueService, GroupService groupService, UsersService usersService) {
        this.transactionService = transactionService;
        this.dueService = dueService;
        this.groupService = groupService;
        this.usersService = usersService;
    }

    @RequestMapping(name = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Transaction>> findAll() {
        List<Transaction> transactions = transactionService.getAll();
        return !transactions.isEmpty() ?
                new ResponseEntity<>(transactions, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(name = "/", value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Transaction> findOne(@PathVariable int id) {
        return transactionService.exists(id) ?
                new ResponseEntity<>(transactionService.get(id), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Transaction> add(@RequestBody Transaction transaction) {
        transaction.setGroup(groupService.get(transaction.getGroup().getId()));
        transaction.setPayer(usersService.get(transaction.getPayer().getId()));
        transaction.setDues(dueService.calculatePayablesFromTransaction(transaction));
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
        transactionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(name = "/", value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Transaction> update(@PathVariable int id, @RequestBody Transaction transaction) {
        if (!transactionService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Transaction oldTransaction = transactionService.get(id);
        transaction.setId(id);
        User payer = usersService.get(transaction.getPayer().getId());
        Group group = groupService.get(transaction.getGroup().getId());
        transaction.setPayer(payer);
        transaction.setGroup(group);
        transaction.setDues(dueService.calculatePayablesFromTransaction(transaction));
        transaction.setAddedTime(oldTransaction.getAddedTime());
        Transaction updatedTransaction = transactionService.save(transaction);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);

    }
}
