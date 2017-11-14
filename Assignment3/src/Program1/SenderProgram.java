package Program1;

import java.io.Console;

public class SenderProgram {
    static Console console;
	public static void main (String args[])
	{
		console = System.console();
		
	    Object obj = getOption();
	}
	
	static Object getOption()
	{
		System.out.println("Class Options");
	    System.out.println("Class with only primitives: 0");
	    System.out.println("Class with a primitive array: 1");
	    System.out.println("Class with a  reference of another object: 2");
	    System.out.println("Class with an array of references:3");
	    System.out.println("Class with a java list: 4");
	   
	    Object obj = null;
	    
		String optionSelected  = console.readLine("Please enter the class: ");		
	    switch(optionSelected)
	    {
	    case "0":
	    	break;
	    case "1":
	    	break;
	    case "3":
	    	break;
	    case "4":
	    	break;
	    default:
	    	System.out.println("ERROR! THIS IS NOT A SELECTED OPTION");
	    	obj = getOption();
	    	break;
	    }
	    
	    return obj;
	}
	
}
