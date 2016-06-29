package fordfulkerson.digraph;

import java.util.PriorityQueue;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileOutputStream;

public class Graph {
	private PriorityQueue<Vertex> vertices = new PriorityQueue<Vertex>();
	private PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
	private Vertex source;
	private Vertex target;
	
	public Graph() {
		this.source = new Vertex(1);
		this.target = new Vertex(2);
	}
	
	public Graph(int numVertice, int degree, int degree_variance) {
		
	}
	
	public Graph(String file) {
		try {
			FileReader fr = new FileReader(file); 
			BufferedReader br = new BufferedReader(fr); 
			String line;
			Edge e_t = null;
			while((line = br.readLine()) != null) {
				System.out.println("Processing: " + line);
				if(e_t == null) {
					e_t = processInputFileLine(line);
					this.source = e_t.getFrom();
				} else {
					e_t = processInputFileLine(line);
				}
			}
			if(e_t != null) {
				this.target = e_t.getTo();
			}

			fr.close(); 
		} catch (Exception ex) {
			System.out.println("Read file error: " + ex.getMessage());
		}
	}
	
	public void addVertex(Vertex v) {
		if (this.vertices.contains(v)) {
			System.out.println("This graph already contains " + v.toString());
		} else if (!v.getNeighours().isEmpty()) { 
			System.out.println("Cannot add vertex with existing edges attached");
		} else {
			this.vertices.add(v);
		}
	}
	
	public void addEdge(Edge e) {
		if (this.vertices.contains(e.getFrom()) && this.vertices.contains(e.getTo())) {
			e.getFrom().addEdge(e);
			e.getTo().addEdge(e);
			this.edges.add(e);
		}
	}
	
	public boolean contains(Vertex v) {
		return this.vertices.contains(v);
	}
	
	public boolean contains(Edge e) {
		return this.edges.contains(e);
	}
	
	public PriorityQueue<Vertex> getVertices() {
		return this.vertices;
	}
	
	public PriorityQueue<Edge> getEdges() {
		return this.edges;
	}
	
	public Vertex getSource() {
		return this.source;
	}
		
	public Vertex getTarget() {
		return this.target;
	}
	
	public String writeToFile(String file) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(Vertex v : this.vertices) {
			sb.append(v.toString());
			sb.append(System.lineSeparator());
		}
		for(Edge e : this.edges) {
			sb.append(e.toString());
			sb.append(System.lineSeparator());
		}
		
		FileOutputStream out = new FileOutputStream(file);
		out.write(sb.toString().getBytes());
		out.close();
		return sb.toString();
	}
	
	public Vertex findVertexByLabel(int label) {
		for (Vertex ve : this.vertices) {
			if (ve.getLabel() == label) {
				return ve;
			}
		}
		return null;
	}
	
	public Edge findEdgeByLabel(int from, int to) {
		for (Edge ed : this.edges) {
			if (ed.getFrom().getLabel() == from
					&& ed.getTo().getLabel() == to) {
				return ed;
			}
		}
		return null;
	}
	
	public void clearVisitedFlag() {
		for (Vertex v : this.vertices) {
			v.setVisited(false);
		}
	}

	private Edge processInputFileLine(String line) {
		String[] inputs = line.split("\\s+");
		if (inputs.length == 1) {
			this.addVertex(new Vertex(Integer.parseInt(inputs[0])));
		} else if (inputs.length == 3) {
			Vertex from = new Vertex(Integer.parseInt(inputs[0]));
			Vertex to = new Vertex(Integer.parseInt(inputs[2]));
			
			for (Vertex v : this.vertices) {
				if (from.equals(v)) {
					from = v;
				}
				if (to.equals(v)) {
					to = v;
				}
			}
					
			int capacity = Integer.parseInt(inputs[1]);
			Edge edge = new Edge(from, to, capacity);
			this.addVertex(from);
			this.addVertex(to);
			this.addEdge(edge);
			return edge;
		}
		return null;
	}
}
