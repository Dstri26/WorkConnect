package src;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetSlackChannel {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		ArrayList<String> channels_id = new ArrayList<String>();
		ArrayList<String> channels_name = new ArrayList<String>();

		String bot_token = "xoxb-2934006031602-3022842672419-YZUSvPsZTtrdJkaiKAXnex1Q";
		String token = "Bearer "+bot_token;
		HttpResponse<String> response ;
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder()
            	.uri(URI.create("https://slack.com/api/conversations.list" + "?pretty=1"))
            	.header("Authorization",token)
            	.header("Content-Type","application/x-www-form-urlencoded")
            	.POST(HttpRequest.BodyPublishers.noBody())
            	.build();
		
		response = client.send(request,HttpResponse.BodyHandlers.ofString());
		//System.out.println(response);
		JSONObject taskObj = new JSONObject(response.body());
		JSONArray taskArr = taskObj.getJSONArray("channels");
		for(int i=0;i<taskArr.length();i++)
		{
			JSONObject obj = taskArr.getJSONObject(i);
			channels_id.add(obj.getString("id"));
			channels_name.add(obj.getString("name"));
			
		}
		
}
}
