/*
 * 
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 */

import java.util.Scanner;

public class Add_Two_Numbers_2 {
	public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode(0);
        ListNode cur = ret;

        int sum = 0;
        while (true) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            cur.val = sum % 10;
            sum /= 10;
            if (l1 != null || l2 != null || sum != 0) {
                cur.next = new ListNode(0);
                cur = cur.next;
            } else {
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        Add_Two_Numbers_2 s = new Add_Two_Numbers_2();
        System.out.println("Not test case");
    }
}
