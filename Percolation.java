
/**
 * Write a description of class Percolation here.
 * 
 * @author James Kardatzke 
 * @version 3/20/17
 **/
public class Percolation
{
    private int[] id;
    private int num;
    private int[] sz;
    private boolean[] open;


    /**
     * Constructor for objects of class Percolation
     */
    public Percolation(int n)
    {
        if (n <= 0) {
            throw new IllegalArgumentException("Bad");
        }
        num = n;
        id = new int[n*n+2];
        sz = new int[n*n+2];
        open = new boolean[n*n+2];
        for (int i = 0; i < n*n+2; i++) {
            id[i] = i;
            sz[i] = 1;
            open[i] = false;
        }
        
        open[n*n] = true;
        open[n*n+1] = true;
    }

    public void open(int row, int col)
    {
        if (row < 1 || col < 1 || row > num || col > num) {
            throw new IndexOutOfBoundsException("Bad");
        }
        int a = (row-1)*num + (col-1);
        open[a] = true;
        
        if (row == 1) {
            union(a, num*num);
        }
        
        else if (open[a-num]){
            union(a, a-num);
        }
    
        if (row == num) {
            union(a, num*num+1);
        }
        
        else if (open[a+num]) {
            union(a, a+num);
        }
        
       
        
        if (a % num != num-1 && open[a+1]) {
            union(a, a+1);
        }
        
        if (a % num != 0 && open[a-1]) {
            union(a, a-1);
        }
    }
    
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > num || col > num) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        int x = (row-1)*num+(col-1);
        return open[x];
    }
    
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > num || col > num) {
            throw new IndexOutOfBoundsException("Bad");
        }
        int a = (row-1)*num + (col-1);
        return connected(num*num, a);
    }
    
    public boolean percolates() {
        return connected(num*num, num*num+1);
    }
    
    public int numberOfOpenSites() {
        int j = 0;
        for (int i = 0; i < num *num; i++) {
            if (open[i]) {
                j++;
            }
        }
        return j;
    }
    
    private void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) {
            return;
        }
        
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }
        
        else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
    
    private boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    
    private int root(int p) {
        int root = id[p];
        while (id[root] != root) {
            root = id[root];
        }
        return root;
    }
    
    public static void main(String[] args) {
        Percolation perc = new Percolation(3);
         System.out.println(perc.isFull(1, 1)); 
    }
}
