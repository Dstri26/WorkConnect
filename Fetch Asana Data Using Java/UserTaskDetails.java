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
import org.json.JSONTokener;

public class UserTaskDetails {
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
