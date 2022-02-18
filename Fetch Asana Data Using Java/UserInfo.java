package asanajava;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserInfo {
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
