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
public class TcpClientConnection 
{
	Socket socket;
	String sentence;
	BufferedReader inFromServer;
	DataOutputStream outToServer;
	
	TcpClientConnection() throws Exception
	{
		socket = new Socket("localhost", 1234);
		outToServer = new DataOutputStream(socket.getOutputStream());
		inFromServer =  new BufferedReader(new InputStreamReader(socket.getInputStream()));		
		sentence=null;
	}
	public void send(String s) throws IOException
	{
		sentence = s;
		System.out.println("To SERVER: " + sentence);/////////////
		outToServer.writeBytes(sentence + "\r\n");
	}
	public String recv() throws IOException
	{	
		sentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + sentence);///////////////////
		return sentence;
	}
	public String recvfile(String filename,String filesize) throws IOException
	{	 
		byte [] bytearray = new byte [Integer.parseInt(filesize)];
		InputStream inFromServerfile = socket.getInputStream();
		BufferedOutputStream filewriter = new BufferedOutputStream(new FileOutputStream(filename)); 
		int bytesRead = inFromServerfile.read(bytearray, 0,Integer.parseInt(filesize));
		filewriter.write(bytearray, 0, bytesRead);
		filewriter.close();
		System.out.println("FROM SERVER file:" + filename+filesize);///////////////////
		return "filerecived";
	}
	public void sendfile(String FileName) throws IOException
	{
		File file = new File(FileName);
		byte[] mybytearray = new byte[(int) file.length()];
		BufferedInputStream filereader = new BufferedInputStream(new FileInputStream(file));
		filereader.read(mybytearray, 0, mybytearray.length);
		
		OutputStream outToServerfile = socket.getOutputStream();
		outToServerfile.write(mybytearray, 0, mybytearray.length);
		
		outToServerfile.flush();
		filereader.close();
		System.out.println("To SERVER file: " + FileName);/////////////
	}
	public void end() throws IOException
	{
		socket.close();
	}
}
