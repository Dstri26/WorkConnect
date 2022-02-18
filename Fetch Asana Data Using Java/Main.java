package asanajava;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		UserTaskDetails ud = new UserTaskDetails();
		ID id = new ID();
		UserInfo ui = new UserInfo();
		ArrayList<String> task_asignee_email = new ArrayList<String>();
		ArrayList<String> task_asignee_name = new ArrayList<String>();
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
