/**
	Inorder iterator and reverse inorder iterator for a binary tree using Morris Traversal algorithm
	
	Applications:
		1. Compare if 2 binary trees are same
		2. Find 2 numbers in a Binary search tree matching a given sum
		3. If a tree can folded to form the same Binary tree
		
*/

class InOrderIterator {
		Node root;
		
		InOrderIterator(Node root){
			this.root = root;
		}
		
		boolean hasNext() {
			return root != null;
		}
		
		// returns next value in the inorder traversal
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
		
		
		// returns next value from last in the reverse inorder traversal
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