import java.net.*;
import java.io.*;

public class server1 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		int port = 2222;
		ServerSocket serversocket = new ServerSocket(port);
		System.out.println("Server Online\n");
		
		try{
			Socket socket = serversocket.accept();
			System.out.println("Connected to Client\n");
			
			String welcome = "Welcome to ECE361 Server. Service type: Lab2Server\r\n";					//handshaking message
			DataOutputStream Welcomewriter = new DataOutputStream(socket.getOutputStream());
			Welcomewriter.writeBytes(welcome);
			
			int PacketNum;
			DataInputStream PacketNumStream = new DataInputStream(socket.getInputStream());
			PacketNum = PacketNumStream.readInt();
			System.out.println(PacketNum);
			
			int lastAct = 0;
			DataInputStream test = new DataInputStream(socket.getInputStream());
			int PacketNum1 = test.readInt();
			System.out.println(PacketNum1);
			
		}catch(Exception e){e.getStackTrace();}
	}
}