package com.onevgo.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Ex141HasCycle {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> listNodes = new HashSet<>();

        ListNode current = head;
        while (current != null) {
            if (listNodes.contains(current)) {
                return true;
            }
            listNodes.add(current);
            current = current.next;
        }
        return false;
    }

    public boolean hasCycle1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
