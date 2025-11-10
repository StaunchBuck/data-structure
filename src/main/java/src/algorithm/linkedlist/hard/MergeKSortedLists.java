package src.algorithm.linkedlist.hard;

import src.leetcode.home.tech.problem.ListNode;

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0)
            return null;
        if(lists.length == 1)
            return lists[0];
        ListNode node = lists[0];
        for(int i=1;i<lists.length;i++){
            node = merge(node,lists[i]);
        }
        return node;
    }

    public ListNode merge(ListNode node1,ListNode node2){
        ListNode head = new ListNode(0);
        ListNode head1 = head;
        while(node1 != null && node2 != null){
            if(node1.val<node2.val){
                head1.next = new ListNode(node1.val);
                node1 = node1.next;
                head1 = head1.next;
            }else{
                head1.next = new ListNode(node2.val);
                node2 = node2.next;
                head1 = head1.next;
            }
        }
        if(node1 == null){
            head1.next = node2;
        }
        if(node2 == null){
            head1.next = node1;
        }
        return head.next;
    }
}
