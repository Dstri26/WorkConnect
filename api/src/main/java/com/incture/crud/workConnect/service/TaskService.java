package com.incture.crud.workConnect.service;

import java.util.List;

import com.incture.crud.workConnect.entity.Task;
import com.incture.crud.workConnect.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public Task saveTask(Task task) {
        return repository.save(task);
    }
    public List<Task> saveTasks(List<Task> tasks) {
        return repository.saveAll(tasks);
    }
    public List<Task> getTasks() {
        return repository.findAll();
    }
    public Task getTaskById(int id) {
        return repository.findById(id).orElse(null);
    }
    public List<Task> getTaskByEmail(String email) {
        return repository.findByEmail(email);
    }
    public List<Task> getTaskByEmailPlatform(String email, String platform) {
        return repository.findByEmailPlatform(email,platform);
    }
    
    public String deleteTask(int id) {
        repository.deleteById(id);
        return "Task removed " + id;
    }
    
    public Task updateTask(Task task) {
        Task existingTask = repository.findById(task.getId()).orElse(null);
        existingTask.setTaskName(task.getTaskName());
        existingTask.setEmail(task.getEmail());
        existingTask.setPlatform(task.getPlatform());
        existingTask.setStatus(task.getStatus());
        return repository.save(existingTask);
    }
}