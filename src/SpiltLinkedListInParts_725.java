/**
 * Created by zzhou on 2/9/2018.
 */
public class SpiltLinkedListInParts_725 {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            cur = cur.next;
            N++;
        }

        int width = N / k, remain = N % k;

        ListNode[] ans = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; i++) {
            ListNode head = new ListNode(0), write = head;
            for (int j = 0; j < width + (i < remain ? 1 : 0); j++) {
                write = write.next = new ListNode(cur.val);
                if (cur != null) cur = cur.next;
            }
            ans[i] = head.next;
        }
        return ans;
    }
}
