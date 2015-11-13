import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TcpServerConnection 
{
	Socket client_socket;
	String sentence;
	DataOutputStream outToServer;
	BufferedReader inFromServer ;
	TcpServerConnection(Socket client_socketa) throws Exception
	{
		client_socket= client_socketa;
		sentence=null;
		outToServer = new DataOutputStream(client_socket.getOutputStream());
		inFromServer =  new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
	}
	public void send(String s) throws IOException
	{
		sentence = s;
		System.out.println("To Client: " + sentence);
		outToServer.writeBytes(sentence + "\r\n");
	}
	public String recv() throws IOException
	{	
		System.out.println("Socket rcv in : " + client_socket);///////
		sentence = inFromServer.readLine();
		System.out.println("FROM Client: " + sentence);
		return sentence;
	}
	public Socket GetSocket()
	{
		return client_socket;
	}
	public void end() throws IOException
	{
		client_socket.close();
	}
	public void sendtoSocket(Socket friend_socket,String msg) throws IOException
	{
		DataOutputStream outToServer = new DataOutputStream(friend_socket.getOutputStream());
		System.out.println("To Friend Client: " + msg);
		outToServer.writeBytes(msg + "\r\n");
	}
	public void recvfile(String filename,String filesize) throws IOException
	{	 
		byte [] bytearray = new byte [Integer.parseInt(filesize)];
		InputStream inFromServerfile = client_socket.getInputStream();
		BufferedOutputStream filewriter = new BufferedOutputStream(new FileOutputStream(filename)); 
		int bytesRead = inFromServerfile.read(bytearray, 0,Integer.parseInt(filesize));
		filewriter.write(bytearray, 0, bytesRead);
		filewriter.close();
		System.out.println("FROM SERVER file:" + sentence);///////////////////
	}
	public void sendtoSocketfile(Socket friend_socket,String FileName) throws IOException
	{		
		File file = new File(FileName);
		byte[] mybytearray = new byte[(int) file.length()];
		BufferedInputStream filereader = new BufferedInputStream(new FileInputStream(file));
		filereader.read(mybytearray, 0, mybytearray.length);
		
		OutputStream outToServerfile = friend_socket.getOutputStream();
		outToServerfile.write(mybytearray, 0, mybytearray.length);
		
		outToServerfile.flush();
		filereader.close();
		System.out.println("\nTo Friend Client file: " + FileName);/////////////
	}
}
