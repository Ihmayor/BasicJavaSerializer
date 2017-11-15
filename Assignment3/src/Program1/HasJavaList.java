package Program1;

import java.util.LinkedList;

public class HasJavaList {
	
	public LinkedList<Object> javaList;
	public int id;
	
	public HasJavaList(LinkedList<Object> newList){
		javaList = newList;
		id = this.hashCode();
	}
	
	

}
