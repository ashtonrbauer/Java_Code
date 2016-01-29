import java.lang.Math;
import java.util.Stack;
import java.util.LinkedList;


/*	Ashton Reed Bauer's Assignment 7: Binary Search Tree
 * 
 * 		My Binary Search Tree Linked List utilizes a Node class
 * that I created which includes a parent, left child, and right
 * child node. The node also stores the data. In order to store the
 * data the binary search tree will compare the data passed into it
 * to the data of the current node it is evaluating. If the data passed
 * is less than the data of the current node, it will continue down the
 * tree to the right; if greater than, to the right. If there is no child
 * node present to the current node, then it will store the data in the
 * respective child node, based upon comparison (> or <). If there is a 
 * child node present, the add function will be recursively called passing
 * the current node's child node, and evaluating further (again respectively
 * based upon comparison. If the data is less than, add(node.leftC, data),
 * otherwise add(node.rightC, data).) 
 * Preconditions: A binary search tree must not pass any parameters when 
 * 					initialized. The add function must be passed an integer
 * 					and a node.
 * Postconditions: None.
 * Invariants:	None.
 * 
 * 		The infix recursive print method I used will prints in the order
 * left child, parent, right child. It will travel down the left side of the
 * tree until there is no longer a left child present. This will find the 
 * smallest integer, based upon storage within the binary tree. It will then
 * climb up the tree, backwards until returning to the root node, print the
 * root, and then climb down the right side with recursive calls.
 * Preconditions:	A node must be passed an integer.
 * Postconditions:	None
 * Invariants:	None
 *  
 * 		My main method generates 100 random integers between 1 and 99 using the
 * Math() class within the java library. The integers are stored into a stack and
 * printed out as they are generated. The values stored in the stack are then 
 * popped off the stack and added into the binary search tree. Then the recursive
 * print method, inOrder(), is called.
 * 
 * 
 * */

public class BSTree
{
	//Specifies the first element in tree.
	Node root;
	//Constructor for initialization
	public BSTree()
	{
		this.root = null;
	}
	
	//Add an integer
	public void add(Node n, int d)
	{	
		//First node added
		if(n == null)
		{
			n = new Node(d);
			root = n;
		}
		//Adding a node to the left child if data passed is less
		//than the node being evaluated and there is no child.
		else if(d < n.data && n.leftC == null)
		{
			n.leftC = new Node(d);
			n.leftC.parent = n;
		}
		//Adding a node to the right child if data passed is less
		//than the node being evaluated and there is no child.
		else if(d >= n.data && n.rightC == null)
		{
			n.rightC = new Node(d);
			n.rightC.parent = n;
		}
		//Recursively calling the add function until no child present.
		else
		{
			//continuing down left child
			if(d < n.data)
				add(n.leftC, d);
			//continuing down right child
			else
				add(n.rightC, d);
		}
	}
	
	//Infix recursive printout method.
	public void inOrder(Node n)
	{
		if(n != null)
		{
			//Will continue down until no child is present.
			//Prints in order: Left Child, Parent, Right Child
			//LC < P <= RC
			inOrder(n.leftC);
			System.out.print(n.data + "~");
			inOrder(n.rightC);
		}
	}
	
	public static void main(String[] args)
	{
		//Using a Stack to store random integers. More functional with Linked Lists
		Stack<Integer> randNums = new Stack<>();
		//For loop adding 100 random integers.
		for(int i = 0; i < 100; i++)
		{
			if(i%10 == 0)
				System.out.println();
			//Random integer between 1 & 99.
			int rand = (int)(Math.random() * 99 + 1);
			//Formats printing.
			if(i == 99)
			{
				randNums.push(rand);
				System.out.print(rand + ".");
			}else
			{	
				randNums.push(rand);
				System.out.print(rand + ", ");
			}
		}
		System.out.println();
		
		//Initialize binary tree.
		BSTree myTree = new BSTree();
		//Add every element of the stack to the Tree.
		for(int i = 0; i < 100; i++)
			myTree.add(myTree.root, randNums.pop());
		//Call recursive print method.
		myTree.inOrder(myTree.root);
			
	}
}
/*The Node class will be used to create my Binary Search Tree linked list.
 * The data stored for this exercise will be an integer. There is a parent, 
 * left child, and right child. When initialized it will set all of the nodes
 * to null and store the passed data.*/
class Node{
	
	int data;
	
	Node parent;
	Node leftC;
	Node rightC;
	
	Node(int d){
		
		this.data = d;
		this.leftC = null;
		this.rightC = null;
		this.parent = null;
		
	}
	Node(){
	}
	
}
