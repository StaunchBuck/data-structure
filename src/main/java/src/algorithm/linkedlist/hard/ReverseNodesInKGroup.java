package src.algorithm.linkedlist.hard;

import src.leetcode.home.tech.problem.ListNode;

public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tempHead = new ListNode(0);
        tempHead.next=head;
        ListNode pointer1 = tempHead;
        ListNode pointer2 = tempHead;
        int count=0;
        //we move upto k items to check if there are k items in the list to reverse
        while(count<=k && pointer2 != null){
            //when we reach kth item, we start reversing the list from ith to ith + k items
            if(count == k){
                count = 1;
                ListNode prev = pointer1.next;
                ListNode curr = prev.next;
                ListNode first = prev;
                //here we keep on reversing upto k items
                while(count<k){
                    ListNode temp = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = temp;
                    count++;
                }
                //here point the reverse the next for the first and last element in the list.
                pointer1.next.next = curr;
                pointer1.next = prev;
                //here we update the pointer1 and pointer2 to the last value of the reversed linked list
                pointer1 = first;
                pointer2 = first;
                //reset the count to 0
                count = 0;
            }
            //keep on discovering upto k items
            pointer2 = pointer2.next;
            count++;
        }
        return tempHead.next;
    }
}
