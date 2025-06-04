package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.redis.redisUser;
import com.example.demo.user.jpa.User;

import user.user;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    @Autowired
    private final jobs job;
    @Autowired
    private final UserService userService;

    public UserController(UserService userService, jobs job) {
        this.job = job;
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // @GetMapping("{name}")
    // public User createUser(@PathVariable("name") String name){
    //     return userService.getUserByname(name);
    // }
    @PostMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user.getName());
    }

    @PostMapping("/redis")
    public redisUser addRedisUser(@RequestBody redisUser user) {
        return userService.addUserRedis(user);
    }

    @GetMapping("{name}")
    public void job(@PathVariable String name) {
        job.scheduling(name);
        job.sched();
    }

    @PostMapping("/kafka")
    public void asyncomms(@RequestBody user User) {
        userService.sendMessage(User);
    }
}
