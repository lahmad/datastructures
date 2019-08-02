package com.dev.luqman.graph;

import java.util.LinkedList;

public class GraphNode<E extends Comparable<E>> {

	private E data;
	private LinkedList<GraphNode<E>>[] neighbours;
	
	public GraphNode (E element) {
		this.data = element;
	}
}
