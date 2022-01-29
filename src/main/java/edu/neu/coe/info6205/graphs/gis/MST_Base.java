package edu.neu.coe.info6205.graphs.gis;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.bqs.Queue;
import edu.neu.coe.info6205.bqs.Queue_Elements;
        import edu.neu.coe.info6205.graphs.gis.MST;
        import edu.neu.coe.info6205.graphs.gis.Sequenced;
        import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.graphs.undirected.Graph_Edges;
import edu.neu.coe.info6205.pq.PQException;
import edu.neu.coe.info6205.pq.PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public abstract class MST_Base<V, X extends Comparable<X> & Sequenced> implements MST<V, X> {
    public static <V, X extends Comparable<X>> Edge<V, X> createEdge(V v1, V v2, X x) {
        return new Edge<>(v1, v2, x);
    }

    @Override
    public EdgeGraph<V, X> getMST() {
        int sequence = 0;
        EdgeGraph<V, X> result = new Graph_Edges<>();
        for (Edge<V, X> edge : queue) {
            edge.getAttribute().setSequence(sequence++);
            result.addEdge(edge);
        }
        return result;
    }

    @Override
    public Iterator<Edge<V, X>> iterator() {
        ArrayList<Edge<V, X>> result = new ArrayList<>();
        for (Edge<V, X> edge : mst) result.add(edge);
        return result.iterator();
    }

    protected PriorityQueue<Edge<V, X>> createPQ(SizedIterable<Edge<V, X>> edges) {
        PriorityQueue<Edge<V, X>> result = new PriorityQueue<>(edges.size(), false, Comparator.comparing(Edge::getAttribute));
        for (Edge<V, X> e : edges) result.give(e);
        return result;
    }

    private void showEdgesInSequence(EdgeGraph<V, X> graph) {
        // TODO remove this debugging code
        PriorityQueue<Edge<V, X>> tempPQ = createPQ(graph.edges());
        while (!tempPQ.isEmpty()) {
            try {
                System.out.println(tempPQ.take());
            } catch (PQException e) {
                e.printStackTrace();
            }
        }
    }

    public MST_Base() {
        this.queue = new Queue_Elements<>();
//        this.pq = createPQ(graph.edges());
    }

    protected final Queue<Edge<V, X>> queue;
//    protected final PriorityQueue<Edge<V, X>> pq;
//    protected final int size;
    protected Iterable<Edge<V, X>> mst;
}