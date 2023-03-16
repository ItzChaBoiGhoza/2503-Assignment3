package Assignment3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BST<T extends Comparable<T>> implements Iterable<T>{
	private BSTNode root;
	
	class BSTNode implements Comparable<BSTNode> {
		private T data;
		private BSTNode left;
		private BSTNode right;

		private int size;

		public BSTNode(T d) {
			setLeft(null);
			setRight(null);
			setData(d);
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public BSTNode getLeft() {
			return left;
		}

		public void setLeft(BSTNode left) {
			this.left = left;
		}

		public BSTNode getRight() {
			return right;
		}

		public void setRight(BSTNode right) {
			this.right = right;
		}

		@Override
		public int compareTo(BST<T>.BSTNode o) {
			// TODO Auto-generated method stub
			return 0;
		}

		public int size() {
			return size;
		}

		private int height(BSTNode r) {
			int h = -1;
			if (r == null) {
				return h;
			} else {
				int leftHeight = height(r.getLeft());
				int rightHeight = height(r.getRight());
				h = Math.max(leftHeight, rightHeight) + 1;
			}
			return h;
		}

		public int height() {
			return height(root);
		}

		public void add(T d) {
			BSTNode n = new BSTNode(d);
			if (root == null) {
				size++;
				root = n;
			} else {
				add(root, n);
			}
		}


		private void add(BSTNode r, BSTNode n) {
			int c = n.compareTo(r);
			if (c < 0) {
				if (r.getLeft() == null) {
					r.setLeft(n);
				} else
					add(r.getLeft(), n);
			} else {
				if (r.getRight() == null)
					r.setRight(n);
				else {
					add(r.getRight(), n);
				}
			}

		}
	}

	@Override
	public Iterator<T> iterator() {
		return new InOrderIterator();
	}

	private class InOrderIterator implements Iterator<T> {
		
		private Queue<T> queue;
		
		InOrderIterator() {
			queue = new LinkedList<>();
			inOrderTraversal(root);
		}
		
		private void inOrderTraversal(BSTNode r) {
			if(r == null) 
				return;
			else {
				inOrderTraversal(r.getLeft());
				queue.offer(r.getData());
				inOrderTraversal(r.getRight());
			}
			
		}

		@Override
		public boolean hasNext() {

			return !queue.isEmpty();
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();			}
			return queue.poll();
		}
	}
}
