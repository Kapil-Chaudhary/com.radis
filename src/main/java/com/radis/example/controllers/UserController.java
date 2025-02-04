package com.radis.example.controllers;

import com.radis.example.dao.UserDao;
import com.radis.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    // CREATE
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        System.out.println(user + "---");
        return userDao.save(user);
    }


    // GET
//    @GetMapping("/{userId}")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") String userId){
        return userDao.get(userId);
    }


    // find all
    @GetMapping
    public List<User> getUsers() {

        Map<Object, Object> all = this.userDao.findAll();
//        for( Map.Entry<Object, Object> entry : all.entrySet())
        Collection<Object> values = all.values();
        List<User> collect = values.stream().map(value -> (User) value).collect(Collectors.toList());
        return collect;
    }

//    @GetMapping
//    public Map<Object, Object> getUsers() {
//        Map<Object, Object> all = this.userDao.findAll();
//        return all;
//    }

    // delete user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        this.userDao.delete(userId);
    }

}
