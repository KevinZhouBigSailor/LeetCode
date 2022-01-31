package Facebook;

public class CopyListwithRandomPointer_138 {
    // https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43491/A-solution-with-constant-space-complexity-O(1)-and-linear-time-complexity-O(N)/42652
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            RandomListNode copy = new RandomListNode(iter.label);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        RandomListNode pseudoHead = new RandomListNode(0);
        RandomListNode copy, copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return pseudoHead.next;
    }

    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    // O(1) space. Also check O(n) space which match old node to new node
    public class Solution {
        public RandomListNode copyRandomList(RandomListNode head) {

            if (head == null) {
                return null;
            }

            // Creating a new weaved list of original and copied nodes.
            RandomListNode ptr = head;
            while (ptr != null) {

                // Cloned node
                RandomListNode newNode = new RandomListNode(ptr.label);

                // Inserting the cloned node just next to the original node.
                // If A->B->C is the original linked list,
                // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
                newNode.next = ptr.next;
                ptr.next = newNode;
                ptr = newNode.next;
            }

            ptr = head;

            // Now link the random pointers of the new nodes created.
            // Iterate the newly created list and use the original nodes' random pointers,
            // to assign references to random pointers for cloned nodes.
            while (ptr != null) {
                ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
                ptr = ptr.next.next;
            }

            // Unweave the linked list to get back the original linked list and the cloned list.
            // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
            RandomListNode ptr_old_list = head; // A->B->C
            RandomListNode ptr_new_list = head.next; // A'->B'->C'
            RandomListNode head_old = head.next;
            while (ptr_old_list != null) {
                ptr_old_list.next = ptr_old_list.next.next;
                ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
                ptr_old_list = ptr_old_list.next;
                ptr_new_list = ptr_new_list.next;
            }
            return head_old;
        }
    }
}

