package WeWork;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EqualStack {
    private class StackPair {
        int sum;
        Stack<Integer> stack;

        public StackPair(Stack<Integer> s) {
            sum = 0;
            Stack<Integer> temp = new Stack<>();
            while (!s.isEmpty()) temp.push(s.pop());
            while (!temp.isEmpty()) {
                sum += temp.peek();
                s.push(temp.pop());
            }
            stack = s;
        }

        public int pop() {
            sum -= stack.peek();
            return stack.pop();
        }
    }

    private boolean isEqual(Queue<StackPair> queue) {
        Queue<StackPair> temp = new PriorityQueue<>((o1, o2) -> o2.sum - o1.sum);
        boolean isEqual = true;
        int sum = queue.peek().sum;
        while (!queue.isEmpty()) {
            if (sum != queue.peek().sum) {
                isEqual = false;
            }
            temp.offer(queue.poll());
        }
        while (!temp.isEmpty()) queue.offer(temp.poll());
        return isEqual;
    }

    private int maxStackEqualSum(List<Stack<Integer>> stacks) {

        // Max Heap
        Queue<StackPair> queue = new PriorityQueue<>((o1, o2) -> o2.sum - o1.sum);

        for (Stack<Integer> s : stacks) queue.add(new StackPair(s));
        printQueue(queue);


        while (!isEqual(queue)) {

            StackPair curr = queue.poll();
            curr.pop();
            queue.offer(curr);
        }

        if (queue.isEmpty()) {
            System.out.println("Empty Queue!");
            return -1;
        }

        printQueue(queue);
        return queue.peek().sum;
    }

    @Test
    public void testIsEqualStack() {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        s1.push(2);
        s1.push(3);
        s1.push(1);
        s1.push(2);

        s2.push(4);
        s2.push(1);
        s2.push(2);
        s2.push(2);

        List<Stack<Integer>> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);

        assertEquals(5, maxStackEqualSum(list));
    }

    private void printQueue(Queue<StackPair> queue) {
        Queue<StackPair> temp = new PriorityQueue<>((o1, o2) -> o2.sum - o1.sum);
        while (!queue.isEmpty()) {
            System.out.println("Queue is not empty");
            printStack(queue.peek().stack);
            temp.add(queue.poll());
        }
        while (!temp.isEmpty()) queue.offer(temp.poll());
    }

    private void printStack(Stack<Integer> s) {
        Stack<Integer> temp = new Stack<>();
        while (!s.isEmpty()) temp.push(s.pop());

        String str = temp.peek() + "";
        s.push(temp.pop());
        while (!temp.isEmpty()) {
            str += " -> " + temp.peek();
            s.push(temp.pop());
        }

        System.out.println(str);
    }
}
