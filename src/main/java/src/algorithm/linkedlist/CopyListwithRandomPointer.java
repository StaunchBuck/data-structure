package src.algorithm.linkedlist;

import java.util.HashMap;
import java.util.Map;
// https://leetcode.com/problems/copy-list-with-random-pointer/description/
public class CopyListwithRandomPointer {

    public Node copyRandomList(Node head) {
        Map<Node, Node> hashMap = new HashMap<>();
        Node cur = head;

        while (cur != null) {
            hashMap.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;

        while (cur != null) {
            Node copy = hashMap.get(cur);
            copy.next = hashMap.get(cur.next);
            copy.random = hashMap.get(cur.random);
            cur = cur.next;
        }

        return hashMap.get(head);
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}