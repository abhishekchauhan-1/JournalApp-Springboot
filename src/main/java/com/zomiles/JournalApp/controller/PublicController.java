package com.zomiles.JournalApp.controller;

import com.zomiles.JournalApp.entity.User;
import com.zomiles.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")

public class PublicController {

    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/create-user")
    public boolean createUser(@RequestBody User user){
        userService.saveEntry(user);
        return true;
    }

}
