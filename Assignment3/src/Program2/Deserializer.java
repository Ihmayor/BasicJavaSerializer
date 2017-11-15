package Program2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import Program1.HasPrim;
import Program1.HasRef;

public class Deserializer {
 
	public Object deserialize(Document document)
	{
		LinkedList<Object> deserialized = new LinkedList<Object>();
		
		LinkedList<HasPrim> hasPrimRef = new LinkedList<HasPrim>();
		for(Element e : document.getRootElement().getChildren("object"))
		{			
			String className = e.getAttributeValue("class").toString();
		    HasPrim obj = null;
			if(className.equals("Program1.HasPrim"))
			{
				obj = (HasPrim)deserializeHasPrim(e);
				hasPrimRef.push(obj);
				deserialized.push(obj);
			}
		}
		
		for(Element e: document.getRootElement().getChildren("object"))
		{
			String className = e.getAttributeValue("class").toString();
		    HasRef obj = null;
			if(className.equals("Program1.HasRef"))
			{
				obj = (HasRef)deserializeHasRef(e, (HasPrim[])hasPrimRef.toArray());
				deserialized.push(obj);
			}
		
		}
	
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		LinkedList<Object> isPrimArray = new LinkedList<Object>();
		for(Element e : document.getRootElement().getChildren("object"))
		{			
			String className = e.getAttributeValue("class").toString();
		    Object obj = null;
			if(className.equals("[I"))
			{
				obj = deserializeIntegerArray(e);
				map.put(Integer.getInteger(e.getAttributeValue("id").toString()),obj);
			}
		}
		
		for(Element e : document.getRootElement().getChildren("object"))
		{			
			String className = e.getAttributeValue("class").toString();
		    Object obj = null;
			if(className.equals("Program1.HasPrimArray"))
			{
				obj = deserializeHasPrimArray(e, map);
			}
		}
		return deserialized;
	}
	
	
	public Object deserializeHasPrimArray(Element e, Map<Integer, Object> map)
	{
		Object newObj = null;
		try {
			Class classObj = Class.forName(e.getAttributeValue("class").toString());
			Object obj = classObj.newInstance();
			Field f1 = classObj.getField("id");
			f1.setAccessible(true);
			f1.set(obj, Integer.valueOf(e.getAttributeValue("id").toString()));
			
			Field f = classObj.getField("refArray");
			f.setAccessible(true);
			Integer i = Integer.getInteger(e.getChild("field").getChildText("reference"));
			Object arr = map.get(i);
			f.set(obj, arr);
			newObj = obj;
		}
		catch(Exception ex)
		{
			ex.printStackTrace(s);
		}
		retrun newObj;
		return null;
	}
	
	
	public Object deserializeIntegerArray(Element e)
	{
		int length = Integer.valueOf(e.getAttributeValue("length").toString());
		int [] retInt = new int[length];
		List<Element> vars = e.getChildren("value");
		for(int i= 0; i< length; i++)
		{
			retInt[i] = Integer.valueOf((vars.get(i)).getText());
		}
        		
		return retInt;
	}
	
	public Object deserializeHasRef(Element e, HasPrim[] prims)
	{
		HasRef newObj = null;
		try {
			Class c = Class.forName(e.getAttributeValue("class").toString());
			Object obj = c.newInstance();
		    HasPrim ref = null;
			for(Element f : e.getChildren("field"))
			{
			    String childRef = f.getChildText("reference");
			    for(int i = 0; i< prims.length;i++)
			    {
			    	if(prims[i].id == Integer.valueOf(childRef))
			    	{
			    		ref = prims[i];
			    		break;
			    	}
			    }
			}
			Field f = c.getField("self");
			Field f1 = c.getField("ref");
			f.setAccessible(true);
			f.setAccessible(true);
			f.set(obj, obj);
			f1.set(obj, ref);
					
			newObj = (HasRef)obj;
		}
		catch(Exception ex)
		{
			
		}
		return newObj;
	}
	
	public Object deserializeHasPrim(Element e)
	{
		Object obj = null;
		try {
			Class c = Class.forName(e.getAttributeValue("class"));
			obj = c.newInstance();
			List<Element> fields = e.getChildren("field");
			for(Element field:fields)
			{				
				Field f= c.getField(field.getAttributeValue("name"));
				f.setAccessible(true);
				f.set(obj, Integer.valueOf(field.getChildText("value")));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return obj;
	}
	
	
	
	/*
	public Object deserializeArray(Element e)
	{
		Object obj = null;
		try {
			Class c = Class.forName(e.getAttributeValue("class"));
			obj = c.newInstance();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return obj;
		
	}
	
	
	public Object createHasPrimArray(Element e) {
		Object obj = null;
		try {
			Class c = Class.forName(e.getAttributeValue("class"));
			obj = c.newInstance();
			List<Element> fields = e.getChildren("field");
			for(Element field:fields)
			{				
				Field f= c.getField(field.getAttributeValue("name"));
				f.setAccessible(true);
				f.set(obj, Integer.valueOf(field.getChildText("value")));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return;
	}
//	public Object createHasRef()
	{
		
	}
	public Object createHasRefArray()
	{
		
	}

 * Deserialization will be implemented in a Java class called Deserializer, and will be invoked using the method:

public Object deserialize(org.jdom.Document document)

This method will deserialize an XML document passed in as parameter,
 returning the reconstituted object (and any objects it refers to).
  Use the facilities provided by JDOM to help with this, 
  in particular the Document and SAXBuilder classes.
 */
}
