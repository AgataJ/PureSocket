import java.io.*;
import java.net.*;
import java.util.*;

public class Greet_Client_Over_UDP
{
    public static void main(String[] args)
    {
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
        try (DatagramSocket mySocket = new DatagramSocket())
        {
			mySocket.setSoTimeout(1000);
			while (true) {
				try {
				// Create the packet to be sent
				//String text = "Hello";
				String wordToTranslate = scanner.nextLine();
				byte[] message = wordToTranslate.getBytes();
				DatagramPacket packetS = new DatagramPacket(message, message.length,
					InetAddress.getLocalHost(), 2000);
				mySocket.send(packetS);
				//System.out.println("Send request to: "+packetS.getAddress()+":"+packetS.getPort());
				
				// Receive the response
				byte[] buffer = new byte[1024];
				DatagramPacket packetR = new DatagramPacket(buffer, buffer.length);
				mySocket.receive(packetR);   
				System.out.println("Received: " +
					new String(packetR.getData()).trim());
				} catch (SocketTimeoutException e) {
					System.out.println("No translation found");
				}
			}
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}