package com.dev.luqman.tree;

import java.util.LinkedHashSet;
import java.util.Set;

import com.dev.luqman.tree.exceptions.ElementNotFoundException;
import com.dev.luqman.tree.exceptions.EmptyTreeException;

public class BinarySearchTreeImpl<E extends Comparable<E>> implements BinarySearchTree<E>{

	private TreeNode<E> root;
	private int size;
	
	public BinarySearchTreeImpl() {
		this.root = null;
		this.size = 0;
	}
	
	@Override
	public E ceiling(E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E floor(E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(E element) {
		
		TreeNode<E> newNode = new TreeNode<E>(element);
		
		if (this.root == null) {
			this.root = newNode;
		}
		else {
			TreeNode<E> current = this.root;
			TreeNode<E> parent = null;
			boolean isLeft = true;
			
			while (current != null) {
				parent = current;
				if (current.getData().compareTo(element) > 0) {
					current = current.getLeft();
					isLeft = true;
				} else {
					current = current.getRight();
					isLeft = false;
				}
			}
			
			if (isLeft) {
				parent.setLeft(newNode);
			} else {
				parent.setRight(newNode);
			}
		}
		++size;
	}

	@Override
	public E remove(E element) throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("BinarySearchTree is empty");
		}
		
		TreeNode<E> result = removeHelper(this.root, element);
		if (result != null) {
			--size;
		}
		
		return result != null ? result.getData() : null;
	}

	@Override
	public boolean contains(E element) throws ElementNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<E> printInOrder() throws EmptyTreeException {
		Set<E> set = new LinkedHashSet<>();
		printInOrderHelper(this.root, set);
		return set;
	}

	@Override
	public Set<E> printPreOrder() throws EmptyTreeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> printPostOrder() throws EmptyTreeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean dfs(E element) throws EmptyTreeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bfs(E element) throws EmptyTreeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E min() throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("BinarySearchTree is empty");
		}
		
		TreeNode<E> current = this.root;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		
		return current.getData();
	}

	@Override
	public E max() throws EmptyTreeException {
		if (this.root == null) {
			throw new EmptyTreeException("BinarySearchTree is empty");
		}
		
		TreeNode<E> current = this.root;
		while (current.getRight() != null) {
			current = current.getRight();
		}
		
		return current.getData();	}

	@Override
	public E leastCommonAncestor(E p, E q) {
		
		
		return null;
	}

	@Override
	public Set<E> boundaryTraversal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> leftView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> rightView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> leafView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int height() {
		return height(this.root);
	}
	
	private int height(TreeNode<E> node) {
		if (node == null) {
			return -1;
		}
		int leftHeight = height(node.getLeft());
		int rightHeight = height(node.getRight());
		
		return Math.max(leftHeight, rightHeight) +1;
	}
	
	private TreeNode<E> removeHelper(TreeNode<E> node, E element) {
		
		if (node == null) {
			return null;
		}
		
		if (node.getData().compareTo(element) > 0 ) {
			removeHelper(node.getLeft(), element);
		}
		else if (node.getData().compareTo( element) < 0) {
			removeHelper(node.getRight(), element);
		}
		else {
			// Found the node.
			if (node.getLeft() == null && node.getRight() == null) {
				node = null;
				return node;
			}
			else if (node.getLeft() == null) {
				node = node.getRight();
			}
			else if (node.getRight() == null) {
				node = node.getLeft();
			}
			else {
				// More than one child.
				TreeNode<E> minNode = findMinTreeNode(node.getRight());
				
				node.setData(minNode.getData());
				removeHelper(node.getRight(), minNode.getData());
			}
		}
		return node;
	}
	
	private TreeNode<E> findMinTreeNode(TreeNode<E> node) {
		
		if (node == null) {
			return node;
		}
		
		TreeNode<E> current = node;
		while(current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;
	}
	

	private void printInOrderHelper(TreeNode<E> node, Set<E> nodes) {
		
		if (node != null) {
			printInOrderHelper(node.getLeft(), nodes);
			nodes.add(node.getData());
			printInOrderHelper(node.getRight(), nodes);
		}
	}
}
