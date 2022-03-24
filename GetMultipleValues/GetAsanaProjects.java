package src;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetAsanaProjects {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		ArrayList<String> projects_id = new ArrayList<String>();
		ArrayList<String> projects_name = new ArrayList<String>();
		
		String apiToken =  "1/1201799234315739:a4ddf5bba1cfe7c19040aff0639f599a";
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
			projects_name.add(prArr.getJSONObject(i).getString("name"));
		}
		
		System.out.println(projects_id);
		System.out.println(projects_name);
	}
	

}
