package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    //			Socket connect = ss.accept();
    //			BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
    //			PrintWriter pw = new PrintWriter(connect.getOutputStream(), true);
    //			
    //			while(true) {
    //				String in = br.readLine();
    //				if(in != null) {
    //					System.out.println(in);
    //					break;
    //				}
    //			}
    private ServerSocket serverSocket;
    private boolean isStopped;
    private Thread runningThread;
    private int port;
    
    public Server(int port){
	this.port = port;
    }

    public void run(){
	synchronized(this){
	    this.runningThread = Thread.currentThread();
	}
	
	try
	{
	    serverSocket = new ServerSocket(port);
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	
	while(!isStopped){	
	    Socket clientSocket = null;
	    try {
		clientSocket = this.serverSocket.accept();
	    } catch (IOException e) {
		if(!isStopped) {
		    System.out.println("Server Stopped.") ;
		    return;
		}
		throw new RuntimeException("Error accepting client connection", e);
	    }

	    try {
		BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
		while(true) {
		    String in = br.readLine();
		    if(in != null) {
			System.out.println(in);
			break;
		    }
		}
	    } catch (IOException e) {
	    }
	}

	System.out.println("Server Stopped.") ;
    }

    public synchronized void stop(){
	isStopped = true;
	try {
	    serverSocket.close();
	} catch (IOException e) {
	    throw new RuntimeException("Error closing server", e);
	}
    }

}
