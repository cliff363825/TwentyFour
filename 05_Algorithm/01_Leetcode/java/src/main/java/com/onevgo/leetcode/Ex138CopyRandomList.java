package com.onevgo.leetcode;

import com.onevgo.leetcode.ex138.Node;

import java.util.HashMap;

public class Ex138CopyRandomList {
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> nodeMap = new HashMap<>();

        Node curOld = head;
        Node dummy = new Node(-1);
        Node curNew = dummy;
        while (curOld != null) {
            if (!nodeMap.containsKey(curOld)) {
                nodeMap.put(curOld, new Node(curOld.val));
            }
            curNew.next = nodeMap.get(curOld);

            if (curOld.random != null) {
                if (!nodeMap.containsKey(curOld.random)) {
                    nodeMap.put(curOld.random, new Node(curOld.random.val));
                }

                curNew.next.random = nodeMap.get(curOld.random);
            }

            curOld = curOld.next;
            curNew = curNew.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {

    }
}
