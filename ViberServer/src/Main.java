import java.io.IOException;
import java.net.ServerSocket;
import java.util.StringTokenizer;

public class Main 
{
	UdpServerConnection userver;
	String sentence;
	String UserName;
	static Handler DbHandler;
	static OnlineUserList Online_UserList;
	static ServerSocket server_socket ;
	public Main() throws Exception 
	{
		sentence=null;
		Online_UserList = new OnlineUserList();
		userver = new UdpServerConnection();
		DbHandler = new Handler();
		server_socket = new ServerSocket(1234);
	}
	public static void main(String[] args) throws Exception 
	{
		Main m = new Main();
		m.run();
	}
	void run() throws Exception
	{
		while(true)
		{
			System.out.println("waiting login msg from client:");/////////////////
			UDPInfo u = userver.recv();
			System.out.println("msg from client:" + u.sentence);/////////////////
			u.sentence = StrToken(u.sentence);
			if(u.sentence.equals("true"))
			{
				System.out.println("Udp :" + u.sentence);/////////////////
				Thread t = new Main2(UserName,u,userver);
				t.start();
			}
			else
			{
				userver.send(u);
			}
		}
	}
	String StrToken(String s) throws IOException
	{
		StringTokenizer st = new StringTokenizer(s);
		int k = st.countTokens();
		String[] cmd = new String[k];
		for (k = 0; k < cmd.length; k++)
			cmd[k] = new String();
		for(int j = 0;st.hasMoreTokens();j++) 
			cmd[j] = st.nextToken();
		
		UserName = cmd[1];
		if(cmd[0].equals("login"))
		{
			System.out.println("logining");/////////////////
			return (sentence=DbHandler.login(cmd[1],cmd[2]));
		}
		else if(cmd[0].equals("signup"))
		{
			System.out.println("signing");/////////////////
			return (sentence=DbHandler.signup(cmd[1],cmd[2],cmd[3],cmd[4],cmd[5],cmd[6],cmd[7]));
		}
		else
		{
			return ("");
		}
	}
}