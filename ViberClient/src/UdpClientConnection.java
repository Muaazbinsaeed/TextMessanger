import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class UdpClientConnection 
{
	String sentence;
	private Scanner cin;
	DatagramSocket clientSocket;
	InetAddress ipAddress;
	
	byte[] sendData ;
	byte[] receiveData ;
	
	UdpClientConnection() throws Exception
	{
		clientSocket = new DatagramSocket();
		ipAddress = InetAddress.getByName("localhost");
		sentence=null;
		
		sendData = new byte[1024];
		receiveData = new byte[1024];
	}
	public void send(String s) throws IOException
	{
		sentence = s+"?";
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, ipAddress, 6789);
		clientSocket.send(sendPacket);
	}
	public String recv() throws IOException
	{	
		DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);		
		clientSocket.receive(receivePacket);
		
		ipAddress = receivePacket.getAddress();
		sentence = new String(receivePacket.getData());
		
		sentence = sentence.substring(0, sentence.indexOf("?"));
		System.out.println("FROM SERVER:" + sentence);
		return sentence;
	}
	public void end() throws IOException
	{
		clientSocket.close();
	}
}