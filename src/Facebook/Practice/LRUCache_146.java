package Facebook.Practice;

import java.util.HashMap;

public class LRUCache_146 {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;
    }

    private HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache_146(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.next = null;

        head.next = tail;
        tail.pre = head;
    }

    private void addNode(DLinkedNode node) {
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode next = node.next;

        pre.next = next;
        next.pre = pre;
    }

    private void moveToHead(DLinkedNode node) {
        this.removeNode(node);
        this.addNode(node);
    }

    private DLinkedNode popTail() {
        DLinkedNode res = tail.pre;
        this.removeNode(tail);
        return res;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        this.moveToHead(node);

        return node.value;
    }

    public void set(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null) {
            count++;
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            if (count > capacity) {
                count--;
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
            }
        } else {
            node.value = value;
            this.moveToHead(node);
        }
    }
}
