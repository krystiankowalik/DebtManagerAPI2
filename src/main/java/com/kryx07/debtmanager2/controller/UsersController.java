package com.kryx07.debtmanager2.controller;

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
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final DueService dueService;
    private final TransactionService transactionService;
    private final GroupService groupService;


    @Autowired
    public UsersController(UsersService usersService, GroupService groupService, DueService dueService, TransactionService transactionService) {
        this.usersService = usersService;
        this.dueService = dueService;
        this.transactionService = transactionService;
        this.groupService = groupService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.getAll();
        return Optional
                .of(new ResponseEntity<>(users, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getById(@PathVariable int id) {
        return new ResponseEntity<>(usersService.get(id), HttpStatus.OK);
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
        if (!usersService.exists(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        usersService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (!usersService.exists(user.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User updatedUser = usersService.save(user);
        return updatedUser != null ? new ResponseEntity<>(updatedUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


  /*  @RequestMapping(value = "/{id}/transactions", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Transaction>> getAllTransactionsByUserId(@PathVariable int id) {

        List<Transaction> transactions = transactionService.findAllByUser_Id(id);
        if (transactions == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return !transactions.isEmpty() ?
                new ResponseEntity<>(transactions, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }*/

  /*  @RequestMapping(value = "/{id}/groups", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Group>> getAllGroupsByUserId(@PathVariable int id) {

        List<Group> groups = groupService.getAllByUserId(id);
        if (groups == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return !groups.isEmpty() ?
                new ResponseEntity<>(groups, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

/*
    @RequestMapping(value = "{id}/dues", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Due>> getAllDuesByGroup(@PathVariable int id) {
        if (!usersService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Due> dues = dueService.getAllByGroups(usersService.get(id).getGroups());


        return dues == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(dues, HttpStatus.OK);
    }
*/


}
