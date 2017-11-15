package Program1;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;


public class SenderProgram {
	
	public static void main (String args[])
	{
		String address = "127.0.0.1";
		int portNumber = 8080;
		try {
			System.out.println("Connecting to server: " + address + " on port: " + portNumber);
			Socket client = new Socket(address, portNumber);
			System.out.println("Client connected to " + client.getRemoteSocketAddress());			
			
			Scanner in;
			in = new Scanner(System.in);
		    System.out.print("Would you like to create an Object?(y/n)");   
		    String response =  in.nextLine();
		    if (response.equals("y") ||response.equals("y"))
		    {
				LinkedList<Object> created = new LinkedList<Object>();
				
				while(response.equals("Y") || response.equals("y"))
			    {
				    Object obj = ObjectCreator.getOption();
			    	created.add(obj);
			    	System.out.println(obj.getClass().getName());
			    	System.out.print("Would you like to create another Object?(y/n)");
				    response =  in.nextLine();
			    }
				
				System.out.println("Serializing Objects");

				Serializer serializer = new Serializer();
				LinkedList<Document> docList = new LinkedList<Document>();
				for(Object obj: created)
				{
					Document doc = serializer.serialize(obj);
					docList.add(doc);
				}
			
				Document sendOff = serializer.combineDocument(docList);
				
			    //notes
			    //Client is the object creator
			    
			    //Server is the deserializer
				
				DataOutputStream outStream = new DataOutputStream(client.getOutputStream());
				outStream.writeUTF("Hello from client");
			
				XMLOutputter outputter = new XMLOutputter();
				outputter.output(sendOff, outStream);
		    }
		    
			
			// create input stream and send message from client to server 
//			DataInputStream in = new DataInputStream(client.getInputStream());
			
	//		System.out.println("Server messaged: " + in.readUTF());
			client.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
}
