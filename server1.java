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
			
			/****************************************************
			* Make connection to server                         *
			****************************************************/
			
			String welcome = "Welcome to ECE361 Server. Service type: Lab2Server\r\n";					//handshaking message
			DataOutputStream Welcomewriter = new DataOutputStream(socket.getOutputStream());
			Welcomewriter.writeBytes(welcome);
			
			/****************************************************
			* Receive the package number from the client        *
			****************************************************/
			int PacketNum;
			DataInputStream PacketNumStream = new DataInputStream(socket.getInputStream());				//store the total number of package
			PacketNum = PacketNumStream.readInt();
			System.out.println(PacketNum);
			
			/***************************************************************
			* Implement the sliding window				    			   *
			***************************************************************/
			
			int lastAct = 0;
			/*DataInputStream test = new DataInputStream(socket.getInputStream());
			int PacketNum1 = test.readInt();
			System.out.println(PacketNum1);*/
			while (lastAct < PacketNum){
				DataInputStream ReceiveNumberStream = new DataInputStream(socket.getInputStream());
				int ReceiveNumber = ReceiveNumberStream.readInt();
				System.out.println("the recieve number is " + ReceiveNumber);
			
				if (ReceiveNumber == lastAct + 1){
					DataOutputStream ACKNumberStream = new DataOutputStream(socket.getOutputStream());
					ACKNumberStream.writeInt(ReceiveNumber);												//send the number of ack packet number
					System.out.println("the ack number is " + ReceiveNumber);
					lastAct = lastAct + 1;
				}
			}
			System.out.println("the receiving process is finished");
			
		}catch(Exception e){e.getStackTrace();}
	}
}