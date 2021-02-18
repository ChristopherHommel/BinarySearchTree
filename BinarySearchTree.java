/**
 * Create a new binary search tree (Not balanced)
 * @author 
 *
 */
public class BinarySearchTree {

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();

		tree.insert(50);
		tree.insert(25);
		tree.insert(15);
		tree.insert(30);
		tree.insert(75);
		tree.insert(85);
		tree.insert(85);
		tree.insert(20);
		tree.insert(55);
		tree.insert(15);
		tree.insert(30);
		tree.insert(95);
		tree.insert(100);
		tree.insert(45);
		tree.insert(35);
		
		tree.inOrderTraverseTree(tree.root);

		System.out.println("test"); 
		
	}
	
	//Set a location for a starting point of a tree
	private Node root;

	/**
	 * Adds a new node to the tree with the 
	 * value of a key stored as an Integer
	 * @param key The value that should be inserted into the tree
	 */
	public void insert(int key) {
		//Create a new node
		Node newNode = new Node(key);

		//If there is currently nothing in the tree then insert new node to the top
		if (root == null) {
			root = newNode;
		} else {
			//Start with root as we traverse though the tree
			Node currentNode = root;
			//Parent for the new node 
			Node parent;

			while (true) {
				//Start at the top of the tree
				parent = currentNode;
				
				//If the key should be inserted on the left or the right side of the tree
				if (key < currentNode.getKey()) {
					//Change the focus to the left child
					currentNode = currentNode.getLeftChild();
					//If there is nothing to the left of the current node
					//Insert the new node to the left
					if (currentNode == null) {
						parent.setLeftChild(newNode);
						return;
					}
				} 
				//Put the node on to the right
				else {
					//Set the focus to the right node
					currentNode = currentNode.getRightChild();
					//If there is nothing to the right of the current node
					//Insert the new node to the right
					if (currentNode == null) {
						parent.setRightChild(newNode);
						return;
					}
				}
			}
		}
	}

	/**
	 * Look for a node containing the value of the key passed in
	 * @param key The value to search for in the tree
	 * @return The node that the key contains
	 */
	public Node search(int key) {
		Node currentNode = root;

		while (currentNode.getKey() != key) {

			if (key < currentNode.getKey()) {
				currentNode = currentNode.getLeftChild();
			} else {
				currentNode = currentNode.getRightChild();
			}

			if (currentNode == null) {
				return null;
			}
		}
		return currentNode;
	}

	/**
	 * Traverse though the tree starting at the smallest value
	 * and ending with the largest value
	 * @param currentNode The node that the recursive function is currently visiting
	 */
	public void inOrderTraverseTree(Node currentNode) {
		if (currentNode != null) {
			inOrderTraverseTree(currentNode.getLeftChild());

			System.out.println(currentNode);

			inOrderTraverseTree(currentNode.getRightChild());
		}
	}

	/**
	 * Traverse through the tree starting at root
	 * Then moving onto all child nodes starting with the left child
	 * And moving onto the right child
	 * @param currentNode The node that the recursive function is currently visiting
	 */
	public void preorderTraverseTree(Node currentNode) {
		if (currentNode != null) {
			System.out.println(currentNode);

			preorderTraverseTree(currentNode.getLeftChild());

			preorderTraverseTree(currentNode.getRightChild());
		}
	}

	/**
	 * Traverse though the tree by visiting the left subtree then the right subtree
	 * Visiting the root node last
	 * @param currentNode The node that the recursive function is currently visiting
	 */
	public void postOrderTraverseTree(Node currentNode) {
		if (currentNode != null) {
			postOrderTraverseTree(currentNode.getLeftChild());

			postOrderTraverseTree(currentNode.getRightChild());

			System.out.println(currentNode);
		}
	}
	

	/**
	 * Remove a value from the tree and inserting the correct nodes into
	 * The correct place from where the node was removed
	 * @param key The value to be removed from the tree
	 * @return True if a node has been deleted, false if there has been no node deleted
	 */
	public boolean remove(int key) {
		Node currentNode = root;
		Node parentNode = root;
		//If we are currently at a left child
		boolean pointingAtLeftChild = true;
		
		//Keep searching for the key until it is found
		while (currentNode.getKey() != key) {
			parentNode = currentNode;
			
			//Search to the left first
			if (key < currentNode.getKey()) {
				pointingAtLeftChild = true;

				currentNode = currentNode.getLeftChild();
			} 
			//Search to the right 
			else {
				//No longer true that we are visiting a left node
				pointingAtLeftChild = false;

				currentNode = currentNode.getRightChild();
			}
			//If we are unable to find the node containing the key
			if (currentNode == null) {
				return false;
			}
		}
		
		//If the Node we are currently visiting does not have children
		if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
			//Nothing else to change if node is root
			if (currentNode == root) {
				root = null;
			} 
			//If it is a left child of the parent, delete that node
			else if (pointingAtLeftChild) {
				parentNode.setLeftChild(null);
			} 
			//If it is a right child of the parent, delete that node
			else {
				parentNode.setRightChild(null);
			}
		}
		
		//If there is not a right child
		else if (currentNode.getRightChild() == null) {
			//Set the root to the left child
			if (currentNode == root) {
				root = currentNode.getLeftChild();
			}
			//Set the node above to the current node if it is a left child
			else if (pointingAtLeftChild) {
				parentNode.setLeftChild(currentNode.getLeftChild());
			} 
			//Set the node above to the current node if it is a right child
			else {
				parentNode.setRightChild(currentNode.getLeftChild());
			}
		}

		//If there is not a left child
		else if (currentNode.getLeftChild() == null) {
			//Set the root to the right child
			if (currentNode == root) {
				root = currentNode.getRightChild();
			}
			//Set the node above to the current node if it is a right child
			else if (pointingAtLeftChild) {
				parentNode.setLeftChild(currentNode.getRightChild());
			} 
			//Set the node above to the current node if it is a left child
			else {
				parentNode.setRightChild(currentNode.getLeftChild());
			}
		} 
		//If there are two children at the current node
		else {
			//Get the node we are currently visting to be replaced
			Node replacement = getReplacementNode(currentNode);
			//If at root the sett root to current node
			if (currentNode == root) {
				root = replacement;
			} 
			//If we are visiting the left child, set the parent to the replacement node
			else if (pointingAtLeftChild) {
				parentNode.setLeftChild(replacement);
			} 
			//If we are visiting the right child, set the parent to the replacement node
			else {
				parentNode.setRightChild(replacement);

			}
			//Set the new replaced left child to the currently visiting left child 
			replacement.setLeftChild(currentNode.getLeftChild());
		}
		return true;
	}

	/**
	 * 
	 * @param replacedNode The current node that we are visiting
	 * @return The node that is going to be replaced
	 */
	public Node getReplacementNode(Node replacedNode) {
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		
		//Move the right child up
		Node focusNode = replacedNode.getRightChild();
		
		//While there are no more left children
		while (focusNode != null) {
			//Set the parent to the node that will be replaced
			replacementParent = replacement;
			//Set the replaced node to the right child of the node we are currently on
			replacement = focusNode;
			//Set the focus node to the left child
			//Move the left child up
			focusNode = focusNode.getLeftChild();
		}
		
		//If the replaced node is not the right child
		if (replacement != replacedNode.getRightChild()) {
			replacementParent.setLeftChild(replacement.getRightChild());
			replacement.setRightChild(replacedNode.getRightChild());

		}

		return replacement;
	}

	/**
	 * 
	 * The class for storing information about the nodes in the binary search tree
	 * @author 
	 */
	private class Node {
		
		private Integer _key;
		private Node _leftChild;
		private Node _rightChild;

		public Node(int key) {
			this._key = key;
			this._leftChild = null;
			this._rightChild = null;
		}

		@Override
		public String toString() {
			return "This value is: " + _key;
		}
		
		/**
		 * 
		 * @return The value of the key of the current node
		 */
		public int getKey() {
			return this._key;
		}
		
		/**
		 * 
		 * @return The value of the left child of the current node
		 */
		public Node getLeftChild() {
			return this._leftChild;
		}
		
		/**
		 * 
		 * @return The value of the right child of the current node
		 */
		public Node getRightChild() {
			return this._rightChild;
		}
			
		/**
		 * 
		 * @param key Set a value for the key in this node
		 */
		public void setKey(int key) {
			_key = key;
		}
		
		/**
		 * 
		 * @param node Set a new left child for the current node
		 */
		public void setLeftChild(Node node) {
			_leftChild = node;
		}
		
		/**
		 * 
		 * @param node Set a new right child for the current node
		 */
		public void setRightChild(Node node) {
			_rightChild = node;
		}
		
		
	}

}
