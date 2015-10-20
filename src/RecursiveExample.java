import edu.princeton.cs.algs4.StdOut;

public class RecursiveExample {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // StdOut.println(closetFibonacci(33));
        int[] a = { -6, 12, 1, 24, 3, 5 };
        int[] b = { 3,2,1 };
        StdOut.println(isTwinPaired(b));
    }

    public static int fib(int n) {
        if (n < 1) {
            return -1;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static int closetFibonacci(int n) {
        if (n < 1)
            return 0;
        if (n == 1)
            return 1;
        int[] fibArr = new int[n];
        fibArr[0] = 1;
        fibArr[1] = 1;
        for (int i = 2; i < n; i++) {
            fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
        }
        int closetFib = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (fibArr[i] <= n) {
                closetFib = fibArr[i];
                break;
            }
        }
        return closetFib;
    }

    public static int isTwinPaired(int[] a) {
        int oddNumber1 = 1;
        int evenNumber1 = 0;
        boolean firstOdd = true;
        boolean firstEven = true;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                // Å¼Êý
                if (firstEven) {
                    evenNumber1 = a[i];
                    firstEven = false;
                } else {
                    if (a[i] >= evenNumber1) {
                        evenNumber1 = a[i];
                    } else
                        return 0;
                }

            } else {
                if (firstOdd) {
                    oddNumber1 = a[i];
                    firstOdd = false;
                } else {
                    if (a[i] >= oddNumber1)
                        oddNumber1 = a[i];
                    else
                        return 0;
                }

            }
        }
        return 1;
    }
}
