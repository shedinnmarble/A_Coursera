import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] openedSites;
    private WeightedQuickUnionUF fullLess, grid;
    private int N;
    private int virtualTop, virtualBottom;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        openedSites = new boolean[N * N];
        this.N = N;
        // only connected to virtual top
        fullLess = new WeightedQuickUnionUF(N * N + 1);
        grid = new WeightedQuickUnionUF(N * N + 2);
        this.virtualTop = xyTo1D(N, N) + 1;
        this.virtualBottom = xyTo1D(N, N) + 2;
    }

    private int xyTo1D(int x, int y) {
        return (x - 1) * N + (y - 1);
    }

    private void valid(int x, int y) {
        if (x < 1 || y < 1 || x > N || y > N) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public void open(int i, int j) {
        valid(i, j);
        int index = xyTo1D(i, j);
        // virtual top
        if (i == 1) {
            grid.union(virtualTop, index);
            fullLess.union(virtualTop, index);
        }
        // virtual bottom
        if (i == N) {
            grid.union(virtualBottom, index);
        }
        // if(id[i-1][j-1]==1) return;
        openedSites[xyTo1D(i, j)] = true; // mark open
        // judge index >=0
        // left ,col-1
        if (j - 1 >= 1 && isOpen(i, j - 1)) {
            fullLess.union(xyTo1D(i, j - 1), index);
            grid.union(xyTo1D(i, j - 1), index);
        }

        // top,row-1
        if (i - 1 >= 1 && isOpen(i - 1, j)) {

            grid.union(xyTo1D(i - 1, j), index);
            fullLess.union(xyTo1D(i - 1, j), index);
        }
        // right, col+1
        if (j + 1 <= N && isOpen(i, j + 1)) {
            grid.union(xyTo1D(i, j + 1), index);
            fullLess.union(xyTo1D(i, j + 1), index);
        }

        // bottom, row+1
        if (i + 1 <= N && isOpen(i + 1, j)) {
            fullLess.union(xyTo1D(i + 1, j), index);
            grid.union(xyTo1D(i + 1, j), index);
        }

    }

    public boolean isOpen(int i, int j) {
        valid(i, j);
        return openedSites[xyTo1D(i, j)];
    }

    public boolean isFull(int i, int j) {
        valid(i, j);
        if (!isOpen(i, j))
            return false;
        return fullLess.connected(virtualTop, xyTo1D(i, j));
    }

    public boolean percolates() {
        return grid.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int N = StdIn.readInt();
        Percolation p = new Percolation(N);
        while (!StdIn.isEmpty()) {
            int i = StdIn.readInt();
            int j = StdIn.readInt();
            p.open(i, j);
            StdOut.println("isopen:" + p.isOpen(i, j) + " isFull:" + p.isFull(i, j) + " percolates:" + p.percolates());

        }
    }

}
