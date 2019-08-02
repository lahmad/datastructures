package com.dev.luqman.tree;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.dev.luqman.tree.Tree;
import com.dev.luqman.tree.exceptions.ElementNotFoundException;
import com.dev.luqman.tree.exceptions.EmptyTreeException;

public class BinaryTreeImpl<E extends Comparable<E>> implements Tree<E> {
	
	private TreeNode<E> root;
	
	private int size;
	
	public BinaryTreeImpl() {
		this.root = null;
		this.size = 0;
	}
	
	@Override
	public void add(E element) {
		
		TreeNode<E> node = new TreeNode<E>(element);
		
		if (this.root == null) {
			this.root = node;
		} else {
			Queue<TreeNode<E>> queue = new LinkedList<>();
			queue.add(this.root);
			
			while (!queue.isEmpty()) {
				
				TreeNode<E> treeNode = queue.poll();
				
				if (treeNode.getLeft() != null) {
					queue.add(treeNode.getLeft());
				} else {
					treeNode.setLeft(node);
					break;
				}
				
				if (treeNode.getRight() != null) {
					queue.add(treeNode.getRight());
				} else {
					treeNode.setRight(node);
					break;
				}
			}
		}
		++size;	
	}

	@Override
	public E remove(E element) throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("Binary Tree is empty");
		}
		
		Queue<TreeNode<E>> queue = new LinkedList<>();
		queue.add(this.root);
		
		TreeNode<E> deletedNode = null;
		TreeNode<E> node = null;
		
		while (!queue.isEmpty()) {
			node = queue.poll();
			
			if (node.getData().compareTo(element) == 0) {
				deletedNode = node;
			}
			if (node.getLeft() != null) {
				queue.add(node.getLeft());
			}
			if (node.getRight() != null) {
				queue.add(node.getRight());
			}
		}
		
		TreeNode<E> result = new TreeNode<E>(deletedNode.getData());
		// Set the data to the deepest node
		deletedNode.setData(node.getData());
		
		// Set the last node to null which is the deepest node.
		node = null;
		--size;
		return result.getData();
	}

	@Override
	public boolean contains(E element) throws ElementNotFoundException {
		
		if (this.root == null) {
			throw new ElementNotFoundException("Binary Tree is empty");
		}
		
		return contains(this.root);
	}

	@Override
	public Set<E> printInOrder() throws EmptyTreeException {
		
		if (this.size == 0) {
			throw new EmptyTreeException("The Binary Tree is empty");
		}
		
		Set<E> nodes = new LinkedHashSet<>();
		
		Stack<TreeNode<E>> stack = new Stack<>();
		stack.push(this.root);
		TreeNode<E> current = this.root;
		
		while (current != null || !stack.isEmpty()) {
			
			while (current != null) {
				stack.push(current.getLeft());
				current = current.getLeft();
			}
			
			TreeNode<E> treeNode = stack.pop();
			nodes.add(treeNode.getData());			
			current = current.getRight();
		}
		
		return nodes;
	}

	@Override
	public Set<E> printPreOrder() throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("Binary Tree is empty");
		}
		
		Set<E> nodes = new LinkedHashSet<>();

		Stack<TreeNode<E>> stack = new Stack<>();
		stack.push(this.root);
		
		while (!stack.isEmpty()) {
			TreeNode<E> node = stack.pop();
			nodes.add(node.getData());
			
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
		}
		
		return nodes;
	}

	@Override
	public Set<E> printPostOrder() throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("Binary Tree is empty");
		}
		
		Set<E> nodes = new LinkedHashSet<>();

		postOrderRecursive(this.root, nodes);
		
		return nodes;
	}

	@Override
	public boolean dfs(E element) throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("Binary Tree is empty");
		}
		
		Stack<TreeNode<E>> stack = new Stack<>();
		stack.push(this.root);
		
		while (!stack.isEmpty()) {
			
			TreeNode<E> node = stack.pop();
			
			if (node.getData().compareTo(element) == 0) {
				return true;
			}
			
		    if (node.getLeft() != null) {
		    	stack.push(node.getLeft());
		    }
		    if (node.getRight() != null) {
		    	stack.push(node.getRight());
		    }
		}
		
		return false;
	}

	@Override
	public boolean bfs(E element) throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("Binary Tree is empty");
		}
		
		Queue<TreeNode<E>> queue = new LinkedList<>();
		queue.add(this.root);
		
		while (!queue.isEmpty()) {
			TreeNode<E> node = queue.poll();
			
			if (node.getData().compareTo(element) == 0) {
				return true;
			}
			
			if (node.getLeft() != null) {
				queue.add(node.getLeft());
			}
			
			if (node.getRight() != null) {
				queue.add(node.getRight());
			}
		}
		
		return false;
	}

	public int size() {
		return this.size;
	}
	
	@Override
	public E min() throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("Binary Tree is empty");
		}
		
		TreeNode<E> min = this.root;
		Stack<TreeNode<E>> stack = new Stack<>();
		stack.push(this.root);
		
		while (!stack.isEmpty()) {
			TreeNode<E> node = stack.pop();
			
			if (node.getData().compareTo(min.getData()) < 0) {
				min = node;
			}
			
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
			
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
		}
		
		return min.getData();
	}

	@Override
	public E max() throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("Binary Tree is empty");
		}
		
		TreeNode<E> max = this.root;
		Stack<TreeNode<E>> stack = new Stack<>();
		stack.push(this.root);
		
		while (!stack.isEmpty()) {
			TreeNode<E> node = stack.pop();
			
			if (node.getData().compareTo(max.getData()) > 0) {
				max = node;
			}
			
			if (node.getLeft() != null) {
				stack.push(node.getLeft());
			}
			
			if (node.getRight() != null) {
				stack.push(node.getRight());
			}
		}
		
		return max.getData();
	}
	

	@Override
	public E leastCommonAncestor(E p, E q) {
		TreeNode<E> node = lcaRecursive(this.root, q, p);
		return node.getData();
	}
	

	@Override
	public Set<E> boundaryTraversal() {
		
		Set<E> nodes = new LinkedHashSet<>(leftView());
		nodes.addAll(leafView());
		nodes.addAll(rightView());		
		return nodes;
	}

	@Override
	public Set<E> leftView() {
		Set<E> set = new LinkedHashSet<>();
		leftView(this.root, set);
		return set;
	}

	@Override
	public Set<E> rightView() {		
		Set<E> set = new LinkedHashSet<>();
		rightView(this.root, set);
		return set;
	}

	@Override
	public Set<E> leafView() {
		Set<E> set = new LinkedHashSet<>();
		leafView(this.root, set);
		return set;
	}
	
	@Override
	public int height() {
		return height(this.root);
	}
	
	private boolean contains(TreeNode<E> node) {
		
		if (node == null) {
			return false;
		}
		
		if (node.getData().compareTo(node.getData()) == 0) {
			return true;
		}
		
		return contains(node.getLeft()) || contains(node.getRight());
	}
	
	private void postOrderRecursive(TreeNode<E> node, Set<E> nodes) {
		if (node != null) {
			postOrderRecursive(node.getLeft(), nodes);
			postOrderRecursive(node.getRight(), nodes);
			nodes.add(node.getData());
		}
	}
	
	private TreeNode<E> lcaRecursive(TreeNode<E> node, E q, E p) {
		if (node == null) {
			return null;
		}
		
		if (node.getData().compareTo(p) == 0 || node.getData().compareTo(q) == 0) {
			return node;
		}
		
		TreeNode<E> left = lcaRecursive(node.getLeft(), q, p);
		TreeNode<E> right = lcaRecursive(node.getRight(), q, p);
		
		if (left != null && right != null) {
			return node;
		}
		
		return left != null ? left : right;
	}
	
	private void leftView(TreeNode<E> node, Set<E> nodes) {
		if (node != null) {
			nodes.add(node.getData());
			
			if (node.getLeft() != null) {
				leftView(node.getLeft(), nodes);
			}
			else if(node.getRight() != null) {
				leftView(node.getRight(), nodes);
			}
		}
	}
	
	private void rightView(TreeNode<E> node, Set<E> nodes) {
		if (node != null) {
			
			nodes.add(node.getData());
			if (node.getRight() != null) {
				rightView(node.getRight(), nodes);
			}
			else if (node.getLeft() != null) {
				rightView(node.getLeft(), nodes);
			}
		}
	}
	
	private void leafView(TreeNode<E> node, Set<E> nodes) {
		
		if (node != null) {
			if (node.getLeft() == null && node.getRight() == null) {
				nodes.add(node.getData());
			}
			leafView(node.getLeft(), nodes);
			leafView(node.getRight(), nodes);
		}
	}
	
	private int height(TreeNode<E> node) {
		if (node == null) {
			return -1;
		}
		
		int leftHeight = height(node.getLeft());
		int righHeight = height(node.getRight());
		
		return Math.max(leftHeight, righHeight) + 1;
	}
}
