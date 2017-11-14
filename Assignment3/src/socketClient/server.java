package socketClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends Thread {
	private ServerSocket serverSocket;
	
	public server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.setSoTimeout(100000); // will wait for client before it expires
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			try {
				System.out.println("Server running on: " + serverSocket.getLocalPort());
				Socket server = serverSocket.accept();
				System.out.println("Server connected to: " + server.getRemoteSocketAddress());
				// read message that client has sent to server
				DataInputStream in = new DataInputStream(server.getInputStream());
				System.out.println(in.readUTF()); // may have to use different read method with our assignment
				
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF("Thank you for connecitng");
				server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		int port = 8080;
		try {
			Thread t = new server(port);
			t.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
