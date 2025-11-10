package src.algorithm.linkedlist;

import src.leetcode.home.tech.problem.ListNode;
import src.leetcode.home.tech.problem.MyLinkedNode;

public class SortList {

    public static void main(String[] args) {
        MyLinkedNode obj = new MyLinkedNode(4,2,1,3);
//        obj.printListNode();
        SortList o = new SortList();
        ListNode node = o.sortList(obj.get());
        obj.printListNodeExternal(node);
    }
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode l1 = head;
        ListNode l2 = slow.next;
        slow.next = null;
        ListNode left = sortList(l1);
        ListNode right = sortList(l2);

        return merge(left,right);
    }

    public ListNode merge(ListNode l1,ListNode l2){
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        ListNode head = new ListNode(0);
        ListNode head1 = head;
        while(l1 != null && l2 != null){
            if(l1.val<l2.val){
                head1.next = l1;
                l1 = l1.next;
                head1 = head1.next;
                head1.next = null;
            }else{
                head1.next = l2;
                l2 = l2.next;
                head1 = head1.next;
                head1.next = null;
            }
        }
        if(l1 == null){
            head1.next = l2;
        }else if(l2 == null){
            head1.next = l1;
        }
        return head.next;
    }
}
