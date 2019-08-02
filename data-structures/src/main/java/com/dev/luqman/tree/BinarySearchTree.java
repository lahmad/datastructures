package com.dev.luqman.tree;

public interface BinarySearchTree<E> extends Tree<E> {

	E ceiling(E element);
	
	E floor(E element);
}
