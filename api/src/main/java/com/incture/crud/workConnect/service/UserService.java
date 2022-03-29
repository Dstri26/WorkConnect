package com.incture.crud.workConnect.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.incture.crud.workConnect.entity.User;
import com.incture.crud.workConnect.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    
    //Service to retrieve all tasks from database
    public List<User> getUsers() {
        return repository.findAll();
    }
    
    //Registration Service
    public Map<String, String> registerUser(User newUser) {
    	List<User> users = repository.findAll();
    	
        HashMap<String, String> res = new HashMap<>();
        for (User user : users) {
            if (user.equals(newUser)) {
                res.put("email", newUser.getEmail());
                res.put("status", "0");
                res.put("msg", "User Already exists!");
                
                return res;
            }
        }
        res.put("email", newUser.getEmail());
        res.put("status", "1");
        res.put("msg", "User Registered Successfully!");
        System.out.println("Registered user: " + newUser.toString());
        newUser.setLoggedIn("true");
        repository.save(newUser);
        return res;
    }
    
    //Login Service
    public Map<String, String> loginUser(User user) {
    	User resUser = repository.findByEmailPass(user.getEmail(),user.getPassword());
    	HashMap<String, String> res = new HashMap<>();
    	if(resUser == null) {
            res.put("email", user.getEmail());
            res.put("status", "0");
            res.put("msg", "User Log In Failed!");
            
            return res;
    	}
    	
        res.put("email", resUser.getEmail());
        res.put("status", "1");
        res.put("msg", "User Logged In Successfully!");
        resUser.setLoggedIn("true");
        repository.save(resUser);
    	System.out.println("Login user: " + resUser);
    	//System.out.println("Login user: " + resUser.toString());
    	return res;
    }
    
    //Logout Service
    public Map<String, String> logoutUser(User rUser) {
    	User user = repository.findByEmail(rUser.getEmail());
    	HashMap<String, String> res = new HashMap<>();    	
        res.put("email", user.getEmail());
        res.put("status", "1");
        res.put("msg", "User Logged Out Successfully!");
        user.setLoggedIn("false");
        repository.save(user);
    	System.out.println("Logged-out user: " + user);
    	return res;
    }
    
    public String deleteUser(int id) {
        repository.deleteById(id);
        return "User removed " + id;
    }
    
    public User updateUser(User User) {
        User existingUser = repository.findById(User.getId()).orElse(null);
        existingUser.setName(User.getName());
        existingUser.setEmail(User.getEmail());
        existingUser.setPassword(User.getPassword());
        existingUser.setPhoneNo(User.getPhoneNo());
        return repository.save(existingUser);
    }
    
   /** public int getScores(User user)
    {
    	String email = user.getEmail();
    	int score = repository.findTaskCompletedByEmail(email);
    	return score;
    }**/
      
}