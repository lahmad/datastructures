package com.dev.luqman;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.dev.luqman.tree.BinarySearchTree;
import com.dev.luqman.tree.BinarySearchTreeImpl;

public class BinarySearchTreeTest {
	
	private static Random random = new Random();
	private BinarySearchTree<Integer> tree;
	
	@Before
	public void setup() throws Exception {
		tree = new BinarySearchTreeImpl<>();
		tree.add(78);
		tree.add(88);
		tree.add(66);
		tree.add(77);
		tree.add(80);
		tree.add(83);
		tree.add(87);
		tree.add(42);
		tree.add(19);
		tree.add(20);
	}
	
	@Test
	public void testAdd() {
		
		assertEquals(10, tree.size());
		tree.add(101);
		assertEquals(11, tree.size());
	}

	@Test
	public void testRemoveLeaf() throws Exception {
		tree.remove(20);
		assertEquals(9, tree.size());
	}
	
	@Test
	public void testRemoveOnChild() throws Exception {
		tree.remove(19);
		assertEquals(9, tree.size());
		System.out.println(tree.printInOrder());

	}
	
	@Test
	public void testContainsTrue() throws Exception {
		assertTrue(tree.contains(20));
	}
	
	@Test
	public void testContainsFalse() throws Exception {
		assertFalse(tree.contains(100));
	}
	
	@Test
	public void testDfsTrue() throws Exception {
		assertTrue(tree.dfs(42));
	}
	
	@Test
	public void testDfsFalse() throws Exception {
		assertFalse(tree.dfs(142));
	}
	@Test
	public void testBfsTrue() throws Exception {
		assertTrue(tree.bfs(42));
	}
	
	@Test
	public void testBfsFalse() throws Exception {
		assertFalse(tree.bfs(142));
	}
	
	@Test
	public void testCeiling() throws Exception {
		Integer result = tree.ceiling(42);
		assertEquals(result, Integer.valueOf(66));
	}
	
	@Test
	public void testCelingNodeNotFound() throws Exception {
		assertNull(tree.ceiling(100));
	}
	
	@Test
	public void testFloor() throws Exception {
		Integer result = tree.floor(42);
		assertEquals(result, Integer.valueOf(20));
	}
	
	@Test
	public void testFloorNodeNotFound() throws Exception {
		assertNull(tree.ceiling(100));
	}
		
}
