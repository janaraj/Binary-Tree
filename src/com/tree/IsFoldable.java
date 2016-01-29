
/**
	Check if the given binary tree can be folded to form the same tree
	
	Solution:
		1. Do Inorder traversal and reverse inorder traversal
		2. Compare the value until it reaches root and return false if there is a mismatch
		
	Complexity for iterative solution:
		Time: O(n/2) -> O(n)
		Space: O(1)
*/

class IsFoldable {

	// Iterative version
	static boolean checkFoldable(Node root) {
	
		//If tree is null then we can assume that the tree is null and return true
		if(root == null)
			return true;
			
		//Inorder traversal
		InOrderIterator ita1 = new InOrderIterator(root);
		
		//Reverse inorder traversal
		InOrderIterator ita2 = new InOrderIterator(root);
		
		Node cur = ita1.next();
		Node right = ita2.rightNext();
		
		while(cur != root) {
			if(cur.value != right.value)
				return false;
			
			cur = ita1.next();
			right = ita2.rightNext();
			
		}
		
		return true;
	}
	
	//Recursive version
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

	public static void main(String args[]) {
	
	
		//If tree is Null
		System.out.println("Iterative: Is Foldable when null -> " +checkFoldable(null));
		
		//Only with root 
		Node root = new Node(10);
		System.out.println("Iterative: Is Foldable with only root -> " +checkFoldable(root));
		System.out.println("Recursive: Is Foldable with only root -> " +isFoldable(root, root));
		
		Node root4 = new Node(20);
		Node root5 = new Node(20);
		Node root6 = new Node(30);
		Node root7 = new Node(40);
		Node root8 = new Node(40);
		Node root9 = new Node(30);
		
		root.left = root4;
		System.out.println("Iterative: Is Foldable with only left child -> " +checkFoldable(root));
		
		root.right = root5;
		root4.left = root6;
		root4.right = root7;
		root5.left = root8;
		root5.right = root9;
		System.out.println("Iterative: Is Foldable when tree has matching values -> " +checkFoldable(root));
		System.out.println("Recursive: Is Foldable when tree has matching values  -> " +isFoldable(root, root));
		
		
		// Failure case
		root5.value = 220;
		System.out.println("Iterative: Is Foldable when tree values are matching  -> " +checkFoldable(root));
		
		System.out.println("Recursive: Is Foldable when tree values are matching  -> " +isFoldable(root, root));
		
		
		
		
	}
}