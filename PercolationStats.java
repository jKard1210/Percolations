import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class PercolationStats {
    private int[] stats;
    private int t;
    private int number;
    public PercolationStats(int n, int trials) {
        number = n;
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException("Out of Bounds");
        }
        t = trials;
        stats = new int[trials];
        for (int i = 0; i < trials; i++) {
            int j = 0;
            Percolation perc = new Percolation(n);
            for (int y = 0; y < n*n*n*n; y++) {
                    int r = StdRandom.uniform(1, n+1);
                    int c = StdRandom.uniform(1, n+1);
                    if (!perc.isOpen(r, c)) {
                     perc.open(r, c);
                     j++;
                    }
                if (perc.percolates()) {
                    break;
                }
            }
            stats[i] = j;
        }
    }
    
    public double mean() {
        return StdStats.mean(stats)/(number*number);
    }
    
    public double stddev() {
        return StdStats.stddev(stats)/(number*number);
    }
    
    public double confidenceLo() {
        return mean() - (1.96*stddev()/Math.sqrt(t));
    }
    
    public double confidenceHi() {
        return mean() + (1.96*stddev()/Math.sqrt(t));
    }
    
    public static void main(String [] args) {
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats pa = new PercolationStats(n, trials);
        StdOut.println(pa.mean());
        StdOut.println(pa.stddev());
        StdOut.println("[" + pa.confidenceLo() + ", " + pa.confidenceHi() + "]");
    }
}