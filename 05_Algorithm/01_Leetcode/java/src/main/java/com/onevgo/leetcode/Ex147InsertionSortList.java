package com.onevgo.leetcode;

public class Ex147InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        // [4,2,1,3]
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        ListNode node = head;

        while (node != null) {
            ListNode current = node;
            node = node.next;

            if (current.val < prev.val) prev = dummy;

            while (prev.next != null && prev.next.val < current.val) prev = prev.next;

            current.next = prev.next;
            prev.next = current;
        }

        return dummy.next;
    }

    public static void main(String[] args) {

    }
}
