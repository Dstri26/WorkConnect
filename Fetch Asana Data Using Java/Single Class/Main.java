package asanajava;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Get the ProjectId and Workspace Id 

class ID {
	public String get_wid() throws IOException, InterruptedException
	{
	String bot_token = "1/1201799234315739:a4ddf5bba1cfe7c19040aff0639f599a";
	String token = "Bearer " + bot_token;
	HttpClient client = HttpClient.newBuilder().build();
	HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create("https://app.asana.com/api/1.0/workspaces"))
	                .headers("Authorization",token)
	                .build();
	
	HttpResponse<String> response;
	response = client.send(request,HttpResponse.BodyHandlers.ofString());

	String jsonString = response.body();
	JSONObject obj = new JSONObject(jsonString);
	
	JSONArray arr = obj.getJSONArray("data");
	String wid = arr.getJSONObject(0).getString("gid");
	
	return wid;
	}
	
	
	public String get_project_id() throws IOException, InterruptedException
	{
		String bot_token = "1/1201799234315739:a4ddf5bba1cfe7c19040aff0639f599a";
		String token = "Bearer " + bot_token;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://app.asana.com/api/1.0/projects?opt_fields=team"))
		                .headers("Authorization",token)
		                .build();
		
		HttpResponse<String> response;
		response = client.send(request,HttpResponse.BodyHandlers.ofString());

		String jsonString = response.body();
		JSONObject obj = new JSONObject(jsonString);
		
		JSONArray arr = obj.getJSONArray("data");
		String project_id = arr.getJSONObject(0).getString("gid");
		
		return project_id;
		
	}

}

//Get the user email,Name & Id using WorkspaceId


class UserInfo {
	public ArrayList<String> get_email() throws IOException, InterruptedException
	{
		ID wid = new ID();
		ArrayList<String> email = new ArrayList<String>();
		String bot_token = "1/1201799234315739:a4ddf5bba1cfe7c19040aff0639f599a";
		String token = "Bearer " + bot_token;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://app.asana.com/api/1.0/workspaces/"+wid.get_wid()+"/users?opt_fields=email,name"))
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
		    email.add(email_id);
		}
		
		
		return email;
	}
	
	
	public ArrayList<String> get_name() throws IOException, InterruptedException
	{
		ID wid = new ID();
		ArrayList<String> name = new ArrayList<String>();
		String bot_token = "1/1201799234315739:a4ddf5bba1cfe7c19040aff0639f599a";
		String token = "Bearer " + bot_token;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://app.asana.com/api/1.0/workspaces/"+wid.get_wid()+"/users?opt_fields=email,name"))
		                .headers("Authorization",token)
		                .build();
		
		HttpResponse<String> response;
		response = client.send(request,HttpResponse.BodyHandlers.ofString());

		String user_info_json = response.body();
		JSONObject obj = new JSONObject(user_info_json);
		JSONArray arr = obj.getJSONArray("data");
		
		for(int i=0;i<arr.length();i++)
		{
			String names = arr.getJSONObject(i).getString("name");
		    name.add(names);
		}
		
		
		return name;
	}
	
	public ArrayList<String> get_uid() throws IOException, InterruptedException
	{
		ID wid = new ID();
		ArrayList<String> uid = new ArrayList<String>();
		String bot_token = "1/1201799234315739:a4ddf5bba1cfe7c19040aff0639f599a";
		String token = "Bearer " + bot_token;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://app.asana.com/api/1.0/workspaces/"+wid.get_wid()+"/users?opt_fields=email,name"))
		                .headers("Authorization",token)
		                .build();
		
		HttpResponse<String> response;
		response = client.send(request,HttpResponse.BodyHandlers.ofString());

		String user_info_json = response.body();
		JSONObject obj = new JSONObject(user_info_json);
		JSONArray arr = obj.getJSONArray("data");
		
		for(int i=0;i<arr.length();i++)
		{
			String user_id = arr.getJSONObject(i).getString("gid");
		    uid.add(user_id);
		}
		
		
		return uid;
	}
	
	
}


//get the taksname,taskassignee,assigneeId,Deadline using Project Id

class UserTaskDetails {
	ID id = new ID();
	public ArrayList<String> get_assignee_uid() throws IOException, InterruptedException, JSONException
	{
		
		ArrayList<String> user_asignee_task_uid = new ArrayList<String>();
		String bot_token = "1/1201799234315739:a4ddf5bba1cfe7c19040aff0639f599a";
		String token = "Bearer " + bot_token;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://app.asana.com/api/1.0/projects/"+id.get_project_id()+"/tasks?opt_fields=assignee,due_on,name"))
		                .headers("Authorization",token)
		                .build();
		
		HttpResponse<String> response;
		response = client.send(request,HttpResponse.BodyHandlers.ofString());

		String user_info_json = response.body();
		JSONObject obj = new JSONObject(user_info_json);
		JSONArray arr = obj.getJSONArray("data");
		
		for(int i=0;i<arr.length();i++)
		{
			JSONObject obj1 = arr.getJSONObject(i);
			JSONObject assignee = obj1.getJSONObject("assignee");
			String gid = assignee.getString("gid");
			user_asignee_task_uid.add(gid);
		}
		return user_asignee_task_uid;
		//System.out.println(arr.get(0));
			
		}
	
	public ArrayList<String> get_deadline() throws IOException, InterruptedException
	
	{
		ArrayList<String> date = new ArrayList<String>();
		String bot_token = "1/1201799234315739:a4ddf5bba1cfe7c19040aff0639f599a";
		String token = "Bearer " + bot_token;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://app.asana.com/api/1.0/projects/"+id.get_project_id()+"/tasks?opt_fields=assignee,due_on,name"))
		                .headers("Authorization",token)
		                .build();
		
		HttpResponse<String> response;
		response = client.send(request,HttpResponse.BodyHandlers.ofString());

		String user_info_json = response.body();
		JSONObject obj = new JSONObject(user_info_json);
		JSONArray arr = obj.getJSONArray("data");
		for(int i=0;i<arr.length();i++)
		{
			String dates = arr.getJSONObject(i).getString("due_on");
		    date.add(dates);
		}
		return date;
		
		
	}

	public ArrayList<String> get_task() throws IOException, InterruptedException
	
	{
		ArrayList<String> task = new ArrayList<String>();
		String bot_token = "1/1201799234315739:a4ddf5bba1cfe7c19040aff0639f599a";
		String token = "Bearer " + bot_token;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://app.asana.com/api/1.0/projects/"+id.get_project_id()+"/tasks?opt_fields=assignee,due_on,name"))
		                .headers("Authorization",token)
		                .build();
		
		HttpResponse<String> response;
		response = client.send(request,HttpResponse.BodyHandlers.ofString());

		String user_info_json = response.body();
		JSONObject obj = new JSONObject(user_info_json);
		JSONArray arr = obj.getJSONArray("data");
		for(int i=0;i<arr.length();i++)
		{
			String tasks = arr.getJSONObject(i).getString("name");
		    task.add(tasks);
		}
		return task;
		
		
	}

}



//Main Class
//print all the values
//Exchanged the userId with emails


public class Main {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		UserTaskDetails ud = new UserTaskDetails();
		UserInfo ui = new UserInfo();
		ArrayList<String> task_asignee_email = new ArrayList<String>();
		//ArrayList<String> task_asignee_name = new ArrayList<String>();
		for(int i=0;i<ud.get_assignee_uid().size();i++)
		{
			for(int j=0;j<ui.get_uid().size();j++)
			{
				if(ud.get_assignee_uid().get(i).equals(ui.get_uid().get(j)))
				{
					task_asignee_email.add(ui.get_email().get(j));
					//task_asignee_name.add(ui.get_name().get(j));
				}
			}
		}
		

		System.out.println(task_asignee_email);
		//System.out.println(task_asignee_name);
		System.out.println(ud.get_task());
		System.out.println(ud.get_deadline());
		System.out.println(ud.get_assignee_uid());
		
	}
	

}
