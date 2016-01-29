package assignments;

public class Node {
	private Object o;
	private Node n;
	
	public Node(Object x){
		this.o = x;
		this.n = null;
	}
	
	public Node(Object x, Node n2){
		this.o = x;
		this.n = n2;
	}
	
	public Object getObject(){
		return this.o;
	}
	
	public void setObject(Object y){
		this.o = y;
	}
	
	public Node getNode(){
		return this.n;
	}
	
	public void setNode(Node n2){
		this.n = n2;
	}
	
}
