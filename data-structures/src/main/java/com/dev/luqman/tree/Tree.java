package com.dev.luqman.tree;

import java.util.Set;

import com.dev.luqman.tree.exceptions.ElementNotFoundException;
import com.dev.luqman.tree.exceptions.EmptyTreeException;

public interface Tree<E> {

	void add(E element);
	
	E remove(E element) throws  EmptyTreeException;
	
	boolean contains(E element) throws ElementNotFoundException;
	
	Set<TreeNode> printInOrder() throws EmptyTreeException;
	
	Set<TreeNode> printPreOrder() throws EmptyTreeException;
	
	Set<TreeNode> printPostOrder() throws EmptyTreeException;
	
	boolean dfs(E element) throws  EmptyTreeException;
	
	boolean bfs(E element) throws  EmptyTreeException;

	E min() throws EmptyTreeException;
	
	E max() throws EmptyTreeException;
	
	int size();
}
