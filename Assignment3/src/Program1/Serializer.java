package Program1;

public class Serializer {
/*
 * Serialization will be implemented in a Java class called Serializer, and will be invoked using the method:

public org.jdom.Document serialize(Object obj)
This method will serialize the complete state of the object passed in as parameter, and produce an XML document that can be stored to file, printed out, or sent over a network connection. Use the facilities provided by JDOM to help you do this, in particular the Document and XMLOutputter classes.
The XML document must have one root element with the tag-name serialized. The object, and any other objects that may need to be serialized, will be nested within the root element, listed one after the other. For example:
The element object will have two attributes: class and id. The class’s value will be the name of the class of the object, and the id’s value will be object’s unique identifier number, most likely created using IdentityHashMap. For example:
Nested within each object element will be 0 or more field elements. Each field element will have two attributes: name and declaringclass. The name’s value will be the name of the field, and the declaringclass’s value will be the name of the field’s declaring class. For example:
If the type of the field is a primitive, store the primitive as content of a value element. For example:
23.7
If the field is a reference to another object, store the id of that object as content of a reference element. For example:
5
Of course, the object being referred to must also be serialized, and will be another object element nested inside the root element. Array objects will be similar to the object element described above, except that an additional length attribute is used, and each element of the array will be stored as content to a value or reference element, depending on the component type. For example:
S
m
i
t
h
 * 
 * 
 * 
 * 
 */
}
