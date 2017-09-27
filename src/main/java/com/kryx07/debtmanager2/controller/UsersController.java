package com.kryx07.debtmanager2.controller;

import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import com.kryx07.debtmanager2.service.GroupService;
import com.kryx07.debtmanager2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final GroupService groupService;

    @Autowired
    public UsersController(UsersService usersService, GroupService groupService) {
        this.usersService = usersService;
        this.groupService = groupService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Void> getAllUsers() {
        usersService.save(new User("J", "K"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/all2", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Void> getAllUsers2() {
        final Set<User> users = new HashSet<>();
        users.add(usersService.get(1));
        users.add(usersService.get(2));
        users.add(usersService.get(3));
        Group group = new Group( "name");
        users.forEach(u->users.add(u));
        groupService.save(group);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/all3/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Void> getAllUsers3(@PathVariable int id) {
        //System.out.println(usersService.get(1));
        System.out.println(usersService.get(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/all4/{id}", produces = "application/json")
    public ResponseEntity<Void> getAllUsers4(@PathVariable(value = "id") int id) {
        //System.out.println(usersService.get(1));
        System.out.println(groupService.get(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
