package com.dev.luqman;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dev.luqman.tree.BinaryTreeImpl;
import com.dev.luqman.tree.Tree;
import com.dev.luqman.tree.TreeNode;
import com.dev.luqman.tree.exceptions.EmptyTreeException;


public class BinaryTreeTest {
	
	private Tree<Integer> tree;

	@Before
	public void setup() {
		tree = new BinaryTreeImpl<>();
		for (int i = 1; i <= 10; i++) {
			tree.add(i);
		}
	}
	
	@Test
	public void testAdd() {		
		Assert.assertEquals(10, tree.size());
		tree.add(20);
		Assert.assertEquals(11, tree.size());
	}
	
	@Test
	public void testContains() throws Exception {
		Assert.assertTrue(tree.contains(10));
	}
	
	@Test
	public void testRemove() throws Exception  {
		Integer result = tree.remove(9);
		Assert.assertEquals(Integer.valueOf(9), result);
	}
	
	@Test(expected = EmptyTreeException.class)
	public void testRemoveEmptyTree() throws Exception {
		Tree<Integer> emptyTree = new BinaryTreeImpl<>();
		emptyTree.remove(10);
	}
	
	@Test(expected = EmptyTreeException.class)
	public void testMinShouldThrowEmptyTreeException() throws Exception {
		Tree<Integer> emptyTree = new BinaryTreeImpl<>();
		emptyTree.min();
	}
	
	@Test
	public void testMin() throws Exception {
		Assert.assertEquals(Integer.valueOf(1), tree.min());
	}
	
	@Test
	public void testMax() throws Exception {
		tree.add(100);
		Assert.assertEquals(Integer.valueOf(100), tree.max());
	}
	
	@Test
	public void testDfsWithNotElement() throws Exception {
		Assert.assertFalse(tree.dfs(1000));
	}
	
	@Test
	public void testDfsElementFound() throws Exception {
		Assert.assertTrue(tree.dfs(4));
	}
	
	@Test
	public void testBfsWithNotElement() throws Exception {
		Assert.assertFalse(tree.bfs(1000));
	}
	
	@Test
	public void testBfsElementFound() throws Exception {
		Assert.assertTrue(tree.bfs(2));
	}
	
	@Test
	public void testPreOrder() throws Exception {
		Set<TreeNode> set = tree.printPreOrder();
		Assert.assertNotNull(set);
	}
}