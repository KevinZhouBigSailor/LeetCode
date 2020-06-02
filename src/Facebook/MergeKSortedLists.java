package Facebook;

import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> heap = new PriorityQueue<ListNode>((l1, l2) -> (l1.val - l2.val));

        ListNode fakehead = new ListNode(0), cur = fakehead, tmp;
        for (ListNode list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }
        while (!heap.isEmpty()) {
            tmp = heap.poll();
            cur.next = tmp;
            cur = cur.next;
            if (tmp.next != null) {
                heap.offer(tmp.next);
            }
        }

        return fakehead.next;
    }
}
