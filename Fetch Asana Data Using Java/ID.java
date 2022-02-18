package asanajava;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class ID {
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
