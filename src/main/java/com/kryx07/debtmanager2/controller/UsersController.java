package com.kryx07.debtmanager2.controller;

import com.kryx07.debtmanager2.model.users.User;
import com.kryx07.debtmanager2.service.GroupService;
import com.kryx07.debtmanager2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService, GroupService groupService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(usersService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addByBody", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> addUserByBody(@RequestBody User newUser) {
        User addedUser = usersService.save(newUser);
        return addedUser != null ? new ResponseEntity<>(addedUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/addByParams", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> addUserByParams(@RequestParam String username, @RequestParam String password) {
        User newUser = new User(username, password);
        usersService.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getById(@PathVariable int id) {
        return new ResponseEntity<>(usersService.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByUsername", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getByUsername(@RequestParam String username) {
        return new ResponseEntity<>(usersService.getByUserName(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/exists/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Boolean> userNameExists(@RequestParam String username) {
        return new ResponseEntity<>(usersService.userExists(username), HttpStatus.OK);
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
}
