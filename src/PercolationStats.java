import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private double[] threshold;
    private int T;
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.T = T;
        threshold = new double[T];
        for (int i = 0; i < T; i++) {
            int count = 0;
            Percolation per = new Percolation(N);
            while (!per.percolates()) {
                int x = StdRandom.uniform(1, N + 1);
                int y = StdRandom.uniform(1, N + 1);
                if (!per.isOpen(x, y)) {
                    per.open(x, y);
                    count++;
                }
            }
            threshold[i] = count * 1.0 / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(threshold);
    }

    public double stddev() {
        return StdStats.stddev(threshold);
    }

    public double confidenceLo() {
        return StdStats.mean(threshold) - (1.96 * StdStats.stddev(threshold)) / Math.sqrt(T);
    }

    public double confidenceHi() {
        return StdStats.mean(threshold) + (1.96 * StdStats.stddev(threshold)) / Math.sqrt(T);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats perc = new PercolationStats(N, T);
        StdOut.println("mean                    = " + perc.mean());
        StdOut.println("stddev                  = " + perc.stddev());
        StdOut.println("95 % confidence interval= " + perc.confidenceLo() + ", " + perc.confidenceHi());
    }

}
