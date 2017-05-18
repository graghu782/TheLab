package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Runnable {
    //    	public Server(int port) {
    //    	    
    //    	}
    //    
    //	public void run()
    //	{
    //	    try {
    //		ServerSocket ss = new ServerSocket(4444);
    //		
    //		while(true) {
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
    //		}
    //		
    //	} catch (IOException e) {
    //		// TODO Auto-generated catch block
    //		e.printStackTrace();
    //	}
    //	}
    private int serverPort = 8080;
    private ServerSocket serverSocket = null;
    private boolean isStopped    = false;
    private Thread runningThread;

    public Server(int port){
	this.serverPort = port;
    }

    public void run(){
	synchronized(this){
	    this.runningThread = Thread.currentThread();
	}
	openServerSocket();
	while(! isStopped()){	
	    Socket clientSocket = null;
	    try {
		clientSocket = this.serverSocket.accept();
	    } catch (IOException e) {
		if(isStopped()) {
		    System.out.println("Server Stopped.") ;
		    return;
		}
		throw new RuntimeException(
			"Error accepting client connection", e);
	    }
	    new Thread(
		    new ServerThread(clientSocket, "Multithreaded Server")).start();
	}
	System.out.println("Server Stopped.") ;
    }


    private synchronized boolean isStopped() {
	return this.isStopped;
    }

    public synchronized void stop(){
	this.isStopped = true;
	try {
	    this.serverSocket.close();
	} catch (IOException e) {
	    throw new RuntimeException("Error closing server", e);
	}
    }

    private void openServerSocket() {
	try {
	    this.serverSocket = new ServerSocket(this.serverPort);
	} catch (IOException e) {
	    throw new RuntimeException("Cannot open port 8080", e);
	}
    }
}
