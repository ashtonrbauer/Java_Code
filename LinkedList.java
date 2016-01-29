
class TriplyLinkedNode
{
	TriplyLinkedNode parent, childLeft, childRight;
	int num;
	
	public TriplyLinkedNode(){
		this.parent = null;
		this.childLeft = null;
		this.childRight = null;
	}
	
	public TriplyLinkedNode(int i){
		this.num = i;
		this.parent = null;
		this.childRight = null;
		this.childLeft = null;
	}
}

public class LinkedList
{
	TriplyLinkedNode root, secondary;
	
	public LinkedList()
	{
		root = null;
		secondary = null;
	}
	
	public void add(int i)
	{
		root = add(root, i);
	}
	
	public TriplyLinkedNode add(TriplyLinkedNode rt, int i)
	{
		if (rt == null)
		{
			rt = new TriplyLinkedNode(i);
			rt.parent = secondary;
		}
		else
		{
			secondary = rt;
			if(rt.num >= i)
				rt.childLeft = add(rt.childLeft, i);
			else
				rt.childRight = add(rt.childRight, i);
		}
		
		return rt;
	}
	
	public int getNode(int i)
	{
		return root.num;
	}
	
	public void setNode(TriplyLinkedNode tLN, int x)
	{
		tLN.num = x;
	}
	
	
	
}
