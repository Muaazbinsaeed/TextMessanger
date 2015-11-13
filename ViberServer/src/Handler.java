import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Handler 
{	
	class UserInfo
	{
		UserInfo(){}
		UserInfo(String Passworda ,String Phonea,String Da,String Ma,String Ya,String Gendera,ArrayList<String> FriendLista)
		{
			Password = Passworda;
			Phone=Phonea;
			D=Da;
			M=Ma;
			Y=Ya;
			Gender=Gendera;
			FriendList =FriendLista;
		}
		public String Password = null;
		String Phone,D,M,Y,Gender;
		public ArrayList<String> FriendList = new ArrayList<String>();
	}
	Hashtable<String, UserInfo> User;
	public ArrayList<String> Online;
	String sentence;
	
	Handler()
	{
		User = new Hashtable<String, UserInfo>();
		Online = new ArrayList<String>();
		
		sentence=null;
		tempPopulate();
	}
	void tempPopulate()
	{
		ArrayList<String> Friend = new ArrayList<String>();
		Friend.add("ali");
		Friend.add("Umer");
		Friend.add("Usama");
		Friend.add("Ikrima");
		Friend.add("Huzaifa");
		Friend.add("Haroon");
		Friend.add("Dogar");
		User.put("muaaz", (new UserInfo("a","sds","asd","c","f","f",  (ArrayList<String>) Friend.clone()))); 
		Friend.remove("ali");
		Friend.add("muaaz");
		User.put("ali", (new UserInfo("a","sds","asd","c","f","f",  (ArrayList<String>) Friend.clone()))); 
		Friend.add("Qasim");
		Friend.remove("muaaz");
		User.put("Ali2", (new UserInfo("b","sds","asd","c","f","f",  (ArrayList<String>) Friend.clone()))); 
		Friend.add("Haider");
		Friend.remove("Usama");
		User.put("Ali3", (new UserInfo("b","sds","asd","c","f","f",  (ArrayList<String>) Friend.clone())));
	}
	String login(String UserName,String Password)
	{
		System.out.print("Verifing Login\t");////////////////
		sentence="false";
		if(User.containsKey(UserName))
		{
			if(((User.get(UserName)).Password).equals(Password))
			{
				Online.add(UserName);
				sentence="true";
			}
		}
		return sentence;
	}
	String signup (String UserName,String Password,String Phone,String D,String M,String Y,String Gender)
	{
		if(User.containsKey(UserName))
			sentence="false";
		else
		{
			ArrayList<String> Friend = new ArrayList<String>();
			User.put(UserName, (new UserInfo(Password,Phone,D,M,Y,Gender,(ArrayList<String>)Friend.clone())));
			sentence = login(UserName,Password);
		}
		return sentence;
	}
	
	String offfriend(String UserName)
	{
		sentence = "";
		UserInfo u = User.get(UserName);
		System.out.println("u.FriendList.size()"+u.FriendList.size());////////////////
	    for (int i = 0; i < u.FriendList.size(); i++)
	    {
	    	if(!Online.contains(u.FriendList.get(i)))
				sentence += u.FriendList.get(i)+" ";
	    }
		System.out.println("Found o f:"+sentence);////////////////
		return sentence;
	}
	String onfriend(String UserName)
	{
		sentence = "";
		UserInfo u = User.get(UserName);
	    for (int i = 0; i < u.FriendList.size(); i++)
	    {
	    	if(Online.contains(u.FriendList.get(i)))
	    		sentence += u.FriendList.get(i)+" ";
	    }
		System.out.println("Found o f:"+sentence);////////////////
		return sentence;
	}
	String addfriend(String UserName,String AddFriend)
	{
		UserInfo u = User.get(UserName);
		if(User.containsKey(AddFriend))
		{
			if( !u.FriendList.contains(AddFriend))
			{
				User.remove(UserName);
				u.FriendList.add(AddFriend);
				User.put(UserName, u);
				ShowAllData();////////////////////////
				addfriend(AddFriend,UserName);
				return "t ";
			}
			return "alreadyexist ";
		}
		return "f ";
	}
	void ShowAllData()
	{
		String str;
		Enumeration<String> names = User.keys();
		while(names.hasMoreElements()) 
		{
		    str = (String) names.nextElement();
		    System.out.print("Key " +str +"\nFrientds:");
		    UserInfo u = User.get(str);
		    System.out.print(" pass :"+u.Password+" size:"+u.FriendList.size()+"\t");
		    for (int i = 0; i < u.FriendList.size(); i++)
		   	    System.out.print(" "+u.FriendList.get(i));
		    System.out.println("");
		}
	}
}
