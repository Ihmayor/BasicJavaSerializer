package Program1;
import org.jdom2.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedList;

public class Serializer {

public Document combineDocument(LinkedList<Document> docs)
{
	Document returnDoc= new Document();
	Element root = new Element("serialized");
	for(Document d: docs)
	{
		root.addContent(d.getRootElement());
	}
	returnDoc.addContent(root);
	return returnDoc;
}
	
public Document serialize(Object obj)
{
	Document doc = new Document();
	Class classObj = obj.getClass();
	String className = classObj.getName();
	switch(className) {
	case "Program1.HasPrim":
		doc = serializeHasPrim(doc, classObj, obj);
		break;
	case "Program1.HasPrimArray":
		doc = serializeHasPrimArray(doc,classObj, obj);
		break;
	case "Program1.HasJavaList":
		doc = serializeHasJavaList(doc,classObj, obj);
		break;
	case "Program1.HasRef":
		doc = serializeHasRef(doc,classObj, obj);
		break;
	case "Program1.HasRefArray":
		doc = serializeHasRefArray(doc,classObj,obj);
		break;
	}
	return doc;
}

public Document serializeHasPrim(Document doc, Class c, Object o)
{
	Element root = new Element("serialized");
	Element elem = new Element("object");
    HasPrim h = (HasPrim)o;
    
	elem.setAttribute("class", c.getName());
	elem.setAttribute("id",Integer.toString(o.hashCode()));
	
	Element varAField = new Element("field");
	varAField.setAttribute("class", "Integer");
	varAField.setAttribute("declaringClass",c.getName());
	Element val0 = new Element("value").addContent(Integer.toString(h.varA));
	varAField.addContent(val0);
    
	Element varBField = new Element("field");
	varBField.setAttribute("class", "Integer");
	varBField.setAttribute("declaringClass",c.getName());
	Element val1 = new Element("value").addContent(Integer.toString(h.varB));
	varBField.addContent(val1);
	
	elem.addContent(varAField);
	elem.addContent(varBField);
	
	root.addContent(elem);
	doc.addContent(root);
	return doc;
}

public Document serializeHasPrimArray(Document doc, Class c, Object o)
{
	Element elem = new Element("object");
	Element arr = new Element("object");
	HasPrimArray hO = (HasPrimArray)o;
	
	elem.setAttribute("class",c.getName());
	elem.setAttribute("id", Integer.toString(o.hashCode()));

	arr.setAttribute("class","[I");
	arr.setAttribute("id",Integer.toString(o.hashCode()));
	arr.setAttribute("length", "3");
	Element i0 = new Element("value");
	i0.addContent(Integer.toString(hO.arrA[0]));
	Element i1 = new Element("value");
	i1.addContent(Integer.toString(hO.arrA[1]));
	Element i2 = new Element("value");
	i2.addContent(Integer.toString(hO.arrA[2]));
	
	doc.addContent(elem);
	doc.addContent(arr);
	
	return doc;
}


public Document serializeHasRef(Document doc, Class c,Object o)
{
	//Convert Object
	HasRef hO = (HasRef)o;
	
	//Set Root
	Element root = new Element("serialized");
	
	//Set hasRefElem
	Element elem = new Element("object");
	elem.setAttribute("class",c.getName());
	elem.setAttribute("id", Integer.toString(o.hashCode()));
	Element fieldRef = new Element("reference");
    fieldRef.addContent(Integer.toString(hO.ref.hashCode()));
    elem.addContent(fieldRef);
    
	//Set Reference
	Element ref = new Element("object");
	ref.setAttribute("class","HasPrim");
	ref.setAttribute("id", Integer.toString(hO.ref.hashCode()));

	//Set FieldA
	Element varAField = new Element("field");
	varAField.setAttribute("class", "Integer");
	varAField.setAttribute("declaringClass",c.getName());
	Element val0 = new Element("value").addContent(Integer.toString(hO.ref.varA));
	varAField.addContent(val0);
    
	//Set FieldB
	Element varBField = new Element("field");
	varBField.setAttribute("class", "Integer");
	varBField.setAttribute("declaringClass",c.getName());
	Element val1 = new Element("value").addContent(Integer.toString(hO.ref.varB));
	varBField.addContent(val1);
    
	ref.addContent(varAField);
	ref.addContent(varBField);	
	root.addContent(elem);
	root.addContent(ref);
	doc.addContent(root);
	return doc;
}

public Document serializeHasRefArray(Document doc, Class c, Object o)
{
	HasRefArray hO= (HasRefArray)o;
	Element root = new Element("serialized");
	Element elem = new Element("object");
	
	
	for(int i = 0; i <3; i++)
	{
		//Set Reference
		Element ref = new Element("object");
		ref.setAttribute("class","HasPrim");
		ref.setAttribute("id", Integer.toString(hO.refArray[i].hashCode()));

		//Set FieldA
		Element varAField = new Element("field");
		varAField.setAttribute("class", "Integer");
		varAField.setAttribute("declaringClass",c.getName());
		Element val0 = new Element("value").addContent(Integer.toString(hO.refArray[i].varA));
		varAField.addContent(val0);
	    
		//Set FieldB
		Element varBField = new Element("field");
		varBField.setAttribute("class", "Integer");
		varBField.setAttribute("declaringClass",c.getName());
		Element val1 = new Element("value").addContent(Integer.toString(hO.refArray[i].varB));
		varBField.addContent(val1);
	}
	
	return doc;
}


public Document serializeHasJavaList(Document doc, Class c, Object o)
{
	Element elem = new Element("test");
	
	
	return doc;
}

}
