package com.dev.luqman.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph<E extends Comparable<E>> {
	
	private Map<E, GraphNode> graph;
	
	public Graph() {
		this.graph = new HashMap<>();
	}
	
	public void addEdge(E source, E destination) {
		
		GraphNode<E> sourceNode = getNode(source);
		GraphNode<E> destinationNode = getNode(destination);
		
		GraphNode<E> destNode = new GraphNode<E>(destination);
		if (sourceNode != null) {
			if (!sourceNode.getNeighbours().contains(destNode)) {
				sourceNode.getNeighbours().add(destNode);
			}
		}
		else {
			GraphNode<E> srcNode = new GraphNode<E>(source);
			srcNode.getNeighbours().add(new GraphNode<E>(destination));
			graph.put(source, srcNode);
		}
		
		if (destinationNode != null) {
			
			if (!destinationNode.getNeighbours().contains(sourceNode))
			{
				destinationNode.getNeighbours().add(new GraphNode<E>(source));
			}
		}
		else {
			destNode.getNeighbours().add(new GraphNode<E>(source));
			graph.put(destination, destNode);
		}
	}
	
	public GraphNode<E> getNode(E element) {
		if (!graph.containsKey(element)) {
			return null;
		}
		return graph.get(element);
	}
	
	public boolean hasPathDfs(E source, E destination) {
		
		GraphNode<E> sourceNode = getNode(source);
		GraphNode<E> destNode = getNode(destination);
		
		if (sourceNode == null || destNode == null) {
			return false;
		}
		
		Set<E> visited = new HashSet<>();
		return hasPathDfsHelper(sourceNode, destNode, visited);
	}
	
	public boolean hasPathBfs(E source, E destination) {
		
		GraphNode<E> sourceNode = getNode(source);
		
		if (sourceNode == null || destination == null) {
			return false;
		}
		
		Set<E> visited = new HashSet<>();
		Queue<GraphNode<E>> queue = new LinkedList<>();
		queue.add(sourceNode);
		visited.add(source);
		
		while (!queue.isEmpty()) {
		
			GraphNode<E> node = queue.poll();
			
			if (node.getData().compareTo(destination) == 0) {
				return true;
			}
			
			if (visited.contains(node.getData())) {
				continue;
			}
			
			visited.add(node.getData());
			
			for (GraphNode<E> child : node.getNeighbours()) {
				queue.add(child);
			}
		}
		
		return false;
	}
	
	public void printGraph() {
		graph.entrySet().forEach(entry -> System.out.println("Key " + entry.getKey() + " value " + entry.getValue()));
	}
	
	private boolean hasPathDfsHelper(GraphNode<E> sourceNode, GraphNode<E> destNode, Set<E> visited) {
		
		//  Base condition node already visited
		if (visited.contains(sourceNode.getData())) {
			return false;
		}
		
		visited.add(sourceNode.getData());
		
		// Found the destination node.
		if (sourceNode.getData().compareTo(destNode.getData()) == 0) {
			return true;
		}
		
		// Traverse all the neighbors.
		for (GraphNode<E> child : sourceNode.getNeighbours()) {
			GraphNode<E> node = getNode(child.getData());
			if (hasPathDfsHelper(node, destNode, visited)) {
				return true;
			}
		}
		return false;
	}
}
