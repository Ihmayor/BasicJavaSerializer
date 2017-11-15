package Program2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
			try {
				System.out.println("Server running on: " + serverSocket.getLocalPort());
				Socket server = serverSocket.accept();
				System.out.println("Server connected to: " + server.getRemoteSocketAddress());
				// read message that client has sent to server
				DataInputStream in = new DataInputStream(server.getInputStream());
				System.out.println(in.readUTF()); // may have to use different read method with our assignment
				
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(in);
				Deserializer deserial = new Deserializer();
				deserial.deserialize(doc);
				
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
