package Program1;

public class HasRef {

	public HasPrimArray ref;
	public HasRef next;
	
	
	public HasRef(HasPrimArray setRef, HasRef setNext)
	{
		ref = setRef;
		next = setNext;
	}
}
