import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpServerConnection 
{
	DatagramSocket serverSocket;
	InetAddress Client_IPAddress;
	int Client_Port;
	String sentence;
	private Scanner cin;
	
	byte[] receiveData;
	byte[] sendData;
	
	UdpServerConnection() throws Exception
	{
		serverSocket = new DatagramSocket(6789);
		receiveData = new byte[1024];
		sendData = new byte[1024];
		sentence=null;
	}
	public String getuserinput()
	{
		System.out.println("Enter Choice");
		cin = new Scanner(System.in);
		return (sentence = cin.nextLine());
	}
	public void send(UDPInfo u) throws IOException
	{
		sentence = u.sentence+"?";
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, u.Client_IPAddress, u.Client_Port);
		serverSocket.send(sendPacket);
	}
	public UDPInfo recv() throws IOException
	{	
		DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
		serverSocket.receive(receivePacket);
		sentence = new String(receivePacket.getData());
		
		sentence = sentence.substring(0, sentence.indexOf("?"));
		Client_IPAddress = receivePacket.getAddress();
		Client_Port = receivePacket.getPort();
		
		System.out.println("FROM Client: " + sentence);
		System.out.println("ClientIP: " + Client_IPAddress);
		return (new UDPInfo(Client_IPAddress,Client_Port,sentence));
		
	}
	public void end() throws IOException
	{
		serverSocket.close();
	}
}