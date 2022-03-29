package com.incture.crud.workConnect.controller;

import java.util.List;
import java.util.Map;

import com.incture.crud.workConnect.entity.Task;
import com.incture.crud.workConnect.entity.User;
import com.incture.crud.workConnect.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class UserController {
    @Autowired
    private UserService service;
    
    @GetMapping("/Users")
    public List<User> findAllUsers() {
        return service.getUsers();
    }
    
    @PostMapping("/registerUser")
    public Map<String, String> addUser(@RequestBody User User) {
        return service.registerUser(User);
    }
    
    @PostMapping("/loginUser")
    public Map<String, String> loginUser(@RequestBody User User) {
        return service.loginUser(User);
    }
    
    @PostMapping("/logoutUser")
    public Map<String, String> logoutUser(@RequestBody User User) {
        return service.logoutUser(User);
    }
    
    /**@GetMapping("/scores")
    public int getScores(User user)
    {
    	return service.getScores(user);
    }**/

//
//    @GetMapping("/UserById/{id}")
//    public User findUserById(@PathVariable int id) {
//        return service.getUserById(id);
//    }
//    @GetMapping("/UserByEmail/{email}")
//    public List<User> findUserByEmail(@PathVariable String email) {
//        return service.getUserByEmail(email);
//    }
//    
//    @GetMapping("/UserByEmailPlatform/{email}/{platform}")
//    public List<User> findUserByEmailPlatform(@PathVariable String email, @PathVariable String platform) {
//        return service.getUserByEmailPlatform(email,platform);
//    }
//    
//    @PutMapping("/update")
//    public User updateUser(@RequestBody User User) {
//        return service.updateUser(User);
//    }
//
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }

}
