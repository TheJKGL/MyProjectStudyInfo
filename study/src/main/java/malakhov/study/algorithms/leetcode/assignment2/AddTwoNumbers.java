package malakhov.study.algorithms.leetcode.assignment2;


//You are given two non-empty linked lists representing two non-negative integers.
// The digits are stored in reverse order, and each of their nodes contains a single digit.
// Add the two numbers and return the sum as a linked list.
//
//You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//Example 1:
//
//Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [7,0,8]
//Explanation: 342 + 465 = 807.

//Example 2:
//
//Input: l1 = [0], l2 = [0]
//Output: [0]

//Example 3:
//
//Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//Output: [8,9,9,9,0,0,0,1]

//Example 4:
//Input: l1 = [9], l2 = [1,9,9,9,9,9,9,9,9,9]
//Output:
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode f3 = new ListNode(3);
        ListNode f2 = new ListNode(4, f3);
        ListNode f1 = new ListNode(2, f2);

        ListNode s3 = new ListNode(4);
        ListNode s2 = new ListNode(6, s3);
        ListNode s1 = new ListNode(5, s2);


        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode result = addTwoNumbers.addTwoNumbers(f1, s1);
        System.out.println(result);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder firstStrNumber = new StringBuilder();
        StringBuilder secondStrNumber = new StringBuilder();

        constructNumber(l1, firstStrNumber);
        constructNumber(l2, secondStrNumber);

        int firstNumber = convertToInt(firstStrNumber);
        int secondNumber = convertToInt(secondStrNumber);
        int sum = firstNumber + secondNumber;

        return constructResultNode(sum);
    }

    private ListNode constructResultNode(int sum) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(sum));
        stringBuilder.reverse();

        LinkedList linkedList = new LinkedList();

        for (int i = 0; i < stringBuilder.length(); i++) {
            int val = Integer.parseInt(String.valueOf(stringBuilder.charAt(i)));
            linkedList.add(val);
        }

        return linkedList.getHead();
    }

    class LinkedList {
        private ListNode head;

        // Method to add a node at the end of the list
        public void add(int val) {
            ListNode newNode = new ListNode(val);

            if (head == null) {
                head = newNode;
            } else {
                ListNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        public ListNode getHead() {
            return head;
        }
    }

    private int convertToInt(StringBuilder input) {
        return Integer.parseInt(input.reverse().toString());
    }

    private void constructNumber(ListNode l1, StringBuilder strNumber) {
        strNumber.append(l1.val);
        if (l1.next != null) {
            constructNumber(l1.next, strNumber);
        }
    }
}
