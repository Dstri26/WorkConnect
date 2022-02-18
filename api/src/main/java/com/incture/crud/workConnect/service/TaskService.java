package com.incture.crud.workConnect.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.incture.crud.workConnect.entity.Task;
import com.incture.crud.workConnect.repository.TaskRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	
	private static HashMap<String,String> users= new HashMap<String,String>();
	
	
    @Autowired
    private TaskRepository repository;
    
    //Service to add single task to database
    public Task saveTask(Task task) {
        return repository.save(task);
    }
    
    //Service to add multiple tasks to database
    public List<Task> saveTasks(List<Task> tasks) {
        return repository.saveAll(tasks);
    }
    
    //Service to retrieve all tasks from database
    public List<Task> getTasks() {
        return repository.findAll();
    }
    
    //Service to retrieve task from database by id
    public Task getTaskById(int id) {
        return repository.findById(id).orElse(null);
    }
    
    //Service to retrieve tasks from database by email
    public List<Task> getTaskByEmail(String email) {
        return repository.findByEmail(email);
    }
    
    //Service to retrieve task from database by email and platform
    public List<Task> getTaskByEmailPlatform(String email, String platform) {
        return repository.findByEmailPlatform(email,platform);
    }
    
    //Service to delete task from database by id
    public String deleteTask(int id) {
        repository.deleteById(id);
        return "Task removed " + id;
    }
    
    //Service to update a task in database
    public Task updateTask(Task task) {
        Task existingTask = repository.findById(task.getId()).orElse(null);
        existingTask.setTaskName(task.getTaskName());
        existingTask.setReceiver(task.getReceiver());
        existingTask.setPlatform(task.getPlatform());
        existingTask.setStatus(task.getStatus());
        return repository.save(existingTask);
    }
}