package com.dev.luqman;

import static org.junit.Assert.assertEquals;

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
		tree.add(78);
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
}
