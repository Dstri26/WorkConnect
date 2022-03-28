package com.incture.crud.workConnect.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.incture.crud.workConnect.entity.Admin;
import com.incture.crud.workConnect.entity.User;
import com.incture.crud.workConnect.service.AdminService;

@RestController
@CrossOrigin(origins="*")
public class AdminController {
	
	@Autowired
	private AdminService service;

    
    @PostMapping("/loginAdmin")
    public Map<String, String> loginUser(@RequestBody Admin admin) {
        return service.loginAdmin(admin);
    }
    
    @PostMapping("/logoutAdmin")
    public Map<String, String> logoutUser(@RequestBody Admin admin) {
        return service.logoutAdmin(admin);
    }
    
    @PutMapping("/updateAdmin")
 public Admin updateAdmin(@RequestBody Admin admin) {
      return service.updateAdmin(admin);
  }

  @DeleteMapping("/deleteAdmin/{id}")
  public String deleteAdmin(@PathVariable int id) {
	  return service.deleteAdmin(id);
  }
  }




