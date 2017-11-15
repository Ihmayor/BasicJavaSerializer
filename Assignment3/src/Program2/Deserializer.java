package Program2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import Program1.HasPrim;

public class Deserializer {
 
	public Object deserialize(Document document)
	{
		LinkedList<Object> deserialized = new LinkedList<Object>();
		
		LinkedList<HasPrim> hasPrimRef = new LinkedList<HasPrim>();
		for(Element e : document.getRootElement().getChildren("object"))
		{			
			String className = e.getAttribute("class").toString();
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
			String className = e.getAttribute("class").toString();
		    HasRef obj = null;
			if(className.equals("Program1.HasRef"))
			{
				obj = (HasRef)deserializeHasRef(e, hasPrimRef.toArray(a));
			}
		
		}
		return deserialized;
	}
	
	public Object deserializeHasRef(Element e, Link)
	
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
