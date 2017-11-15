package Program1;

import java.io.Console;
import java.util.LinkedList;
import java.util.Scanner;


public class SenderProgram {
	
	public static void main (String args[])
	{
		Scanner in;
		in = new Scanner(System.in);
	    System.out.print("Would you like to create an Object?(y/n)");   
	    String response =  in.nextLine();
		LinkedList<Object> created = new LinkedList<Object>();
				
		while(response.equals("Y") || response.equals("y"))
	    {
		    Object obj = ObjectCreator.getOption();
	    	created.add(obj);
		    System.out.print("Would you like to create an Object?(y/n)");
		    response =  in.nextLine();
	    }
		
		System.out.println("Serializing Objects");
		
	}
	
}
