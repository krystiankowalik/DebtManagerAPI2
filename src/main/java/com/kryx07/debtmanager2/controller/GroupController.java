package com.kryx07.debtmanager2.controller;

import com.kryx07.debtmanager2.model.due.Due;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import com.kryx07.debtmanager2.service.GroupService;
import com.kryx07.debtmanager2.service.DueService;
import com.kryx07.debtmanager2.service.TransactionService;
import com.kryx07.debtmanager2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final TransactionService transactionService;
    private final DueService dueService;
    private final UsersService usersService;

    @Autowired
    public GroupController(GroupService groupService, TransactionService transactionService, DueService dueService, UsersService usersService) {
        this.groupService = groupService;
        this.transactionService = transactionService;
        this.dueService = dueService;
        this.usersService = usersService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Group>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/byUserId", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Group>> getGroupsByUser(@RequestParam int userId) {
        return new ResponseEntity<>(groupService.getAllByUserId(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Group> addGroup(@RequestBody Group group) {
        Group newGroup = groupService.save(group);
        return newGroup != null ? new ResponseEntity<>(newGroup, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}/addUsers", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<List<User>> addUsersToGroup(@PathVariable int id, @RequestBody List<User> users) {
        Group updatedGroup = groupService.get(id);
        users.forEach(updatedGroup::addUser);
        updatedGroup = groupService.save(updatedGroup);
        return updatedGroup != null ?
                new ResponseEntity<>(updatedGroup
                        .getUsers()
                        .stream()
                        .filter(users::contains)
                        .collect(Collectors.toList()), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}/addUser/{userId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Group> addUsersToGroup(@PathVariable int id, @PathVariable int userId) {
        Group updatedGroup = groupService.get(id);
        User user = usersService.get(userId);
        updatedGroup.addUser(user);
        groupService.save(updatedGroup);

        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Group> updateGroup(@PathVariable int id, @RequestBody Group group) {
        group.setId(id);
        Group updatedGroup = groupService.save(group);

        return updatedGroup != null ?
                new ResponseEntity<>(updatedGroup, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteGroup(@PathVariable int id) {
        if (groupService.exists(id)) {
            groupService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Group> findOne(@PathVariable int id) {
        return new ResponseEntity<>(groupService.get(id), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}/transactions", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Transaction>> getAllTransactionsByGroupId(@PathVariable int id) {
        return groupService.exists(id) ?
                new ResponseEntity<>(transactionService.findAllByGroup_Id(id), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @RequestMapping(value = "{id}/dues", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Due>> getAllPayablesByGroup(@PathVariable int id) {
        return groupService.exists(id) ?
                new ResponseEntity<>(dueService.getAllByGroupId(id), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

