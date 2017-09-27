package com.kryx07.debtmanager2.controller;

import com.kryx07.debtmanager2.service.GroupService;
import com.kryx07.debtmanager2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService, GroupService groupService) {
        this.usersService = usersService;
    }
}
