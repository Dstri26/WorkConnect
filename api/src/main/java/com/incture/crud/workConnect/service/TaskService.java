package com.incture.crud.workConnect.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    
    private static String userName(String code) throws IOException, InterruptedException {
		if(!users.containsKey(code)) {
			
			String bot_token = "xoxb-2934006031602-3022842672419-YZUSvPsZTtrdJkaiKAXnex1Q";
			String token = "Bearer "+bot_token;
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder()
		                	.uri(URI.create("https://slack.com/api/users.profile.get" + "?user=" + code + "&pretty=1"))
		                	.header("Authorization",token)
		                	.header("Content-Type","application/x-www-form-urlencoded")
		                	.POST(HttpRequest.BodyPublishers.noBody())
		                	.build();
			HttpResponse<String> response;
		
			response = client.send(request,HttpResponse.BodyHandlers.ofString());
			
			String jsonString = response.body();
			JSONObject obj = new JSONObject(jsonString);
			users.put(code, obj.getJSONObject("profile").getString("email"));
			return obj.getJSONObject("profile").getString("email");

			
		}
		else {
			return users.get(code);
		}
    }
    
    public String fetchData() throws IOException, InterruptedException {
        //int flag=0;
        ArrayList<Task> op = new ArrayList<Task>();
        
        
        String bot_token = "xoxb-2934006031602-3022842672419-YZUSvPsZTtrdJkaiKAXnex1Q";
		String token = "Bearer "+bot_token;
		String channel_id = "C0313AG3ZPB";
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://slack.com/api/conversations.history" + "?channel=" + channel_id + "&pretty=1"))
		                .header("Authorization",token)
		                .header("Content-Type","application/x-www-form-urlencoded")
		                .POST(HttpRequest.BodyPublishers.noBody())
		                .build();
		
		

		HttpResponse<String> response;
		
		response = client.send(request,HttpResponse.BodyHandlers.ofString());
		//System.out.println(response.body()); 

		JSONObject taskObj = new JSONObject(response.body());
		JSONArray taskArr = taskObj.getJSONArray("messages");
 	
		for(int i =0;i<taskArr.length();i++) {
			JSONObject tempObj = taskArr.getJSONObject(i); 
			if(tempObj.getString("type").equals("message")) {
				if(tempObj.getString("user").equals("U030NQSKSCB")) {
					continue;
				}
				String tempEmail = userName(tempObj.getString("user"));
				Date d = new Date((long)Double.parseDouble(tempObj.getString("ts")));
				Task newTask = new Task();
		        newTask.setTaskName(tempObj.getString("text"));
		        newTask.setEmail(tempEmail);
		        newTask.setPlatform("slack");
		        newTask.setStatus(0);
		        newTask.setTime(d);
		        op.add(newTask);
			}
		}
		
		repository.saveAll(op);
		return "Updated the Database";
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