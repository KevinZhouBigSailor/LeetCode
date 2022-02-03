public class ConvertBinarySearchTreeToSortedDoublyLinkedList_426 {
    /**
     * Initiate the first and the last nodes as nulls.
     *
     * Call the standard inorder recursion helper(root) :
     *
     * If node is not null :
     *
     * Call the recursion for the left subtree helper(node.left).
     *
     * If the last node is not null, link the last and the current node nodes.
     *
     * Else initiate the first node.
     *
     * Mark the current node as the last one : last = node.
     *
     * Call the recursion for the right subtree helper(node.right).
     *
     * Link the first and the last nodes to close DLL ring and then return the first node.
     */
    // the smallest (first) and the largest (last) nodes
    Node first = null;
    Node last = null;

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public void helper(Node node) {
        if (node != null) {
            // left
            helper(node.left);
            // node
            if (last != null) {
                // link the previous node (last)
                // with the current one (node)
                last.right = node;
                node.left = last;
            } else {
                // keep the smallest node
                // to close DLL later on
                first = node;
            }
            last = node;
            // right
            helper(node.right);
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        helper(root);
        // close DLL
        last.right = first;
        first.left = last;
        return first;
    }

    /**
     * Step 1: Divide:
     * We divide tree into three parts: left subtree, root node, right subtree.
     * Convert left subtree into a circular doubly linked list as well as the right subtree.
     * Be careful. You have to make the root node become a circular doubly linked list.
     *
     * Step 2: Conquer:
     * Firstly, connect left circular doubly linked list with the root circular doubly linked list.
     * Secondly, connect them with the right circular doubly linked list. Here we go!
     */
    class Solution {
        public Node treeToDoublyList(Node root) {
            if (root == null) {
                return null;
            }

            Node leftHead = treeToDoublyList(root.left);
            Node rightHead = treeToDoublyList(root.right);
            root.left = root;
            root.right = root;
            return connect(connect(leftHead, root), rightHead);
        }

        // Used to connect two circular doubly linked lists. n1 is the head of circular DLL as well as n2.
        private Node connect(Node n1, Node n2) {
            if (n1 == null) {
                return n2;
            }
            if (n2 == null) {
                return n1;
            }

            Node tail1 = n1.left;
            Node tail2 = n2.left;

            tail1.right = n2;
            n2.left = tail1;
            tail2.right = n1;
            n1.left = tail2;

            return n1;
        }
    }
}
