package fordfulkerson.digraph;

public class Edge implements Comparable<Edge>{
	private Vertex from;
	private Vertex to;
	private int capacity;
	private int traffic = 0;
	
	public Edge(Vertex from, Vertex to, int capacity) {
		this.from = from;
		this.to = to;
		this.capacity = capacity;
	}

	public Vertex getFrom() {
		return this.from;
	}
	
	public Vertex getTo() {
		return this.to;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public int getTraffic() {
		return this.traffic;
	}
	
	public void setTraffic(int traffic) {
		this.traffic = traffic;
	}
	
	public boolean contains(Vertex v) {
		return (this.getFrom().equals(v) || this.getTo().equals(v));
	}
	
	public int isValidAugmentingEdge(Vertex from) {
		if (this.getFrom().equals(from)) {
			return (capacity - traffic);
		}
		if (this.getTo().equals(from)) {
			return traffic;
		}
		return 0;
	}
	
	public void increaseTrafficFrom(Vertex v, int load) {
		if (this.contains(v)) {
			if (v.equals(this.from)) {
				this.traffic += load; 
			} else if (v.equals(this.to)) {
				this.traffic -= load;
			}
		}	
	}
	
	public String toString() {
		return this.from.toString() + "--"
					+ this.capacity + "--" 
					+ this.traffic + "--"
					+ this.to.toString();
	}
	
	@Override
	public boolean equals(Object edge) {
		if (!(edge instanceof Edge)) {
			return false;
		}
		Edge edge_t = (Edge)edge;
		return ((edge_t.getFrom().equals(this.from)) 
				&& (edge_t.getTo().equals(this.to))
				&& (edge_t.getCapacity() == this.capacity));
	}
	
	@Override
	public int compareTo(Edge e) {
		if ((e.getCapacity() - e.getTraffic()) > (e.capacity - this.traffic)) {
			return -1;
		} else if ((e.getCapacity() - e.getTraffic()) < (e.capacity - this.traffic)) {
			return 1;
		} else {
			return 0;
		}
	}
}
