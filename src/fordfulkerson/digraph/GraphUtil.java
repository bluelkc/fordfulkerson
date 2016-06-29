package fordfulkerson.digraph;

public class GraphUtil {

	public static int DFS(Graph g) {
		int maxflow = 0;
		Vertex source = g.getSource();
		Vertex target = g.getTarget();
		maxflow = findAugmentingPath(source, target, maxflow);
		return maxflow;
	}
	
	public static int findAugmentingPath(Vertex from, Vertex target, int flow) {
		from.setVisited(true);
		for (Edge e : from.getNeighours()) {
			int flow_t = e.isValidAugmentingEdge(from);
			if (flow_t > flow && flow != 0) {
				flow_t = flow;
			}
			if (flow_t > 0) {
				if (e.contains(target)) {
					e.setTraffic(e.getTraffic() + flow_t);
					System.out.println("Reached by edge " + e.toString());
					return flow_t;
				} else if (e.getFrom().equals(from)) {
					if (!e.getTo().isVisited()) {
						int forward_flow = findAugmentingPath(e.getTo(), target, flow_t);
						if (forward_flow > 0) {
							e.setTraffic(e.getTraffic() + forward_flow);
							return forward_flow;
						}
					}
				} else if (e.getTo().equals(from)) {
					if (!e.getFrom().isVisited()) {
						int backward_flow = findAugmentingPath(e.getFrom(), target, flow_t);
						if (backward_flow > 0) {
							e.setTraffic(e.getTraffic() - backward_flow);
							return backward_flow;
						}
					}
				}
			}
		}
		from.setVisited(false);
		return 0;
	}
		
}
