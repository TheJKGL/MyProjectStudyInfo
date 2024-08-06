package malakhov.study.tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Tree {
    public static void main(String[] args) {
        Node root =
                new Node(20,
                        new Node(7,
                                new Node(4, null, new Node(6)), new Node(9)),
                        new Node(35,
                                new Node(31, new Node(28), null),
                                new Node(40, new Node(38), new Node(52))));

        //System.out.println("Sum of tree:" + root.sumWide(root));
        System.out.println(root.sumRecursive());
    }

    public static class Node {
        int value;
        public Node left;
        public Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(int value) {
            this.value = value;
        }

        public int sumRecursive() {
            int sum = value;
            if (left != null) {
                sum += left.sumRecursive();
            }

            if (right != null) {
                sum += right.sumRecursive();
            }
            return sum;
        }

        public int sumDeep(Node root) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);

            int summ = 0;

            while (!stack.isEmpty()) {
                Node node = stack.pop();

                System.out.println(node.value);
                summ += node.value;

                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            return summ;
        }

        public int sumWide(Node root) {
            Queue<Node> stack = new ArrayDeque<>();
            stack.add(root);

            int summ = 0;

            while (!stack.isEmpty()) {
                Node node = stack.remove();

                System.out.println(node.value);
                summ += node.value;

                if (node.right != null) {
                    stack.add(node.right);
                }
                if (node.left != null) {
                    stack.add(node.left);
                }
            }
            return summ;
        }
    }
}










