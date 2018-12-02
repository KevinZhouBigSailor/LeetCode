/*
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 */

public class MergeTwoSortedList_21 {
	public class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode fakeHead = new ListNode(0);
	    ListNode ret = fakeHead;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				ret.next = l1;
				l1 = l1.next;
				
			} else {
				ret.next = l2;
				l2 = l2.next;
			}
			
			ret = ret.next;

		}

		if (l1 == null) ret.next = l2;
	    if (l2 == null) ret.next = l1;

		return fakeHead.next;
    }
}
