package freecoding.exercise.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MergeTwoSortedLists {

    @Test
    public void test1(){
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        ListNode expected =
                new ListNode(1,
                        new ListNode(1,
                                new ListNode(2,
                                        new ListNode(3,
                                                new ListNode(4,
                                                        new ListNode(4))))));

        ListNode actual = new Solution().mergeTwoLists(list1, list2);
        assertEquals(expected, actual);

    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //leetCode
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            // maintain an unchanging reference to node ahead of the return node.
            ListNode prehead = new ListNode(-1);

            ListNode prev = prehead;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    prev.next = l1;
                    l1 = l1.next;
                } else {
                    prev.next = l2;
                    l2 = l2.next;
                }
                prev = prev.next;
            }

            // exactly one of l1 and l2 can be non-null at this point, so connect
            // the non-null list to the end of the merged list.
            prev.next = l1 == null ? l2 : l1;

            return prehead.next;
        }
    }

    //leetCode
    class Solution1 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            else if (l2 == null) {
                return l1;
            }
            else if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }
            else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }

        }
    }

    class SolutionMine {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

            ListNode entry = null, start = null;

            while(l1 != null || l2 != null){
                if((l1 != null && l2 != null && l1.val <= l2.val) || (l1 != null && l2 == null)){
                    if(entry == null)
                        start = entry = new ListNode(l1.val);
                    else {
                        entry.next = new ListNode(l1.val);
                        entry = entry.next;
                    }
                    l1 = l1.next;
                }
                if((l1 != null && l2 != null && l2.val < l1.val) || (l1 == null && l2 != null)){
                    if(entry == null)
                        start = entry = new ListNode(l2.val);
                    else {
                        entry.next = new ListNode(l2.val);
                        entry = entry.next;
                    }
                    l2 = l2.next;
                }
            }

            return start;
        }
    }
}
