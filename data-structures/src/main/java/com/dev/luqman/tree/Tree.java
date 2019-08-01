package com.dev.luqman.tree;

import java.util.Set;

import com.dev.luqman.tree.exceptions.ElementNotFoundException;
import com.dev.luqman.tree.exceptions.EmptyTreeException;

public interface Tree<E> {

	void add(E element);
	
	E remove(E element) throws  EmptyTreeException;
	
	boolean contains(E element) throws ElementNotFoundException;
	
	Set<E> printInOrder() throws EmptyTreeException;
	
	Set<E> printPreOrder() throws EmptyTreeException;
	
	Set<E> printPostOrder() throws EmptyTreeException;
	
	boolean dfs(E element) throws  EmptyTreeException;
	
	boolean bfs(E element) throws  EmptyTreeException;

	E min() throws EmptyTreeException;
	
	E max() throws EmptyTreeException;
	
	E leastCommonAncestor(E p, E q);
	
	Set<E> boundaryTraversal();
	
	Set<E> leftView();
	
	Set<E> rightView();
	
	Set<E> leafView();
	
	int size();
}
