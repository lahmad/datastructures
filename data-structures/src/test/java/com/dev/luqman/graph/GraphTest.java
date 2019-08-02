package com.dev.luqman.graph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {

	private Graph<Character> graph;
	
	@Before
	public void setup() {
		graph = new Graph<>();
		
		graph.addEdge('A', 'B');
		graph.addEdge('A', 'C');
		graph.addEdge('B', 'D');
		graph.addEdge('D', 'H');
		graph.addEdge('C', 'H');
		graph.addEdge('H', 'I');
		graph.addEdge('D', 'F');
		graph.addEdge('G', 'L');
	}
	
	@Test
	public void testGraphAddEdge() {
		graph.addEdge('K', 'L');
	}
	
	@Test
	public void testDfsPathShouldReturnTrue() {
		assertTrue(graph.hasPathDfs('G', 'L'));
		assertTrue(graph.hasPathDfs('A', 'F'));
		assertTrue(graph.hasPathDfs('I', 'A'));
	}
	
	@Test
	public void testBfsPathShouldReturnFalse() {
		assertFalse(graph.hasPathBfs('A', 'Z'));

		graph.printGraph();
	}
	
	@Test
	public void testDfsPathShouldReturnFalse() {
		assertFalse(graph.hasPathDfs('A', 'Z'));
	}
	
	@Test
	public void testBfsPathShouldReturnTrue() {
		assertFalse(graph.hasPathBfs('I', 'A'));
	}
	
	
}
