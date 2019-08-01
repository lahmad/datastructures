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
	public Set<TreeNode> printInOrder() throws EmptyTreeException {
		
		if (this.size == 0) {
			throw new EmptyTreeException("The Binary Tree is empty");
		}
		
		Set<TreeNode> nodes = new LinkedHashSet<>();
		
		Stack<TreeNode<E>> stack = new Stack<>();
		stack.push(this.root);
		TreeNode<E> current = this.root;
		
		while (current != null || !stack.isEmpty()) {
			
			while (current != null) {
				stack.push(current.getLeft());
				current = current.getLeft();
			}
			
			TreeNode<E> treeNode = stack.pop();
			nodes.add(treeNode);			
			current = current.getRight();
		}
		
		return nodes;
	}

	@Override
	public Set<TreeNode> printPreOrder() throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("Binary Tree is empty");
		}
		
		Set<TreeNode> nodes = new LinkedHashSet<>();

		Stack<TreeNode<E>> stack = new Stack<>();
		stack.push(this.root);
		
		while (!stack.isEmpty()) {
			TreeNode<E> node = stack.pop();
			nodes.add(node);
			
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
	public Set<TreeNode> printPostOrder() throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("Binary Tree is empty");
		}
		
		Set<TreeNode> nodes = new LinkedHashSet<>();

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
	
	private boolean contains(TreeNode<E> node) {
		
		if (node == null) {
			return false;
		}
		
		if (node.getData().compareTo(node.getData()) == 0) {
			return true;
		}
		
		return contains(node.getLeft()) || contains(node.getRight());
	}
	
	private void postOrderRecursive(TreeNode<E> node, Set<TreeNode> nodes) {
		if (node != null) {
			postOrderRecursive(node.getLeft(), nodes);
			postOrderRecursive(node.getRight(), nodes);
			nodes.add(node);
		}
	}
}