package com.kryx07.debtmanager2.controller;

import com.kryx07.debtmanager2.model.transaction.Transaction;
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
@RequestMapping("/users/{id}/transactions")
public class UsersTransactionController {

    private final UsersService usersService;
    private final DueService dueService;
    private final TransactionService transactionService;
    private final GroupService groupService;
    private final TransactionController transactionController;


    @Autowired
    public UsersTransactionController(UsersService usersService, GroupService groupService, DueService dueService, TransactionService transactionService, TransactionController transactionController) {
        this.usersService = usersService;
        this.dueService = dueService;
        this.transactionService = transactionService;
        this.groupService = groupService;
        this.transactionController = transactionController;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Transaction>> getAllTransactionsByUserId(@PathVariable int id) {

        List<Transaction> transactions = transactionService.findAllByUser_Id(id);
        if (transactions == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return !transactions.isEmpty() ?
                new ResponseEntity<>(transactions, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value = "/{transactionId}/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable int transactionId, @RequestBody Transaction transaction) {
        return transactionController.update(transactionId, transaction);
    }

    @RequestMapping(value= "/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        return transactionController.add(transaction);
    }

}
