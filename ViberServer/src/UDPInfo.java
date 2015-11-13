import java.net.InetAddress;

public class UDPInfo 
{
	UDPInfo(InetAddress Client_IPAddressa,int Client_Porta,String sentencea)
	{
		Client_IPAddress=Client_IPAddressa;
		Client_Port=Client_Porta;
		sentence=sentencea;
	}
	InetAddress Client_IPAddress;
	int Client_Port;
	String sentence;
}
