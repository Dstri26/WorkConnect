package slacktext;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.*;

class GetMessage
{
	public ArrayList<String> GetMessage() throws IOException, InterruptedException
	{
		ArrayList<String> text_list = new ArrayList<String>();
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
		
		
		String jsonString = response.body();
		JSONObject obj = new JSONObject(jsonString);
		
		JSONArray arr = obj.getJSONArray("messages"); 
		
		for (int i = 0; i < arr.length(); i++)
		{
		    String text = arr.getJSONObject(i).getString("text");
		    text_list.add(text);
		    
		}
		return text_list;
		
	}
	
}

class GetTimeStamp
{
	public ArrayList<String> GetTimeStamp() throws IOException, InterruptedException
	{
		ArrayList<String> time_list = new ArrayList<String>();
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
		
		
		String jsonString = response.body();
		JSONObject obj = new JSONObject(jsonString);
		
		JSONArray arr = obj.getJSONArray("messages"); 
		for (int i = 0; i < arr.length(); i++)
		{
		    String time = arr.getJSONObject(i).getString("ts");
		    time_list.add(time);
		    
		}
		return time_list;
		
		
	}
	

}

class UserId
{
	public ArrayList<String> UserId() throws IOException, InterruptedException
	{
		ArrayList<String> user_list = new ArrayList<String>();
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
		
		
		String jsonString = response.body();
		JSONObject obj = new JSONObject(jsonString);
		
		JSONArray arr = obj.getJSONArray("messages");
		for (int i = 0; i < arr.length(); i++)
		{
		    String user = arr.getJSONObject(i).getString("user");
		    user_list.add(user);
		    
		}
		return user_list;
		
		
	}
	
}

class name extends UserId
{
	public ArrayList<String> name() throws IOException, InterruptedException
	{
		ArrayList<String> names = new ArrayList<String>();
		UserId obj = new UserId();
		for(int i=0;i<obj.UserId().size();i++)
		{
			if(obj.UserId().get(i).equals("U02U5MXFJ4Q"))
			{
				names.add("AKP");
			}
			else if(obj.UserId().get(i).equals("U02TG0HF9JN"))
			{
				names.add("Anisha");
			}
			else if(obj.UserId().get(i).equals("U02TG0HGTU2"))
			{
				names.add("Prajojita");
			}
			else if(obj.UserId().get(i).equals("U030NQSKSCB"))
			{
				names.add("BotApp");
			}
			else
			{
				names.add("Trideep");
			}
		}
		return names;
		
		
		
	}
}




public class ConversationHistory extends name {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		int index_of_bot = 0;
		UserId user = new UserId();
		name names = new name();
		GetMessage text = new GetMessage();
		GetTimeStamp time = new GetTimeStamp();
		
		
		
		ArrayList<String> user_list = user.UserId();
		ArrayList<String> temp_user = new ArrayList<String>();
		ArrayList<String> emails = new ArrayList<String>();
		
		temp_user = user_list;
		//System.out.println(temp_user);
		
		for(int i=0;i<user_list.size();i++)
		{
			if(user_list.get(i).equals("U030NQSKSCB"))
			{
				index_of_bot = i;
			}
			
		}
		user_list.remove(user_list.get(index_of_bot));
		
		for(int i=0;i<user_list.size();i++)
		{
			String bot_token = "xoxb-2934006031602-3022842672419-YZUSvPsZTtrdJkaiKAXnex1Q";
			String token = "Bearer "+bot_token;
		//String channel_id = "C0313AG3ZPB";
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder()
		                	.uri(URI.create("https://slack.com/api/users.profile.get" + "?user=" + user_list.get(i) + "&pretty=1"))
		                	.header("Authorization",token)
		                	.header("Content-Type","application/x-www-form-urlencoded")
		                	.POST(HttpRequest.BodyPublishers.noBody())
		                	.build();
			HttpResponse<String> response;
		
			response = client.send(request,HttpResponse.BodyHandlers.ofString());
		//System.out.println(response.body());
		
		
			String jsonString = response.body();
			JSONObject obj = new JSONObject(jsonString);
		
			String mail = obj.getJSONObject("profile").getString("email");
			emails.add(mail);
		}
		emails.add(index_of_bot, null);
		
		System.out.println("Time Stamp"+time.GetTimeStamp());	
		System.out.println("Texts from channel"+text.GetMessage());	
		System.out.println("User-id"+user.UserId());	
		System.out.println("Emails"+emails);	
		
	}

}
