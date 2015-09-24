import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedQueueOfString {
    private Node first, last;

    private class Node {
        String item;
        Node next;
    }

    public void enqueue(String item) {
        Node oldest = last;
        last = new Node();
        last.item = item;
        last.next = null;
        
        if (isEmpty())
            first = last;
        else
            oldest.next = last;
    }

    public String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LinkedQueueOfString queue = new LinkedQueueOfString();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                queue.enqueue(item);
            } else
                StdOut.print(queue.dequeue());
        }
    }

}
