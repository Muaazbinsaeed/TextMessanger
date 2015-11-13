import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

class TCPClient 
{
	TcpClientConnection server;
	String sentence;
	public TCPClient() throws Exception 
	{
		server = new TcpClientConnection();
		sentence = null;
	}
	public void AddFriend(String UserName,String Friend)
	{
		try 
		{
			server.send(" caddfriend "+" "+UserName+" "+Friend+" ");
			//sentence = RcvHandler(server.recv());				
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		//return sentence;
	}
	public void FriendList(String Status,String UserName, String Password) 
	{
		String Friends = null;
			try 
			{
				if(Status.equals("online"))
					server.send(" confriend "+UserName+" "+Password+" ");
				else if(Status.equals("offline"))
					server.send(" cofffriend "+UserName+" "+Password+" ");
				//Friends = RcvHandler(server.recv());				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			//return Friends;
	}
	public void sendonmsg(String UserName,String Friend,String Msg) 
	{
			try 
			{
				server.send(" csendonmsg "+UserName+" "+Friend+" "+Msg+" ");
				//sentence = RcvHandler(server.recv());		
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			//return sentence;
	}
	public String sendonfile(String UserName,String Friend,String filename) throws Exception 
	{
		File file = new File(filename);
		if(!file.exists())
			return "File send try Not Exist";
		//if(file.length() > )
			//return "File send try  is Bigger";
		
		Integer size = ((int) file.length());
		String filesize = size.toString();
		server.send(" csendonfile "+UserName+" "+Friend+" "+filename+" "+filesize);
		server.sendfile(filename);
		return "File send try Sending File";
	}
	public void sendoffmsg(String UserName,String Friend,String Msg) 
	{
			try 
			{
				server.send(" csendoffmsg "+UserName+" "+Friend+" "+Msg+" ");
				//sentence = RcvHandler(server.recv());		
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			//return sentence;
	}
	void RcvHandler() throws Exception
	{
		String msg = server.recv();
		
		StringTokenizer st = new StringTokenizer(msg);
		int k = st.countTokens();
		String[] cmd = new String[k];
		for (k = 0; k < cmd.length; k++)
			cmd[k] = new String();
		for(int j = 0;st.hasMoreTokens();j++) 
			cmd[j] = st.nextToken();
		sentence="";
		if(cmd[0].equals("saddfriend"))
		{
			if(cmd.length > 1)
			{
				for (k = 1; k < cmd.length; k++)
				{
					if( k < cmd.length-1)
						sentence +=cmd[k]+" ";
					else
						sentence +=cmd[k];
				}
			}
			else
				sentence = "";
			StartingLoginPage.L.waitAddingFriend(sentence);
		}
		else if(cmd[0].equals("sonfriend"))
		{
			if(cmd.length > 1)
			{
				for (k = 1; k < cmd.length; k++)
				{
					if( k < cmd.length-1)
						sentence +=cmd[k]+" ";
					else
						sentence +=cmd[k];
				}
			}
			else
				sentence = "";
			StartingLoginPage.L.waitOnlineFriendListLabel(sentence);
			
		}
		else if(cmd[0].equals("sofffriend"))
		{
			if(cmd.length > 1)
			{
				for (k = 1; k < cmd.length; k++)
				{
					if( k < cmd.length-1)
						sentence +=cmd[k]+" ";
					else
						sentence +=cmd[k];
				}
			}
			else
				sentence = "";
			StartingLoginPage.L.waitOfflineFriendListLabel(sentence);
		}
		else if(cmd[0].equals("srcvonmsg"))
		{
			StartingLoginPage.L.waitsendonmsg(cmd);
		}
		else if(cmd[0].equals("srcvoffmsg"))
		{
			//StartingLoginPage.L.waitsendoffmsg(cmd);
		}
		else if(cmd[0].equals("srcvonfile"))
		{
			sentence = server.recvfile(cmd[3], cmd[4]);
			if(sentence.equals("filerecived"))
				StartingLoginPage.L.waitsendonfile(cmd[1],cmd[3]);
		}
		System.out.println("After Tokinization :"+sentence+";");/////////////////////
		//return sentence;
		
	}
}



