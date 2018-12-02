/**
 * Created by zzhou on 2/22/2018.
 */
public class PopulatingNextRightPointersinEachNode_116 {
    //based on level order traversal
    public void connect(TreeLinkNode root) {
        TreeLinkNode head = root;//The left most node in the lower level
        TreeLinkNode prev = null;//The previous node in the lower level
        TreeLinkNode curr = null;//The current node in the upper level
        while (head != null) {
            curr = head;
            prev = null;
            head = null;
            while (curr != null) {
                if (curr.left != null) {
                    if (prev != null)
                        prev.next = curr.left;
                    else
                        head = curr.left;
                    prev = curr.left;
                }
                if (curr.right != null) {
                    if (prev != null)
                        prev.next = curr.right;
                    else
                        head = curr.right;
                    prev = curr.right;
                }
                curr = curr.next;
            }
        }
    }
}
