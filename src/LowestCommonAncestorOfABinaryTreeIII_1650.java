public class LowestCommonAncestorOfABinaryTreeIII_1650 {
    public ParentNode lowestCommonAncestor(ParentNode p, ParentNode q) {
        ParentNode a = p, b = q;
        while (a != b) {
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }
        return a;
    }
}

class ParentNode {
    public int val;
    public ParentNode left;
    public ParentNode right;
    public ParentNode parent;
}

