package Program1;

public class HasRef {

	public HasPrim ref;
	public HasRef self;
	
	
	public HasRef(HasPrim setRef)
	{
		ref = setRef;
		self = this;
	}
}
