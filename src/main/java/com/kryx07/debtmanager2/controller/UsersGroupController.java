package com.kryx07.debtmanager2.controller;

import com.kryx07.debtmanager2.model.users.Group;
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
@RequestMapping("/users/{id}/groups")
public class UsersGroupController {

    private final UsersService usersService;
    private final DueService dueService;
    private final TransactionService transactionService;
    private final GroupService groupService;


    @Autowired
    public UsersGroupController(UsersService usersService, GroupService groupService, DueService dueService, TransactionService transactionService) {
        this.usersService = usersService;
        this.dueService = dueService;
        this.transactionService = transactionService;
        this.groupService = groupService;
    }



    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Group>> getAllGroupsByUserId(@PathVariable int id) {

        List<Group> groups = groupService.getAllByUserId(id);
        if (groups == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return !groups.isEmpty() ?
                new ResponseEntity<>(groups, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
