package com.dev.luqman.tree;

public class TreeNode<E extends Comparable<E>> {

	private E data;
	
	private TreeNode<E> right;
	
	private TreeNode<E> left;
	
	public TreeNode(E data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public TreeNode<E> getRight() {
		return right;
	}

	public void setRight(TreeNode<E> right) {
		this.right = right;
	}

	public TreeNode<E> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<E> left) {
		this.left = left;
	}
	
	@Override
	public String toString() {
		return String.format("Node -> data=%s", this.data);
	}
	
}
