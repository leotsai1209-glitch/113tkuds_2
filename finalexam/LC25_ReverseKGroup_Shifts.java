public ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode prev = dummy;

    while (true) {
        ListNode tail = prev;
        for (int i = 0; i < k && tail != null; i++) {
            tail = tail.next;
        }
        if (tail == null) break;

        ListNode next = tail.next;
        ListNode[] reversed = reverse(prev.next, tail);
        prev.next = reversed[0];
        reversed[1].next = next;
        prev = reversed[1];
    }

    return dummy.next;
}

private ListNode[] reverse(ListNode head, ListNode tail) {
    ListNode prev = tail.next;
    ListNode curr = head;

    while (prev != tail) {
        ListNode temp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = temp;
    }

    return new ListNode[]{tail, head};
}