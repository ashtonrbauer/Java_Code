/*
Created by Ashton Reed Bauer
*/
public class Graph{

	private int [][]  adjacencyList;  // adjacency matrix
    private int [] fromWhichVertex;
 
    public Graph (int num_edges) {
       this.adjacencyList  = new int [num_edges][num_edges];
       this.fromWhichVertex = new int[num_edges];
    }

    public int size(){
    	//Length of graph is how many individual vertexes we have.
    	return this.fromWhichVertex.length;
    }

    public void setFromVertex(int source_vertex){
    	//This is the root of this path, or the parent/label for indexing.
    	this.fromWhichVertex[source_vertex] = source_vertex;
    }

    public int getFromVertex(int source_vertex){
    	//this will return root or parent index
    	return this.fromWhichVertex[source_vertex];
    }

    public void add(int from, int target, int weight){
    	//this will add an edge, including source, destination, and weight.
    	this.adjacencyList[from][target] = weight;
    }

    public void remove(int from, int target){
    	//this will set a weight to 0, essentially making it invisible.
    	this.adjacencyList[from][target] = 0;
    }
    public int weight(int from, int target){
    	//this will return the weight of the source->target edge.
    	return this.adjacencyList[from][target];
    }

    public int [] adjacent(int vertex){
    	//Increments through and returns all neighbors, or adjacent vertices in the path.
    	int incrementor = 0;
    	for(int curr = 0; curr < adjacencyList[vertex].length; curr++){
    		if(adjacencyList[vertex][curr] > 0){
    			incrementor++;
    		}
    	}	
    	int [] result = new int[incrementor];
    	incrementor = 0;
    	for(int curr = 0; curr < adjacencyList[vertex].length; curr++){
    		if(adjacencyList[vertex][curr] > 0){
    			result[incrementor++] = curr;
    		}
    	}
    	return result;
    	
    }
}