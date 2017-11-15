package Program2;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

public class ReceiverProgram extends Thread{

	private ServerSocket serverSocket;
	
	public ReceiverProgram (int port) {
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.setSoTimeout(100000); // will wait for client before it expires
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public void run() {
		while (true) {
			System.out.println("Whyyy");
			try {
				System.out.println("Server running on: " + serverSocket.getLocalPort());
				Socket server = serverSocket.accept();
				System.out.println("Server connected to: " + server.getRemoteSocketAddress());
				
				
				// read message that client has sent to server
				BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream())); 
				
				System.out.println(in.readLine());
				
	//		    SAXBuilder builder = new SAXBuilder();
//			    Document doc = builder.build(in);
		
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF("Thank you for connecitng");

				//Deserializer deserial = new Deserializer();
//				deserial.deserialize(doc);
				 server.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		int port = 8080;
		try {
			Thread t = new ReceiverProgram(port);
			t.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
