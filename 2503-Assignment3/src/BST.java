import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BST<T extends Comparable<T>> implements Iterable<T> {
	class BSTNode implements Comparable<BSTNode> {
		private T data;
		private BSTNode left;
		private BSTNode right;
		private BSTNode next;


		public BSTNode(T d) {
			setLeft(null);
			setRight(null);
			setData(d);
			setNext(null);
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
		
		public BSTNode getNext() {
			return next;
		}
		
		public void setNext(BSTNode n) {
			next = n;
		}

		public boolean isLeaf() {
			return (getLeft() == null) && (getRight() == null);
		}

		@Override
		public int compareTo(BST<T>.BSTNode o) {
			return this.getData().compareTo(o.getData());
		}

		public boolean isEmpty() {
			return size == 0;
		}
	}

	private static final int INORDER = 0;
	private static final int PREORDER = 1;
	private static final int POSTORDER = 2;
	private static final int LEVELORDER = 3;
	private static final int REVINORDER = 4;

	private BSTNode root;
	private int size;
	private Comparator<T> comparator;
	private Queue<T> queue = new LinkedList<T>();
	
	public BST() {
		root = null;
		size = 0;
		comparator = null;
	}
	
	public BST(Comparator<T> externalComp) {
		root = null;
		size = 0;
		comparator = externalComp;
	}

	public int size() {
		return size;
	}
	
	public void inOrder() {
		traverse(root, INORDER);
	}
	
	public void preOrder() {
		traverse(root, PREORDER);
	}
	
	public void postOrder() {
		traverse(root, POSTORDER);
	}
	
	public void levelOrder() {
		traverse(root, LEVELORDER);
	}
 	
	public void deleteNode(T key) {
        root = deleteNode(root, key);
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
	
	public T find(T f) {
		BSTNode curr = root;
		while(curr != null && !f.equals(curr.getData())) {
			if(f.compareTo(curr.getData()) < 0) {
				curr = curr.getLeft();
			} else {
				curr = curr.getRight();
			}
		}
		return (curr == null) ? null : curr.getData();
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
	
	private BSTNode deleteNode(BSTNode node, T key) {
		if(node == null) {
			return null;
		}
		
		int cmp = comparator != null ? comparator.compare(key, node.data) : key.compareTo(node.data);
		if(cmp < 0) {
			node.left = deleteNode(node.left, key);
		} else if(cmp > 0) {
			node.right = deleteNode(node.right, key);
		} else {
			if(node.left == null) {
				return node.right;
			} else if(node.right == null) {
				return node.left;
			} else {
				BSTNode temp = node.right;
				while(temp.left != null) {
					temp = temp.left;
				}
				node.data = temp.data;
				node.right = deleteNode(node.right, temp.data);
			}
		}
		return node;
    }

	private void visit(BSTNode r) {
		if (r != null)
			queue.add(r.getData());
//			System.out.println(r.getData());
	}
	
	public void traverse(int travType) {
		traverse(root, travType);
	}
	
	private void traverse(BSTNode r, int travType) {
		if(r != null) {
			switch (travType) {
			case INORDER:
					traverse(r.getLeft(), travType);
					visit(r);
					traverse(r.getRight(), travType);
					break;
			case PREORDER:
					visit(r);
					traverse(r.getLeft(), travType);
					traverse(r.getRight(), travType);
					break;
			case POSTORDER:
					traverse(r.getLeft(), travType);
					traverse(r.getRight(), travType);
					visit(r);
					break;
			case LEVELORDER:
					levelOrder(r);
					break;
			}
		}
	}
	
	/* traverse the subtree r using level order. */
	private void levelOrder(BSTNode r) {
		if(r == null) {
			return;
		}
		Queue<BSTNode> queue = new LinkedList<>();
		queue.add(r);
		while(!queue.isEmpty()) {
			BSTNode node = queue.remove();
			System.out.println(node.getData());
			if(node.getLeft() != null) {
				queue.add(node.getLeft());
			}
			if(node.getRight() != null) {
				queue.add(node.getRight());
			}
		}
	}

	private class BSTIterator implements Iterator {
		public BSTIterator() {
				queue.clear();
				traverse(root, INORDER);
			}

		@Override
		public boolean hasNext() {
			return !queue.isEmpty();
		}
		@Override
		public T next() {
			return queue.remove();
		}
	}

	@Override
	public Iterator<T> iterator() {
		Iterator it = new BSTIterator();
		return  it;
	}

}
