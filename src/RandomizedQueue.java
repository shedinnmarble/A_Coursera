import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node first;
    private int count;

    public RandomizedQueue() {
        first = null;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            first.next = null;
        } else {
            first.next = oldFirst;
        }
        count++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        // get random item number
        int randomItem = StdRandom.uniform(1, count + 1);
        Node deletedItem = first;
        Node prevDeletedItem = first;
        for (int i = 1; i < randomItem; i++) {
            prevDeletedItem = deletedItem;
            deletedItem = deletedItem.next;
        }
        if (deletedItem == first) {
            first = first.next;
        } else {
            prevDeletedItem.next = deletedItem.next;
        }
        Item item = deletedItem.item;
        count--;
        return item;
    }

    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        // get random item number
        int randomItem = StdRandom.uniform(1, count + 1);
        Node deletedItem = first;
        for (int i = 1; i < randomItem; i++) {
            deletedItem = deletedItem.next;
        }
        Item item = deletedItem.item;
        return item;
    }

    private class RqIterator implements Iterator<Item> {
        private Node current = null;

        public RqIterator() {
            if (first != null) {
                current = new Node();
                current.item = first.item;
                current.next = first.next;
            }
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null)
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }
    }

    @Override
    public Iterator<Item> iterator() {
        // TODO Auto-generated method stub
        return new RqIterator();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else
                StdOut.println(queue.dequeue());
        }
    }

}
