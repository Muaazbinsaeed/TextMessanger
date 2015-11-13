import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;
public class Main2 extends Thread
{
	TcpServerConnection tserver;
	UdpServerConnection userver;
	String sentence;
	UDPInfo UDP_Info;
	String UserName;
	public Main2(String User,UDPInfo u,UdpServerConnection uservera) throws Exception 
	{
		UserName = User;
		UDP_Info=u;
		sentence=null;
		userver = uservera;
	}
	public void run()
	{
		try
		{
			System.out.println("Udp Server sending:" + UDP_Info.sentence);/////////////////
			userver.send(UDP_Info);
			Socket client_socket = Main.server_socket.accept();
			tserver = new TcpServerConnection(client_socket);
			Main.Online_UserList.add(UserName, client_socket);
			Main.Online_UserList.ShowAllData();/////////////////////////
			while (true) 
			{
				try 
				{
					System.out.println("waiting TCP msg from client:");// ///////////////
					sentence = tserver.recv();
					StrToken(sentence);
					
				} 
				catch (Exception e)
				{
					System.out.println("Catch Conection Broke" + e);
					Main.Online_UserList.remove(UserName);
					Main.DbHandler.Online.remove(UserName);
					Main.Online_UserList.ShowAllData();//////////////////////////
					break;
				}
			}
		}
		catch(Exception e3)
		{
			System.out.println("Conection Broke" + e3);
		}
	}
	void StrToken(String s) throws IOException
	{
		StringTokenizer st = new StringTokenizer(s);
		int k = st.countTokens();
		String[] cmd = new String[k];
		for (k = 0; k < cmd.length; k++)
			cmd[k] = new String();
		for(int j = 0;st.hasMoreTokens();j++) 
			cmd[j] = st.nextToken();
		if(cmd[0].equals("cofffriend"))
		{
			sentence=" sofffriend "+Main.DbHandler.offfriend(cmd[1]);
			tserver.send(sentence);
		}
		else if(cmd[0].equals("confriend"))
		{
			sentence= " sonfriend "+Main.DbHandler.onfriend(cmd[1]);
			tserver.send(sentence);
		}
		else if(cmd[0].equals("csendonmsg"))
		{
			sentence="";
			for (k = 3; k < cmd.length; k++)
				sentence +=cmd[k]+" ";
			sendonmsg(cmd[1],cmd[2],sentence);
		}
		else if(cmd[0].equals("csendonfile"))
		{
			sendonfile(cmd[1],cmd[2],cmd[3],cmd[4]);
			//server.send(" csendonfile "+UserName+" "+Friend+" "+filename+" "+filesize);////////////////
		}
		else if(cmd[0].equals("csendoffmsg"))
		{
			sentence="";
			for (k = 3; k < cmd.length; k++)
				sentence +=cmd[k]+" ";
			sendoffmsg(cmd[1],cmd[3],sentence);
		}
		else if(cmd[0].equals("caddfriend"))
		{
			sentence="";
			sentence = " saddfriend "+ Main.DbHandler.addfriend(cmd[1],cmd[2]);
			tserver.send(sentence);
		}
		else
			tserver.send("");
	}
	
	void sendoffmsg(String UserName,String Friend,String Msg) throws IOException
	{
		Socket FriendSocket = Main.Online_UserList.getSocket(Friend);
		if(FriendSocket != null)
		{
			tserver.sendtoSocket(FriendSocket   ,  "srcvoffmsg "+UserName+" "+Friend+" "+Msg+" ");
		}
		else
		{
			System.out.println("Socket of Friend Not Found");/////////////////
		}
	}
	void sendonmsg(String UserName,String Friend,String Msg) throws IOException
	{
		Main.Online_UserList.ShowAllData();/////////////////////////////////
		
		Socket FriendSocket = Main.Online_UserList.getSocket(Friend);
		System.out.println("Socket to Send"+FriendSocket+";\tN:"+UserName+";\tF:"+Friend+";");//////////////
		if(FriendSocket.isConnected())
		{	
			tserver.sendtoSocket(FriendSocket   ,  "srcvonmsg "+UserName+" "+Friend+" "+Msg+" ");
		}
		else
		{
			System.out.println("Socket of Friend Not Found");//////////////
		}
	}
	void sendonfile(String UserName,String Friend,String filename,String filesize) throws IOException
	{
		//server.send(" csendonfile "+UserName+" "+Friend+" "+filename+" "+filesize);////////////////
		Main.Online_UserList.ShowAllData();/////////////////////////////////
		
		Socket FriendSocket = Main.Online_UserList.getSocket(Friend);
		System.out.println("Socket to Send"+FriendSocket+";\tN:"+UserName+";\tF:"+Friend+";");//////////////
		if(FriendSocket.isConnected())
		{
			System.out.println("1 " + filename);/////////////
			tserver.recvfile(filename, filesize);
			System.out.println("2 " + filename);/////////////
			tserver.sendtoSocket(FriendSocket   ,  "srcvonfile "+UserName+" "+Friend+" "+filename+" "+" "+filesize+" ");
			System.out.println("3 " + filename);/////////////
			tserver.sendtoSocketfile(FriendSocket,filename);	
			System.out.println("4 " + filename);/////////////
		}
		else
		{
			System.out.println("Socket of Friend Not Found");//////////////
		}
	}
}