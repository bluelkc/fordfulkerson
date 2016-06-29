package fordfulkerson.digraph;

import java.util.PriorityQueue;
import java.util.UUID;
import java.util.Iterator;

public class Vertex implements Comparable<Vertex>{
	private int label;
	private boolean visited = false;
	private PriorityQueue<Edge> neighbours = new PriorityQueue<Edge>();
	
	public Vertex(int label) {
		this.label = label;
	}
	
	public void addEdge(Edge e) {
		if (e.getFrom().equals(this) || e.getTo().equals(this)) {
			if (this.neighbours.contains(e)) {
				System.out.println("Duplicated edge," + e.toString() + ", added to " + this.toString());				
			} else if (e.getTo().equals(e.getFrom())) {
				System.out.println("Self-looped edge" + e.toString() + "added to " + this.toString());
			} else {
				this.neighbours.add(e);
				System.out.println("Edge, " + e.toString() + ", added to " + this.toString() + " successfully.");
			}
		} else {
			System.out.println("Disconnected edge added to " + this.toString());
		}
	}
	
	public void removeEdge(Edge e) {
		this.neighbours.remove(e);
	}
	
	public boolean isConnectedTo(Vertex v) {
		if (v.equals(this)) {
			return true;
		}
		Iterator<Edge> it = this.neighbours.iterator();
		while(it.hasNext()) {
			if (it.next().contains(v)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isVisited() {
		return this.visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	@Override
	public boolean equals(Object v) {
		if(! (v instanceof Vertex)) {
			return false;
		}
		Vertex v_t = (Vertex)v;
		return (this.label == v_t.getLabel());
	}
	
	@Override
	public int compareTo(Vertex v) {
		if (v.getLabel() > this.label) {
			return 1;
		} else if (v.getLabel() < this.label) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public PriorityQueue<Edge> getNeighours() {	
		return this.neighbours;
	}
	
	public int getLabel() {
		return this.label;
	}
	
	public String toString() {	
		return "[Vertex " + this.label + "]";
	}
}
