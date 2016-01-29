public class BinaryTreeTest {

  public static void main(String[] args) {
    new BinaryTreeTest().run();
  }

  static class Node {
    Node left;

    Node right;

    int value;

    public Node(int value) {
      this.value = value;
    }
  }

	public void run() {
		// build the simple tree from chapter 11.
		Node root = new Node(5);
		System.out.println("Binary Tree Example");
		System.out.println("Building tree with root value " + root.value);
		insert(root, 1);
		insert(root, 8);
		insert(root, 6);
		insert(root, 3);
		insert(root, 9);
		System.out.println("Traversing tree in order");
		//printInOrder(root);
		
		Node root2 = new Node(6);
		System.out.println("Binary Tree Example");
		System.out.println("Building tree with root value " + root2.value);
		insert(root2, 5);
		insert(root2, 3);
		insert(root2, 8);
		insert(root2, 1);
		insert(root2, 9);
		
		System.out.println("Traversing tree reverse order");
		reverseInOrder(root);
		
		System.out.println("Traversing tree in order");
		//printInOrder(root2);
		
		System.out.println("Both trees are same ? " + checkInOrder(root,root2));
		
		
		Node root3 = new Node(10);
		System.out.println("Mirror");
		Node root4 = new Node(20);
		Node root5 = new Node(220);
		Node root6 = new Node(30);
		Node root7 = new Node(40);
		Node root8 = new Node(40);
		Node root9 = new Node(30);
		root3.left = root4;
		root3.right = root5;
		root4.left = root6;
		root4.right = root7;
		root5.left = root8;
		root5.right = root9;
		System.out.println("Traversing tree in order");
		
		System.out.println("Is mirror " +checkFoldable(root3));
		System.out.println("Is mirror checking " +isFoldable(root3, root3));
	}

	
	static boolean isFoldable(Node root1, Node root2) {
	
		if(root1 == null && root2 == null)
			return true;
			
		if((root1 != null && root2 == null) || (root1 == null && root2 != null ))
			return false;
			
		if(root1.value != root2.value)
			return false;
		else 
			return isFoldable(root1.left, root2.right) && isFoldable(root1.right, root2.left);
	}
	
	
	
	static boolean checkFoldable(Node root1) {
	
		InOrderIterator ita1 = new InOrderIterator(root1);
		InOrderIterator ita2 = new InOrderIterator(root1);
		
		int n1 = 0;
		int n2 = 0;
		Node cur = ita1.next();
		n1 = cur.value;
		n2 = ita2.rightNext().value;
		
		while(cur != root1) {
			if(n1 != n2)
				return false;
			System.out.println("N1 ----> " + n1);
			cur = ita1.next();
			n1 = cur.value;
			n2 = ita2.rightNext().value;
			
		}
		return true;
	}
	
	static boolean checkTwoNodesSum(int n, Node root1) {
	
		InOrderIterator ita1 = new InOrderIterator(root1);
		InOrderIterator ita2 = new InOrderIterator(root1);
		if(n == 0) {
			return false;
		}
		int n1 = 0;
		int n2 = 0;
		n1 = ita1.next().value;
		n2 = ita2.rightNext().value;
		while(n1 != n2) {
			if(n1 + n2 == n) {
				System.out.println("Found numbers " + n1 + "--" + n2);
				return true;
			} else if(n1 + n2 > n) {
				n2 = ita2.rightNext().value;
			} else {
				n1 = ita1.next().value;
			}
		}
		return false;
	}
	
	static boolean checkInOrder(Node root1, Node root2) {
		
		InOrderIterator ita1 = new InOrderIterator(root1);
		InOrderIterator ita2 = new InOrderIterator(root2);
		
		while(ita1.hasNext() || ita2.hasNext()){
			Node node1 = ita1.rightNext();
			Node node2 = ita2.rightNext();
			
			if(node1 == null || node2 == null) {
				return false;
			}
			
			System.out.println("val1 " + node1.value + "---" +  node2.value);
			if(node1.value != node2.value)
				return false;
		}
		
		
		
		return true;
	}
	
	public void insert(Node node, int value) {
    if (value < node.value) {
		if (node.left != null) {
			insert(node.left, value);
		} else {
			System.out.println("  Inserted " + value + " to left of "
				+ node.value);
			node.left = new Node(value);
		}
		
	} else if (value > node.value) {
		if (node.right != null) {
			insert(node.right, value);
		} else {
			System.out.println("  Inserted " + value + " to right of "
				+ node.value);
			node.right = new Node(value);
		}
	}
  }

  public void printInOrder(Node node) {
    if (node != null) {
	System.out.println(" recursion " + node.value);
      printInOrder(node.left);
      System.out.println("  Traversed " + node.value);
      printInOrder(node.right);
    }
  }
  
   public void reverseInOrder(Node node) {
    if (node != null) {
      System.out.println(" recursion " + node.value);
	  printInOrder(node.right);
      System.out.println("  Traversed " + node.value);
      printInOrder(node.left);
    }
  }
  
	static class InOrderIterator {
		Node root;
		
		InOrderIterator(Node root){
			this.root = root;
		}
		
		boolean hasNext() {
			return root != null;
		}
		
		Node next() {
			Node next = null;
			while(root != null) {
				if(root.left == null){
					next = root;
					root = root.right;
					break;
				} else {
					Node temp = root.left;
					while(temp.right != null && temp.right != root) {
						temp = temp.right;
					}
					if(temp.right == null) {
						temp.right = root;
						root = root.left;
					} else {
						next = temp.right;
						temp.right = null;
						root = root.right;
						break;
					}
				}
			}
			
			return next;
			
		}
		
		Node rightNext() {
			Node next = null;
			while(root != null) {
				if(root.right == null){
					next = root;
					root = root.left;
					break;
				} else {
					Node temp = root.right;
					while(temp.left != null && temp.left != root) {
						temp = temp.left;
					}
					if(temp.left == null) {
						temp.left = root;
						root = root.right;
					} else {
						next = temp.left;
						temp.left = null;
						root = root.left;
						break;
					}
				}
			}
			
			return next;
			
		}
	}


}