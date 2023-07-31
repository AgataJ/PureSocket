import java.net.*;
import java.io.*;

public class Greet_Server_Over_TCP
{
    public static void main(String[] args)
    {
        try (ServerSocket server = new ServerSocket(2000))
		{
			System.out.println("Listening...");
			while (true)
			{
				Socket service = server.accept();
				System.out.println("Connection established");
				Server_Over_TCP_Thread st = new Server_Over_TCP_Thread(service);
				st.start();
			}
		} catch (IOException e) {
			System.out.println("Connection finished");
			System.out.println(e);
		}
    }
}