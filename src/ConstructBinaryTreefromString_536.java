public class ConstructBinaryTreefromString_536 {
    /*
       ust scan the string from left to right, every time we meet ‘(’ , create left child node, then if we meet ‘(’ again from the same parent node, we create right child node.

    If every time we meet ‘)’, just return to parent node.
     */
    int i = 0;

    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        return helper(s.toCharArray());
    }

    private TreeNode helper(char[] s) {
        // done
        if (i == s.length) {
            return null;
        }

        // extract number
        StringBuilder num = new StringBuilder();
        while (i < s.length && s[i] != '(' && s[i] != ')') {
            num.append(s[i]);
            i++;
        }

        // create new node
        TreeNode n = null;
        if (num.length() != 0) {
            n = new TreeNode(Integer.parseInt(num.toString()));
        }
        // check parenthesis type
        if (i < s.length && s[i] == '(') {
            // create left child node
            i++;
            n.left = helper(s);
            i++;

            if (i < s.length && s[i] == '(') {
                // create right child node
                i++;
                n.right = helper(s);
                i++;
            }
        }
        // if meets ')', return to parent node
        return n;
    }
}
