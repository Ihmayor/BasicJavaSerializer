package Program1;

import java.io.Console;
import java.util.LinkedList;

public class ObjectCreator {
    static Console console;

	static Object getOption()
	{
	
		console = System.console();
		System.out.println("Class Options");
	    System.out.println("Class with only primitives: 0");
	    System.out.println("Class with a primitive array: 1");
	    System.out.println("Class with a  reference of another object: 2");
	    System.out.println("Class with an array of references:3");
	    System.out.println("Class with a java list: 4");
	   
	    Object obj = null;
	    
	    System.out.print("Please enter the class: ");
		String optionSelected  = console.readLine();		
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

	
	
	

//A simple object with only primitives for instance variables. The user of your program must also be able to set the values for these fields.
public static Object createHasPrim()
{
	System.out.println("Please init values for class: hasPrim (int varA, intvarB)");
    System.out.print("Please enter the first int for varA: ");
	String varA  = console.readLine();	
	System.out.println();
	System.out.println();	System.out.print("Please enter the second int for varB: ");
	String varB  = console.readLine();
	
	return new HasPrim(Integer.valueOf(varA), Integer.valueOf((varB)));
}

//An object that contains references to other objects. Of course, these other objects must also be created at the same time, and their primitive instance variables must be settable by the user. Your program must also be able to deal with circular references (i.e. objects connected in a graph).
public static Object createHasRef()
{
	System.out.println("Please init values for class: hasRef(hasPrim ref)");
    HasPrim obj = (HasPrim)createHasPrim();
	HasRef made = new HasRef(obj);
	return made;	
}

//An object that contains an array of primitives. Allow the user to set the values for the array elements to arbitrary values.
public static Object createHasPrimArray()
{
	System.out.println("Please init values for class: hasPrimArray(int[3] array)");
	System.out.print("Please enter int at index0: ");
	String var0 = console.readLine();
	System.out.print("Please enter int at index1: ");
    String var1 = console.readLine();
	System.out.print("Please enter int at index2: ");
	String var2 = console.readLine();
	int [] newArray = new int[] {Integer.valueOf(var0),Integer.valueOf(var1),Integer.valueOf(var2)};
	
	return new HasPrimArray(newArray);
}

//An object that contains an array of object references. The other objects must also be created at the same time.
public static Object createHasRefArray()
{
	System.out.println("Please init values for class: hasPrimArray(hasPrim[3] array)");
	HasPrim hasPrim0 = (HasPrim)createHasPrim();
	HasPrim hasPrim1 = (HasPrim)createHasPrim();
	HasPrim hasPrim2 = (HasPrim)createHasPrim();
	
	return new HasRefArray(new HasPrim[] {hasPrim0, hasPrim1, hasPrim2});
}

//An object that uses an instance of one of Java’s collection classes to refer to several other objects. These objects, too, must be created at the same time.
public static Object createHasJavaList()
{
	
	System.out.println("Please init values for class: hasJavaList(new LinkedList<object>(hasPrim, hasPrimArray, hasRef)");
	HasPrim var0 = (HasPrim)createHasPrim();
	HasPrimArray var1 = (HasPrimArray)createHasPrimArray();
	HasRef var2 = (HasRef)createHasRef();
	LinkedList<Object> list = new LinkedList<Object>();
	list.add(var0);
	list.add(var1);
	list.add(var2);
	
	return new HasJavaList(list);
}
  
}

