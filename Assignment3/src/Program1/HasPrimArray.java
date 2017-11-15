package Program1;

public class HasPrimArray {

	public int [] arrA;
	public int id;
	public HasPrimArray(int [] setArr)
	{
		arrA=  setArr;
		id = this.hashCode();
	}
}
