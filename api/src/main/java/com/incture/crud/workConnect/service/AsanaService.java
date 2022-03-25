package com.incture.crud.workConnect.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.incture.crud.workConnect.entity.Task;
import com.incture.crud.workConnect.repository.TaskRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AsanaService {
	private static HashMap<String,String> users= new HashMap<String,String>();
	
    @Autowired
    private TaskRepository repository;
    
    @Value("${app.asana.token}")
    private String AsanaToken;
    
    @Value("${app.asana.access}")
    private String AsanaAccess;
    
    //Get Asana project id's
    public ArrayList<String> getProjectId() throws IOException, InterruptedException
    {
    	ArrayList<String> projects_id = new ArrayList<String>();
		
		String apiToken =  this.AsanaToken;
    	String token = "Bearer " + apiToken;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest prRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://app.asana.com/api/1.0/projects"))
                .headers("Authorization",token)
                .build();

		HttpResponse<String> prResponse;
		prResponse = client.send(prRequest,HttpResponse.BodyHandlers.ofString());
		JSONObject prObj = new JSONObject(prResponse.body());

		JSONArray prArr = prObj.getJSONArray("data");
		for(int i=0;i<prArr.length();i++)
		{
			projects_id.add(prArr.getJSONObject(i).getString("gid"));
		}
		
		return projects_id;
    
    }
     
     //Get Asana project's Name
    public ArrayList<String> getProjectName() throws IOException, InterruptedException
    {
    	ArrayList<String> projects_name = new ArrayList<String>();
		
		String apiToken =  this.AsanaToken;
    	String token = "Bearer " + apiToken;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest prRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://app.asana.com/api/1.0/projects"))
                .headers("Authorization",token)
                .build();

		HttpResponse<String> prResponse;
		prResponse = client.send(prRequest,HttpResponse.BodyHandlers.ofString());
		JSONObject prObj = new JSONObject(prResponse.body());

		JSONArray prArr = prObj.getJSONArray("data");
		for(int i=0;i<prArr.length();i++)
		{
			projects_name.add(prArr.getJSONObject(i).getString("name"));
		}
		
		return projects_name;
    
    }
    
    
	public void updateEmail(String wid) throws IOException, InterruptedException
	{
		String bot_token = this.AsanaToken;
		String token = "Bearer " + bot_token;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://app.asana.com/api/1.0/workspaces/"+wid+"/users?opt_fields=email"))
		                .headers("Authorization",token)
		                .build();
		
		HttpResponse<String> response;
		response = client.send(request,HttpResponse.BodyHandlers.ofString());

		String user_info_json = response.body();
		JSONObject obj = new JSONObject(user_info_json);
		JSONArray arr = obj.getJSONArray("data");
		
		for(int i=0;i<arr.length();i++)
		{
			String email_id = arr.getJSONObject(i).getString("email");
			String userGid = arr.getJSONObject(i).getString("gid");
		    users.put(userGid, email_id);
		}
	}
	
    public String fetchAsanaData() throws IOException, InterruptedException {
    	ArrayList<Task> op = new ArrayList<Task>();
    	String optxt="";
    	String wsId = "";
    	
        if(this.AsanaAccess.equals("no")) {
        	return "No Access for Asana";
        }
        
        
    	//Fetch Workspace ID
    	String apiToken =  this.AsanaToken;
    	String token = "Bearer " + apiToken;
    	HttpClient client = HttpClient.newBuilder().build();
    	HttpRequest wsRequest = HttpRequest.newBuilder()
    	                .uri(URI.create("https://app.asana.com/api/1.0/workspaces"))
    	                .headers("Authorization",token)
    	                .build();
    	
    	HttpResponse<String> wsResponse;
    	wsResponse = client.send(wsRequest,HttpResponse.BodyHandlers.ofString());
    	JSONObject wsObj = new JSONObject(wsResponse.body());
    	JSONArray wsArr = wsObj.getJSONArray("data");
    	wsId = wsArr.getJSONObject(0).getString("gid");
    	
    	//Update User Data
    	updateEmail(wsId);
    	
    	
    	//Fetch Project ID and Name
		HttpRequest prRequest = HttpRequest.newBuilder()
		                .uri(URI.create("https://app.asana.com/api/1.0/projects"))
		                .headers("Authorization",token)
		                .build();
		
		HttpResponse<String> prResponse;
		prResponse = client.send(prRequest,HttpResponse.BodyHandlers.ofString());
		JSONObject prObj = new JSONObject(prResponse.body());
		
		JSONArray prArr = prObj.getJSONArray("data");
		String prId = prArr.getJSONObject(0).getString("gid");
		String prName = prArr.getJSONObject(0).getString("name");
		
		//Fetch tasks from project
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://app.asana.com/api/1.0/projects/"+prId+"/tasks?opt_fields=assignee,name"))
		                .headers("Authorization",token)
		                .build();
		
		HttpResponse<String> taskResponse;
		taskResponse = client.send(request,HttpResponse.BodyHandlers.ofString());
		
		JSONObject taskObj = new JSONObject(taskResponse.body());
		JSONArray taskArr = taskObj.getJSONArray("data");
		Date d = new Date();
		for(int i=0;i<taskArr.length();i++)
		{
			JSONObject tempObj = taskArr.getJSONObject(i);
			JSONObject assignee = tempObj.getJSONObject("assignee");
			
			Task newTask = new Task();
			if(repository.checkAsana(users.get(assignee.getString("gid")),"asana",tempObj.getString("name")) != null){
				continue;
			}
			newTask.setTaskName(tempObj.getString("name"));
	        newTask.setReceiver(users.get(assignee.getString("gid")));
	        newTask.setPlatform("asana");
	        newTask.setStatus(0);
	        newTask.setIsDeleted(0);
	        newTask.setTime(d);
	        op.add(0,newTask);
			
		}

		//Adding tasks to database if tasks available
		if(op.size()>0) {
			optxt = "Added "+ op.size() +" Asana tasks to database";
			repository.saveAll(op);
		}
		else {
			optxt = "No asana task to add to database";
		}
		
		return optxt;
    }
 }
