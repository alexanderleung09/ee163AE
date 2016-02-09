import java.net.*;
import java.io.*;
import java.util.*;
public class client1 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		try{
			/****************************************************
			* Make connection to server                         *
			****************************************************/
			Socket socket = new Socket("localhost",2222);
			BufferedReader reader1 = new BufferedReader (new InputStreamReader(socket.getInputStream()));
			System.out.println(reader1.readLine());							//receiving the welcome message from the server
			
			/***************************************************************
			* Get package number and send it to the server    			   *
			***************************************************************/
			Scanner src = new Scanner (System.in);
			int PacketNum = src.nextInt();
			DataOutputStream PacketNumStream = new DataOutputStream(socket.getOutputStream());
			PacketNumStream.writeInt(PacketNum);							//send the number of packet to the server
			
			/***************************************************************
			* Implement the sliding window				    			   *
			***************************************************************/
			int sent = 1;													//sliding window
			while (sent <= PacketNum){
				DataOutputStream Packet = new DataOutputStream(socket.getOutputStream());
				Packet.writeInt(sent);							//send the number of packet to the server
				System.out.println("the sending number is " + sent);
				
				DataInputStream ReturnPacket = new DataInputStream(socket.getInputStream());
				int ACKnum = ReturnPacket.readInt();							//read the ACK number
				System.out.println("the ack number is " + ACKnum);
				if (ACKnum == sent){
					sent = sent + 1;
				}
				
			}

		}catch(Exception e){e.getStackTrace();}
		
	}
	
}