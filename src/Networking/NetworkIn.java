package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NetworkIn {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(13);
			
			//while(true) {
				Socket connect = ss.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
				PrintWriter pw = new PrintWriter(connect.getOutputStream(), true);
				
				while(true) {
					String in = br.readLine();
					if(in != null) {
						System.out.println(in);
						break;
					}
				}
			//}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
