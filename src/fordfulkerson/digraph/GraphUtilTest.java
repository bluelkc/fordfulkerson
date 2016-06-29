package fordfulkerson.digraph;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class GraphUtilTest {

	@Test
	public void test() throws IOException {
		// constructor function
		Graph myGraph = new Graph("input/input.txt");
		assertNotNull("Generated graph is null.", myGraph);
		
		// writeToFile
		String temp = myGraph.writeToFile("output/output1.txt");
		System.out.println(temp);
		
		while(GraphUtil.DFS(myGraph) > 0) {		
			// writeToFile
			temp = myGraph.writeToFile("output/output2.txt");
			System.out.println(temp);
			myGraph.clearVisitedFlag();
		}
	}

}
