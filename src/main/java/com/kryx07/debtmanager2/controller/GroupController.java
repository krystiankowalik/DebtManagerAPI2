package com.kryx07.debtmanager2.controller;

import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import com.kryx07.debtmanager2.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(value = "/byUserId", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Group>> getGroupsByUser(@RequestParam int userId) {
        return new ResponseEntity<>(groupService.getAllByUserId(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Group>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Group> addGroup(@RequestBody Group group) {
        Group newGroup = groupService.save(group);
        return newGroup != null ? new ResponseEntity<>(newGroup, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group) {
        Group updatedGroup = groupService.save(group);
        return updatedGroup != null ? new ResponseEntity<>(updatedGroup, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}/addUsers", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Group> addUsersToGroup(@PathVariable int id, @RequestBody List<User> users) {
        Group updatedGroup = groupService.get(id);
        users.forEach(updatedGroup::addUser);
        updatedGroup = groupService.save(updatedGroup);
        return updatedGroup != null ? new ResponseEntity<>(updatedGroup, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

