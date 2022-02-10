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
    
    //Function to retrieve email associated with a Slack User Code
    private static String userEmail(String code) throws IOException, InterruptedException {
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
    
    //Service to check if a message is app mention or not
    public String matchMention(String txt) throws IOException, InterruptedException {
        String regex = "<@[A-Z0-9]+>";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(txt);
        //System.out.println("Pattern found from "+ m.start() + " to " + (m.end() - 1));
        boolean op = m.find();
        if(op) {
            String matchedText= m.group(0);
            System.out.println(matchedText.substring(2,matchedText.length()-1));
            return userEmail(matchedText.substring(2,matchedText.length()-1));
        }
        else {
        	return null;
        }
    }
    
    
    //Service to fetch all the tasks from a channel
    public String fetchData() throws IOException, InterruptedException {
    	
    	//List to store all the retrieved tasks to directly add in the repository
        ArrayList<Task> op = new ArrayList<Task>();
        
        //Retrieve latest date to find all the tasks after that
        Date x = repository.retrieveLast();
        
        //Slack Bot credentials
        String bot_token = "xoxb-2934006031602-3022842672419-YZUSvPsZTtrdJkaiKAXnex1Q";
		String token = "Bearer "+bot_token;
		String channel_id = "C0313AG3ZPB";
		String url_str = "";
		
		//Fetch task/messages assigned in the slack channel
		//For initial condition when no task has been added
        if(x == null) {
        	url_str = "https://slack.com/api/conversations.history" + "?channel=" + channel_id +"&pretty=1";
        }
        else {
        	long ts = (x.getTime()/1000)+1;
        	url_str = "https://slack.com/api/conversations.history" + "?channel=" + channel_id + "&oldest="+ String.valueOf(ts)+ "&pretty=1";
        }
        
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create(url_str))
		                .header("Authorization",token)
		                .header("Content-Type","application/x-www-form-urlencoded")
		                .POST(HttpRequest.BodyPublishers.noBody())
		                .build();
		HttpResponse<String> response;
		
		//Received response json for the requested tasks
		response = client.send(request,HttpResponse.BodyHandlers.ofString());		
		System.out.println(response.body());
		JSONObject taskObj = new JSONObject(response.body());
		JSONArray taskArr = taskObj.getJSONArray("messages");
		
		//Text to send as a response to client
		String optxt = "";


			for(int i =0;i<taskArr.length();i++) {
				JSONObject tempObj = taskArr.getJSONObject(i);
				
				//Removes the bot messages
				if(tempObj.getString("user").equals("U030NQSKSCB")) {
					continue;
				}
				
				//Fetching all the receiver(app mentioned) emails
				String receiverEmail = matchMention(tempObj.getString("text"));
				
				//Checking for app mentions messages only
				if(tempObj.getString("type").equals("message") && receiverEmail!=null) {
					String senderEmail = userEmail(tempObj.getString("user"));
					Date d = new Date(((long) Double.parseDouble(tempObj.getString("ts"))*1000));
					
					//Creating and adding a new Task object to the output tasks array
					Task newTask = new Task();
					newTask.setTaskName(tempObj.getString("text"));
			        newTask.setSender(senderEmail);
			        newTask.setReceiver(receiverEmail);
			        newTask.setPlatform("slack");
			        newTask.setStatus(0);
			        newTask.setTime(d);
			        op.add(0,newTask);
				}
			}
		
		//Adding tasks to database if tasks available
		if(op.size()>0) {
			optxt = "Added "+ op.size() +" tasks to database";
			//repository.saveAll(op);
		}
		else {
			optxt = "No task to add to database";
		}
		
		return optxt;
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
        existingTask.setSender(task.getSender());
        existingTask.setReceiver(task.getReceiver());
        existingTask.setPlatform(task.getPlatform());
        existingTask.setStatus(task.getStatus());
        return repository.save(existingTask);
    }
}