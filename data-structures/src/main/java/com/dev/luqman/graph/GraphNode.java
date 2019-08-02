package com.dev.luqman.graph;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class GraphNode<E extends Comparable<E>> {

	private E data;
	private LinkedList<GraphNode<E>> neighbours;
	
	public GraphNode (E element) {
		this.data = element;
		this.neighbours = new LinkedList<>();
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public LinkedList<GraphNode<E>> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(LinkedList<GraphNode<E>> neighbours) {
		this.neighbours = neighbours;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Key -> ").append(data);
		String s = neighbours.stream().map(GraphNode::getData).
		map(Object::toString).collect(Collectors.joining(","));
		sb.append(" value -> ").append(s);
		
		return sb.toString();
	}
}
