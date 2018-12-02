/**
 * Created by zzhou on 1/30/2018.
 */
public class DeleteNodeinaLinkedList_237 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
