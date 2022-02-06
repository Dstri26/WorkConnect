package src;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Test {
	public static void main(String args[])
	{
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
		try {
			response = client.send(request,HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
			
		
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
