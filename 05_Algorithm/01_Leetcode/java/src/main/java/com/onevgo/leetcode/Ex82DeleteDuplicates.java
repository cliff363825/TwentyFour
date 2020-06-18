package com.onevgo.leetcode;

public class Ex82DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        // init
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = dummy;
        while (current.next != null) {
            current = current.next;
            if (current.next == null || current.next.val != current.val) {
                prev.next = current;
                prev = current;
            } else {
                while (current.next != null && current.next.val == current.val) {
                    current = current.next;
                }
                prev.next = null;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(3);
        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        System.out.println(new Ex82DeleteDuplicates().deleteDuplicates(head));
    }
}
