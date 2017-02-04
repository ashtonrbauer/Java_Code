/*
Created by Ashton Reed Bauer
*/

@SuppressWarnings("unchecked") public class Dijkstra {

	//G will be the graph, source will be where our Dijkstra begins, and flag is used to determine whether you want to return the weights or list of edges.
	

	 public static int [] dijkstra (Graph G, int source, int flag) {
   		//This array will represent our d[] in the algorithm. Or our currently shortest known.
        int [] d = new int [G.size()];
		//This will represent our pi[] in the algorithm. Contains the predecessor of V in the Shortest path from S to V.      
        int [] pi = new int [G.size()];
		//This will partially serve to help account for Relax(u, v, w) along with @relax(int [] d_alt, boolean [] vis_alt).
        boolean [] visited = new boolean [G.size()];
  
		//Initialize our d[] to infinity and d[S] to zero.
        for (int key=0; key<d.length; key++) {
           d[key] = Integer.MAX_VALUE;
        }
        d[source] = 0;
  
        for (int curr=0; curr<d.length; curr++) {
			//This will pull the minimum value, allowing us to 'relax' it in visited[].
            int u = relax(d, visited);
            visited[u] = true;
  
            //While working dijkstra(G,s,f) d[u] via pi[u] is our shortest path to u.
			//array of target vertices or neighbors.  
            int [] targets = G.adjacent(u);
            for (int key=0; key<targets.length; key++) {
            	//Pseudocode calls that delta(S,V) <= d[u] + weight(u,v).
				//This evaluates that condition and takes d[u] + weight(u,v) as a possible delta
                int v = targets[key];
                int delta = d[u] + G.weight(u,v);
                if (d[v] > delta) {
                    d[v] = delta;
                    pi[v] = u;
                }
            }

        }
        //return weight array(d[]) or predecessor array(pi[])
        if(flag == 0)
        	return d;
    	else
    		return pi;
    }

	private static int relax(int [] dist, boolean [] vis) {
        //"infinity"
        int u = Integer.MAX_VALUE;
        // graph not connected, or no unvisited vertices
        int v = -1;
        for (int i=0; i<dist.length; i++) {
           if (!vis[i] && dist[i]<u) {
           	v=i; 
           	u=dist[i];
           }
        }
        return v;
    }

}	