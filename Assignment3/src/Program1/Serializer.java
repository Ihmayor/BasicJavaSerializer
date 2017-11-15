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
		for(Element e: d.getRootElement().getChildren())
		{
			root.addContent(e.clone());
		}
	}
	returnDoc.setRootElement(root);
	return returnDoc;
}
	
public Document serialize(Object obj)
{
	Document doc = new Document();
	try {
		Class<? extends Object> classObj = obj.getClass();
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
		
	}
	catch(Exception ex)
	{
		System.out.println("weee are here!!!");
       ex.printStackTrace();
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
	varAField.setAttribute("name", "varA");
	varAField.setAttribute("declaringClass",c.getName());
	Element val0 = new Element("value").addContent(Integer.toString(h.varA));
	varAField.addContent(val0);
    
	Element varBField = new Element("field");
	varAField.setAttribute("name", "varB");
	varBField.setAttribute("declaringClass",c.getName());
	Element val1 = new Element("value").addContent(Integer.toString(h.varB));
	varBField.addContent(val1);
	
	elem.addContent(varAField);
	elem.addContent(varBField);
	
	root.addContent(elem);
	doc.setRootElement(root);
	return doc;
}

public Document serializeHasPrimArray(Document doc, Class c, Object o)
{
	Element root = new Element("serialized");
	Element elem = new Element("object");
	Element arr = new Element("object");
	HasPrimArray hO = (HasPrimArray)o;
	
	elem.setAttribute("class",c.getName());
	elem.setAttribute("id", Integer.toString(o.hashCode()));

	//CREATE FIELD
	Element field = new Element("field");
	field.setAttribute("name","arrA");
	field.setAttribute("declaringclass",c.getName());
	
	//ATTACH ARRAY REFERENCE TO FIELD
	Element fieldRef = new Element("reference");
	fieldRef.addContent(Integer.toString(hO.arrA.hashCode()));
	field.addContent(fieldRef);
	
	
	//ATTACH FIELD
	elem.addContent(field);
	
	//Create array
	arr.setAttribute("class","[I");
	arr.setAttribute("id",Integer.toString(hO.arrA.hashCode()));
	arr.setAttribute("length", "3");
	Element i0 = new Element("value");
	i0.addContent(Integer.toString(hO.arrA[0]));
	Element i1 = new Element("value");
	i1.addContent(Integer.toString(hO.arrA[1]));
	Element i2 = new Element("value");
	i2.addContent(Integer.toString(hO.arrA[2]));
	
	//Add both serialized elements
	root.addContent(elem);
	root.addContent(arr);
	doc.setRootElement(root);
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
	
	Element field1 = new Element("field");
	field1.setAttribute("name","self");
	field1.setAttribute("declaringclass","HasRef");
	
	Element field1Ref = new Element("reference");
    field1Ref.addContent(Integer.toString(hO.hashCode()));
    field1.addContent(field1Ref);
	elem.addContent(field1);
	
	Element field = new Element("field");
	field.setAttribute("name","ref");
	field.setAttribute("declaringclass","HasRef");
	
	Element fieldRef = new Element("reference");
    fieldRef.addContent(Integer.toString(hO.ref.hashCode()));
    field.addContent(fieldRef);
    elem.addContent(field);
    
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
	doc.setRootElement(root);
	return doc;
}


public Document serializeHasRefArray(Document doc, Class c, Object o)
{
	HasRefArray hO= (HasRefArray)o;
	Element root = new Element("serialized");
	
	//Create Main Class
	Element elem = new Element("object");
	elem.setAttribute("class", "HasRefArray");
	elem.setAttribute("id", Integer.toString(hO.hashCode()));


	//Create Array Object of Ref
    Element arr = new Element("object");
    arr.setAttribute("class", "[HasPrim");
    arr.setAttribute("id", Integer.toString(hO.refArray.hashCode()));
    arr.setAttribute("length", "3");

    //Create Element Refs for Array
	Element index0 = new Element("reference");
	index0.addContent(Integer.toString(hO.refArray[0].hashCode()));
	Element index1 = new Element("reference");
	index0.addContent(Integer.toString(hO.refArray[1].hashCode()));
	Element index2 = new Element("reference");
	index0.addContent(Integer.toString(hO.refArray[2].hashCode()));

    //Add Element Refs for Array
	arr.addContent(index0);
	arr.addContent(index1);
	arr.addContent(index2);
	
	//Add this to root.
	root.addContent(arr);

	//Create Field for Array in Original Element
	Element fieldArr = new Element("field");
	fieldArr.setAttribute("name","refArray");
	fieldArr.setAttribute("declaringclass", c.getName());
	
	Element arrRef = new Element("reference");
	arrRef.addContent(Integer.toString(hO.refArray.hashCode()));
	fieldArr.addContent(arrRef);
	//Attach Field
	elem.addContent(fieldArr);
	
	//Serialize Each Object In Array
	for(int i = 0; i <3; i++)
	{
		//Set Reference
		Element ref = new Element("object");
		ref.setAttribute("class","HasPrim");
		ref.setAttribute("id", Integer.toString(hO.refArray[i].hashCode()));

		//Set FieldA
		Element varAField = new Element("field");
		varAField.setAttribute("name", "varA");
		varAField.setAttribute("declaringClass",c.getName());
		Element val0 = new Element("value").addContent(Integer.toString(hO.refArray[i].varA));
		varAField.addContent(val0);
	    
		//Set FieldB
		Element varBField = new Element("field");
		varBField.setAttribute("name", "varB");
		varBField.setAttribute("declaringClass",c.getName());
		Element val1 = new Element("value").addContent(Integer.toString(hO.refArray[i].varB));
		varBField.addContent(val1);
	
		//Add each as root
		root.addContent(ref);
	}
	
	doc.setRootElement(root);
	return doc;
}


public Document serializeHasJavaList(Document doc, Class c, Object o)
{
	Element root = new Element("serialized");
	Element elem = new Element("object");

	//Get Original Objects
	HasJavaList hO = (HasJavaList)o;
	
	//Get original linked list
	LinkedList<Object> l1 = hO.javaList;	

	//Get Reference Objects in Array
	HasPrim o0 = (HasPrim)l1.get(0);
    HasPrimArray o1= (HasPrimArray)l1.get(1);
    HasRef o2 = (HasRef)l1.get(2);
    
    //Set Element
    elem.setAttribute("class", "HasJavaList");
    elem.setAttribute("id", Integer.toString(hO.hashCode()));
    
    //Create Field for Array
    Element field = new Element("field");
    field.setAttribute("name","javaList");
    field.setAttribute("declaringclass","HasJavaList");
    
    //Create Field Ref for Array
    Element fieldRef = new Element("reference");
    fieldRef.addContent(Integer.toString(hO.javaList.hashCode()));
    field.addContent(fieldRef);
    elem.addContent(field);
    root.addContent(elem);
    doc.setRootElement(root);
    
    Document doc0 = serializeHasRefArray(new Document(), o0.getClass(), o0);
    Document doc1 = serializeHasRefArray(new Document(), o1.getClass(), o1);
    Document doc2 = serializeHasRefArray(new Document(), o2.getClass(), o2);
	
    
    
	LinkedList<Document> totalDocs = new LinkedList<Document>();
	totalDocs.add(doc);
	totalDocs.add(doc0);
	totalDocs.add(doc1);
	totalDocs.add(doc2);
	
	Document combinedDoc = combineDocument(totalDocs);
	
	return combinedDoc;
}

}
