package com.dev.luqman.tree;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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
		
		E result = null;
		TreeNode<E> node = findNode(this.root, element);
		if (node == null) {
			result = null;
		}
		
		else if (node.getRight() != null) {
			TreeNode<E> min = findMinTreeNode(node.getRight());
			result = min.getData();
		}
		else {
			TreeNode<E> current = this.root;
			TreeNode<E> previous = null;
			
			while (current.getData().compareTo(element) != 0) {
				if (current.getData().compareTo(element) > 0) {
					previous = current;
					current = current.getLeft();
				} else {
					current = current.getRight();
				}
			}
			result = previous.getData();
		}
		return result;
	}

	@Override
	public E floor(E element) {
		
		E result = null;
		TreeNode<E> node = findNode(this.root, element);
		
		if (node == null) {
			result = null;
		} 
		else {
			if (node.getLeft() != null) {
				TreeNode<E> max = findMaxTreeNode(node.getLeft());
				result = max.getData();
			}
			else {
				TreeNode<E> current = this.root;
				TreeNode<E> previous = null;
				
				while (current.getData().compareTo(element) != 0) {
					if (current.getRight() != null) {
						previous = current;
						current = current.getRight();
					}
					else {
						current = current.getLeft();
					}
				}
				result = previous.getData();
			}
		}
		return result;
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
		if (this.root == null) {
			return false;
		} else {
			TreeNode<E> current = this.root;
			while (current != null) {
				if (current.getData().compareTo(element) > 0) {
					current = current.getLeft();
				}
				else if (current.getData().compareTo(element) < 0) {
					current = current.getRight();
				}
				else {
					return true;
				}
			}
		}
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
		Set<E> set = new LinkedHashSet<>();
		printPreOrderHelper(this.root, set);
		return set;
	}

	@Override
	public Set<E> printPostOrder() throws EmptyTreeException {
		Set<E> set = new LinkedHashSet<>();
		printPostOrderHelper(this.root, set);
		return set;	
	}

	@Override
	public boolean dfs(E element) throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("BinarySearchTree is empty");
		}
		
		Stack<TreeNode<E>> stack = new Stack<>();
		stack.push(this.root);
		
		while (!stack.isEmpty()) {
			
			TreeNode<E> node = stack.pop();

			if (node.getData().compareTo(element) > 0) {
				if (node.getLeft() != null)
					stack.push(node.getLeft());
			}
			else if (node.getData().compareTo(element) < 0) {
				if (node.getRight() != null)
				stack.push(node.getRight());
			}
			else {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean bfs(E element) throws EmptyTreeException {
		
		if (this.root == null) {
			throw new EmptyTreeException("BinarySearchTree is empty");
		}
		
		Queue<TreeNode<E>> queue = new LinkedList<>();
		queue.add(this.root);
		while (!queue.isEmpty()) {
			TreeNode<E> node = queue.poll();
			
			if (node.getData().compareTo(element) == 0) {
				return true;
			}
			else if (node.getData().compareTo(element) < 0) {
				if (node.getRight() != null)
					queue.add(node.getRight());
			}
			else {
				if (node.getLeft() != null)
					queue.add(node.getLeft());
			}
		}
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
	
	private TreeNode<E> findMaxTreeNode(TreeNode<E> node) {
		
		if (node == null) {
			return node;
		}
		
		TreeNode<E> current = node;
		while(current.getRight() != null) {
			current = current.getRight();
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
	
	private void printPreOrderHelper(TreeNode<E> node, Set<E> nodes) {
		if (node != null) {
			nodes.add(node.getData());
			printPreOrderHelper(node.getLeft(), nodes);
			printPreOrderHelper(node.getRight(), nodes);
		}
	}
	
	private void printPostOrderHelper(TreeNode<E> node, Set<E> nodes) {
		if (node != null) {
			printPostOrderHelper(node.getLeft(), nodes);
			printPostOrderHelper(node.getRight(), nodes);
			nodes.add(node.getData());
		}
	}
	
	private TreeNode<E> findNode(TreeNode<E> root, E element) {
		
		if (root == null) {
			return root;
		}
		
		TreeNode<E> current = root;
		while (current != null) {
			if (current.getData().compareTo(element) == 0) {
				break;
			}
			
			else if (current.getData().compareTo(element) < 0) {
				current = current.getRight();
			}
			else {
				current = current.getLeft();
			}
		}
		
		return current;
	}
}
