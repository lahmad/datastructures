package com.dev.luqman.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dev.luqman.tree.BinaryTreeImpl;
import com.dev.luqman.tree.Tree;
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
		assertEquals(10, tree.size());
		tree.add(20);
		assertEquals(11, tree.size());
	}
	
	@Test
	public void testContains() throws Exception {
		assertTrue(tree.contains(10));
	}
	
	@Test
	public void testRemove() throws Exception  {
		Integer result = tree.remove(9);
		assertEquals(Integer.valueOf(9), result);
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
		assertEquals(Integer.valueOf(1), tree.min());
	}
	
	@Test
	public void testMax() throws Exception {
		tree.add(100);
		assertEquals(Integer.valueOf(100), tree.max());
	}
	
	@Test
	public void testDfsWithNotElement() throws Exception {
		assertFalse(tree.dfs(1000));
	}
	
	@Test
	public void testDfsElementFound() throws Exception {
		assertTrue(tree.dfs(4));
	}
	
	@Test
	public void testBfsWithNotElement() throws Exception {
		assertFalse(tree.bfs(1000));
	}
	
	@Test
	public void testBfsElementFound() throws Exception {
		assertTrue(tree.bfs(2));
	}
	
	@Test
	public void testPreOrder() throws Exception {
		Set<Integer> set = tree.printPreOrder();
		Assert.assertNotNull(set);
	}
	
	@Test
	public void testLca() {
		Integer lcaElement = tree.leastCommonAncestor(Integer.valueOf(8), Integer.valueOf(5));
		Assert.assertEquals(Integer.valueOf(2), lcaElement);
	}
	
	@Test
	public void testLcaNotFoundOneNode() {
		Integer lcaElement = tree.leastCommonAncestor(Integer.valueOf(199), Integer.valueOf(5));
		Assert.assertEquals(Integer.valueOf(5), lcaElement);
	}
	
	@Test
	public void testLcaChild() {
		Integer lcaElement = tree.leastCommonAncestor(Integer.valueOf(4), Integer.valueOf(8));
		Assert.assertEquals(Integer.valueOf(4), lcaElement);
	}
	
	@Test
	public void testLeftView() {
		Set<Integer> leftView = tree.leftView();	
		assertThat(leftView, IsIterableContainingInOrder.contains(1, 2, 4, 8));
	}
	
	@Test
	public void testLeafView() {
		Set<Integer> leafView = tree.leafView();	
		assertThat(leafView, IsIterableContainingInOrder.contains(8, 9, 10, 6, 7));
	}
	

	@Test
	public void testRightView() {
		Set<Integer> rightView = tree.rightView();	
		assertThat(rightView, IsIterableContainingInOrder.contains(1, 3, 7));
	}
	
	@Test
	public void testBoundaryTraversal() {
		Set<Integer> boundaryNodes = tree.boundaryTraversal();
		assertThat(boundaryNodes, IsIterableContainingInOrder.contains(1, 2, 4, 8, 9, 10, 6, 7, 3));
	}
	
	@Test
	public void testHeight() {
		assertEquals(3, tree.height());
	}
}
