import java.util.LinkedList;

public class BinarySearchTree<E extends Comparable<E>> {

    // ^ the code above is necessary bc the E's in the tree are comparable, and can be
    // compared with each other, note: only comparing with the same type

    private int size;
    private Node<E> root;

    public BinarySearchTree() {
        this.root = null;
        // we're going to be using root alot, so we need to be explicit when referring to
        // the root of a tree and the subtree
        // most of the time we will be using root as a local var in regards to the root of the subtree
        // this.root will refer to root of the entire tree
        this.size = 0;
    }


    public int size() {
        return size;
    }


    //does this tree have the item in it or not
    // does the root of this tree have this item !!!
    public boolean contains(E item) {
        return contains(this.root, item);
    }

    // root is of a subtree
    // root is current root im on, does root of this tree have this item
    private boolean contains(Node<E> root, E item) {
        //base case
        if(root==null) { // if the node doesnt exist, it makes sense to stop
            return false;
        }
        //                              item in the root
        int comparison = item.compareTo(root.item);
        if(comparison == 0) {
            return true;
        }
        else if(comparison < 0 ) {
            // search all the left subtree for item
            return contains(root.left,item);
        } else {
            // // search all the left subtree for item
            return contains(root.right, item);
        }
    }

    // add items without having to worry about the starting point
    // whatever the reselt of add is, were going to add the item to the root
    // and set up the root to be this item
    private void add(E item) {
        this.root = add(this.root, item);
    }

    // were returning a node, take in a node thats a root and has an item
    private Node<E> add(Node<E> root, E item) {
        //base case
         // if the root of the subtree is null, we've found a place to put our new node
         if(root == null) {
             return new Node(item);
         }
         int comparison = item.compareTo(root.item);
         // trying to add an item thats already there
         if(comparison == 0) {
             return root; // this means dont change me parent
         }
         if(comparison < 0) {
             root.left = add(root.left, item);
             return root;
         } else {
             root.right = add(root.right, item);
             return root;
         }
    }

    //remove an item from a tree if it exists
    public void remove(E item) {
        // want to remove from this.root, the item
        // want to remove this item but the tree, at this root
        this.root = this.remove(this.root, item);

    }

    // want to remove this item that starts at the subtree root
    private Node<E> remove(Node<E> root, E item) {
        if (root == null) {
            return null;
        }
        int comparison = item.compareTo(root.item);
        if (comparison < 0) {
            root.left = remove(root.left, item);
            return root;
        } else if (comparison > 0) {
            root.right = remove(root.right, item);
            return root;
        } else {
            //when youre removing item from tree, you have 3 cases

            if (root.left == null && root.right == null) { // means left and right subtrees dont have any children
                return null;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else {
                Node<E> current = root.left;
                while (current.right != null) {
                    current = root.right;
                }

                root.item = current.item; //remember root is the top of tree, current is the node being copied to root
                root.left = remove(root.left, root.item); //from the left sub tree (of root) remove roots duplicate
                return root;
            }
        }
    }
// # 18 on exam review
    public static <E extends Comparable<E>> boolean equals(Node<E> treeA, Node<E> treeB) {
        if(treeA == null && treeB == null) {
            return true;
        }
        else if(treeA != null && treeB == null) {
            return false;
        }
        else if(treeA == null && treeB != null) {
            return false;
        }
        else if(!treeA.item.equals(treeB.item)) {
            return false;
            }
        else { //both nodes exist and have same data
            return equals(treeA.left,treeB.left) && equals(treeA.right,treeB.right);
        }
    }

    // Final question #12
    public static LinkedList<E> gudsq(TreeNode<E> root) {
        if(root == null) {
            return LinkedList;
        }

    }



    public String toString() {
        //return toString(this.root);
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    private String toString(Node<E> root) {
        if(root == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(toString(root.left));
        builder.append(" ");
        builder.append(root.item);
        builder.append(" ");
        builder.append(toString(root.right));
        return builder.toString();
    }
    private void preOrderTraverse(Node<E> root, int depth, StringBuilder sb) {
        for(int i = 1; i < depth; i++) {
            sb.append("  "); //indentation
        }
        if(root == null) {
            sb.append("null\n");
        } else {
            sb.append(root.toString());
            sb.append("\n");
            preOrderTraverse(root.left, depth + 1, sb);
            preOrderTraverse(root.right, depth + 1, sb);
        }
    }
    //Exam Number 15
    //write a recursive method which, given the root of a tree or subtree, returns the height of the tree or subtree.

    public static <E> int getHeight(Node<E> root) {
        int theHeight;
        //base case
        if(root == null) {
            return 0;
        }
        int leftSide = getHeight(root.left);
        int rightSide = getHeight(root.right);

    return Math.max(leftSide,rightSide) + 1;
    }
    // # 16
    //A tree is considered balanced if the height of its left subtree
    // and right subtree differ by no more than 1 and the subtrees
    // are also balanced.
    /*public static <E> boolean isBalanced(Node<E> root) {
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        //base case
        if(root == null) {
            return true;
        }else if(root.left == null && root.right == null) {
            return true;
        }else if(Math.absolutevalue.(leftHeight-rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right)){
            return true;
        }
        return false;
    }

     */



        private static class Node<E> {
        // the nodes that exist are these purple items
        private E item;
        private Node<E> left;
        private Node<E> right;

        //constructor that doesnt really do anything except take in the item
        public Node(E item){
            this.item = item;
        }
        @Override
        public String toString() {
            return item.toString();
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(16);
        bst.add(12);
        bst.add(6);
        bst.add(45);
        bst.add(15);
        bst.add(90);
        bst.add(25);
        bst.add(30);
        bst.add(37);


        /*
        bst.add(16);
        bst.add(45);
        bst.add(30);
        bst.add(90);
        bst.add(12);
        bst.add(15);
        bst.add(6);
        bst.add(25);
        bst.add(37);

        */

        System.out.println(bst);


    }






}
