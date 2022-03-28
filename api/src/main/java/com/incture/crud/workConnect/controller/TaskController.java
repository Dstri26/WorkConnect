package com.incture.crud.workConnect.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.incture.crud.workConnect.entity.Task;
import com.incture.crud.workConnect.service.AsanaService;
import com.incture.crud.workConnect.service.SlackService;
import com.incture.crud.workConnect.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class TaskController {
    @Autowired
    private TaskService service;
    
    @Autowired
    private SlackService slackService;
    
    @Autowired
    private AsanaService asanaService;
    
    @PostMapping("/addTask")
    public Task addTask(@RequestBody Task task) {
        return service.saveTask(task);
    }

    @PostMapping("/addTasks")
    public List<Task> addTasks(@RequestBody List<Task> tasks) {
        return service.saveTasks(tasks);
    }

    @GetMapping("/Tasks")
    public List<Task> findAllTasks() {
        return service.getTasks();
    }
    
    @GetMapping("/fetchSlack")
    public String fetchSlackData() throws IOException, InterruptedException {
        return slackService.fetchSlackData();
    }
    @GetMapping("/fetchAsana")
    public String fetchAsanaData() throws IOException, InterruptedException {
        return asanaService.fetchAsanaData();
    }
    
    @GetMapping("/updateStatus/{id}")
    public Map<String, String> updateStatus(@PathVariable int id) {
        return service.updateStatus(id);
    }
    
    @GetMapping("/TaskById/{id}")
    public Task findTaskById(@PathVariable int id) {
        return service.getTaskById(id);
    }
    
    @GetMapping("/TaskByEmail/{email}")
    public List<Task> findTaskByEmail(@PathVariable String email) {
        return service.getTaskByEmail(email);
    }
    
    @GetMapping("/TaskByEmailPlatform/{email}/{platform}")
    public List<Task> findTaskByEmailPlatform(@PathVariable String email, @PathVariable String platform) {
        return service.getTaskByEmailPlatform(email,platform);
    }
    
    @PutMapping("/update")
    public Task updateTask(@RequestBody Task task) {
        return service.updateTask(task);
    }

    @DeleteMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable int id) {
        return service.deleteTask(id);
    }
    

    

}
