
//Don't change the class name
public class Container{
	private Point data;//Don't delete or change this field;
	private Container next;
	private Container prev;
	//Don't delete or change this function
	public Container(Point data){//constructor
		this.data=data;
		prev=null;
		next=null;
	}
	public Container(Point data,Container next,Container prev){//constructor
		this(data);
		this.prev=prev;
		this.next=next;
	}
	
	public void SetNext(Container next) {//set next
		this.next=next;
	}
	public void SetPrev(Container prev) {//set prev
		this.prev=prev;
	}
	public Point getData()
	{
		return data;
	}
	public Container getNext()
	{
		return next;
	}
	public Container getPrev()
	{
		return prev;
	}
}
