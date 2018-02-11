package ua.com.mnjl.userkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.mnjl.userkeeper.model.User;
import ua.com.mnjl.userkeeper.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public  List<User> getAll(){
        return userService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<User> getUser(@PathVariable("id") long id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
         return userService.save(user);
    }
}
