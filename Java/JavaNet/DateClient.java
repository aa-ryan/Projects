import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;

// A command line client for DateServer.java

public class DateClient {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.print("Usage java DateClient IP-of-the-server");
			return;
		}

		try {
			var socket = new Socket(args[0], 59090);
			var in = new Scanner(socket.getInputStream());
			System.out.println("Server response: " + in.nextLine());
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

