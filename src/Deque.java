import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    private Node first, last;
    private int count;

    public Deque() {
        first = null;
        last = null;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node firster = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            last = first;
            first.next = null;
            first.previous = null;
        } else {
            first.next = firster;
            first.previous = null;
            firster.previous = first;
        }
        count++;

    }

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node lastest = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
            last.next = null;
            last.previous = null;
        } else {
            lastest.next = last;
            last.next = null;
            last.previous = lastest;
        }
        count++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first != null)
            first.previous = null;
        count--;
        if (isEmpty()) {
            last = null;
        }

        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        if (last != null)
            last.next = null;
        count--;
        if (isEmpty()) {
            first = null;

        }
        return item;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null)
                throw new NoSuchElementException();
            
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
        return new ListIterator();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Deque<String> deque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            deque.addLast(item);
            for (String s : deque) {
                StdOut.print(s + ",");
            }
            StdOut.println();
        }

    }

}
