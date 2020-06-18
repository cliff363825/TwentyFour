package com.onevgo.leetcode;

import java.util.HashSet;

public class Ex142DetectCycle {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> listNodes = new HashSet<>();

        ListNode current = head;
        while (current != null) {
            if (listNodes.contains(current)) {
                return current;
            }
            listNodes.add(current);
            current = current.next;
        }

        return null;
    }

    public ListNode detectCycle1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) return null;

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {

    }
}
