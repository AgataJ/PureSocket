import java.net.*;
import java.io.*;
import java.util.*;

public class Server_Over_TCP_Thread extends Thread
{
    Socket service;
    
    public Server_Over_TCP_Thread(Socket s)
    {
        service = s;
    }
    
    @Override
    public void run()
    {
        DataInputStream socketIn = null;
        DataOutputStream socketOut = null;
        try
        {            
            socketIn = new DataInputStream(service.getInputStream());
            socketOut = new DataOutputStream(service.getOutputStream());

			while (true) {
				// Read the message from the client
				String message = socketIn.readUTF();
				// Print the message
				System.out.println("Received: " + message);
				if (message.equals("stop")) break;
				// Answer goodbye to the client
				socketOut.writeUTF(message.toUpperCase());
			}
            
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (socketOut != null)
                    socketOut.close();
            } catch (IOException ex) {}
            try {            
                if (socketIn != null)
                    socketIn.close();
            } catch (IOException ex) {}
            try {            
                if (service != null)
                    service.close();
            } catch (IOException ex) {}
        }
    }
}