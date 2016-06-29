package fordfulkerson.digraph;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class GraphTest {

	@Test
	public void test() throws IOException {
		
		// constructor function
		Graph myGraph = new Graph("input/input.txt");
		assertNotNull("Generated graph is null.", myGraph);
		
		// number of vertices and edges
		assertEquals("Num of vertices incorrect.", 4, myGraph.getVertices().size());
		assertEquals("Num of edges incorrect.", 5, myGraph.getEdges().size());
		
		// number of neighbours for each vertex
		for(Vertex v : myGraph.getVertices()) {
			for(Edge e : v.getNeighours()) {
				System.out.println(v.toString() + " has edge " + e.toString());
			}
			if(v.getLabel() == 1 || v.getLabel() == 4) {
				assertEquals("Number of neighbour edges not correct for " + v.toString(), 2, v.getNeighours().size());
			} else {
				assertEquals("Number of neighbour edges not correct for " + v.toString(), 3, v.getNeighours().size());
			}
		}

		// findEdgeByLabel
		assertEquals("Capacity of found edge doesn't match.", 20, myGraph.findEdgeByLabel(1, 2).getCapacity());
		assertEquals("Capacity of found edge doesn't match.", 10, myGraph.findEdgeByLabel(1, 3).getCapacity());
		assertEquals("Capacity of found edge doesn't match.", 30, myGraph.findEdgeByLabel(2, 3).getCapacity());
		assertEquals("Capacity of found edge doesn't match.", 10, myGraph.findEdgeByLabel(2, 4).getCapacity());
		assertEquals("Capacity of found edge doesn't match.", 20, myGraph.findEdgeByLabel(3, 4).getCapacity());
		
		// findVertexByLabel
		assertEquals("Number of neighbours incorrect.", 2, myGraph.findVertexByLabel(1).getNeighours().size());
		assertEquals("Number of neighbours incorrect.", 3, myGraph.findVertexByLabel(2).getNeighours().size());
		assertEquals("Number of neighbours incorrect.", 3, myGraph.findVertexByLabel(3).getNeighours().size());
		assertEquals("Number of neighbours incorrect.", 2, myGraph.findVertexByLabel(4).getNeighours().size());
		
		// writeToFile
		String temp = myGraph.writeToFile("output/output.txt");
		System.out.println(temp);
	}

}
