package com.kryx07.debtmanager2.controller;

import com.kryx07.debtmanager2.model.due.Due;
import com.kryx07.debtmanager2.service.DueService;
import com.kryx07.debtmanager2.service.GroupService;
import com.kryx07.debtmanager2.service.TransactionService;
import com.kryx07.debtmanager2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users/{id}/dues")
public class UsersDuesController {

    private final UsersService usersService;
    private final DueService dueService;
    private final TransactionService transactionService;
    private final GroupService groupService;


    @Autowired
    public UsersDuesController(UsersService usersService, GroupService groupService, DueService dueService, TransactionService transactionService) {
        this.usersService = usersService;
        this.dueService = dueService;
        this.transactionService = transactionService;
        this.groupService = groupService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Due>> getAllDuesByGroup(@PathVariable int id) {
        if (!usersService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Due> dues = dueService.getAllByGroups(usersService.get(id).getGroups());


        return dues == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(dues, HttpStatus.OK);
    }


}
