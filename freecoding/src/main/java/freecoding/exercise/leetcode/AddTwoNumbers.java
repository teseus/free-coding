package freecoding.exercise.leetcode;

import lombok.EqualsAndHashCode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddTwoNumbers {
    public static ListNode addTwoNumbersMe(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        ListNode l = l3;
        int val;

        while (l1 != null || l2 != null){
            val = (l1!= null?l1.val:0) + (l2!= null?l2.val:0) + l.val;
            l.val = val % 10;

            if((l1!=null&&l1.next!=null) ||
                    (l2!=null&&l2.next != null) ||
                        val>=10){
                l.next = new ListNode(val>=10?1:0);
            }

            l = l.next;
            if(l1!=null)l1 = l1.next;
            if(l2!=null)l2 = l2.next;
        }

        return l3;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    @Test
    public void testAddTwoNumbers1(){
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(0);
        l2.next.next = new ListNode(0);

        ListNode l3 = new ListNode(0);
        l3.next = new ListNode(0);
        l3.next.next = new ListNode(0);
        l3.next.next.next = new ListNode(1);


        ListNode expected = AddTwoNumbers.addTwoNumbers(l1, l2);
        assertEquals(expected, l3);
    }

    @EqualsAndHashCode
    public static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int x) { val = x; }

    }

    @Test
    public void testAddTwoNumbers(){
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(1);
        l1.next.next.next = new ListNode(1);
        l1.next.next.next.next = new ListNode(1);
        l1.next.next.next.next.next = new ListNode(1);
        l1.next.next.next.next.next.next = new ListNode(1);
        l1.next.next.next.next.next.next.next = new ListNode(1);
        l1.next.next.next.next.next.next.next.next = new ListNode(1);
        l1.next.next.next.next.next.next.next.next.next = new ListNode(1);
        l1.next.next.next.next.next.next.next.next.next.next = new ListNode(1);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(1);
        l2.next.next = new ListNode(1);
        l2.next.next.next = new ListNode(1);
        l2.next.next.next.next = new ListNode(1);
        l2.next.next.next.next.next = new ListNode(1);
        l2.next.next.next.next.next.next = new ListNode(1);
        l2.next.next.next.next.next.next.next = new ListNode(1);
        l2.next.next.next.next.next.next.next.next = new ListNode(1);
        l2.next.next.next.next.next.next.next.next.next = new ListNode(1);
        l2.next.next.next.next.next.next.next.next.next.next = new ListNode(1);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(2);
        l3.next.next = new ListNode(2);
        l3.next.next.next = new ListNode(2);
        l3.next.next.next.next = new ListNode(2);
        l3.next.next.next.next.next = new ListNode(2);
        l3.next.next.next.next.next.next = new ListNode(2);
        l3.next.next.next.next.next.next.next = new ListNode(2);
        l3.next.next.next.next.next.next.next.next = new ListNode(2);
        l3.next.next.next.next.next.next.next.next.next = new ListNode(2);
        l3.next.next.next.next.next.next.next.next.next.next = new ListNode(2);


        ListNode expected = AddTwoNumbers.addTwoNumbers(l1, l2);
        assertEquals(expected, l3);
    }

    @Test
    public void testAddTwoNumbers2(){
        ListNode l1 = new ListNode(0);

        ListNode l2 = new ListNode(0);

        ListNode l3 = new ListNode(0);


        assertEquals(AddTwoNumbers.addTwoNumbers(l1, l2), l3);
    }

    @Test
    public void testAddTwoNumbers3(){
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(8);

        ListNode l2 = new ListNode(0);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(8);


        ListNode expected = AddTwoNumbers.addTwoNumbers(l1, l2);
        assertEquals(expected, l3);
    }

}
