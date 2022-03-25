package com.incture.crud.workConnect.service;

import com.incture.crud.workConnect.entity.Task;
import com.incture.crud.workConnect.repository.TaskRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {	
	
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
    
    //Service to update task from database by id
    public Map<String, String> updateStatus(int id) {
    	HashMap<String, String> res = new HashMap<>();
    	Task newTask = repository.findById(id).orElse(null);
    	if(newTask==null) {
    		res.put("status", "0");
    		res.put("action", "failed");
    	}
    	else {
    		if(newTask.getStatus()==0) {
        		newTask.setStatus(1);
        		newTask.setIsCompleted(1);
        		newTask = updateTask(newTask);
        		res.put("status", "1");
        		res.put("action", "update");
        	}
        	else if(newTask.getStatus()==1) {
        		newTask.setIsDeleted(1);
        		newTask = updateTask(newTask);
        		res.put("status", "1");
        		res.put("action", "delete");
        	}
    	}
    	
        return res;
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