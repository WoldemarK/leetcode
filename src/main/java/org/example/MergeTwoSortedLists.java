package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2,  new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(mergeTwoLists(l1, l2));
    }
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        Stream<ListNode> stream1 = toStream(list1);
        Stream<ListNode> stream2 = toStream(list2);

        ListNode result = new ListNode();

        final ListNode[] temp = {result};

        Stream.concat(stream1, stream2)
                .sorted(Comparator.comparingInt(a -> a.val))
                .forEach(node -> {
                    temp[0].next = node;
                    temp[0] = temp[0].next;
                });

        return result.next;
    }
    private static Stream<ListNode> toStream(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        return list.stream();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}