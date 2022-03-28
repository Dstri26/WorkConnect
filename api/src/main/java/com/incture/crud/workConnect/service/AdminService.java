package com.incture.crud.workConnect.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.crud.workConnect.entity.Admin;
import com.incture.crud.workConnect.entity.User;
import com.incture.crud.workConnect.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository repository;
	
	
	//Login Service
    public Map<String, String> loginAdmin(Admin admin) {
    	Admin readmin = repository.findByEmailPass(admin.getEmail(),admin.getPassword());
    	HashMap<String, String> res = new HashMap<>();
    	if(readmin == null) {
            res.put("email", admin.getEmail());
            res.put("status", "0");
            res.put("msg", "Admin Log In Failed!");
            
            return res;
    	}
    	
        res.put("email", readmin.getEmail());
        res.put("status", "1");
        res.put("msg", "Admin Logged In Successfully!");
        readmin.setLoggedIn("true");
        repository.save(readmin);
    	System.out.println("Login user: " + readmin);
    	//System.out.println("Login user: " + resUser.toString());
    	return res;
    }
    
    //Logout Service
    public Map<String, String> logoutAdmin(Admin radmin) {
    	Admin admin = repository.findByEmail(radmin.getEmail());
    	HashMap<String, String> res = new HashMap<>();    	
        res.put("email", admin.getEmail());
        res.put("status", "1");
        res.put("msg", "User Logged Out Successfully!");
        admin.setLoggedIn("false");
        repository.save(admin);
    	System.out.println("Logged-out user: " + admin);
    	return res;
    }
    
    //delete admin
    
    public String deleteAdmin(int id) {
        repository.deleteById(id);
        return "Admin removed " + id;
    }
    
    //update admin
    public Admin updateAdmin(Admin admin) {
        Admin existingadmin = repository.findById(admin.getId()).orElse(null);
        existingadmin.setName(admin.getName());
        existingadmin.setEmail(admin.getEmail());
        existingadmin.setPassword(admin.getPassword());
        existingadmin.setPhoneno(admin.getPhoneno());
        return repository.save(existingadmin);
    }


}
