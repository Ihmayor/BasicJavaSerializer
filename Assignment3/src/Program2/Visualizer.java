package Program2;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.LinkedList;

public class Visualizer {
	
	public static void VisualizeObjects(LinkedList<Object> objects)
	{
		for(Object obj: objects)
		{
			VisualizeObject(obj);
		}
	}
	
	public static void VisualizeObject(Object object)
	{
		//Fetch Class Object for further inspection
		Class classObj = object.getClass();
		String declaringClass = classObj.getName();
		System.out.println("Class Name: "+declaringClass);
		inspectFields(object,classObj,true,new int[] {});
	}
	
	
	//Method to specifically inspect fields and print results to console
	public static void inspectFields(Object toInspect, Class classObj, boolean isRecursive, int[] alreadyInspected) {
		System.out.println("Declaring Class Name: "+classObj.getName());
			
		if (isInspected(toInspect.hashCode(), alreadyInspected))
			return;
		else 
		{
			int[] appendInspected = addHashCode(toInspect.hashCode(), alreadyInspected);

			String tabOver = calculateTabOver(alreadyInspected.length);
			
			//The fields the class declares
			System.out.println();
			System.out.println();
			System.out.println(tabOver+"Declared Fields of Class")	;
			Field[] f = classObj.getDeclaredFields();		
			try 
			{
				for (int i = 0; i<f.length; i++)
				{
					f[i].setAccessible(true);
					Object val = null;
					System.out.println(tabOver+"=============================================");
					System.out.println(tabOver+"Field Name: \t"+f[i].getName());
					System.out.println(tabOver+"Field Type: \t"+f[i].getType().getName());
					if (f[i].getType().isPrimitive())
					{	
							val = getPrimitiveValue(f[i].getType().getName(),f[i].get(toInspect));
						
					}
					else if (f[i].getType().isArray())
					{	
						//Must be able to handle arrays (names, component Type, length, all contents)
					   f[i].setAccessible(true);
					   Class cType = f[i].getType().getComponentType();
					   Object array = f[i].get(toInspect);
					   System.out.println(tabOver+f[i].getName());
					   System.out.println(tabOver+"\tArray Length: "+Array.getLength(array));
					   System.out.println(tabOver+"\tArray Component Type: "+ cType.getName());
					   System.out.println(tabOver+"\tArray Contents:");
					   
					   for (int index = 0; index < Array.getLength(array); index++)
					   {
							
						   System.out.println(tabOver+"\t===================================================");
						   Object obj = Array.get(array, index);
						   if (obj == null)
						   {
							   System.out.println(tabOver+"\tIndex: " +index + " Val: "+obj);
			    		   }
						   else if (isRecursive)
						   {
							   System.out.println(tabOver+"\tIndex: " +index + " Value: ");
				    		   //Add Inspected Hashcode for Tabbing purposes
							   int[] accountForArray = addHashCode(-1, appendInspected);
							   inspectFields(obj,obj.getClass(), isRecursive, accountForArray);
						   }
						   else
						   {
							   System.out.println(tabOver+"\tIndex:"+index+" Value: "+obj.hashCode());
						   }
					   }
					}
					//If field contains object:
					else
					{
						
						//if recursive => Inspect nested object
						if (isRecursive)
						{	
							if (f[i].get(toInspect) != null)
								inspectFields(f[i].get(toInspect), f[i].get(toInspect).getClass(), isRecursive, appendInspected);
							else 
								val = null;
							val = "Inspected Inner Object";
						}
					    //If not => print hash code.
						else
						{
							if (f[i].get(toInspect) != null)
								val = f[i].get(toInspect).hashCode();
						}
					
					}
					
				if (val != null)
					System.out.println(tabOver+"Field Value: "+val.toString());
				else 
					System.out.println(tabOver+"Field Value is null.");
				}
			}
			 catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
			}
		}
	}
	
	//Add hashcode to array of already inspected objects
	private static int[] addHashCode(int hashCode, int[] alreadyInspected)
	{
		int[] appendInspected = new int[alreadyInspected.length+1];
		for(int i = 0; i< alreadyInspected.length;i++)
		{
			appendInspected[i] = alreadyInspected[i];
		}
		appendInspected[alreadyInspected.length] = hashCode;
		
		return appendInspected;
	}
	
	private static String calculateTabOver(int repeatNum)
	{
		String tabOver = "";
		for (int tabNum = 0; tabNum < repeatNum; tabNum++) {
			tabOver += "\t";
		}
		return tabOver;
	}
	
	//Check If the method has been already inspected as to not inspect it again
	private static boolean isInspected(int hashCode, int[] alreadyInspected) {
		for (int hash : alreadyInspected) {
			if (hash == hashCode)
				return true;
		}
		return false;
	}

	
	//Get the Base Non-Recursive Value of an object regardless what it is. 
	public static Object getNonRecursiveValue(Object toGetVal) {
		Object val = null;
		Class classObj = toGetVal.getClass();
		if (classObj.isPrimitive()) {
			try {
				val = getPrimitiveValue(classObj.getName(), toGetVal);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		} else if (classObj.isArray()) {
			// Must be able to handle arrays (names, component Type, length, all contents)
			try {
				System.out.println("\tArray Name: " + toGetVal.getClass().getName());
				System.out.println("\tArray Length: " + Array.getLength(toGetVal));
				System.out.println("\tArray Component Type: " + Array.class.getComponentType().getName());
				System.out.println("\tArray Contents:");
				for (int index = 0; index < Array.getLength(toGetVal); index++) {
					Object obj = Array.get(toGetVal, index);
					if (obj != null)
						val = getNonRecursiveValue(obj);
					System.out.println("\tArray Index: " + index + " Value: " + val);
				}
				val = "Array Inspected";
			} catch (IllegalArgumentException e) {
				{
					System.out.println("Illegal Access Exception for Object" + toGetVal.getClass().getName());
					val = "Unattained";
				}
			}
		} else {
			// If not => print hash code.
			val = toGetVal.hashCode();
		}

		return val;
	}

	//Get value of a Primitive Object from Generic Object Type
	public static Object getPrimitiveValue(String typeName, Object toGetVal)
	{
		Object val = "No Value";
		switch (typeName) {
		case "Double":
			val = ((Double) toGetVal).doubleValue();
			break;
		case "double":
			val = (double)toGetVal;
			break;
		case "Integer":
			val = ((Integer) toGetVal).intValue();
			break;
		case "int":
			val = (int)toGetVal;
			break;
		case "void":
		case "Void":
			val = ((Void) toGetVal);
			break;
		case "Float":
			val = ((Float) toGetVal).floatValue();
			break;
		case "float":
			val = (float)toGetVal;
			break;
		case "Boolean":
			val = ((Boolean) toGetVal).booleanValue();
			break;
		case "boolean":
			val = (boolean)toGetVal;
			break;
		case "Character":
			val = ((Character) toGetVal).charValue();
			break;
		case "char":
			val = (char)toGetVal;
			break;
		case "Long":
			val = ((Long) toGetVal).longValue();
			break;
		case "long":
			val = (long)toGetVal;
			break;
		case "Short":
			val = ((Short) toGetVal).shortValue();
			break;
		case "short":
			val = (short)toGetVal;
			break;
		case "Byte":
			val = ((Byte) toGetVal).byteValue();
			break;
		case "byte":
			val = (byte)toGetVal;
			break;
		default:
			val = "Unattained";
			break;
		}
		return val;
	}

	
}
