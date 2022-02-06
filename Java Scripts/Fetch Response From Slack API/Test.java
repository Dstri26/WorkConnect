package slacktext;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.*;

public class Test {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		ArrayList<String> text_list = new ArrayList<String>();
		ArrayList<String> time_list = new ArrayList<String>();
		ArrayList<String> user_list = new ArrayList<String>();
		
		
		//for fetching texts,time_stamp and user_id
		String bot_token = "xoxb-2934006031602-3022842672419-YZUSvPsZTtrdJkaiKAXnex1Q";
		String token = "Bearer "+bot_token;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://slack.com/api/conversations.history" + "?channel=C0313AG3ZPB&pretty=1"))
		                .header("Authorization",token)
		                .header("Content-Type","application/x-www-form-urlencoded")
		                .POST(HttpRequest.BodyPublishers.noBody())
		                .build();
		
		

		HttpResponse<String> response;
		
		response = client.send(request,HttpResponse.BodyHandlers.ofString());
		//System.out.println(response.body());
		
		
		String jsonString = response.body();
		JSONObject obj = new JSONObject(jsonString);
		
		JSONArray arr = obj.getJSONArray("messages"); 
		
		for (int i = 0; i < arr.length(); i++)
		{
		    String text = arr.getJSONObject(i).getString("text");
		    text_list.add(text);
		    
		}
		
		for (int i = 0; i < arr.length(); i++)
		{
		    String time = arr.getJSONObject(i).getString("ts");
		    time_list.add(time);
		    
		}
		
		for (int i = 0; i < arr.length(); i++)
		{
		    String user = arr.getJSONObject(i).getString("user");
		    user_list.add(user);
		    
		}
		
		
		System.out.println(text_list);
		System.out.println(time_list);
		System.out.println(user_list);
		
		
		
	}

}
