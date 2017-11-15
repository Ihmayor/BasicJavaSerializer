package Program2;

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
	}
	

	public static void ShowFields(Class classObject, Object obj)
	{
	
	}


	
}
