import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int N = 0;

    public RandomizedQueue() {
        q = (Item[]) new Object[1];
    }
    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];

        for (int i = 0; i < N; i++) {

            temp[i] = q[i];
        }
        q = temp;

    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (N == q.length)
            resize(2 * N); // double size of array if necessary
        q[N++] = item; // add item

    }

    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int randomId = StdRandom.uniform(0, N);
        Item item = q[randomId];
        q[randomId] = q[--N];
        q[N] = null;
        if (N > 0 && N == q.length / 4)
            resize(q.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        // get random item number
        int randomId = StdRandom.uniform(0, N);
        Item item = q[randomId];
        return item;
    }

    private class RqIterator implements Iterator<Item> {
        private Item[] arr;
        private int count = 0;

        public RqIterator() {
            count = N;

            arr = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {

                arr[i] = q[i];
            }
            StdRandom.shuffle(arr);
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return count > 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = arr[--count];
            arr[count] = null;
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
        RandomizedQueue<String> testQueue = new RandomizedQueue<String>();
        testQueue.enqueue("7");
        testQueue.enqueue("8");
        testQueue.enqueue("9");
        for (String s : testQueue) {
            StdOut.println(s);
        }
        StdOut.println(testQueue.sample());

    }

}

