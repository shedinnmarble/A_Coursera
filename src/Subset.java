import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int N = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            queue.enqueue(item);
        }
        for (int i = 0; i < N; i++) {
            StdOut.println(queue.dequeue());
        }
    }

}
