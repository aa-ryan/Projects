import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class DateServer 
{
	public static void main(String[] args) throws IOException
	{
		try (var listener = new ServerSocket(59090)) 
		{
			System.out.println("The date server is running");
			
			while(true) {
				try (var socket = listener.accept())
				{
					var out = new PrintWriter(socket.getOutputStream(), true);
					out.println(new Date().toString());
					System.out.println("Socket");
				}
			}
		}
	}
}


// javac DateServer.java && java DateServer
// netstat -an | grep 59090
// nc localhost 59090