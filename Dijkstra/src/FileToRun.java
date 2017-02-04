import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Scanner;

public class FileToRun{
	/* Created by: Ashton Reed Bauer
		Design & Analysis: Algorithms Dijkstra assignment for Dr. Zelikovsky.
		Due: 3/24/16
	*/

	public static void main(String[] args)throws FileNotFoundException, IOException, ArrayIndexOutOfBoundsException{
		
		Scanner user = new Scanner(System.in);
		System.out.println("Please enter file name:");
		String fileName = user.nextLine();
		user.close();
		//This will take in the file from the user, and flag if warning arises
		//This can be used for performance increase if utilized in command line
/*		
		try{
			fileName = args[0];
		}catch(ArrayIndexOutOfBoundsException exc){
			 System.err.println("Caught ArrayOutOfBoundsException: " + exc.getMessage());
			 System.out.println("Please try again and use format----> java FileToRun FileName.txt");
			 System.exit(0);
		}
*/
		//This will be used as our timer.
		double startTimer = System.nanoTime();
		double totalTimer = System.nanoTime();
		
		//This will actually read from the file.
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		
		//The next five lines will take the first line of the files and parse the n and m values.
		String line = in.readLine();
		StringTokenizer lineTokens = new StringTokenizer(line);
		String n = lineTokens.nextToken();
		String m = lineTokens.nextToken();
		int num_of_vertices = Integer.parseInt(n.substring(2));
		int num_of_edges = Integer.parseInt(m.substring(2));
		


		//System.out.println(num_of_vertices);

		//This will be used to account for bi-directional/undirected graph.
    	//ArrayList<Integer> addedLabels = new ArrayList<Integer>(num_of_vertices);
    	boolean [] addedLabels = new boolean[num_of_vertices];

    	//This is an instance of my Graph class that will be used to store all edges, vertices, and weights.
    	Graph graph_of_file = new Graph(num_of_vertices);

    	///////////////////////////////////////////////////////////////
		///////////////THIS HANDLES TEXT FILE FORMATTING///////////////
		///////////////////////////////////////////////////////////////
		int source = 0;
		while ((line = in.readLine()) != null){
      		lineTokens = new StringTokenizer(line);
      		if(lineTokens.countTokens() == 1){
      			//This will be parent node from the lines that contain only one token. The token will not be \n or blank space.
      			source = Integer.parseInt(lineTokens.nextToken());
            	//This basically is saying if we haven't created a label for this vertex, do so. This is necessary to account for bi-directional.
            	if(addedLabels[source] == false){
              		graph_of_file.setFromVertex(source);
              		addedLabels[source] = true;
            	}
      		}else if(lineTokens.countTokens() == 2){
      			//@target will be the first token, or the destination edge, and @weight will be the second token and the weight(source, destination).
      			int target = Integer.parseInt(lineTokens.nextToken());
      			int weight = Integer.parseInt(lineTokens.nextToken());
      			graph_of_file.add(source, target, weight);
            	//Same logic as state in line 60: Make sure we don't duplicate labels because of bi-directional.
            	if(addedLabels[target] == false){
              		graph_of_file.setFromVertex(target);
              		addedLabels[target] = true;
            	}
            //this will account for bi-directional graph.
           	graph_of_file.add(target, source, weight);
      		}else{
      			//if for some reason a number of tokens isn't 1 or 2, just skip it.
      			continue;
      		}
      		
        }
        ///////////////////////////////////////////////////////////////
		//////////////FILE IS FORMATTED AND ALL////////////////////////
		//////////////WEIGHTS, EDGES, AND 	   ////////////////////////
		//////////////SOURCES ADDED TO GRAPH.  ////////////////////////
		///////////////////////////////////////////////////////////////


        //This will output the amount of time it took to process file input.
        double endTimer = System.nanoTime() - startTimer;
        System.out.println("It took us "+endTimer/1000000000 +" seconds to process file formatting & add every edge to the Graph.");

        //This will reset the time to now evaluate djikstra
        startTimer = System.nanoTime();

        //Return our Delta(S,V) in the form of the array, will all smallest weights.
        //If our third parameter is 0, it will return d[], 1 will return our pi[].
        int[] deltaSV = Dijkstra.dijkstra(graph_of_file, 0, 0);
        int sum=0;
        for(int i = 0; i < deltaSV.length; i++){
          sum += deltaSV[i];
        } 
        System.out.println("Total weight of Single-Source Shortest-Path: " + sum);
        endTimer = System.nanoTime() - startTimer;
        System.out.println("It took us "+endTimer/1000000000 +" seconds to process Dijkstra on the graph and output the path length.");
		endTimer = System.nanoTime() - totalTimer;
		System.out.println("Total approximate run time of program: "+endTimer/1000000000+" seconds.");
		System.exit(0);
	}

}
