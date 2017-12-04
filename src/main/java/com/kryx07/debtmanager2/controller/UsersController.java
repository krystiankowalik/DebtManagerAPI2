package com.kryx07.debtmanager2.controller;

import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.model.users.User;
import com.kryx07.debtmanager2.service.GroupService;
import com.kryx07.debtmanager2.service.PayableService;
import com.kryx07.debtmanager2.service.TransactionService;
import com.kryx07.debtmanager2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final PayableService payableService;
    private final TransactionService transactionService;


    @Autowired
    public UsersController(UsersService usersService, GroupService groupService, PayableService payableService, TransactionService transactionService) {
        this.usersService = usersService;
        this.payableService = payableService;
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.findAll();
        return Optional
                .of(new ResponseEntity<>(users, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getById(@PathVariable int id) {
        return new ResponseEntity<>(usersService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> addUserByBody(@RequestBody User newUser) {
        User addedUser = usersService.save(newUser);
        return addedUser != null ?
                new ResponseEntity<>(addedUser, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable int id) {
        if (!usersService.userExists(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return usersService.delete(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (!usersService.userExists(user.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User updatedUser = usersService.save(user);
        return updatedUser != null ? new ResponseEntity<>(updatedUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/addByParams", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> addUserByParams(@RequestParam String username, @RequestParam String password) {
        User newUser = new User(username, password);
        usersService.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}/transactions", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Transaction>> getAllTransactionsByUserId(@PathVariable int id) {

        List<Transaction> transactions = transactionService.findAllByUser_Id(id);

        return !transactions.isEmpty() ?
                new ResponseEntity<>(transactions, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



    /*@RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        return new ResponseEntity<>(usersService.getByUserName(username), HttpStatus.OK);
    }*/

    @RequestMapping(value = "/exists/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Boolean> userNameExists(@RequestParam String username) {
        return new ResponseEntity<>(usersService.userExists(username), HttpStatus.OK);
    }


}
