package GUI;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NetworkReciever
{
    private ServerSocket ss;
    private Socket s;
    private Scanner sc;
    private PrintStream ps;
    
    public NetworkReciever(int port) throws IOException {
	ss = new ServerSocket(port);
	s = ss.accept();
	sc = new Scanner(s.getInputStream());
	ps = new PrintStream(s.getOutputStream());
    }
    
    public String readNext() {
	return sc.next();
    }
}
