package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NetworkOut {
	public static void main(String[] args) {
		String hostName = "127.0.0.1";
		
		try	 {
			Socket s = new Socket(hostName, 4444);
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			pw.println("hello");
			s.close();
		} catch(IOException e) {
			System.err.println(e);
		}
	}
}
