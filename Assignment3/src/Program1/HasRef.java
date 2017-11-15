package Program1;

public class HasRef {

	public HasPrim ref;
	public HasRef self;
	public int id;
	
	public HasRef(HasPrim setRef)
	{
		id = this.hashCode();
		ref = setRef;
		self = this;
	}
}
