package com.onevgo.leetcode;

public class Ex24SwapPairs {

    public ListNode swapPairs(ListNode head) {
        // 1 -> 2 -> 3 -> 4
        // head sec
        // 2 -> 1 -> 3 -> 4
        //     prev head  sec
        // 2 -> 1 -> 4-> 3
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevNode = dummy;

        while (head != null && head.next != null) {
            // 交换两个节点
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // prevNode -> secondNode -> firstNode -> ...
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            prevNode = firstNode;
            head = head.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        System.out.println(new Ex24SwapPairs().swapPairs(listNode));
    }
}
