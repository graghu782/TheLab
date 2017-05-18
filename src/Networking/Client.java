package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import GUI.DrawingSurface;

public class Client implements Runnable
{
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private Thread runningThread;
    private boolean isStopped;
    
    public Client(String hostname, int port) throws UnknownHostException, IOException
    {
	socket = new Socket(hostname, port);

    }

    public void run()
    {
	synchronized(this){
	    this.runningThread = Thread.currentThread();
	}
	
	
	try
	{
	    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	
	while(!isStopped) {
	    pw.println("LOL");
	    
	}
    }
}
