class UDPClient 
{
	UdpClientConnection server;
	String sentence;
	public UDPClient() throws Exception 
	{
		sentence=null;
		server = new UdpClientConnection();
	}
	public void run() throws Exception
	{
		server = new UdpClientConnection();
	}
	public boolean Login(String UserName, String Password) 
	{
		try
		{
			System.out.println(UserName + "\t" + Password);// /////
			server.send(" login "+UserName+" "+Password+" ");
			System.out.println("msg send login");// /////
			sentence = server.recv();
			System.out.println("Recv message Login ="+sentence);// /////
			if(sentence.equals("true"))
				return true;
		}
		catch (Exception e)
		{
			System.out.println("Login:"+e);
		}
		return false;
	}

	public String SignUp(String UserName, String Password,String Phone,String D,String M ,String Y,String Gender) 
	{
		try
		{
			System.out.println(" signup "+UserName+" "+Password+" "+Phone+" "+D+" "+M+" "+Y+" "+Gender+" ");// /////
			server = new UdpClientConnection();
			server.send(" signup "+UserName+" "+Password+" "+Phone+" "+D+" "+M+" "+Y+" "+Gender+" ");
			sentence = server.recv();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return sentence;
	}
}
