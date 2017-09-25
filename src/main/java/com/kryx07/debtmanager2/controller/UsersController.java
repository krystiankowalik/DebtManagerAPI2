package com.kryx07.debtmanager2.controller;

import com.kryx07.debtmanager2.model.users.User;
import com.kryx07.debtmanager2.model.users.UsersGroup;
import com.kryx07.debtmanager2.service.UserGroupService;
import com.kryx07.debtmanager2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final UserGroupService userGroupService;

    @Autowired
    public UsersController(UsersService usersService, UserGroupService userGroupService) {
        this.usersService = usersService;
        this.userGroupService = userGroupService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Void> getAllUsers() {
        usersService.save(new User("J", "K"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/all2", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Void> getAllUsers2() {
        Set<User> users = new HashSet<>();
        users.add(usersService.get(1));
        users.add(usersService.get(2));
        users.add(usersService.get(3));
        UsersGroup usersGroup = new UsersGroup(users);

        userGroupService.save(usersGroup);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/all3", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Void> getAllUsers3() {
        System.out.println(usersService.get(1));
        return new ResponseEntity<>(HttpStatus.OK);
    }



  /*  private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UsersGroup> getAllUsers() {
        return new ResponseEntity<UsersGroup>(usersService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)//,consumes = "application/json")
    public ResponseEntity<User>   addUser(@RequestBody User user) {

        URI uri = null;
        try {
            uri = new URI(ServletUriComponentsBuilder.fromCurrentRequestUri().build() + "/" + user.getUserName());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return usersService.addUser(user)?
                ResponseEntity.created(uri).build() :
                ResponseEntity.badRequest().build();
    }*/
}
