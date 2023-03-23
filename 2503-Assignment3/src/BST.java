import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BST<T extends Comparable<T>> implements Iterable<T> {
	class BSTNode implements Comparable<BSTNode> {
		private T data;
		private BSTNode left;
		private BSTNode right;


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

		public boolean isLeaf() {
			return (getLeft() == null) && (getRight() == null);
		}

		public int compareTo(BSTNode o) {
			return this.getData().compareTo(o.getData());
		}

	}

	private static final int INORDER = 0;

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
	
	private int compare(T o1, T o2) {
		if(comparator == null) {
			return o1.compareTo(o2);
		} else {
			return comparator.compare(o1, o2);
		}
	}

	public int height() {
		return height(root);
	}
//	
//	public T find(T d) {
//		return find(d, root);
//	}
	
	public void inOrder() {
		traverse(root, INORDER);
	}
	
	public void deleteNode(T key) {
        root = deleteNode(root, key);
    }

	private int height(BSTNode r) {
//		int h = -1;
//		if (r != null) {
//			int rh = height(r.getRight());
//			int lh = height(r.getLeft());
//			h = (rh > lh ? 1 + rh : 1 + lh);
//		}
//		return h;
		int h = -1;
		if(r == null) {
			return h;
		} else {
			int leftHeight = height(r.getLeft());
			int righHeight = height(r.getRight());
			h = Math.max(leftHeight, righHeight) + 1;
		}
		return h;
	}
	
	public T find(T f) {
		BSTNode curr = root;
		while(curr != null && !f.equals(curr.getData())) {
			if(compare(f, curr.getData()) < 0) {
				curr = curr.getLeft();
			} else {
				curr = curr.getRight();
			}
		}
		return (curr == null) ? null : curr.getData();
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

	public int size() {
		return size;
	}

	private void add(BSTNode r, BSTNode n) {
		int c = compare(n.getData(), r.getData());
		if (c < 0) {
			if (r.getLeft() == null) {
				r.setLeft(n);
				size++;
			} else
				add(r.getLeft(), n);
		} else if(c > 0) {
			if(r.getRight() == null) {
				r.setRight(n);
				size++;
			} else {
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
//		if (node == null) {
//            return null;
//        }
//
//        int cmp = compare(key, node.data);
//        if (cmp < 0) {
//            node.left = deleteNode(node.left, key);
//        } else if (cmp > 0) {
//            node.right = deleteNode(node.right, key);
//        } else {
//            if (node.left == null) {
//                return node.right;
//            } else if (node.right == null) {
//                return node.left;
//            } else {
//                BSTNode temp = node.right;
//                while (temp.left != null) {
//                    temp = temp.left;
//                }
//                node.data = temp.data;
//                node.right = deleteNode(node.right, temp.data);
//            }
//        }
//
//        return node;
    }

	private void visit(BSTNode r) {
		if (r != null)
			queue.add(r.getData());
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
			}
		} 
	}
	
	/* traverse the subtree r using level order. */
//	private void levelOrder(BSTNode r) {
//		if(r == null) {
//			return;
//		}
//		Queue<BSTNode> queue = new LinkedList<>();
//		queue.add(r);
//		while(!queue.isEmpty()) {
//			BSTNode node = queue.remove();
//			System.out.println(node.getData());
//			if(node.getLeft() != null) {
//				queue.add(node.getLeft());
//			}
//			if(node.getRight() != null) {
//				queue.add(node.getRight());
//			}
//		}
//	}

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
		return new BSTIterator();
	}

}
