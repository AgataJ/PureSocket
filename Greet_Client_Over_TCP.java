import java.net.*;
import java.io.*;
import java.util.*;

public class Greet_Client_Over_TCP
{
    public static void main(String[] args)
    {
        try (   
            Socket mySocket = new Socket("localhost", 2000);
            DataInputStream socketIn =
                new DataInputStream(mySocket.getInputStream());
            DataOutputStream socketOut =
                new DataOutputStream(mySocket.getOutputStream());
        )
        {                        
			Scanner scanner = new Scanner(new InputStreamReader(System.in));
			while (true) {
				String userMessage = scanner.nextLine();
				socketOut.writeUTF(userMessage);
				String response = socketIn.readUTF();
				System.out.println("Received: " + response);
			}
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}