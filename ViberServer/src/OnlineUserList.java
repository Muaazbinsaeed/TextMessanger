import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

public class OnlineUserList 
{
	Hashtable<String, Socket> OnlineUser;
	String sentence;
	
	OnlineUserList()
	{
		OnlineUser = new Hashtable<String, Socket>();
		sentence=null;
	}
	Socket getSocket(String Name)
	{
		return OnlineUser.get(Name);
	}
	void add(String Name,Socket socket)
	{
		OnlineUser.put(Name,socket);
	}
	void remove(String Name)
	{
		OnlineUser.remove(Name);
	}
	void UpdateOnlineUser()
	{
		
	}
	void ShowAllData()
	{
		System.out.print("Showing Updated Online User Line:");
		String str;
		int i=0;
		Enumeration<String> names = OnlineUser.keys();
		while(names.hasMoreElements()) 
		{
		    str = (String) names.nextElement();
		    i++;
		    System.out.print(i+").Key :"+str);
		    Socket u =  OnlineUser.get(str);
		    System.out.println("\tSocket :"+u);
		}
	}
}
