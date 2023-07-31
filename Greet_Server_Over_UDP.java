import java.io.*;
import java.net.*;
import java.util.*;

public class Greet_Server_Over_UDP
{
    public static void main(String[] args)
    {
		Map<String, String> dictionary = Map.of("kot", "cat", "pies", "dog");
		
        try (DatagramSocket mySocket =
            new DatagramSocket(2000, InetAddress.getLocalHost()))
        {
			while (true) {
				// Receive the text
				byte[] buffer = new byte[1024];
				DatagramPacket packetR = new DatagramPacket(buffer, buffer.length);
				mySocket.receive(packetR);            
				//System.out.println("Received: " +
					//new String(packetR.getData()).trim());
				var translation = dictionary.get(new String(packetR.getData()).trim());
				if (translation != null) {
					System.out.println("Send translation: "+translation);
					// Get host and port from the message
					int destPort = packetR.getPort();
					InetAddress destAddr = packetR.getAddress();
					//System.out.println("Send response to: "+destAddr+":"+destPort);

					// Create the response to be sent
					//String text = "Goodbye";
					byte[] message = translation.getBytes();
					DatagramPacket packetS = new DatagramPacket(message, message.length,
						destAddr, destPort);
					mySocket.send(packetS);
				} else {
					System.out.println("No translation found");
				}
			}
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}