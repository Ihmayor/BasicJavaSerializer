package Program1;

public class HasRefArray {

	public HasPrim[] refArray;
	public int id;
	
	public HasRefArray(HasPrim[] setRefArray)
	{
		setRefArray = refArray;
		id = this.hashCode();
	}
}
