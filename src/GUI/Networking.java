package GUI;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Networking
{
    private Socket s;
    private Scanner sc;
    private PrintStream ps;
    
    public Networking(String host, int port) throws UnknownHostException, IOException {
	s = new Socket(host, port);
	sc = new Scanner(s.getInputStream());
	ps = new PrintStream(s.getOutputStream());
    }
    
    public void sendMessage(String x) {
	ps.print(x);
    }
}